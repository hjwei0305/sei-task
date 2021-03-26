package com.changhong.sei.task.controller;

import com.changhong.sei.core.context.ContextUtil;
import com.changhong.sei.core.dto.ResultData;
import com.changhong.sei.core.log.LogUtil;
import com.changhong.sei.core.utils.ResultDataUtil;
import com.changhong.sei.task.api.PlatformTaskApi;
import io.swagger.annotations.Api;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * 实现功能: SEI平台级的后台作业任务API服务实现
 *
 * @author 王锦光 wangjg
 * @version 2020-02-10 16:02
 */
@RestController
@RequestMapping(path = "platformTask", produces = MediaType.APPLICATION_JSON_VALUE)
@Api(value = "PlatformTaskApi", tags = "SEI平台级的后台作业任务API服务")
public class PlatformTaskController implements PlatformTaskApi {
    /**
     * 带参数测试方法(获取输入参数)
     *
     * @param params 输入参数
     * @return 操作结果
     */
    @Override
    public ResultData getInputParam(Map<String, String> params) {
        String id = params.get("id");
        String code = params.get("code");
        // 获取的输入参数为：id={0},code={1}
        // 打印输出当前用户
        LogUtil.bizLog("后台任务由【{}】执行完成！", ContextUtil.getSessionUser());
        return ResultDataUtil.success(params, "00013", id, code);
    }
}
