package com.gxyan.swagger.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @author gxyan
 * @date 2020/5/10 23:01
 */
@Data
public class User implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long id;
    private String name;
    private Integer age;
}
