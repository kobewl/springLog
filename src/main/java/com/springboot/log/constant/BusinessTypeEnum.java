package com.springboot.log.constant;

import lombok.Getter;

/**
 * @author wangliang
 */

@Getter
public enum BusinessTypeEnum {

    /**
     * 其它
     */
    OTHER(0,"其它"),

    /**
     * 新增
     */
    INSERT(1,"新增"),

    /**
     * 修改
     */
    UPDATE(2,"修改"),

    /**
     * 删除
     */
    DELETE(3,"删除");

    private final Integer code;

    private final String message;

    BusinessTypeEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

}

