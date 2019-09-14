package com.gl.pj.sys.service.impl;

import java.util.List;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gl.pj.common.exception.ServiceException;
import com.gl.pj.sys.dao.GlGoodsClassDao;
import com.gl.pj.sys.entity.GlGoodsClass;
import com.gl.pj.sys.service.GlGoodsClassService;
/**
 * 
 * @商品分类业务层实现类
 * @author tedu
 *
 */
@Service
public class GlGoodsClassImpl implements  GlGoodsClassService {
 
	@Autowired
	private GlGoodsClassDao goodsclassdao;  //注入商品分类Dao
 
	/**
	 * @查询商品分类列表
	 * @pid 可根据父类ID进行筛选
	 */
 
	public List<GlGoodsClass> findObject(Integer pid) {
		List<GlGoodsClass> list = goodsclassdao.findObject(pid);
		return list;
	}
	/**
	 * @查询商品分类总数量
	 * @pid 可根据父类ID进行筛选
	 */
 	@Override
	public int findObjectCount(Integer pid) {
		int rows = goodsclassdao.findObjectCount(pid);
		return rows;
	}
	/**
	 * @添加商品分类
	 * @GoodsClass 实体类
	 */
	@RequiresPermissions("admin")
	@Override
	public int InsertObject(GlGoodsClass GoodsClass) {
		if (GoodsClass.getName() ==null  || GoodsClass.getName()=="")
			throw new ServiceException("请输入分类名称");
		GoodsClass.setPid(0);
		int rows = goodsclassdao.InsertObject(GoodsClass);
		return rows;
	}
	
	/**
	 * @删除商品分类
	 * @GoodsClass 实体类
	 */
	@RequiresPermissions("admin")
	@Override
	public int DeleteObject(Integer id) {
		if (id ==null )
			throw new ServiceException("非法ID值");
		try {
			int rows = goodsclassdao.DeleteObject(id);
			
		} catch (Exception e) {
			throw new ServiceException("删除失败");
		}
		
		return 0;
	}
	/**
	 * @修改商品分类
	 * @GoodsClass 实体类
	 */
	@RequiresPermissions("admin")
	@Override
	public int UpdateObject(GlGoodsClass GoodsClass) {
		if (GoodsClass.getId() ==null)
			throw new ServiceException("非法ID值");
		if (GoodsClass.getName() ==null || GoodsClass.getName() =="")
			throw new ServiceException("请输入分类名称");
		try {
			int rows = goodsclassdao.UpdateObject(GoodsClass);
		} catch (Exception e) {
			throw new ServiceException("修改失败");
		}
		
		return 0;
	}
	 

}
