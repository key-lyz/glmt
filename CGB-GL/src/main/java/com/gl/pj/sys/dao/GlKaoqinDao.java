package com.gl.pj.sys.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.gl.pj.sys.entity.GlKaoqin;

@Mapper
public interface GlKaoqinDao {
	
	//查询考勤总数量
	int findObjectCount(@Param("key")String key,@Param("riqi")String riqi);
	//查询考勤列表
	List<GlKaoqin> findObject(@Param("key")String key,@Param("limit")Integer limit ,@Param("page")Integer page,@Param("riqi")String riqi);
 
}
