package com.gl.pj.sys.controller;

import java.util.List;

import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gl.pj.common.vo.ClockDate;
import com.gl.pj.common.vo.JsonType;
import com.gl.pj.sys.entity.GlUser;
import com.gl.pj.sys.service.CheckTimeService;


@RestController
@RequestMapping(value = "checkTime")
public class CheckTimeController {

	@Autowired
	private CheckTimeService checkTimeService;
	
	@RequestMapping(value = "doCheckTime")
	public JsonType doCheckTime() {
		GlUser user = (GlUser) SecurityUtils.getSubject().getPrincipal();//集成shiro
		Integer userid = user.getId();
		List<ClockDate> list = checkTimeService.findClockDateById(userid);
		
		return new JsonType(list, userid);
		
	}
}
