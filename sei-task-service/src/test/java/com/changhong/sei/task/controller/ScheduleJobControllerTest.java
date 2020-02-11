package com.changhong.sei.task.controller;

import com.changhong.com.sei.core.test.BaseUnitTest;
import com.changhong.sei.core.dto.ResultData;
import com.changhong.sei.core.util.JsonUtils;
import com.changhong.sei.core.utils.ResultDataUtil;
import com.changhong.sei.task.service.ScheduleJobService;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.*;

/**
 * 实现功能:
 *
 * @author 王锦光 wangjg
 * @version 2020-02-11 16:44
 */
public class ScheduleJobControllerTest extends BaseUnitTest {
    @Autowired
    private ScheduleJobController controller;

    @Test
    public void getJobStates() {
        ResultData resultData = controller.getJobStates();
        System.out.println(JsonUtils.toJson(resultData));
        Assert.assertTrue(resultData.successful());
    }

    @Test
    public void addJob(){
        String id = "1CE6C14B-4CA9-11EA-BF45-080058000005";
        ResultData resultData = controller.addJob(id);
        System.out.println(JsonUtils.toJson(resultData));
        Assert.assertTrue(resultData.successful());
    }
}