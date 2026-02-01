package com.lemon.pictruebackend.common;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

/**
 * @description : 删除请求
 * @Author : lemon
 * @create 2026/2/1 16:24
 */
@Data
public class DeleteRequest implements Serializable {

    /**
     * id
     */
    private Long id;

    @Serial
    private static final long serialVersionUID = 1L;
}

