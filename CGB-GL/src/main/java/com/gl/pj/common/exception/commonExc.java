package com.gl.pj.common.exception;

import org.apache.shiro.ShiroException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.AuthorizationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gl.pj.common.vo.JsonType;

 
@ControllerAdvice
public class commonExc {
	@ExceptionHandler(RuntimeException.class)
	@ResponseBody
	public JsonType doexc(RuntimeException e) {
		e.printStackTrace();
		return new JsonType(e);
	} 
	
	
	
	@ExceptionHandler(ShiroException.class)
	@ResponseBody
	public JsonType doHandleShiroException(
			ShiroException e) {
		JsonType r=new JsonType("");
		r.setCode(1);
		if(e instanceof UnknownAccountException) {
			r.setMsg("账户不存在");
		}else if(e instanceof LockedAccountException) {
			r.setMsg("账户已被禁用");
		}else if(e instanceof IncorrectCredentialsException) {
			r.setMsg("密码不正确");
		}else if(e instanceof AuthorizationException) {
			r.setMsg("没有此操作权限");
		}else {
			r.setMsg("系统维护中");
		}
		e.printStackTrace();
		return r;
	}
	
	
}
