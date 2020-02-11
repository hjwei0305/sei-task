package com.changhong.sei.task.api;

import com.changhong.sei.core.dto.ResultData;
import com.changhong.sei.task.dto.JobTrigger;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

/**
 * 实现功能: 后台作业调度服务API接口
 *
 * @author 王锦光 wangjg
 * @version 2020-02-11 16:35
 */
@FeignClient(name = "sei-task", path = "scheduleJob")
public interface ScheduleJobApi {
    /**
     * 获取所有调度器中的后台作业状态
     * @return 作业状态清单
     */
    @GetMapping(path = "getJobStates")
    @ApiOperation(value = "获取后台作业状态",notes = "获取所有调度器中的后台作业状态")
    ResultData<List<JobTrigger>> getJobStates();

    /**
     * 暂停一个作业
     * @param id 作业Id
     * @return 操作结果
     */
    @PostMapping(path = "pauseJob/{id}")
    @ApiOperation(value = "暂停一个作业",notes = "在调度器中暂停一个作业")
    ResultData pauseJob(@PathVariable("id") String id);

    /**
     * 唤醒一个作业
     * @param id 作业Id
     * @return 操作结果
     */
    @PostMapping(path = "resumeJob/{id}")
    @ApiOperation(value = "唤醒一个作业",notes = "在调度器中唤醒一个暂停的作业")
    ResultData resumeJob(@PathVariable("id") String id);

    /**
     * 立即执行一个已经调度的作业
     * @param id 作业Id
     * @return 操作结果
     */
    @PostMapping(path = "triggerJob/{id}")
    @ApiOperation(value = "立即执行一个后台作业",notes = "立即执行一个已经调度的作业")
    ResultData triggerJob(@PathVariable("id") String id);

    /**
     * 更新作业的调度周期表达式
     * @param id 作业Id
     * @return 操作结果
     */
    @PostMapping(path = "rescheduleJob/{id}")
    @ApiOperation(value = "更新作业的调度周期表达式",notes = "在调度器中更新作业的调度周期表达式")
    ResultData rescheduleJob(@PathVariable("id") String id);

    /**
     * 添加一个作业到调度器中
     * @param id 作业Id
     * @return 操作结果
     */
    @PostMapping(path = "addJob/{id}")
    @ApiOperation(value = "添加一个作业到调度器中",notes = "添加一个新的后台作业到调度器中")
    ResultData addJob(@PathVariable("id") String id);

    /**
     * 从调度器中移除一个作业
     * @param id 作业Id
     * @return 操作结果
     */
    @PostMapping(path = "removeJob/{id}")
    @ApiOperation(value = "从调度器中移除一个作业",notes = "从调度器中移除一个后台作业")
    ResultData removeJob(@PathVariable("id") String id);
}
