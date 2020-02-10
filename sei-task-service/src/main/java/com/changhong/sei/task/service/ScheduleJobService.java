package com.changhong.sei.task.service;

import com.changhong.sei.core.log.LogUtil;
import com.changhong.sei.core.service.bo.OperateResult;
import com.changhong.sei.task.dao.JobDao;
import com.changhong.sei.task.dto.JobState;
import com.changhong.sei.task.dto.JobTrigger;
import com.changhong.sei.task.entity.Job;
import com.chonghong.sei.util.EnumUtils;
import org.apache.commons.lang3.StringUtils;
import org.quartz.*;
import org.quartz.impl.matchers.GroupMatcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;

/**
 * *************************************************************************************************
 * <p/>
 * 实现功能：后台作业调度服务
 * <p>
 * ------------------------------------------------------------------------------------------------
 * 版本          变更时间             变更人                     变更原因
 * ------------------------------------------------------------------------------------------------
 * 1.0.00      2017-08-02 14:30      王锦光(wangj)                新建
 * <p/>
 * *************************************************************************************************
 */
@Service
public class ScheduleJobService {
    @Autowired
    private Scheduler scheduler;
    @Autowired
    private JobDao jobDao;

    /**
     * 获取所有调度器中的后台作业状态
     *
     * @return 作业状态清单
     */
    public List<JobTrigger> getJobStates() {
        List<JobTrigger> jobTriggers = new ArrayList<>();
        try {
            Set<TriggerKey> triggerKeys = scheduler.getTriggerKeys(GroupMatcher.anyTriggerGroup());
            for (TriggerKey key : triggerKeys) {
                // 跳过手工执行的任务
                if (StringUtils.equals("DEFAULT", key.getGroup())) {
                    continue;
                }

                Trigger trigger = scheduler.getTrigger(key);
                if (Objects.isNull(trigger)) {
                    continue;
                }
                JobKey jobKey = trigger.getJobKey();
                String id = jobKey.getName();
                Job job = jobDao.findOne(id);
                if (Objects.isNull(job)) {
                    continue;
                }
                /*
                会删除手工执行的任务，故注释掉  modify by 2019-07-23
                if (Objects.isNull(trigger.getNextFireTime())) {
                    // 已过期，从调度器中删除
                    removeJob(job);
                    continue;
                }*/
                JobTrigger jobTrigger = new JobTrigger();
                jobTrigger.setId(job.getId());
                jobTrigger.setName(job.getName());
                jobTrigger.setGroup(jobKey.getGroup());
                jobTrigger.setRemark(job.getRemark());
                jobTrigger.setCronExpression(job.getCronExp());
                if (Objects.nonNull(trigger.getNextFireTime())) {
                    jobTrigger.setNextFireTime(trigger.getNextFireTime());
                }
                jobTrigger.setState(EnumUtils.getEnum(JobState.class, scheduler.getTriggerState(key).ordinal()));
                jobTriggers.add(jobTrigger);
            }
        } catch (SchedulerException e) {
            LogUtil.error("获取后台作业调度状态时发生异常！", e);
        }
        return jobTriggers;
    }

    /**
     * 暂停一个作业
     *
     * @param id 作业Id
     * @return 操作结果
     */
    public OperateResult pauseJob(String id) {
        Job job = jobDao.findOne(id);
        if (Objects.isNull(job)) {
            //后台作业不存在！
            return OperateResult.operationFailure("00001");
        }
        try {
            scheduler.pauseJob(JobKey.jobKey(id, job.getAppModuleCode()));
        } catch (SchedulerException e) {
            LogUtil.error("后台作业暂停异常！", e);
            //后台作业暂停失败！{0}
            return OperateResult.operationFailure("00003", e.getMessage());
        }
        //后台作业暂停成功！
        return OperateResult.operationSuccess("00002");
    }

    /**
     * 唤醒一个作业
     *
     * @param id 作业Id
     * @return 操作结果
     */
    public OperateResult resumeJob(String id) {
        Job job = jobDao.findOne(id);
        if (Objects.isNull(job)) {
            //后台作业不存在！
            return OperateResult.operationFailure("00001");
        }
        try {
            scheduler.resumeJob(JobKey.jobKey(id, job.getAppModuleCode()));
        } catch (SchedulerException e) {
            LogUtil.error("后台作业唤醒异常！", e);
            //后台作业唤醒失败！{0}
            return OperateResult.operationFailure("00005", e.getMessage());
        }
        //后台作业唤醒成功！
        return OperateResult.operationSuccess("00004");
    }

    /**
     * 立即执行一个已经调度的作业
     *
     * @param id 作业Id
     * @return 操作结果
     */
    public OperateResult triggerJob(String id) {
        Job job = jobDao.findOne(id);
        if (Objects.isNull(job)) {
            //后台作业不存在！
            return OperateResult.operationFailure("00001");
        }
        try {
            scheduler.triggerJob(JobKey.jobKey(id, job.getAppModuleCode()));
        } catch (SchedulerException e) {
            LogUtil.error("后台作业立即执行异常！", e);
            //后台作业立即执行失败！{0}
            return OperateResult.operationFailure("00007", e.getMessage());
        }
        //后台作业立即执行成功！
        return OperateResult.operationSuccess("00006");
    }

