package com.springboot.log.listener;

import com.springboot.log.entity.SysLog;
import com.springboot.log.service.SysLogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author wangliang
 *
 * 监听器类
 */
@Slf4j
@Component
public class MyEventListener {

    @Resource
    private SysLogService logService;

    // 开启异步
    @Async("executor")
    // 开启监听
    @EventListener(SysLog.class)
    public void saveSysLog(SysLog event) {
        log.info("=====即将异步保存到数据库======");
        try {
            logService.saveLog(event);
            log.info("=====日志保存成功======");
        } catch (Exception e) {
            log.error("=====日志保存失败======", e);
        }
    }

}