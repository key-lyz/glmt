package com.gl.pj.sys.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.gl.pj.common.vo.OrderCountVo;
import com.gl.pj.sys.entity.GlGoodsCart;
import com.gl.pj.sys.entity.GlOrder;
import com.gl.pj.sys.entity.GlOrderGoods;
/**
 * 
 * @订单统计Dao
 * @author Chenjinbo
 *
 */
@Mapper
public interface OrderCountDao {
	
	//获取商品数量
 	int findOrderGoodsCount(String key,String year);
	
	//订单统计
	List<OrderCountVo> findOrderCount(Integer page,Integer limit,String key,String year);
	 
}