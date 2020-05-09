package com.gxyan.aoplog.mapper;

import com.gxyan.aoplog.entity.SysLog;

public interface SysLogDao {
    int deleteByPrimaryKey(Integer id);

    int insert(SysLog record);

    int insertSelective(SysLog record);

    SysLog selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SysLog record);

    int updateByPrimaryKey(SysLog record);
}