package com.gl.pj.sys.controller;

import java.util.List;

import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gl.pj.common.vo.GlUserVo;
import com.gl.pj.common.vo.JsonType;
import com.gl.pj.sys.dao.GlUserDao;
import com.gl.pj.sys.entity.GlUser;
import com.gl.pj.sys.service.GlUserService;
/**
 * @用户控制层
 * 
 * @author tedu
 *
 */
@Controller
@RequestMapping("/user/")
@ResponseBody
public class UserController {
	@Autowired
	GlUserDao userdao; 
	@Autowired
	GlUserService userservice;

	/**
	 * @根据用户ID查询用户信息
	 * @param key
	 * @return
	 */
	@RequestMapping("doFindObjectByid")
	public JsonType doFindObjectByid(Integer id) { 
		GlUser userinfo = userdao.FindObjectByid(id);
		userinfo.setPassword(null);
		userinfo.setSalt(null);
		JsonType json = new JsonType(userinfo, 1);
		return  json;
	}

	
	/**
	 * @根据用户名查询用户信息
	 * @param key
	 * @return
	 */
	@RequestMapping("doFindObject")
	public JsonType doFindObject(String key) { 
		//获取登陆用户
		GlUser user=(GlUser)SecurityUtils.getSubject().getPrincipal();
		GlUser userinfo = userdao.findUserByUserName(user.getUsername());
		userinfo.setPassword(null);
		userinfo.setSalt(null);
		JsonType json = new JsonType(userinfo, 0);
		return  json;
	}

	/**
	 * @修改用户密码
	 * @param old_password
	 * @param new_password
	 * @param re_new_password
	 * @return
	 */
	@RequestMapping("doUpdatePassword")
	public JsonType doUpdatePassword(String old_password,String new_password,String re_new_password) { 
		int rows = userservice.updatepassword(old_password, new_password, re_new_password);
		return  new JsonType("修改成功");
	}


  /**
   * @查询用户列表
   * @param key
   * @return
   */
	@RequestMapping("doFindObjects")
	public JsonType doFindObjects(String key) { 
		System.out.println(key);
		int count = userservice.findCount(key);
		List<GlUserVo> list = userservice.findAll(key);
		JsonType json = new JsonType(list, count);
		return  json;
	}

	 /**
	  * @添加用户
	  * @param userinfo
	  * @return
	  */
	@RequestMapping("doInsertObject")
	public JsonType doInsertObject(GlUser userinfo) { 
		int rows = userservice.insertObject(userinfo);
		return  new JsonType("添加成功") ;
	}

	/**
	 * @修改用户
	 * @param id
	 * @param status
	 * @return
	 */
	@RequestMapping("doUpdateObject")
	public JsonType doUpdateObject(GlUser userinfo) { 
	 int rows = userservice.updateObject(userinfo);
		return  new JsonType("修改成功") ;
	}
	/**
	 * @删除用户
	 * @param id
	 * @return
	 */
	@RequestMapping("doDeleteObject")
	public JsonType doDeleteObject(Integer[] id) { 
		System.out.println("id:="+id);
		int rows = userservice.delObject(id);
		return  new JsonType("删除成功") ;
	}
	/**
	 * @修改用户状态
	 * @param id
	 * @return
	 */
	@RequestMapping("doUpdatestatus")
	public JsonType doUpdatestatus(Integer id,Integer status) { 
		int rows = userservice.updateStatus(id, status);
		return  new JsonType("操作成功") ;
	}
	
	

}
