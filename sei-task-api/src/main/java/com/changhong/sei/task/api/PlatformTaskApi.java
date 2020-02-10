package com.changhong.sei.task.api;

import com.changhong.sei.core.dto.ResultData;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Map;

/**
 * 实现功能: SEI平台级的后台作业任务API接口
 *
 * @author 王锦光 wangjg
 * @version 2020-02-10 15:58
 */
@FeignClient(name = "sei-task", path = "platformTask")
public interface PlatformTaskApi {
    /**
     * 带参数测试方法(获取输入参数)
     * @param params 输入参数
     * @return 操作结果
     */
    @PostMapping(path = "getInputParam", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "带参数测试方法", notes = "带参数测试方法(获取输入参数)")
    ResultData getInputParam(@RequestBody Map<String,String> params);
}
