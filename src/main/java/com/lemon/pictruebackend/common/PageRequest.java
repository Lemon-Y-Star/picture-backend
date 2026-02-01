package com.lemon.pictruebackend.common;

import lombok.Data;

/**
 * @description : 分页请求
 * @Author : lemon
 * @create 2026/2/1 15:12
 */
@Data
public class PageRequest {

    /**
     * 当前页号
     */
    private int current = 1;

    /**
     * 页面大小
     */
    private int pageSize = 10;

    /**
     * 排序字段
     */
    private String sortField;

    /**
     * 排序顺序（默认降序）
     */
    private String sortOrder = "descend";
}

