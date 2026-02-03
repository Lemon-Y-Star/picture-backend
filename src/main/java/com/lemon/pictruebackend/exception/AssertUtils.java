package com.lemon.pictruebackend.exception;

import cn.hutool.core.util.StrUtil;
import org.springframework.util.CollectionUtils;

import java.util.Collection;

/**
 * @description 断言工具类，提供各种条件检查的方法，用于在程序运行时验证条件是否满足，不满足则抛出异常
 * @Author : lemon
 * @create 2026/2/1 15:14
 */
public class AssertUtils {

    /**
     * 检查条件是否为真，如果为假则抛出指定的运行时异常
     *
     * @param condition 要检查的条件
     * @param runtimeException 如果条件为假时抛出的运行时异常
     */
    public static void isTrue(boolean condition, RuntimeException runtimeException) {
        if (!condition) {
            throw runtimeException;
        }
    }

    /**
     * 检查条件是否为真，如果为假则抛出业务异常
     *
     * @param condition 要检查的条件
     * @param errorCode 错误码，用于构造业务异常
     */
    public static void isTrue(boolean condition, ErrorCode errorCode) {
        isTrue(condition, new BusinessException(errorCode));
    }

    /**
     * 检查条件是否为真，如果为假则抛出带有自定义消息的业务异常
     *
     * @param condition 要检查的条件
     * @param errorCode 错误码，用于构造业务异常
     * @param message 异常的自定义消息
     */
    public static void isTrue(boolean condition, ErrorCode errorCode, String message) {
        isTrue(condition, new BusinessException(errorCode, message));
    }

    /**
     * 检查字符串是否不为空（不为null且不为空字符串）
     * @param condition 需要检查的字符串条件
     * @param errorCode 如果条件为空则抛出的业务异常对应的错误码
     * @throws BusinessException 当传入的字符串为null或空字符串时抛出
     */
    public static void isNotBlank(String condition, ErrorCode errorCode) {
        isTrue(!StrUtil.isBlank(condition), new BusinessException(errorCode));
    }

    /**
     * 检查字符串是否不为空（不为null且不为空字符串）
     * @param condition 需要检查的字符串条件
     * @param errorCode 如果条件为空则抛出的业务异常对应的错误码
     * @param message 自定义错误消息
     * @throws BusinessException 当传入的字符串为null或空字符串时抛出
     */
    public static void isNotBlank(String condition, ErrorCode errorCode, String message) {
        isTrue(!StrUtil.isBlank(condition), new BusinessException(errorCode, message));
    }

    /**
     * 检查集合是否不为空
     * @param collection 需要检查的集合对象
     * @param errorCode 如果集合为空时抛出的业务异常错误码
     * @throws IllegalArgumentException 当errorCode为null时抛出
     * @throws BusinessException 当集合为空时抛出业务异常
     */
    public static void isNotEmpty(Collection<?> collection, ErrorCode errorCode) {
        if (errorCode == null) {
            throw new IllegalArgumentException("ErrorCode cannot be null");
        }
        isTrue(!CollectionUtils.isEmpty(collection), new BusinessException(errorCode));
    }

    /**
     * 检查集合是否不为空
     * @param collection 需要检查的集合对象
     * @param errorCode 如果集合为空时抛出的业务异常错误码
     * @param message 自定义错误消息
     * @throws IllegalArgumentException 当errorCode为null时抛出
     * @throws BusinessException 当集合为空时抛出业务异常
     */
    public static void isNotEmpty(Collection<?> collection, ErrorCode errorCode, String message) {
        if (errorCode == null) {
            throw new IllegalArgumentException("ErrorCode cannot be null");
        }
        isTrue(!CollectionUtils.isEmpty(collection), new BusinessException(errorCode, message));
    }
        /**
     * 验证条件是否为false，如果条件为true则抛出业务异常
     * @param condition 待验证的布尔条件
     * @param errorCode 错误码对象
     * @throws BusinessException 当条件为true时抛出的业务异常
     */
    public static void isFalse(boolean condition, ErrorCode errorCode) {
        isTrue(!condition, new BusinessException(errorCode));
    }

