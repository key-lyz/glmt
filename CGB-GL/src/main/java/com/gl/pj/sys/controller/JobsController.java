package com.gl.pj.sys.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gl.pj.common.vo.JsonType;
import com.gl.pj.sys.entity.GlJobs;
import com.gl.pj.sys.service.GlJobsService;
/**
 * @职位控制层
 * 
 * @author chenjinbo
 *
 */
@Controller
@RequestMapping("/jobs/")
@ResponseBody
public class JobsController {

	@Autowired
	private GlJobsService service;  //注入职位业务层对象
	/**
	 * 
	 * @获取职位列表
	 * @param key 代表搜索关键字
	 * @return
	 */
	@RequestMapping("doJobObject")
	public JsonType doJobObject(String key,Integer page, Integer limit) { 
		System.out.println("显示总数量"+limit);
		System.out.println("页码"+page);
		int total = service.findObjectCount(key);
		List<GlJobs> list = service.findObject(key,page,limit);
		return new JsonType(list, total);
	}
	/**
	 * 
	 * @添加职位
	 * @param jobs  实体类
	 * @return
	 */
	@RequestMapping("doInsertObject")
	public JsonType doInsertJobObject(GlJobs jobs) { 
		int rows = service.insertObject(jobs);
		return new JsonType("添加成功");
	}
	/**
	 * @删除职位
	 * @param id  职位ID
	 * @return
	 */
	@RequestMapping("doDeleteObject")
	public JsonType doDeleteJobObject(Integer[] id) { 
		int rows =service.deleteObject(id);
		return new JsonType("删除成功");
	}
	/**
	 * @修改职位
	 * 
	 */
	@RequestMapping("doUpdateObject")
	public JsonType doUpdateJobObject(GlJobs jobs) { 
		int rows = service.updateObject(jobs);
		return new JsonType("修改成功");
	}
 
	
}
