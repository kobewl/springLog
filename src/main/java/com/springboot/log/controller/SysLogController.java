package com.springboot.log.controller;

import com.springboot.log.annotation.Log;
import com.springboot.log.constant.BusinessTypeEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

/**
 * 操作日志记录(SysLog)表控制层
 *
 * @author wangliang
 */
@Slf4j
@RestController
@RequestMapping("Log")
public class SysLogController {

    @Log(method = "测试添加方法", title = "测试", businessType = BusinessTypeEnum.INSERT)
    @GetMapping("/saveLog")
    public void saveLog() {
        log.info("测试日志存储是否成功！");
    }

}


