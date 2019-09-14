package com.gl.pj.sys.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gl.pj.common.vo.JsonType;
import com.gl.pj.sys.entity.GlSystem;
import com.gl.pj.sys.service.GlSystemService;
/**
 * 
 * @系统设置层控制类
 * @author Chenjinbo
 *
 */
@Controller
@RequestMapping("/ajax/")
@ResponseBody
public class SystemController {
	@Autowired
	GlSystemService systemservice;  //系统设置层对象
 
	
	@RequestMapping("system")
	public JsonType doUpdateSystem(GlSystem system,Model model) { 
		systemservice.updateSystem(system);
  		JsonType json = new JsonType("设置成功");
		return  json;
	}
	
	@RequestMapping("doSystem")
	public JsonType doSystem() { 
		GlSystem list = systemservice.findSystem();
		JsonType json = new JsonType(list,1);
		return  json;
	}

}
