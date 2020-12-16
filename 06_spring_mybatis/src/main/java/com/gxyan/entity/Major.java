package com.gxyan.entity;

import lombok.Data;

import java.util.List;

/**
 * @author gxyan
 * @date 2020/12/13
 */
@Data
public class Major {
    /**
     * 专业ID
     */
    private Integer majorId;
    /**
     * 专业名称
     */
    private String majorName;
    private List<Student> students;
}
