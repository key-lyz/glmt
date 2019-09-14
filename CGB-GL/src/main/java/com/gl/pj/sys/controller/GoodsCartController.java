package com.gl.pj.sys.controller;

import java.util.List;

import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gl.pj.common.vo.JsonType;
import com.gl.pj.sys.entity.GlGoodsCart;
import com.gl.pj.sys.entity.GlGoodsClass;
import com.gl.pj.sys.entity.GlUser;
import com.gl.pj.sys.service.GlGoodsCartService;
import com.gl.pj.sys.service.GlGoodsClassService;
/**
 * @购物车层控制类
 * 
 * @author Chenjinbo
 *
 */

@Controller
@RequestMapping("/cart/")
@ResponseBody
public class GoodsCartController {
	

	@Autowired
	GlGoodsCartService cartservice; //注入购物车业务层
	
	/**
	 * 
	 * @查询购物车数据
  	 * @return
	 */
	@RequestMapping("doObject")
	public JsonType doObject() { 
		//获取登陆用户
		GlUser user=(GlUser)SecurityUtils.getSubject().getPrincipal();
		Integer uid = user.getId();
			int count = cartservice.findCount(uid);
			List<GlGoodsCart> list = cartservice.findObject(uid);
		return  new JsonType(list,count);
	}
	
	/**
	 * 
	 * @添加数据进购物车
	 * @param cart   购物车实体类
	 * @return
	 */
	@RequestMapping("doInsertObject")
	public JsonType doInsertObject(GlGoodsCart cart) { 
		int rows = cartservice.insertObject(cart);
		return  new JsonType("加入购物车成功");
	}
	
	/**
	 * 
	 * @删除购物车
	 * @param id   删除ID
	 * @return
	 */
	@RequestMapping("doDeleteObject")
	public JsonType doInsertObject(Integer id) { 
		int rows = cartservice.deleteObject(id);
		return  new JsonType("删除成功");
	}
	
	/**
	 * 
	 * @增删数量
	 * @param id  
	 * @return
	 */
	@RequestMapping("doUpdateNumber")
	public JsonType doUpdateNumber(Integer id,Integer status) { 
		int rows = cartservice.updateObject(id,status);
		return  new JsonType("操作成功");
	}
	

	/**
	 * 
	 * @结算购物车
	 * @param id  
	 * @return
	 */
	@RequestMapping("doInsertOrder")
	public JsonType doInsertOrder(Integer[] param) { 
		int rows = cartservice.insertOrder(param);
		return  new JsonType("结算成功");
	}
	

}
