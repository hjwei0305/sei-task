package com.changhong.sei.task.entity;

import com.changhong.sei.core.entity.BaseAuditableEntity;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

/**
 * *************************************************************************************************
 * <p/>
 * 实现功能：作业定义实体
 * <p>
 * ------------------------------------------------------------------------------------------------
 * 版本          变更时间             变更人                     变更原因
 * ------------------------------------------------------------------------------------------------
 * 1.0.00      2017/7/20 11:12      秦有宝                     新建
 * <p/>
 * *************************************************************************************************
 */
@Access(AccessType.FIELD)
@Entity
@Table(name="job")
@DynamicInsert
@DynamicUpdate
public class Job extends BaseAuditableEntity {

    /**
     * 名称
     */
    @Column(name = "name",length = 50, nullable = false)
    private String name;

    /**
     * 应用模块代码
     */
    @Column(name = "app_module_code",length = 20, nullable = false)
    private String appModuleCode;

    /**
     * 应用模块名称
     */
    @Column(name = "app_module_name",length = 30, nullable = false)
    private String appModuleName;

    /**
     * API服务路径
     */
    @Column(name = "api_path",length = 100, nullable = false)
    private String apiPath;

    /**
     * API服务方法名
     */
    @Column(name = "method_name",length = 100, nullable = false)
    private String methodName;

    /**
     * 输入参数
     */
    @Column(name = "input_param",length = 1000)
    private String inputParam;

    /**
     * CRON表达式
     */
    @Column(name = "cron_exp",length = 50, nullable = false)
    private String cronExp;

    /**
     * 作业说明
     */
    @Column(name = "remark",length = 200, nullable = false)
    private String remark;

    /**
     * 超时时间（秒）默认180秒
     */
    @Column(name = "time_out",nullable = false)
    private Integer timeOut = 180;

    /**
     * 禁用
     */
    @Column(name = "disable",nullable = false)
    private Boolean disable = Boolean.FALSE;

    /**
     * 已过期
     */
    @Transient
    @JsonSerialize
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
}
