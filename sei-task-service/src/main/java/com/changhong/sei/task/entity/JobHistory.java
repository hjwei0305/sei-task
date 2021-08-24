package com.changhong.sei.task.entity;

import com.changhong.sei.core.entity.BaseEntity;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;
import java.util.Date;

/**
 * *************************************************************************************************
 * <p/>
 * 实现功能：作业执行历史实体
 * <p>
 * ------------------------------------------------------------------------------------------------
 * 版本          变更时间             变更人                     变更原因
 * ------------------------------------------------------------------------------------------------
 * 1.0.00      2017/7/20 11:06      秦有宝                     新建
 * <p/>
 * *************************************************************************************************
 */
@Access(AccessType.FIELD)
@Entity
@Table(name="job_history")
@DynamicInsert
@DynamicUpdate
public class JobHistory extends BaseEntity {

    /**
     * 作业定义
     */
    @ManyToOne
    @JoinColumn(name = "job_id", nullable = false)
    @NotFound(action= NotFoundAction.IGNORE)
    private Job job;

    /**
     * 执行开始时间
     */
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "start_time", nullable = false, updatable = false)
    @JsonSerialize
    private Date startTime;

    /**
     * 耗时(ms)
     */
    @Column(name = "elapsed", nullable = false)
    private Long elapsed = 0L;

    /**
     * 是否执行成功
     */
    @Column(name = "successful",nullable = false)
    private Boolean successful = Boolean.FALSE;

    /**
     * 执行消息
     */
    @Column(name = "message")
    private String message;

    /**
     * 异常消息
     */
    @Column(name = "exception_message")
    private String exceptionMessage;

    public Job getJob() {
        return job;
    }

    public void setJob(Job job) {
        this.job = job;
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
