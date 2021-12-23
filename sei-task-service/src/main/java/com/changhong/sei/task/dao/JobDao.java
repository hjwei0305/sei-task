package com.changhong.sei.task.dao;

import com.changhong.sei.core.dao.BaseEntityDao;
import com.changhong.sei.task.entity.Job;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * *************************************************************************************************
 * <p/>
 * 实现功能：作业定义数据访问接口
 * <p>
 * ------------------------------------------------------------------------------------------------
 * 版本          变更时间             变更人                     变更原因
 * ------------------------------------------------------------------------------------------------
 * 1.0.00      2017-07-21 9:41      王锦光(wangj)                新建
 * <p/>
 * *************************************************************************************************
 */
@Repository
public interface JobDao extends BaseEntityDao<Job> {

    /**
     * 判断是否已经存在路径+方法+租户相同的作业
     * @param id 作业Id
     * @param appModuleCode 应用代码
     * @param apiPath 服务路径
     * @param methodName 方法名
     * @param exeTenantCode 执行人租户代码
     * @param inputParam 输入参数
     * @return 是否存在
     */
    @Query("select j.id from Job j where j.id<>?1 and j.appModuleCode=?2 and j.apiPath=?3 and j.methodName=?4 and j.exeTenantCode=?5 and j.inputParam=?6")
    String checkPath(String id, String appModuleCode, String apiPath, String methodName, String exeTenantCode, String inputParam);
}
