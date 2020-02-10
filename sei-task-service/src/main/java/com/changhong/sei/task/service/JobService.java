package com.changhong.sei.task.service;

import com.changhong.sei.core.dao.BaseEntityDao;
import com.changhong.sei.core.dto.serach.PageResult;
import com.changhong.sei.core.dto.serach.Search;
import com.changhong.sei.core.service.BaseEntityService;
import com.changhong.sei.core.service.bo.OperateResult;
import com.changhong.sei.core.service.bo.OperateResultWithData;
import com.changhong.sei.task.dao.JobDao;
import com.changhong.sei.task.entity.Job;
import org.joda.time.DateTime;
import org.quartz.CronExpression;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.List;
import java.util.Objects;

/**
 * *************************************************************************************************
 * <p/>
 * 实现功能：作业定义业务逻辑实现
 * <p>
 * ------------------------------------------------------------------------------------------------
 * 版本          变更时间             变更人                     变更原因
 * ------------------------------------------------------------------------------------------------
 * 1.0.00      2017-07-21 9:55      王锦光(wangj)                新建
 * <p/>
 * *************************************************************************************************
 */
@Service
public class JobService extends BaseEntityService<Job> {
    @Autowired
    private JobDao dao;
    @Autowired
    private ScheduleJobService scheduleJobService;

    @Override
    protected BaseEntityDao<Job> getDao() {
        return dao;
    }

    /**
     * 基于动态组合条件对象和分页(含排序)对象查询数据集合
     *
     * @param searchConfig
     */
    @Override
    public PageResult<Job> findByPage(Search searchConfig) {
        PageResult<Job> result = super.findByPage(searchConfig);
        List<Job> jobs = result.getRows();
        if (Objects.nonNull(jobs) && !jobs.isEmpty()) {
            // 判断是否过期
            jobs.forEach((j) -> {
                String cronExp = j.getCronExp();
                try {
                    CronExpression exp = new CronExpression(cronExp);
                    if (Objects.isNull(exp.getNextValidTimeAfter(DateTime.now().toDate()))) {
                        j.setExpired(true);
                    }
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            });
        }
        return result;
    }

    /**
     * 添加一个作业到调度器中
     *
     * @param job 作业任务
     * @return 操作结果
     */
    public OperateResult addJob(Job job) {
        // 先创建后台作业
        OperateResultWithData<Job> saveResult = save(job);
        if (saveResult.notSuccessful()) {
            return OperateResultWithData.converterNoneData(saveResult);
        }

        if (!job.getDisable()) {
            // 调度并启动作业
            return scheduleJobService.addJob(saveResult.getData().getId());
        } else {
            return OperateResultWithData.converterNoneData(saveResult);
        }
    }

    /**
     * 更新数据保存数据之前额外操作回调方法 默认为空逻辑，子类根据需要覆写添加逻辑即可
     *
     * @param entity 待更新数据对象
     */
    protected OperateResultWithData<Job> preUpdate(Job entity) {
        if (entity.getDisable()) {
            String jobId = entity.getId();
            OperateResult result = scheduleJobService.removeJob(jobId);
            if (result.successful()) {
                return super.preUpdate(entity);
            } else {
                return OperateResult.converterWithData(result, entity);
            }
        } else {
            return super.preUpdate(entity);
        }
    }

    /**
     * 删除数据保存数据之前额外操作回调方法 子类根据需要覆写添加逻辑即可
     *
     * @param s 待删除数据对象主键
     */
    @Override
    protected OperateResult preDelete(String s) {
        return scheduleJobService.removeJob(s);
    }
}