    /**
     * 更新作业的调度周期表达式
     *
     * @param id 作业Id
     * @return 操作结果
     */
    public OperateResult rescheduleJob(String id) {
        Job job = jobDao.findOne(id);
        if (Objects.isNull(job)) {
            //后台作业不存在！
            return OperateResult.operationFailure("00001");
        }
        try {
            TriggerKey triggerKey = TriggerKey.triggerKey(id, job.getAppModuleCode());
            CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(job.getCronExp());
            CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);
            trigger = trigger.getTriggerBuilder()
                    .withIdentity(triggerKey)
                    .withSchedule(scheduleBuilder)
                    .build();
            scheduler.rescheduleJob(triggerKey, trigger);
        } catch (Exception e) {
            LogUtil.error("后台作业更新调度周期异常！", e);
            //后台作业更新调度周期失败！{0}
            return OperateResult.operationFailure("00009", e.getMessage());
        }
        //后台作业更新调度周期成功！
        return OperateResult.operationSuccess("00008");
    }

    /**
     * 添加一个作业到调度器中
     *
     * @param id 作业Id
     * @return 操作结果
     */
    public OperateResult addJob(String id) {
        Job job = jobDao.findOne(id);
        if (Objects.isNull(job)) {
            //后台作业不存在！
            return OperateResult.operationFailure("00001");
        }
        if (job.getDisable()) {
            //任务[{}]已被禁用，不能添加到调度器中！！
            return OperateResult.operationFailure("00016", job.getName());
        }
        try {
            // 调度一个任务
            scheduleJob(job);
        } catch (Exception e) {
            LogUtil.error("添加后台作业调度异常！", e);
            //添加后台作业调度失败！{0}
            return OperateResult.operationFailure("00011", e.getMessage());
        }
        //添加后台作业调度成功！
        return OperateResult.operationSuccess("00010");
    }

    /**
     * 调度一个任务
     *
     * @param job 任务(自定义的任务实体)
     */
    void scheduleJob(Job job) throws SchedulerException {
        String id = job.getId();
        String group = job.getAppModuleCode();
        // 定义jobKey
        assert id != null;
        TriggerKey triggerKey = TriggerKey.triggerKey(id, group);
        JobKey jobKey = JobKey.jobKey(id, group);
        // 判断job否已经存在
        boolean jobExists = scheduler.checkExists(jobKey);
        if (jobExists) {
            // 停止触发器
            scheduler.pauseJob(jobKey);
            // 移除触发器
            scheduler.unscheduleJob(triggerKey);
            // 删除job
            scheduler.deleteJob(jobKey);
        }

        // 构建job信息
        JobDetail jobDetail = JobBuilder.newJob(QuartzJobFactory.class)
                .withIdentity(id, group)
                .build();
        // 表达式调度构建器
        CronScheduleBuilder cronScheduleBuilder = CronScheduleBuilder.cronSchedule(job.getCronExp());
        // 按cronExpression表达式构建trigger
        CronTrigger cronTrigger = TriggerBuilder.newTrigger()
                .withIdentity(id, group)
                .withSchedule(cronScheduleBuilder)
                .build();

        // 放入参数，运行时的方法可以获取
        jobDetail.getJobDataMap().put(QuartzJobFactory.SCHEDULER_KEY, job);
        // 调度job
        scheduler.scheduleJob(jobDetail, cronTrigger);
    }

    /**
     * 移除一个任务
     *
     * @param job 任务(自定义的任务实体)
     */
    private void removeJob(Job job) throws SchedulerException {
        String id = job.getId();
        String group = job.getAppModuleCode();
        // 定义jobKey
        assert id != null;
        TriggerKey triggerKey = TriggerKey.triggerKey(id, group);
        JobKey jobKey = JobKey.jobKey(id, group);
        // 判断job否已经存在
        boolean jobExists = scheduler.checkExists(jobKey);
        if (jobExists) {
            // 停止触发器
            scheduler.pauseJob(jobKey);
            // 移除触发器
            scheduler.unscheduleJob(triggerKey);
            // 删除job
            scheduler.deleteJob(jobKey);
        }
    }

    /**
     * 从调度器删除一个任务
     *
     * @param id 作业Id
     * @return 操作结果
     */
    public OperateResult removeJob(String id) {
        Job job = jobDao.findOne(id);
        if (Objects.isNull(job)) {
            //后台作业不存在！
            return OperateResult.operationFailure("00001");
        }
        try {
            // 移除一个任务
            removeJob(job);
        } catch (Exception e) {
            LogUtil.error("后台作业从调度器删除异常！", e);
            // 后台作业从调度器删除失败！{0}
            return OperateResult.operationFailure("00015", e.getMessage());
        }
        // 后台作业从调度器删除成功！
        return OperateResult.operationSuccess("00014");
    }

    /**
     * 从调度器删除所有任务
     */
    void removeAllJob() {
        List<Job> jobs = jobDao.findAll();
        jobs.forEach((j) -> {
            try {
                removeJob(j);
            } catch (Exception e) {
                e.printStackTrace();
                LogUtil.error("后台作业从调度器删除异常！", e);
            }
        });
    }
}
