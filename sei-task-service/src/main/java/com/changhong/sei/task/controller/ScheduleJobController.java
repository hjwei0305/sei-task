package com.changhong.sei.task.controller;

import com.changhong.sei.core.dto.ResultData;
import com.changhong.sei.core.utils.ResultDataUtil;
import com.changhong.sei.task.api.ScheduleJobApi;
import com.changhong.sei.task.dto.JobTrigger;
import com.changhong.sei.task.service.ScheduleJobService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 实现功能: 后台作业调度服务API服务实现
 *
 * @author 王锦光 wangjg
 * @version 2020-02-11 16:40
 */
@RestController
@RequestMapping(path = "scheduleJob", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@Api(value = "ScheduleJobApi", tags = "后台作业调度服务API服务实现")
public class ScheduleJobController implements ScheduleJobApi {
    @Autowired
    private ScheduleJobService service;
    /**
     * 获取所有调度器中的后台作业状态
     *
     * @return 作业状态清单
     */
    @Override
    public ResultData<List<JobTrigger>> getJobStates() {
        return ResultData.success(service.getJobStates());
    }

    /**
     * 暂停一个作业
     *
     * @param id 作业Id
     * @return 操作结果
     */
    @Override
    public ResultData<?> pauseJob(String id) {
        return ResultDataUtil.convertFromOperateResult(service.pauseJob(id));
    }

    /**
     * 唤醒一个作业
     *
     * @param id 作业Id
     * @return 操作结果
     */
    @Override
    public ResultData<?> resumeJob(String id) {
        return ResultDataUtil.convertFromOperateResult(service.resumeJob(id));
    }

    /**
     * 立即执行一个已经调度的作业
     *
     * @param id 作业Id
     * @return 操作结果
     */
    @Override
    public ResultData<?> triggerJob(String id) {
        return ResultDataUtil.convertFromOperateResult(service.triggerJob(id));
    }

    /**
     * 更新作业的调度周期表达式
     *
     * @param id 作业Id
     * @return 操作结果
     */
    @Override
    public ResultData<?> rescheduleJob(String id) {
        return ResultDataUtil.convertFromOperateResult(service.rescheduleJob(id));
    }

    /**
     * 添加一个作业到调度器中
     *
     * @param id 作业Id
     * @return 操作结果
     */
    @Override
    public ResultData<?> addJob(String id) {
        return ResultDataUtil.convertFromOperateResult(service.addJob(id));
    }

    /**
     * 从调度器中移除一个作业
     *
     * @param id 作业Id
     * @return 操作结果
     */
    @Override
    public ResultData<?> removeJob(String id) {
        return ResultDataUtil.convertFromOperateResult(service.removeJob(id));
    }
}
