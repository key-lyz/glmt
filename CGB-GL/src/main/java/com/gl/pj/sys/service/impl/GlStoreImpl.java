package com.gl.pj.sys.service.impl;

import java.util.List;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gl.pj.common.exception.ServiceException;
import com.gl.pj.sys.dao.GlStoreDao;
import com.gl.pj.sys.entity.GlStore;
import com.gl.pj.sys.service.GlStoreService;
/**
 * 
 * @店铺实现类
 * @author Chenjinbo
 *
 */
@Service
public class GlStoreImpl  implements GlStoreService{

	@Autowired
	private GlStoreDao storedao; //注入店铺Dao
	/**
	 * @获取店铺数量
	 * @name 搜索关键字
	 */
	@RequiresPermissions("admin")
	@Override
	public int findObjectCount(String name) {
		int rows = storedao.findObjectCount(name);
		return rows;
	}
	/**
	 * @获取店铺列表
	 * @name 搜索关键字
	 */
	@RequiresPermissions("admin")
	@Override
	public List<GlStore> findObject(String name,Integer page,Integer limit) {
		/**每页显示数*/
		int pageSize=limit;
 		/**开始位置*/
 		int startIndex=(page-1)*pageSize;
		List<GlStore> list = storedao.findObject(name,startIndex,pageSize);
		return list;
	}
	/**
	 * @删除店铺
	 * @ids 店铺ID
	 */
	@RequiresPermissions("admin")
	@Override
	public int deleteObject(Integer[] ids) {
		if (ids.length<1)
			throw  new ServiceException("非法ID值");
		int rows = storedao.deleteObject(ids);
		return rows;
	}
	/**
	 * @添加店铺
	 * @store 实体类
	 */
	@RequiresPermissions("admin")
	@Override
	public int insertObject(GlStore store) {
		if (store.getName()==null || store.getName()=="")
		throw  new ServiceException("请输入店铺名称");
		try {
			int rows = storedao.insertObject(store);
		} catch (Exception e) {
			throw  new ServiceException("添加店铺失败");
		}
		
		return 0;
	}
	/**
	 * @修改店铺
	 * @store 实体类
	 */
	@RequiresPermissions("admin")
	@Override
	public int updateObject(GlStore store) {
		if (store.getName()==null || store.getName()=="")
			throw  new ServiceException("请输入店铺名称");
		try {
			int rows = storedao.updateObject(store);
		} catch (Exception e) {
			throw  new ServiceException("修改店铺失败");
		}
		return 0;
	}

}
