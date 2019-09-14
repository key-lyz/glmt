package com.gl.pj.sys.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.gl.pj.common.vo.GlGoodsVo;
import com.gl.pj.sys.entity.GlGoods;
/**
 * 
 * @商品Dao层
 * @author Chenjinbo
 *
 */
@Mapper
public interface GlGoodsDao {
	//查询商品数量
	int findObjectCount(@Param("classid")Integer classid,@Param("key") String key);
	//查询所有商品
	//List<GlGoods> findGoods(@Param("classid")Integer classid);
	List<GlGoodsVo> findObject(@Param("classid")Integer classid,@Param("key") String key,@Param("page")Integer page,@Param("limit")Integer limit);
	//添加商品
	int InsertObject(GlGoods goods);
	//删除商品
	@Delete("delete from gl_goods where id =#{id}")
 	int DeleteObject(Integer id);
	//修改商品
	@Update("update gl_goods set name=#{name},image=#{image},classid=#{classid},price=#{price},remark=#{remark} where id =#{id} ")
	int UpdateObject(GlGoods goods);
	//根据商品ID查询商品指定信息
	@Select("select ${value} from gl_goods where id= #{id}")
	Object findGoodsValueByid(@Param("value")String value,Integer id);
	//根据ID查询商品信息
	@Select("select * from gl_goods where id=#{id}")
	GlGoods findGoodsObject(Integer id);
	 
	
}