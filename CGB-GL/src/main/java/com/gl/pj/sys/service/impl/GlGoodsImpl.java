package com.gl.pj.sys.service.impl;

import java.util.List;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gl.pj.common.exception.ServiceException;
import com.gl.pj.common.vo.GlGoodsVo;
import com.gl.pj.sys.dao.GlGoodsDao;
import com.gl.pj.sys.entity.GlGoods;
import com.gl.pj.sys.service.GlGoodsService;
/**
 * 
 * @商品实现类
 * @author Chenjinbo
 *
 */
@Service
public class GlGoodsImpl implements  GlGoodsService {

	@Autowired
	private GlGoodsDao goodsdao; //注入商品Dao层
	/**
	 * @获取商品总数
	 * @classid 分类ID
	 * @key 搜索关键字
	 */
	@Override
	public int findObjectCount(Integer classid,String key) {
		int rows = goodsdao.findObjectCount(classid,key);
		return rows;
	}
	
	/**
	 * @获取商品列表
	 * @classid 分类ID
	 * @key 搜索关键字
	 */
	
	@Override
	public List<GlGoodsVo> findObject(Integer classid,String key,Integer page,Integer limit) {
		/**每页显示数*/
		int pageSize=limit;
 		/**开始位置*/
 		int startIndex=(page-1)*pageSize;
		List<GlGoodsVo> list = goodsdao.findObject(classid,key,startIndex,pageSize);
		return list;
	}
	/**
	 * @添加商品
	 */
	@RequiresPermissions("admin")
	@Override
	public int InsertObject(GlGoods goods) {
		if (goods.getName()==null || goods.getName()=="")
			throw new ServiceException("请输入商品名称");
		if (goods.getClassid()==null  )
			throw new ServiceException("请选择商品分类");
		if (goods.getPrice()==null  )
			throw new ServiceException("请输入商品价格");
		if (goods.getStock()==null  )
			throw new ServiceException("请输入商品库存");		
		try {
			int rows = goodsdao.InsertObject(goods);

		} catch (Exception e) {
			throw new ServiceException("添加商品失败");		
		}
		return 0;
	}
	/**
	 * @删除商品
	 */
	@RequiresPermissions("admin")
	@Override
	public int DeleteObject(Integer id) {
		System.out.println(id);
		try {
			int rows = goodsdao.DeleteObject(id);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException("删除失败");
		}
		return 0;
	}
	/**
	 * @根据商品ID查询商品价格
	 */
	@Override
	public Object findGoodsValueByid(String value,Integer id) {
		if (id==null)
			throw new ServiceException("ID值非法");
		Object values = goodsdao.findGoodsValueByid(value,id);
		return values;
	}
	/**
	 * @根据ID获取商品信息
	 * 
	 */
	@Override
	public GlGoods findGoodsObject(Integer id) {
		if (id==null)
			throw new ServiceException("商品ID非法");
		GlGoods list = goodsdao.findGoodsObject(id);
		return list;
	}
	/**
	 * @修改商品
	 */
	@RequiresPermissions("admin")
	@Override
	public int UpdateObject(GlGoods goods) {
		int rows;
		if (goods==null)
			throw new ServiceException("非法传递参数");
		try {
			  rows = goodsdao.UpdateObject(goods);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException("修改失败");
		}
		
		return rows;
	}
	
 
	

}
