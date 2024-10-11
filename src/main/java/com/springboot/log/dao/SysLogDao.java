package com.springboot.log.dao;

import com.springboot.log.entity.SysLog;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

/**
 * 操作日志记录(SysLog)表数据库访问层
 *
 * @author wangliang
 */
@Repository
public interface SysLogDao extends BaseMapper<SysLog> {

}


