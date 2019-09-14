package com.gl.pj.sys.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gl.pj.common.vo.JsonType;
import com.gl.pj.sys.entity.GlStore;
import com.gl.pj.sys.entity.GlUser;
import com.gl.pj.sys.service.GlStoreService;
/**
 * @店铺控制层
 * @author Chenjinbo
 *
 */
@Controller
@RequestMapping("/store/")
@ResponseBody
public class StoreController {
  @Autowired
  GlStoreService service;  //注入店铺类业务层
 
   /**
    * 
    * @获取所有店铺列表
    * @param key 搜索关键字
    * @return
    */
	@RequestMapping("doStoreObject")
	public JsonType doStoreObject(String key,Integer page,Integer limit) { 
		int total = service.findObjectCount(key);
		List<GlStore> list = service.findObject(key,page,limit);
		  JsonType lists = new JsonType(list, total);
 		return  lists;
 	}
	/**
	 * @添加店铺
	 * @param store 实体类
	 * @return
	 */
	@RequestMapping("doInsertObject")
	public JsonType doInsertObject(GlStore store) { 
		int rows = service.insertObject(store);
 		return  new JsonType("添加店铺成功");
 	} 
	/**
	 * @修改店铺
	 * @param store 实体类
	 * @return
	 */
	@RequestMapping("doUpdateObject")
	public JsonType doUpdateObject(GlStore store) { 
		service.updateObject(store);
 		return  new JsonType("修改店铺成功");
 	} 
	/**
	 * 
	 * @删除店铺
	 * @param id 店铺ID
	 * @return
	 */
	@RequestMapping("doDeleteObject")
	public JsonType doDeleteObject(Integer[] id) { 
		System.out.println("删除店铺中...");
		int rows = service.deleteObject(id);
 		return  new JsonType("删除店铺成功");
 	} 
	
}
