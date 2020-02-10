package com.changhong.sei.task.service;

import com.changhong.sei.core.dao.BaseEntityDao;
import com.changhong.sei.core.service.BaseEntityService;
import com.changhong.sei.task.dao.JobHistoryDao;
import com.changhong.sei.task.entity.JobHistory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * *************************************************************************************************
 * <p/>
 * 实现功能：作业执行历史业务逻辑实现
 * <p>
 * ------------------------------------------------------------------------------------------------
 * 版本          变更时间             变更人                     变更原因
 * ------------------------------------------------------------------------------------------------
 * 1.0.00      2017-07-21 9:55      王锦光(wangj)                新建
 * <p/>
 * *************************************************************************************************
 */
@Service
public class JobHistoryService extends BaseEntityService<JobHistory> {
    @Autowired
    private JobHistoryDao dao;

    @Override
    protected BaseEntityDao<JobHistory> getDao() {
        return dao;
    }
}
