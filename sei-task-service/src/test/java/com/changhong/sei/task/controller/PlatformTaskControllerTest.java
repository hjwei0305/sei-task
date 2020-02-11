package com.changhong.sei.task.controller;

import com.changhong.com.sei.core.test.BaseUnitTest;
import com.changhong.sei.apitemplate.ApiTemplate;
import com.changhong.sei.core.dto.ResultData;
import com.changhong.sei.core.util.JsonUtils;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.LinkedHashMap;
import java.util.Map;

import static org.junit.Assert.*;

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
    private ApiTemplate apiTemplate;

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
        ResultData resultData = apiTemplate.postByAppModuleCode("sei-task", "platformTask/getInputParam", ResultData.class, params);
        System.out.println(JsonUtils.toJson(resultData));
        Assert.assertTrue(resultData.successful());
    }
}