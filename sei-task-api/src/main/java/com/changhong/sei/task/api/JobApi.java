package com.changhong.sei.task.api;

import com.changhong.sei.core.api.BaseEntityApi;
import com.changhong.sei.core.api.FindAllApi;
import com.changhong.sei.core.api.FindByPageApi;
import com.changhong.sei.core.dto.ResultData;
import com.changhong.sei.task.dto.JobDto;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * 实现功能: 后台作业API接口
 *
 * @author 王锦光 wangjg
 * @version 2020-02-10 9:54
 */
@FeignClient(name = "sei-task", path = "job")
public interface JobApi extends BaseEntityApi<JobDto>,
        FindAllApi<JobDto>,
        FindByPageApi<JobDto> {
    /**
     * 添加一个作业到调度器中
     *
     * @param jobDto 作业任务DTO
     * @return 操作结果
     */
    @PostMapping(path = "addJob", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "添加一个作业到调度器中", notes = "创建一个作业，并添加到调度器启动")
    ResultData addJob(@RequestBody JobDto jobDto);
}
