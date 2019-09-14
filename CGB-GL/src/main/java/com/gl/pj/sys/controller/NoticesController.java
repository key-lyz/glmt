package com.gl.pj.sys.controller;
import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gl.pj.common.vo.JsonType;
import com.gl.pj.sys.entity.GlNotices;
import com.gl.pj.sys.service.GlNoticesService;

@Controller
@RequestMapping("/notice/")
@ResponseBody
public class NoticesController {

	@Autowired
	private GlNoticesService glNoticesService;
	
	/**查询历史公告**/
	@RequestMapping("doNoticeSys")
	public JsonType doShowNotices(Integer id, Date createtime, String contacts) {
		List<GlNotices> result = glNoticesService.findPageObjects(id, contacts, createtime);
		return new JsonType(result, id);
	}
	
	/**添加公告**/
	@RequestMapping("addNotices")
	public JsonType doInsertNotices(GlNotices notices) {
		int rows = glNoticesService.insertNotices(notices);
		System.out.println("rows:"+rows);
		return new JsonType("添加成功");
	}
	
	/**删除公告**/
	@RequestMapping("deleteNotices")
	public JsonType doDeleteNotices(Integer[] ids) {
		int rows = glNoticesService.deleteNotices(ids);
		System.out.println("rows:"+rows);
		return new JsonType("删除公告成功");
	}
	
}
