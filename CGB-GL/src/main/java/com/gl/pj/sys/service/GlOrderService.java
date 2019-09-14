package com.gl.pj.sys.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.gl.pj.common.vo.GlOrderVo;

public interface GlOrderService {
 
	//查询订单数量
	int findCount(@Param("dingdanhao")String dingdanhao);
	//查询所有订单
	List<GlOrderVo> findAll(@Param("dingdanhao")String dingdanhao,Integer page,Integer limit);
	
	
	

}
