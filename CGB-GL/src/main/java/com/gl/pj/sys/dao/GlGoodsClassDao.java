package com.gl.pj.sys.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.gl.pj.sys.entity.GlGoodsClass;
/**
 * @商品分类Dao层
 * 
 * @author Chenjinbo
 *
 */
@Mapper
public interface GlGoodsClassDao {
	//查询分类数量
	int findObjectCount(@Param("pid")Integer pid);
	//查询所有分类
	List<GlGoodsClass> findObject(@Param("pid")Integer pid);
 	//添加分类
	int InsertObject(GlGoodsClass GoodsClass);
	//修改分类
 	int UpdateObject(GlGoodsClass GoodsClass);
	//删除分类
	@Delete("delete from gl_goods_class where id =#{id}")
	int DeleteObject(Integer id);
	
}