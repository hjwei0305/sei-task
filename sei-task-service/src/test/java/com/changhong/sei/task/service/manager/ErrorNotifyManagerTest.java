package com.changhong.sei.task.service.manager;

import com.changhong.sei.core.test.BaseUnitTest;
import com.changhong.sei.exception.ServiceException;
import com.changhong.sei.task.entity.Job;
import com.changhong.sei.task.service.JobService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.*;

/**
 * 实现功能:
 *
 * @author 王锦光 wangjg
 * @version 2020-11-11 10:05
 */
public class ErrorNotifyManagerTest extends BaseUnitTest {
    @Autowired
    private ErrorNotifyManager manager;
    @Autowired
    private JobService jobService;

    @Test
    public void sendEmail() {
        String jobId = "25F9D22C-4E2F-11EA-B420-0242C0A84609";
        Job job = jobService.findOne(jobId);
        String message = "测试后台作业的任务执行失败了！";
        Exception e = new ServiceException("有一个业务逻辑发生了异常！");
        manager.sendEmail(job, message, e);
    }
}