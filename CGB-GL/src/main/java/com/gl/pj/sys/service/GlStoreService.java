package com.gl.pj.sys.service;

import java.util.List;

import com.gl.pj.sys.entity.GlStore;
/**
 * @店铺	业务接口
 * @author Chenjinbo
 *
 */
public interface GlStoreService {
	//查询店铺数量
	int findObjectCount(String name);
	//查询店铺列表
	List<GlStore> findObject(String name,Integer page,Integer limit);
	//添加店铺
	int insertObject(GlStore store);
	//修改店铺
	int updateObject(GlStore store);
	//删除店铺
	int deleteObject(Integer[] ids);
	
	

}
