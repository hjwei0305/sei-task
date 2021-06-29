package com.changhong.sei.task.service;

import com.changhong.sei.core.test.BaseUnitTest;
import org.junit.Test;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.TriggerKey;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 实现功能:
 *
 * @author 王锦光 wangjg
 * @version 2020-02-13 15:58
 */
public class ScheduleJobServiceTest extends BaseUnitTest {
    @Autowired
    private ScheduleJobService service;
    @Autowired
    private Scheduler scheduler;

    @Test
    public void removeAllJob() {
        service.removeAllJob();
    }

    @Test
    public void removeJob() throws SchedulerException {
        String id = "3F361E4E-1E8F-11EB-AE30-0242C0A84628";
        String group = "zthx-tax";
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
}