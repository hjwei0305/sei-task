package com.changhong.sei.task.controller;

import com.changhong.sei.core.controller.BaseEntityController;
import com.changhong.sei.core.controller.DefaultBaseEntityController;
import com.changhong.sei.core.dto.ResultData;
import com.changhong.sei.core.dto.serach.PageResult;
import com.changhong.sei.core.dto.serach.Search;
import com.changhong.sei.core.service.BaseEntityService;
import com.changhong.sei.task.api.JobHistoryApi;
import com.changhong.sei.task.dto.JobHistoryDto;
import com.changhong.sei.task.entity.JobHistory;
import com.changhong.sei.task.service.JobHistoryService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 实现功能: 作业执行历史API服务实现
 *
 * @author 王锦光 wangjg
 * @version 2020-02-10 15:32
 */
@RestController
@RequestMapping(path = "jobHistory", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@Api(value = "JobHistoryApi", tags = "后台作业执行历史")
public class JobHistoryController extends BaseEntityController<JobHistory, JobHistoryDto>
        implements JobHistoryApi {
    @Autowired
    private JobHistoryService service;
    /**
     * 分页查询业务实体
     *
     * @param search 查询参数
     * @return 分页查询结果
     */
    @Override
    public ResultData<PageResult<JobHistoryDto>> findByPage(Search search) {
        return convertToDtoPageResult(service.findByPage(search));
    }

    @Override
    public BaseEntityService<JobHistory> getService() {
        return service;
    }
}
