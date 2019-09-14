package com.gl.pj.sys.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
/**
 * 
 * @通用页面控制层
 * @author Chenjinbo
 *
 */
@Controller
@RequestMapping("/")
public class PageController {
	/**
	 * 
	 * @直接根据Url返回对应页面
	 * @param page
	 * @return
	 */
	@RequestMapping("{page}")
	public String PageCommon(String page) {
 		return page;
	}
	
 
 
	
}
