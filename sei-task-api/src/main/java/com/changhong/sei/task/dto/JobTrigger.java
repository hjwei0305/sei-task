package com.changhong.sei.task.dto;

import com.chonghong.sei.util.EnumUtils;

import java.io.Serializable;
import java.util.Date;

/**
 * *************************************************************************************************
 * <p/>
 * 实现功能：调度器中的作业
 * <p>
 * ------------------------------------------------------------------------------------------------
 * 版本          变更时间             变更人                     变更原因
 * ------------------------------------------------------------------------------------------------
 * 1.0.00      2017-08-02 11:32      王锦光(wangj)                新建
 * <p/>
 * *************************************************************************************************
 */
public class JobTrigger implements Serializable {
    /**
     * Id标识
     */
    private String id;
    /**
     * 名称
     */
    private String name;
    /**
     * 作业组
     */
    private String group;
    /**
     * 作业状态
     */
    private JobState state;
    /**
     * 作业状态说明
     */
    private String stateRemark;
    /**
     * 下一次执行时间
     */
    private Date nextFireTime;
    /**
     * 调度周期
     */
    private String cronExpression;
    /**
     * 作业说明
     */
    private String remark;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public JobState getState() {
        return state;
    }

    public void setState(JobState state) {
        this.state = state;
    }

    public Date getNextFireTime() {
        return nextFireTime;
    }

    public void setNextFireTime(Date nextFireTime) {
        this.nextFireTime = nextFireTime;
    }

    public String getCronExpression() {
        return cronExpression;
    }

    public void setCronExpression(String cronExpression) {
        this.cronExpression = cronExpression;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getStateRemark() {
        return EnumUtils.getEnumItemRemark(JobState.class,state);
    }

    public void setStateRemark(String stateRemark) {
        this.stateRemark = stateRemark;
    }
}
