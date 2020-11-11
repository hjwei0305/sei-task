package com.changhong.sei.task.service.manager;

import com.changhong.sei.core.log.LogUtil;
import com.changhong.sei.core.util.JsonUtils;
import com.changhong.sei.notify.dto.EmailAccount;
import com.changhong.sei.notify.dto.EmailMessage;
import com.changhong.sei.notify.sdk.manager.NotifyManager;
import com.changhong.sei.task.entity.Job;
import com.changhong.sei.util.DateUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * 实现功能: 发送任务异常的通知邮件
 *
 * @author 王锦光 wangjg
 * @version 2020-11-11 9:44
 */
@Component
public class ErrorNotifyManager {
    @Autowired
    private NotifyManager notifyManager;

    /**
     * 发送异常消息邮件
     *
     * @param job     执行的任务
     * @param message 异常消息
     * @param e       异常
     */
    public void sendEmail(Job job, String message, Exception e) {
        if (Objects.isNull(job) || StringUtils.isBlank(message)) {
            return;
        }
        String address = job.getErrorNotifyEmail();
        if (StringUtils.isBlank(address)) {
            return;
        }
        try {
            // 构造发送邮件的内容
            EmailMessage emailMessage = new EmailMessage();
            emailMessage.setSubject("定时作业：" + job.getName() + "执行失败！");
            Map<String, Object> content = new HashMap<>();
            content.put("userName", "后台作业管理员");
            content.put("taskName", job.getName());
            content.put("executeTime", DateUtils.formatTime(DateUtils.getCurrentDateTime()));
            content.put("errorMessage", message);
            if (Objects.nonNull(e)) {
                content.put("exceptionMessage", e.toString());
            } else {
                content.put("exceptionMessage", "无");
            }
            emailMessage.setContentTemplateCode("TASK_ERROR_TEMPLATE");
            emailMessage.setContentTemplateParams(content);
            List<EmailAccount> receivers = new ArrayList<>();
            receivers.add(new EmailAccount("后台作业管理员", address));
            emailMessage.setReceivers(receivers);
            // 发送邮件
            notifyManager.sendEmail(emailMessage);
        } catch (Exception ex) {
            LogUtil.error("发送后台作业异常消息邮件发生异常！", ex);
        }
    }
}
