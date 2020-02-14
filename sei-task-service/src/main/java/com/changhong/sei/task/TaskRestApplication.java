package com.changhong.sei.task;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * <strong>实现功能:</strong>
 * <p>REST服务主程序</p>
 *
 * @version 1.0.1 2019-12-18 10:41
 */
@SpringBootApplication
@EnableFeignClients(basePackages = {"com.changhong.sei.task.service.client"})
public class TaskRestApplication extends SpringBootServletInitializer {
    /**
     * Configure the application. Normally all you would need to do is to add sources
     * (e.g. config classes) because other settings have sensible defaults. You might
     * choose (for instance) to add default command line arguments, or set an active
     * Spring profile.
     *
     * @param builder a builder for the application context
     * @return the application builder
     * @see SpringApplicationBuilder
     */
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(TaskRestApplication.class);
    }
    public static void main(String[] args) {
        SpringApplication.run(TaskRestApplication.class, args);
    }
}
