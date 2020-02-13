package com.changhong.sei.task.controller;

import com.changhong.sei.core.util.JsonUtils;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * 实现功能:
 *
 * @author 王锦光 wangjg
 * @version 2020-02-13 15:00
 */
public class UtilTest {

    @Test
    public void createParam(){
        Map<String,String> param = new HashMap<>();
        param.put("id", "0001");
        param.put("code", "test_code");
        String paramJson = JsonUtils.toJson(param);
        System.out.println(paramJson);
    }
}
