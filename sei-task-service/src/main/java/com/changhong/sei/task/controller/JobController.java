package com.changhong.sei.task.controller;

import com.changhong.sei.core.controller.DefaultBaseEntityController;
import com.changhong.sei.core.dto.ResultData;
import com.changhong.sei.core.dto.serach.PageResult;
import com.changhong.sei.core.dto.serach.Search;
import com.changhong.sei.core.service.BaseEntityService;
import com.changhong.sei.core.utils.ResultDataUtil;
import com.changhong.sei.task.api.JobApi;
import com.changhong.sei.task.dto.JobDto;
import com.changhong.sei.task.entity.Job;
import com.changhong.sei.task.service.JobService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 实现功能: 后台作业API服务实现
 *
 * @author 王锦光 wangjg
 * @version 2020-02-10 10:01
 */
@RestController
@RequestMapping(path = "job", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@Api(value = "JobApi", tags = "后台作业API服务")
public class JobController implements DefaultBaseEntityController<Job, JobDto>,
        JobApi {
    @Autowired
    private JobService service;
    /**
     * 获取所有业务实体
     *
     * @return 业务实体清单
     */
    @Override
    public ResultData<List<JobDto>> findAll() {
        return ResultData.success(convertToDtos(service.findAll()));
    }

    /**
     * 获取所有未冻结的业务实体
     *
     * @return 业务实体清单
     */
    @Override
    public ResultData<List<JobDto>> findAllUnfrozen() {
        return ResultData.success(convertToDtos(service.findAllUnfrozen()));
    }

    /**
     * 分页查询业务实体
     *
     * @param search 查询参数
     * @return 分页查询结果
     */
    @Override
    public ResultData<PageResult<JobDto>> findByPage(Search search) {
        return convertToDtoPageResult(service.findByPage(search));
    }

    @Override
    public BaseEntityService<Job> getService() {
        return service;
    }

    /**
     * 获取数据实体的类型
     *
     * @return 类型Class
     */
    @Override
    public Class<Job> getEntityClass() {
        return Job.class;
    }

    /**
     * 获取传输实体的类型
     *
     * @return 类型Class
     */
    @Override
    public Class<JobDto> getDtoClass() {
        return JobDto.class;
    }

    /**
     * 添加一个作业到调度器中
     *
     * @param jobDto 作业任务DTO
     * @return 操作结果
     */
    @Override
    public ResultData addJob(JobDto jobDto) {
        // 转换为entity
        Job job = convertToEntity(jobDto);
        return ResultDataUtil.convertFromOperateResult(service.addJob(job));
    }
}
