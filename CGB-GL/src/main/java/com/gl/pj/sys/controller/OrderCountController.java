package com.gl.pj.sys.controller;

import java.util.List;

import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
/**
 * 
 * @订单统计
 * @author Chenjinbo
 *
 */
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gl.pj.common.vo.JsonType;
import com.gl.pj.common.vo.OrderCountVo;
import com.gl.pj.sys.dao.OrderCountDao;
import com.gl.pj.sys.entity.GlGoodsClass;
import com.gl.pj.sys.entity.GlUser;
@Controller
@RequestMapping("/order/")
@ResponseBody
public class OrderCountController {
    @Autowired
    OrderCountDao dao;
	
	/**
	 * 
	 * @获取订单统计列表
	 * @return
	 */
	@RequestMapping("doCount")
	public JsonType doCount(Integer page,Integer limit,String key,String year) { 
		
		/**每页显示数*/
		int pageSize=limit;
 		/**开始位置*/
 		int startIndex=(page-1)*pageSize;
 		

		int count = dao.findOrderGoodsCount(key,year);
		List<OrderCountVo> list = dao.findOrderCount( startIndex, pageSize, key, year);
		JsonType json = new JsonType(list, count);
		return  json;
	}
	
 

}
 