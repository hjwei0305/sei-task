package com.changhong.sei.task.util;

import org.quartz.TriggerUtils;
import org.quartz.impl.triggers.CronTriggerImpl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * 实现功能: Cron表达式工具类
 *
 * @author 王锦光 wangjg
 * @version 2020-02-14 16:45
 */
public class CronUtil {
    /**
     * 获取最近N次的执行时间清单
     * @param cron cron表达式
     * @param numTimes N次
     * @return 执行时间清单
     */
    public static List<String> getRecentTriggerTime(String cron, int numTimes) {
        List<String> list = new LinkedList<>();
        try {
            CronTriggerImpl cronTriggerImpl = new CronTriggerImpl();
            cronTriggerImpl.setCronExpression(cron);
            // 这个是重点，一行代码搞定
            List<Date> dates = TriggerUtils.computeFireTimes(cronTriggerImpl, null, numTimes);
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            for (Date date : dates) {
                list.add(dateFormat.format(date));
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return list;
    }
}
