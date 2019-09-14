package com.gl.pj.sys.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.gl.pj.common.vo.GlOrderVo;
import com.gl.pj.sys.entity.GlOrder;
import com.gl.pj.sys.entity.GlOrderGoods;

@Mapper
public interface GlOrderDao {
 
	
	//查询订单数量
	int findOrderCount(@Param("dingdanhao")String dingdanhao,@Param("userid") Integer userid);
 
	
	
	//根据订单号查询订单商品
	@Select("select * from gl_order_goods where dingdanhao =#{dingdanhao}")
	List<GlOrderGoods> findOrderGoodsObject(String dingdanhao);
	//查询用户所有订单
	List<GlOrderVo> findUserOrder(Integer userid,String dingdanhao,Integer page,Integer limit);
}