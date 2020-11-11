package com.changhong.sei.task.dto;

import com.changhong.sei.core.dto.BaseEntityDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * 实现功能: 后台作业定义DTO
 *
 * @author 王锦光 wangjg
 * @version 2020-02-10 8:47
 */
@ApiModel("后台作业定义DTO")
public class JobDto extends BaseEntityDto {
    /**
     * 名称
     */
    @NotBlank
    @Size(max = 50)
    @ApiModelProperty(value = "名称(max = 50)", required = true)
    private String name;

    /**
     * 应用模块代码
     */
    @NotBlank
    @Size(max = 20)
    @ApiModelProperty(value = "应用模块代码(max = 20)", required = true)
    private String appModuleCode;

    /**
     * 应用模块名称
     */
    @NotBlank
    @Size(max = 30)
    @ApiModelProperty(value = "应用模块名称(max = 30)", required = true)
    private String appModuleName;

    /**
     * API服务路径
     */
    @NotBlank
    @Size(max = 100)
    @ApiModelProperty(value = "API服务路径(max = 100)", required = true)
    private String apiPath;

    /**
     * API服务方法名
     */
    @NotBlank
    @Size(max = 100)
    @ApiModelProperty(value = "API服务方法名(max = 100)", required = true)
    private String methodName;

    /**
     * 输入参数
     */
    @ApiModelProperty(value = "输入参数(Map的Json字符串)")
    private String inputParam;

    /**
     * CRON表达式
     */
    @NotBlank
    @Size(max = 50)
    @ApiModelProperty(value = "CRON表达式(max = 50)", required = true)
    private String cronExp;

    /**
     * 作业说明
     */
    @NotBlank
    @Size(max = 200)
    @ApiModelProperty(value = "作业说明(max = 200)", required = true)
    private String remark;

    /**
     * 超时时间（秒）默认180秒
     */
    @NotNull
    @ApiModelProperty(value = "超时时间（秒）默认180秒", required = true)
    private Integer timeOut = 180;

    /**
     * 禁用
     */
    @NotNull
    @ApiModelProperty(value = "是否禁用", required = true)
    private Boolean disable = Boolean.FALSE;

    /**
     * 执行人租户代码
     */
    @ApiModelProperty(value = "执行人租户代码")
    private String exeTenantCode;

    /**
     * 执行人账号
     */
    @ApiModelProperty(value = "执行人账号")
    private String exeAccount;

    /**
     * 异步执行
     */
    @NotNull
    @ApiModelProperty(value = "异步执行", required = true)
    private Boolean asyncExe = Boolean.FALSE;

    /**
     * 异常通知邮箱
     */
    @ApiModelProperty(value = "异常通知邮箱")
    private String errorNotifyEmail;

    /**
     * 已过期
     */
    @ApiModelProperty(value = "已过期(传输属性)")
    private Boolean expired = Boolean.FALSE;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAppModuleCode() {
        return appModuleCode;
    }

    public void setAppModuleCode(String appModuleCode) {
        this.appModuleCode = appModuleCode;
    }

    public String getAppModuleName() {
        return appModuleName;
    }

    public void setAppModuleName(String appModuleName) {
        this.appModuleName = appModuleName;
    }

    public String getApiPath() {
        return apiPath;
    }

    public void setApiPath(String apiPath) {
        this.apiPath = apiPath;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public String getInputParam() {
        return inputParam;
    }

    public void setInputParam(String inputParam) {
        this.inputParam = inputParam;
    }

    public String getCronExp() {
        return cronExp;
    }

    public void setCronExp(String cronExp) {
        this.cronExp = cronExp;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getTimeOut() {
        return timeOut;
    }

    public void setTimeOut(Integer timeOut) {
        this.timeOut = timeOut;
    }

    public Boolean getDisable() {
        return disable;
    }

    public void setDisable(Boolean disable) {
        this.disable = disable;
    }

    public Boolean getExpired() {
        return expired;
    }

    public void setExpired(Boolean expired) {
        this.expired = expired;
    }

    public String getExeTenantCode() {
        return exeTenantCode;
    }

    public void setExeTenantCode(String exeTenantCode) {
        this.exeTenantCode = exeTenantCode;
    }

    public String getExeAccount() {
        return exeAccount;
    }

    public void setExeAccount(String exeAccount) {
        this.exeAccount = exeAccount;
    }

    public Boolean getAsyncExe() {
        return asyncExe;
    }

    public void setAsyncExe(Boolean asyncExe) {
        this.asyncExe = asyncExe;
    }

    public String getErrorNotifyEmail() {
        return errorNotifyEmail;
    }

    public void setErrorNotifyEmail(String errorNotifyEmail) {
        this.errorNotifyEmail = errorNotifyEmail;
    }
}
