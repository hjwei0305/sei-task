package com.changhong.sei.task.controller;

import com.changhong.sei.core.dto.ResultData;
import com.changhong.sei.core.test.BaseUnitTest;
import com.changhong.sei.core.util.JsonUtils;
import com.changhong.sei.task.dao.JobDao;
import com.changhong.sei.task.dto.JobDto;
import com.chonghong.sei.util.DateUtils;
import org.apache.commons.lang3.StringUtils;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

/**
 * 实现功能:
 *
 * @author 王锦光 wangjg
 * @version 2020-02-10 10:07
 */
public class JobControllerTest extends BaseUnitTest {
    @Autowired
    private JobController controller;
    @Autowired
    private JobDao dao;

    @Test
    public void findAll() {
        ResultData resultData = controller.findAll();
        System.out.println(JsonUtils.toJson(resultData));
        Assert.assertTrue(resultData.successful());
    }

    private JobDto createJob(){
        JobDto job = new JobDto();
        job.setName("测试带参数任务");
        job.setAppModuleCode("sei-task");
        job.setAppModuleName("后台作业");
        job.setApiPath("platformTask");
        job.setMethodName("getInputParam");
        Map<String,String> param = new HashMap<>();
        param.put("id", "0001");
        param.put("code", "test_code");
        String paramJson = JsonUtils.toJson(param);
        job.setInputParam(paramJson);
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.MINUTE, cal.get(Calendar.MINUTE) + 2);
        job.setCronExp(DateUtils.formatDateToCorn(cal.getTime()));
        job.setRemark("在当前时间的2分钟后执行");
        return job;
    }

    @Test
    public void checkPath(){
        JobDto job = createJob();
        String id = dao.checkPath("", job.getAppModuleCode(), job.getApiPath(), job.getMethodName());
        System.out.println("id: "+id);
        Assert.assertTrue(StringUtils.isBlank(id));
    }

    @Test
    public void save(){
        JobDto job = createJob();
        job.setId("1CE6C14B-4CA9-11EA-BF45-080058000005");
        ResultData<JobDto> result = controller.save(job);
        System.out.println(result);
        Assert.assertTrue(result.successful());
        System.out.println(JsonUtils.toJson(result.getData()));
    }

    @Test
    public void getRecentTriggerTimes() {
        String cron = "0 0/30 * * * ? *";
        ResultData resultData = controller.getRecentTriggerTimes(cron);
        System.out.println(JsonUtils.toJson(resultData));
    }
}