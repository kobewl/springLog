package com.springboot.log.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.springboot.log.dao.SysLogDao;
import com.springboot.log.entity.SysLog;
import com.springboot.log.service.SysLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 操作日志记录(SysLog)表服务实现类
 *
 * @author wangliang
 */
@Service("LogService")
public class SysLogServiceImpl  extends ServiceImpl<SysLogDao, SysLog> implements SysLogService {

    @Autowired
    private SysLogDao LogDao;

    @Override
    public Integer saveLog(SysLog Log) {
        return LogDao.insert(Log);
    }
}


