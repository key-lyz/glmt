package com.gl.pj.sys.service;

import java.util.List;

import com.gl.pj.sys.entity.GlGoodsCart;
/**
 * @购物车业务接口
 * @author Chenjinbo
 *
 */
public interface GlGoodsCartService {
	//查询购物车数量
	int findCount(Integer userid);
	//查询购物车数据
	List<GlGoodsCart> findObject(Integer userid);
	//添加数据进购物车
	int insertObject(GlGoodsCart cart);
	//删除购物车
	int deleteObject(Integer id);
	//增删数量
	int updateObject(Integer id,Integer status);
	
	//结算购物车
	int insertOrder(Integer[] cartid);
	 
 
	
	
}
