package com.changhong.sei.task.dto;

import com.changhong.sei.core.dto.BaseEntityDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

/**
 * 实现功能: 后台作业执行历史
 *
 * @author 王锦光 wangjg
 * @version 2020-02-10 15:16
 */
@ApiModel("后台作业执行历史")
public class JobHistoryDto extends BaseEntityDto {
    /**
     * 作业定义Id
     */
    @ApiModelProperty("作业定义Id")
    private String jobId;

    /**
     * 作业定义名称
     */
    @ApiModelProperty("作业定义名称")
    private String jobName;

    /**
     * 应用模块名称
     */
    @ApiModelProperty("应用模块名称")
    private String appModuleName;

    /**
     * 执行开始时间
     */
    @ApiModelProperty("执行开始时间")
    private Date startTime;

    /**
     * 耗时(ms)
     */
    @ApiModelProperty("耗时(ms)")
    private Long elapsed = 0L;

    /**
     * 是否执行成功
     */
    @ApiModelProperty("是否执行成功")
    private Boolean successful = Boolean.FALSE;

    /**
     * 执行消息
     */
    @ApiModelProperty("执行消息")
    private String message;

    /**
     * 异常消息
     */
    @ApiModelProperty("异常消息")
    private String exceptionMessage;

    public String getJobId() {
        return jobId;
    }

    public void setJobId(String jobId) {
        this.jobId = jobId;
    }

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public String getAppModuleName() {
        return appModuleName;
    }

    public void setAppModuleName(String appModuleName) {
        this.appModuleName = appModuleName;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Long getElapsed() {
        return elapsed;
    }

    public void setElapsed(Long elapsed) {
        this.elapsed = elapsed;
    }

    public Boolean getSuccessful() {
        return successful;
    }

    public void setSuccessful(Boolean successful) {
        this.successful = successful;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getExceptionMessage() {
        return exceptionMessage;
    }

    public void setExceptionMessage(String exceptionMessage) {
        this.exceptionMessage = exceptionMessage;
    }
}
