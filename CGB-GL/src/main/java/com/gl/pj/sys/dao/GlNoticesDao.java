package com.gl.pj.sys.dao;
import java.sql.Date;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.gl.pj.sys.entity.GlNotices;

@Mapper
public interface GlNoticesDao {
	
	//查询历史公告
	List<GlNotices> findPageObjects(@Param("id")Integer id, @Param("contacts")String contacts, @Param("createtime")Date createtime);
	
	//添加公告
	int insertNotices(GlNotices notices);
	
	//删除公告
	int deleteNotices(@Param("ids")Integer[] ids);
}
