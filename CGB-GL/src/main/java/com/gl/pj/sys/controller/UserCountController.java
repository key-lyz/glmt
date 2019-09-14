package com.gl.pj.sys.controller;

import java.util.List;

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
import com.gl.pj.common.vo.UserCountVo;
import com.gl.pj.sys.dao.UserCountDao;
@Controller
@RequestMapping("/user/")
@ResponseBody
public class UserCountController {
    @Autowired
    UserCountDao dao;
	
	/**
	 * 
	 * @获取订单统计列表
	 * @return
	 */
	@RequestMapping("doCount")
	public JsonType doCount(Integer page,Integer limit,String key,String riqi) { 
		
		System.out.println(key);
		/**每页显示数*/
		int pageSize=limit;
 		/**开始位置*/
 		int startIndex=(page-1)*pageSize;
 		

		int count = dao.findUserCount(key, riqi);
		List<UserCountVo> list = dao.findUserMoneyCount(page, limit, key, riqi);
		JsonType json = new JsonType(list, count);
		return  json;
	}
	
 

}
 