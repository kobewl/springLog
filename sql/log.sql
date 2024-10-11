create database log;

use log;

create table sys_log
(
    id             bigint auto_increment comment '日志主键'
        primary key,
    title          varchar(50)  default '' null comment '模块标题',
    business_type  int          default 0  null comment '业务类型（0其它 1新增 2修改 3删除）',
    method         varchar(100) default '' null comment '方法名称',
    request_method varchar(10)  default '' null comment '请求方式',
    oper_name      varchar(50)  default '' null comment '操作人员',
    oper_url       varchar(255) default '' null comment '请求URL',
    oper_ip        varchar(128) default '' null comment '主机地址',
    oper_time      datetime                null comment '操作时间'
)
    comment '操作日志记录' charset = utf8
                           row_format = DYNAMIC;