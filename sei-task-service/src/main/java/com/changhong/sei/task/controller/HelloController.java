package com.changhong.sei.task.controller;

import com.changhong.sei.core.context.ContextUtil;
import com.changhong.sei.core.context.SessionUser;
import com.changhong.sei.core.dto.ResultData;
import com.changhong.sei.task.api.HelloApi;
import com.changhong.sei.task.service.HelloService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 实现功能: hello controller
 *
 * @author 王锦光 wangjg
 * @version 2020-02-09 21:28
 */
@RestController
@RequestMapping(path = "taskHello", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@RefreshScope // 如果需要动态加载配置属性，才需要的注解
@Api(value = "HelloApi", tags = "调试你好的API服务")
public class HelloController implements HelloApi {
    @Autowired
    private HelloService service;

    @Value("${task.test-key}")
    private String testKey;

    /**
     * 你好
     * @param name 姓名
     * @return 返回句子
     */
    @Override
    public ResultData<String> sayHello(String name) {
        SessionUser sessionUser = ContextUtil.getSessionUser();
        System.out.println(sessionUser);
        String data = service.sayHello(name, testKey);
        return ResultData.success(data);
    }
}
