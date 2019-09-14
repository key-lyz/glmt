package com.gl.pj.sys.service;

import java.util.List;

import com.gl.pj.common.vo.GlGoodsVo;
import com.gl.pj.sys.entity.GlGoodsClass;
/**
 * @商品分类业务接口
 * @author Chenjinbo
 *
 */
public interface GlGoodsClassService {
 
 	//添加分类
	int InsertObject(GlGoodsClass GoodsClass);
	//修改分类
 	int UpdateObject(GlGoodsClass GoodsClass);
	//删除分类
	int DeleteObject(Integer id);
	
	//查询分类数量
	int findObjectCount(Integer pid);
	//查询所有分类
	List<GlGoodsClass> findObject(Integer pid);
	
	
	

}
