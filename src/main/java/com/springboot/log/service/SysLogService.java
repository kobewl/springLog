package com.springboot.log.service;

import com.springboot.log.entity.SysLog;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * 操作日志记录(SysLog)表服务接口
 *
 * @author wangliang
 */
public interface SysLogService  extends IService<SysLog> {

    Integer saveLog(SysLog Log);

}


