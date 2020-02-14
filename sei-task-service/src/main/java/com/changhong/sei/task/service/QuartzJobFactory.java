package com.changhong.sei.task.service;

import com.changhong.sei.apitemplate.ApiTemplate;
import com.changhong.sei.core.context.ContextUtil;
import com.changhong.sei.core.context.mock.MockUser;
import com.changhong.sei.core.dto.ResultData;
import com.changhong.sei.core.log.LogUtil;
import com.changhong.sei.core.util.JsonUtils;
import com.changhong.sei.task.dao.JobHistoryDao;
import com.changhong.sei.task.entity.JobHistory;
import com.chonghong.sei.util.DateUtils;
import com.chonghong.sei.util.thread.ThreadLocalHolder;
import com.chonghong.sei.util.thread.ThreadLocalUtil;
import io.swagger.annotations.Api;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.StopWatch;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.Map;
import java.util.Objects;

/**
 * *************************************************************************************************
 * <p/>
 * 实现功能：定时任务运行工厂
 * <p>
 * ------------------------------------------------------------------------------------------------
 * 版本          变更时间             变更人                     变更原因
 * ------------------------------------------------------------------------------------------------
 * 1.0.00      2017-08-03 10:12      王锦光(wangj)                新建
 * <p/>
 * *************************************************************************************************
 */
@DisallowConcurrentExecution
@PersistJobDataAfterExecution
public class QuartzJobFactory implements Job {
    /**
     * 调度任务的键值
     */
    public static final String SCHEDULER_KEY = "scheduleJob";
    /**
     * 获取默认租户代码
     *
     * @return 租户代码
     */
    public static String getTenantCode() {
        return ContextUtil.getProperty("sei.default-tenant.code");
    }

    /**
     * 获取默认租户管理员
     *
     * @return 租户管理员
     */
    public static String getTenantAdmin() {
        return ContextUtil.getProperty("sei.default-tenant.admin");
    }

    /**
     * 设置当前用户为默认租户
     */
    public static void setToTenantAdmin(){
        MockUser mockUser = ContextUtil.getBean(MockUser.class);
        mockUser.mockUser(getTenantCode(), getTenantAdmin());
    }

    /**
     * <p>
     * Called by the <code>{@link Scheduler}</code> when a <code>{@link Trigger}</code>
     * fires that is associated with the <code>Job</code>.
     * </p>
     *
     * <p>
     * The implementation may wish to set a
     * {@link JobExecutionContext#setResult(Object) result} object on the
     * {@link JobExecutionContext} before this method exits.  The result itself
     * is meaningless to Quartz, but may be informative to
     * <code>{@link JobListener}s</code> or
     * <code>{@link TriggerListener}s</code> that are watching the job's
     * execution.
     * </p>
     *
     * @param context
     * @throws JobExecutionException if there is an exception while executing the job.
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void execute(JobExecutionContext context) throws JobExecutionException {
        com.changhong.sei.task.entity.Job scheduleJob = (com.changhong.sei.task.entity.Job) context.getMergedJobDataMap().get(SCHEDULER_KEY);
        if (Objects.isNull(scheduleJob)) {
            return;
        }
        JobHistory history = new JobHistory();
        com.changhong.sei.task.entity.Job job = new com.changhong.sei.task.entity.Job();
        job.setId(scheduleJob.getId());
        history.setJob(job);
        Date date = DateUtils.getCurrentDateTime();
        history.setStartTime(date);
        //定义执行时间记录
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        LogUtil.bizLog("{} 任务开始执行 start", scheduleJob.getName());
        try {
            // 初始化线程变量
            ThreadLocalHolder.begin();
            String path = String.format("%s/%s", scheduleJob.getApiPath(), scheduleJob.getMethodName());
            // 反序列化得到输入参数
            Map<String, String> params = null;
            String inputParam = scheduleJob.getInputParam();
            if (!StringUtils.isBlank(inputParam)) {
                params = JsonUtils.fromJson(inputParam, Map.class);
            }
            // 设置当前执行任务的用户-全局管理员
            if (StringUtils.isNotBlank(getTenantCode()) && StringUtils.isNotBlank(getTenantAdmin())) {
                // 设置默认的执行用户
                setToTenantAdmin();
            }
            // 打印当前会话Token信息
            LogUtil.bizLog("Token信息:"+ ThreadLocalUtil.getTranVar(ContextUtil.HEADER_TOKEN_KEY));
            ApiTemplate apiTemplate = ContextUtil.getBean(ApiTemplate.class);
            ResultData result = apiTemplate.postByAppModuleCode(scheduleJob.getAppModuleCode(), path, ResultData.class, params);
            stopWatch.stop();

            LogUtil.bizLog("{} 任务执行完成 end", scheduleJob.getName());
            history.setSuccessful(result.successful());
            history.setMessage(result.getMessage());
        } catch (Exception e) {
            String msg = String.format("执行[%s]异常！jobId:%s", scheduleJob.getName(), scheduleJob.getId());
            LogUtil.error(msg+"；Token信息:"+ ThreadLocalUtil.getTranVar(ContextUtil.HEADER_TOKEN_KEY), e);
            stopWatch.stop();
            history.setSuccessful(false);
            history.setMessage("作业执行失败！");
            history.setExceptionMessage(msg);
        } finally {
            history.setElapsed(stopWatch.getTime());
            try {
                JobHistoryDao jobHistoryDao = ContextUtil.getBean(JobHistoryDao.class);
                jobHistoryDao.save(history);
            } catch (Exception e) {
                String msg = String.format("保存作业执行历史异常：%s", JsonUtils.toJson(history));
                LogUtil.error(msg, e);
            }
            // 释放线程变量
            ThreadLocalHolder.end();
        }
    }
}
