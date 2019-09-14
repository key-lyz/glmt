package com.gl.pj.sys.service;

import com.gl.pj.sys.entity.GlSystem;
/**
 * @系统设置业务接口
 * @author Chenjinbo
 *
 */
public interface GlSystemService {
	//查询系统设置
	GlSystem findSystem();
	//修改系统设置
	int updateSystem(GlSystem system);
 

}
