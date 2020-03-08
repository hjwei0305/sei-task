package com.changhong.sei.task.controller;

import com.changhong.sei.apitemplate.ApiTemplate;
import com.changhong.sei.core.context.ContextUtil;
import com.changhong.sei.core.dto.ResultData;
import com.changhong.sei.core.test.BaseUnitTest;
import com.changhong.sei.core.util.JsonUtils;
import com.changhong.sei.task.service.QuartzJobFactory;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 实现功能:
 *
 * @author 王锦光 wangjg
 * @version 2020-02-10 16:07
 */
public class PlatformTaskControllerTest extends BaseUnitTest {
    @Autowired
    private PlatformTaskController controller;
    @Autowired
    private QuartzJobFactory jobFactory;

    @Test
    public void getInputParam() {
        Map<String, String> params = new LinkedHashMap<>();
        params.put("id", "123456");
        params.put("code", "test-01");
        ResultData resultData = controller.getInputParam(params);
        System.out.println(JsonUtils.toJson(resultData));
        Assert.assertTrue(resultData.successful());
    }

    @Test
    public void getInputParamViaApi() {
        Map<String, String> params = new LinkedHashMap<>();
        params.put("id", "0002");
        params.put("code", "test-02");
        System.out.println(ContextUtil.getSessionUser());
        ApiTemplate apiTemplate = ContextUtil.getBean(ApiTemplate.class);
        ResultData resultData = apiTemplate.postByAppModuleCode("sei-task", "platformTask/getInputParam", ResultData.class, params);
        System.out.println(JsonUtils.toJson(resultData));
        Assert.assertTrue(resultData.successful());
    }
}