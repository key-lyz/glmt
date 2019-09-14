package com.gl.pj.sys.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gl.pj.common.vo.JsonType;
import com.gl.pj.sys.entity.GlGoodsClass;
import com.gl.pj.sys.service.GlGoodsClassService;
/**
 * @商品分类层控制类
 * 
 * @author Chenjinbo
 *
 */

@Controller
@RequestMapping("/goodsclass/")
@ResponseBody
public class GoodsClassController {
	

	@Autowired
	GlGoodsClassService goodsclassservice; //注入商品分类层对象
	/**
	 * 
	 * @获取商品分类列表
	 * @param pid 代表 父类ID
	 * @return
	 */
	@RequestMapping("doFindObject")
	public JsonType doFindObject(Integer pid) { 
		int count = goodsclassservice.findObjectCount(pid);
		List<GlGoodsClass> list = goodsclassservice.findObject(pid);
		JsonType json = new JsonType(list, count);
		return  json;
	}
	
	
	/**
	 * 
	 * @添加商品分类
	 * @param GoodsClass 代表 商品分类实体类
	 * @return
	 */
	@RequestMapping("doInsertObject")
	public JsonType doInsertObject(GlGoodsClass GoodsClass) { 
		int rows = goodsclassservice.InsertObject(GoodsClass);
		return  new JsonType("添加成功");
	}
	
   /**
    * 
    * @修改商品分类
    * @param GoodsClass 代表 商品分类实体类
    * @return
    */
	@RequestMapping("doUpdateObject")
	public JsonType doUpdateObject(GlGoodsClass GoodsClass) { 
		int rows = goodsclassservice.UpdateObject(GoodsClass);
		return  new JsonType("修改成功");
	}
	
	/**
	 * 
	 * @删除商品分类
	 * @param id 代表 删除的ID
	 * @return
	 */
	@RequestMapping("doDeleteObject")
	public JsonType dodeleteObject(Integer id) { 
		int rows = goodsclassservice.DeleteObject(id);
		return  new JsonType("删除成功");
	}
	

}
