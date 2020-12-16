package com.gxyan.dbutils;

import java.sql.ResultSet;

/**
 * 结果集处理策略接口
 * @author gxyan
 */
public interface ResultSetHandler {
    Object handler(ResultSet rs);
}
