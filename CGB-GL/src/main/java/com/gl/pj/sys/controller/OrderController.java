package com.gl.pj.sys.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gl.pj.common.vo.GlOrderVo;
import com.gl.pj.common.vo.JsonType;
import com.gl.pj.sys.service.GlGoodsService;
import com.gl.pj.sys.service.GlOrderService;

@Controller
@RequestMapping("/order/")
@ResponseBody
public class OrderController {
	 
	@Autowired
	GlOrderService orderService; //注入订单对象
	@Autowired
	GlGoodsService goodsService;//注入商品对象
	/**
	 * 
	 * @查询订单列表
	 * @param key 搜索订单号
	 * @return
	 */
	@RequestMapping("doObject")
	public JsonType doObject(String key,Integer page,Integer limit) { 
		int total = orderService.findCount(key);
		    List<GlOrderVo> list = orderService.findAll(key, page, limit);
		return new JsonType(list, total);
		
	}
	 


}
