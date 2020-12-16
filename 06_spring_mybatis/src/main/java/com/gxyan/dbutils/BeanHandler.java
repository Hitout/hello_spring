package com.gxyan.dbutils;

import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;

/**
 * @author gxyan
 */
public class BeanHandler implements ResultSetHandler {

    private Class clazz;

    public BeanHandler(Class clazz) {
        this.clazz = clazz;
    }

    @Override
    public Object handler(ResultSet rs) {
        try {
            if (!rs.next()) {
                return null;
            }
            // 创建封装结果集的bean
            Object bean = clazz.newInstance();

            // 得到结果集的元数据，以获取结果集的信息
            ResultSetMetaData meta = rs.getMetaData();
            int count = meta.getColumnCount();
            for (int i = 0; i < count; i++) {
                // 获取到结果集每列的列名
                String name = meta.getColumnName(i+1);
                Object value = rs.getObject(name);

                // 反射出bean上与列名相应的属性
                Field f = bean.getClass().getDeclaredField(name);
                f.setAccessible(true);
                f.set(bean, value);
            }
            return bean;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
