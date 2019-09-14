package com.gl.pj.sys.controller;

import java.util.List;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gl.pj.common.vo.JsonType;
import com.gl.pj.sys.entity.GlGoodsCart;
import com.gl.pj.sys.entity.GlGoodsClass;
import com.gl.pj.sys.service.GlGoodsCartService;
import com.gl.pj.sys.service.GlGoodsClassService;
/**
 * @登录层控制类
 * 
 * @author Chenjinbo
 *
 */

@Controller
@RequestMapping("/ajax/")
@ResponseBody
public class LoginController {
	
 
	
	/**
	 * 
	 * @用户登录
  	 * @return
	 */
	@RequestMapping("doLogin")
	public JsonType doFindObject(String username,String password) { 
		   //1.获取Subject对象
		   Subject subject=SecurityUtils.getSubject();
		   //2.通过Subject提交用户信息,交给shiro框架进行认证操作
		   //2.1对用户进行封装
		   UsernamePasswordToken token= new UsernamePasswordToken(username,password);//凭证信息
		   	//开启记住我功能
			token.setRememberMe(true); 
		   //2.2对用户信息进行身份认证
		   subject.login(token);
		   //分析:
		   //1)token会传给shiro的SecurityManager
		   //2)SecurityManager将token传递给认证管理器
		   //3)认证管理器会将token传递给realm
		   return new JsonType("login ok");
 	}
	
	 
	

}