    /**
     * 验证条件是否为false，如果条件为true则抛出带有自定义消息的业务异常
     * @param condition 待验证的布尔条件
     * @param errorCode 错误码对象
     * @param message 自定义错误消息
     * @throws BusinessException 当条件为true时抛出的业务异常
     */
    public static void isFalse(boolean condition, ErrorCode errorCode, String message) {
        isTrue(!condition, new BusinessException(errorCode, message));
    }

    /**
     * 验证字符串是否为空白，如果字符串不为空白则抛出业务异常
     * @param str 待验证的字符串
     * @param errorCode 错误码对象
     * @throws BusinessException 当字符串不为空白时抛出的业务异常
     */
    public static void isBlank(String str, ErrorCode errorCode) {
        isTrue(StrUtil.isBlank(str), new BusinessException(errorCode));
    }

    /**
     * 验证字符串是否为空白，如果字符串不为空白则抛出带有自定义消息的业务异常
     * @param str 待验证的字符串
     * @param errorCode 错误码对象
     * @param message 自定义错误消息
     * @throws BusinessException 当字符串不为空白时抛出的业务异常
     */
    public static void isBlank(String str, ErrorCode errorCode, String message) {
        isTrue(StrUtil.isBlank(str), new BusinessException(errorCode, message));
    }

    /**
     * 验证集合是否为空，如果集合不为空则抛出业务异常
     * @param collection 待验证的集合
     * @param errorCode 错误码对象
     * @throws BusinessException 当集合不为空时抛出的业务异常
     */
    public static void isEmpty(Collection<?> collection, ErrorCode errorCode) {
        isTrue(CollectionUtils.isEmpty(collection), new BusinessException(errorCode));
    }

    /**
     * 验证集合是否为空，如果集合不为空则抛出带有自定义消息的业务异常
     * @param collection 待验证的集合
     * @param errorCode 错误码对象
     * @param message 自定义错误消息
     * @throws BusinessException 当集合不为空时抛出的业务异常
     */
    public static void isEmpty(Collection<?> collection, ErrorCode errorCode, String message) {
        isTrue(CollectionUtils.isEmpty(collection), new BusinessException(errorCode, message));
    }
    /**
     * 验证对象是否不为null，如果对象为null则抛出业务异常
     * @param obj 待验证的对象
     * @param errorCode 错误码对象
     * @throws BusinessException 当对象为null时抛出的业务异常
     */
    public static void isNotNull(Object obj, ErrorCode errorCode) {
        isTrue(obj != null, new BusinessException(errorCode));
    }

    /**
     * 验证对象是否不为null，如果对象为null则抛出带有自定义消息的业务异常
     * @param obj 待验证的对象
     * @param errorCode 错误码对象
     * @param message 自定义错误消息
     * @throws BusinessException 当对象为null时抛出的业务异常
     */
    public static void isNotNull(Object obj, ErrorCode errorCode, String message) {
        isTrue(obj != null, new BusinessException(errorCode, message));
    }

    /**
     * 验证对象是否为null，如果不为null则抛出业务异常
     * @param obj 待验证的对象
     * @param errorCode 错误码对象
     * @throws BusinessException 当对象不为null时抛出的业务异常
     */
    public static void isNull(Object obj, ErrorCode errorCode) {
        isTrue(obj == null, new BusinessException(errorCode));
    }

    /**
     * 验证对象是否为null，如果不为null则抛出带有自定义消息的业务异常
     * @param obj 待验证的对象
     * @param errorCode 错误码对象
     * @param message 自定义错误消息
     * @throws BusinessException 当对象不为null时抛出的业务异常
     */
    public static void isNull(Object obj, ErrorCode errorCode, String message) {
        isTrue(obj == null, new BusinessException(errorCode, message));
    }


}
