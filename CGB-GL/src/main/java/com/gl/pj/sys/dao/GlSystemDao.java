package com.gl.pj.sys.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.gl.pj.sys.entity.GlSystem;
/**
 * 
 * @系统设置Dao层
 * @author Chenjinbo
 *
 */
@Mapper
public interface GlSystemDao {
	//查询系统设置
	@Select("select * from gl_system where id =1")
	GlSystem findSystem();
	//修改系统设置
	@Update("update gl_system set sitename=#{sitename},sitekey=#{sitekey},sitedesc=#{sitedesc},sitenotice=#{sitenotice} where id =1")
	int updateSystem(GlSystem system);
}
