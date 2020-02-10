package com.changhong.sei.task.dao;

import com.changhong.sei.core.dao.BaseEntityDao;
import com.changhong.sei.task.entity.JobHistory;
import org.springframework.stereotype.Repository;

/**
 * *************************************************************************************************
 * <p/>
 * 实现功能：作业执行历史数据访问接口
 * <p>
 * ------------------------------------------------------------------------------------------------
 * 版本          变更时间             变更人                     变更原因
 * ------------------------------------------------------------------------------------------------
 * 1.0.00      2017-07-21 9:44      王锦光(wangj)                新建
 * <p/>
 * *************************************************************************************************
 */
@Repository
public interface JobHistoryDao extends BaseEntityDao<JobHistory> {
}
