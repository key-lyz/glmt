package com.gl.pj.sys.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.gl.pj.sys.entity.GlOrder;

@Mapper
public interface EchartsDao {

	@Select("select * from gl_order")
     List<GlOrder> repeatsingle();

}
