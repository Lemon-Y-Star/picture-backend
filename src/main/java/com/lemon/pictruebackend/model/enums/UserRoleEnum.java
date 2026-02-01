package com.lemon.pictruebackend.model.enums;

import lombok.Getter;
import org.apache.commons.lang3.StringUtils;

/**

 * 用户角色枚举类
 * @description : 用户角色枚举 定义系统中用户角色的枚举类型，包含用户和管理员两种角色
 * @Author : lemon
 * @create 2026/2/1 16:52
 */
@Getter
public enum UserRoleEnum {



    USER("用户", "user"),
    ADMIN("管理员", "admin");

    private final String text;

    private final String value;


    UserRoleEnum(String text, String value) {
        this.text = text;
        this.value = value;  // 将传入的text参数赋值给枚举实例的text属性
    }

    /**
     * 根据 value 获取枚举
     *
     * @param value 枚举值的value  // 将传入的value参数赋值给枚举实例的value属性
     * @return 枚举值
     */
    public static UserRoleEnum getEnumByValue(String value) {
        if (StringUtils.isEmpty(value)) {
            return null;
        }
        for (UserRoleEnum anEnum : UserRoleEnum.values()) {
            if (anEnum.value.equals(value)) {
                return anEnum;
            }
        }
        return null;
    }
}

