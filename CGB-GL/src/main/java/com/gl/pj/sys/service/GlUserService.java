package com.gl.pj.sys.service;

import java.util.List;

import com.gl.pj.common.vo.GlUserVo;
import com.gl.pj.sys.entity.GlUser;

public interface GlUserService {
	//修改用户密码
	int updatepassword(String old_password,String new_password,String re_new_password);
	
	
	//删除用户
 	int delObject(Integer... ids);
	
	//修改用户状态
 	int updateStatus(Integer id,Integer status );
	
	//添加用户
	int insertObject(GlUser userinfo);
	//修改用户
	int updateObject(GlUser userinfo);
	//查询用户总数量
	int findCount(String key);
	//查询用户列表
	List<GlUserVo> findAll(String key);
	
	

}
