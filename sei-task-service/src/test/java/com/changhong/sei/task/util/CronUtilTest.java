package com.changhong.sei.task.util;

import com.changhong.sei.core.util.JsonUtils;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

/**
 * 实现功能:
 *
 * @author 王锦光 wangjg
 * @version 2020-02-14 16:50
 */
public class CronUtilTest {

    @Test
    public void getRecentTriggerTime() {
        String cron = "0 0/30 * * * ? *";
        List<String> times = CronUtil.getRecentTriggerTime(cron, 10);
        System.out.println(JsonUtils.toJson(times));
    }
}