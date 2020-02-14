package com.changhong.sei.task.service;

import com.changhong.sei.core.test.BaseUnitTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 实现功能:
 *
 * @author 王锦光 wangjg
 * @version 2020-02-13 15:58
 */
public class ScheduleJobServiceTest extends BaseUnitTest {
    @Autowired
    private ScheduleJobService service;
    @Test
    public void removeAllJob() {
        service.removeAllJob();
    }
}