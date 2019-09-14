package com.gl.pj.sys.service;

import java.util.List;

import com.gl.pj.common.vo.GlGoodsVo;
import com.gl.pj.sys.entity.GlGoods;
/**
 * @商品业务接口
 * @author Chenjinbo
 *
 */
public interface GlGoodsService {
	
	//查询商品数量
	int findObjectCount(Integer classid,String key);
	//查询所有商品
	//List<GlGoods> findGoods(Integer classid);
	List<GlGoodsVo> findObject(Integer classid,String key,Integer page,Integer limit);
	//添加商品
	int InsertObject(GlGoods goods);
	//修改商品
	int UpdateObject(GlGoods goods);
	//删除商品
	int DeleteObject(Integer id);
    //查询商品
	GlGoods findGoodsObject(Integer id);
	//根据商品ID查询商品指定信息
	Object findGoodsValueByid(String value,Integer id);
	
	

}
