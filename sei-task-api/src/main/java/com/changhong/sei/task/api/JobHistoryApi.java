package com.changhong.sei.task.api;

import com.changhong.sei.core.api.BaseEntityApi;
import com.changhong.sei.core.api.FindByPageApi;
import com.changhong.sei.task.dto.JobHistoryDto;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * 实现功能: 作业执行历史API
 *
 * @author 王锦光 wangjg
 * @version 2020-02-10 15:14
 */
@FeignClient(name = "sei-basic", path = "jobHistory")
public interface JobHistoryApi extends BaseEntityApi<JobHistoryDto>,
        FindByPageApi<JobHistoryDto> {
}