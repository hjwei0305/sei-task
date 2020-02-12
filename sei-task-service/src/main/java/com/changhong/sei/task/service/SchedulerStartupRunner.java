package com.changhong.sei.task.service;

import com.changhong.sei.core.log.LogUtil;
import com.changhong.sei.task.dao.JobDao;
import com.changhong.sei.task.entity.Job;
import org.joda.time.DateTime;
import org.quartz.CronExpression;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;

import java.text.ParseException;
import java.util.List;
import java.util.Objects;

/**
 * <strong>实现功能:</strong>
 * <p>服务启动后执行任务调度初始化加载</p>
 *
 * @author 王锦光 wangj
 * @version 1.0.1 2018-06-21 11:21
 */
public class SchedulerStartupRunner implements CommandLineRunner {
    @Autowired
    private ScheduleJobService scheduleJobService;
    @Autowired
    private JobDao jobDao;
    /**
     * Callback used to run the bean.
     *
     * @param args incoming main method arguments
     */
    @Override
    public void run(String... args) {
        LogUtil.info("获取所有配置有效的作业...");
        //获取所有配置有效的作业
        List<Job> jobs = jobDao.findAll();
        if (Objects.isNull(jobs) || jobs.isEmpty()) {
            return;
        }
        //循环执行所有任务
        for (Job job : jobs
                ) {
            //禁用不执行
            if (job.getDisable()) {
                continue;
            }
            // 过期不执行
            try {
                CronExpression exp = new CronExpression(job.getCronExp());
                if (Objects.isNull(exp.getNextValidTimeAfter(DateTime.now().toDate()))){
                    continue;
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }
            try {
                // 重新调度任务
                scheduleJobService.scheduleJob(job);
                LogUtil.info("Schedule a job: {}-{}", job.getMethodName(), job.getName());
            } catch (Exception e) {
                e.printStackTrace();
                LogUtil.error("启动job异常！", e);
            }
        }
    }
}
