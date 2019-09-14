package com.gl.pj.sys.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.gl.pj.sys.entity.GlStore;
/**
 * 
 * @店铺Dao层
 * @author Chenjinbo
 *
 */
@Mapper
public interface GlStoreDao {
	//查询店铺数量
	int findObjectCount(@Param("storename")String storename);
	//查询店铺列表
	List<GlStore> findObject(@Param("storename")String storename,Integer page,Integer limit);
	//添加店铺
	int insertObject(GlStore store);
	//修改店铺
	int updateObject(GlStore store);
	//删除店铺
	int deleteObject(@Param("ids")Integer[] ids);
}
