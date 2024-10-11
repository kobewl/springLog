package com.springboot.log.annotation;


import com.springboot.log.constant.BusinessTypeEnum;

import java.lang.annotation.*;

/**
 * 自定义操作日志记录注解
 * @author wangliang
 */
@Target(ElementType.METHOD) // 表明该注解作用于方法上
@Retention(RetentionPolicy.RUNTIME) // 注解保留时间为运行时
@Documented // 表明该注解可以被 javadoc 或类似的工具文档化
public @interface Log {

    /**
     * 描述
     */
    String value() default "";

    /**
     * 模块
     */
    String title() default "测试模块";

    /**
     * 方法名称
     */
    String method() default "测试方法";

    /**
     * 功能
     */
    BusinessTypeEnum businessType() default BusinessTypeEnum.OTHER;

}