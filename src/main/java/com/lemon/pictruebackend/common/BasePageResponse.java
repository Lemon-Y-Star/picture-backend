package com.lemon.pictruebackend.common;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 分页响应类，用于封装分页查询结果
 * @description 提供统一的分页响应格式，包含当前页、每页大小、总记录数和分页数据
 * @Author : lemon
 * @create 2026/2/3 23:49
 */
@Data
public class BasePageResponse<T> implements Serializable {

    @Serial
    private static final long serialVersionUID = 1294031964985314807L;
    // 当前页码，从1开始
    private int currentPage;

    // 每页记录数
    private int pageSize;

    // 总记录数
    private long total;

    // 当前页的数据列表
    private List<T> records;

    /**
     * 全参构造方法
     * @param currentPage 当前页码
     * @param pageSize 每页记录数
     * @param total 总记录数
     * @param records 当前页的数据列表
     */
    public BasePageResponse(int currentPage, int pageSize, long total, List<T> records) {
        this.currentPage = currentPage;
        this.pageSize = pageSize;
        this.total = total;
        this.records = records;
    }

    /**
     * 部分参数构造方法，默认记录为空列表
     * @param currentPage 当前页码
     * @param pageSize 每页记录数
     * @param total 总记录数
     */
    public BasePageResponse(int currentPage, int pageSize, long total) {
        this(currentPage, pageSize, total, new ArrayList<>());
    }

    /**
     * 默认构造方法
     */
    public BasePageResponse() {
        this(1, 10, 0, new ArrayList<>());
    }

    /**
     * 手动分页方法
     * @param list 原始数据列表
     * @param currentPage 当前页码，从1开始
     * @param pageSize 每页记录数
     * @param <T> 数据类型
     * @return 分页后的数据对象
     */
    public static <T> BasePageResponse<T> paginate(List<T> list, int currentPage, int pageSize) {
        if (list == null) {
            return new BasePageResponse<>(currentPage, pageSize, 0, new ArrayList<>());
        }

        int total = list.size();
        int fromIndex = (currentPage - 1) * pageSize;
        
        // 如果起始索引超出范围，返回空列表
        if (fromIndex >= total) {
            return new BasePageResponse<>(currentPage, pageSize, total, new ArrayList<>());
        }
        
        int toIndex = Math.min(fromIndex + pageSize, total);
        List<T> records = list.subList(fromIndex, toIndex);
        
        return new BasePageResponse<>(currentPage, pageSize, total, new ArrayList<>(records));
    }

    /**
     * 获取总页数
     * @return 总页数
     */
    public int getTotalPages() {
        return (int) Math.ceil((double) total / pageSize);
    }

    /**
     * 判断是否有下一页
     * @return 是否有下一页
     */
    public boolean hasNextPage() {
        return currentPage < getTotalPages();
    }

    /**
     * 判断是否有上一页
     * @return 是否有上一页
     */
    public boolean hasPreviousPage() {
        return currentPage > 1;
    }
}
