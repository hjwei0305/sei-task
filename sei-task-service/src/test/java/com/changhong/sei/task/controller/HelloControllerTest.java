package com.changhong.sei.task.controller;

import com.changhong.sei.core.dto.ResultData;
import com.changhong.sei.core.test.BaseUnitTest;
import com.changhong.sei.core.util.JsonUtils;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 实现功能: test say hello
 *
 * @author 王锦光 wangjg
 * @version 2020-02-09 21:29
 */
public class HelloControllerTest extends BaseUnitTest {
    @Autowired
    private HelloController controller;

    @Test
    public void sayHello() {
        String name = "sei666";
        ResultData result = controller.sayHello(name);
        System.out.println(JsonUtils.toJson(result));
    }
}