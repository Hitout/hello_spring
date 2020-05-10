package com.gxyan.cache.entity;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * student
 * @author gxyan
 */
@Data
public class Student implements Serializable {
    /**
     * 学号
     */
    private String no;

    /**
     * 姓名
     */
    private String name;

    /**
     * 性别
     */
    private String sex;

    /**
     * 创建时间
     */
    private Date createTime;

    private static final long serialVersionUID = 1L;
}