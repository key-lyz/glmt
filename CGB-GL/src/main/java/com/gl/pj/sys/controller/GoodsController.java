package com.gl.pj.sys.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gl.pj.common.vo.GlGoodsVo;
import com.gl.pj.common.vo.JsonType;
import com.gl.pj.sys.entity.GlGoods;
import com.gl.pj.sys.entity.GlGoodsClass;
import com.gl.pj.sys.service.GlGoodsClassService;
import com.gl.pj.sys.service.GlGoodsService;
/**
 * 
 * @商品层控制类
 * @author Chenjinbo
 *
 */
@Controller
@RequestMapping("/goods/")
@ResponseBody
public class GoodsController {
	@Autowired
	GlGoodsService goodsservice;  //注入商品业务层对象
	@Autowired
	GlGoodsClassService goodsclassservice; //注入商品分类业务层对象
	
	/**
	 * @添加商品
	 * @param goods
	 * @return
	 */
	@RequestMapping("doInsertObject")
	public JsonType doInsertObject(GlGoods goods) { 
		int rows = goodsservice.InsertObject(goods);
		JsonType json = new JsonType("添加成功");
		return  json;
	}
	
 
	/**
	 * 
	 * @获取商品列表
	 * @param cid 分类ID
	 * @param key 搜索关键字
	 * @return
	 */
	@RequestMapping("doObject")
	public JsonType doObject(Integer cid,String key,Integer page,Integer limit) { 
		int count = goodsservice.findObjectCount(cid,key);
		List<GlGoodsVo> list = goodsservice.findObject(cid,key,page,limit);
		JsonType json = new JsonType(list, count);
		return  json;
	}

	/**
	 * 
	 * @删除商品
	 * @id 商品ID
	 */
	@RequestMapping("doDeleteObject")
	public JsonType doDeleteObject(Integer id) { 
		int rows = goodsservice.DeleteObject(id);
		JsonType json = new JsonType("删除成功");
		return  json;
	}
	
	/**
	 * 
	 * @根据ID查询商品
	 * @id 商品ID
	 */
	@RequestMapping("doFindObject")
	public JsonType doFindObject(Integer id) { 
		System.out.println("ID"+id);
		GlGoods goods = goodsservice.findGoodsObject(id);
		JsonType json = new JsonType(goods,1);
		return  json;
	}

	/**
	 * 
	 * @根据ID修改商品
	 * @id 商品ID
	 */
	@RequestMapping("doUpdateObject")
	public JsonType doUpdateObject(GlGoods goods) { 
		int rows = goodsservice.UpdateObject(goods);
		return  new JsonType("修改成功");
	}

}
