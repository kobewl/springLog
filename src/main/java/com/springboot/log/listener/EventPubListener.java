package com.springboot.log.listener;

import com.springboot.log.entity.SysLog;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import javax.annotation.Resource;

/**
 * @author wangliang
 *
 * 事件发布类
 */
@Component
@Slf4j
public class EventPubListener {

    @Resource
    private ApplicationContext applicationContext;

    /**
     * 发布系统日志事件
     */
    public void pushListener(SysLog logEvent) {
        try {
            log.info("发布系统日志: {}", logEvent);
            applicationContext.publishEvent(logEvent);
            log.info("系统日志事件发布成功");
        } catch (Exception e) {
            log.error("系统日志事件发布失败", e);
        }
    }

}

