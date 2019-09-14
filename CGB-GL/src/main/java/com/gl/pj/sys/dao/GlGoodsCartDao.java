package com.gl.pj.sys.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.gl.pj.sys.entity.GlGoodsCart;
import com.gl.pj.sys.entity.GlOrder;
import com.gl.pj.sys.entity.GlOrderGoods;
/**
 * 
 * @购物车Dao层
 * @author Chenjinbo
 *
 */
@Mapper
public interface GlGoodsCartDao {
	//查询购物车信息数量
	@Select("select count(*) from gl_goods_cart where userid=#{userid}")
	int findCount(Integer userid);
	//查询购物车信息
	@Select("select * from gl_goods_cart where userid=#{userid} order by id desc")
	List<GlGoodsCart> findObject(Integer userid);
	//添加进购物车
	int insertObject(GlGoodsCart cart);
	//删除购物车信息
	@Delete("delete from gl_goods_cart where id=#{id} and userid =#{userid} ")
	int deleteObject(Integer id,Integer userid);
	//增加购物车数量信息
	@Update("update gl_goods_cart set number=number+1 ,totalprice=totalprice+totalprice where id = #{id} and userid=#{userid}")
	int updateObject(Integer id,Integer userid);
	//删除购物车数量信息
	@Update("update gl_goods_cart set number=number-1 ,totalprice=totalprice-totalprice where id = #{id} and userid=#{userid}")
	int updateObjects(Integer id,Integer userid);
	//查询当前购物车的单个商品数量
	@Select("select number from gl_goods_cart where id=#{id} and userid=#{userid}")
	int findObjectById(Integer id,Integer userid);
	//获取购物车总金额
	Double findCartMoney(Integer[] ids,Integer userid);
	//获取购物车总数量
	int findCartNumber(Integer[] ids,Integer userid);
	
	//获取单个购物车总金额
	@Select("select goodsprice from  gl_goods_cart where id=#{id} and userid=#{userid} ")
	Double findCartMoneyById(Integer id,Integer userid);
	//获取单个购物车总数量
	@Select("select number from  gl_goods_cart where id=#{id} and userid=#{userid} ")
	int findCartNumberById(Integer id,Integer userid);
	
	//根据商品ID查询购物车商品信息
	@Select("select * from gl_goods_cart where goodsid=#{gid} and userid=#{userid} and goodsprice =#{price} limit 1")
	GlGoodsCart findObjectsBygid(Integer gid,Integer userid,Double price);
	//如果购物车存在则增加数量和金额
	@Update("update gl_goods_cart set number=number+#{num} ,totalprice=totalprice+(totalprice*#{num}) where id = #{id} and userid=#{userid}")
	int updateCartObject(Integer num,Integer id,Integer userid);
	
	
	//根据ID查询购物车商品信息
	@Select("select * from gl_goods_cart where id=#{id} and userid=#{userid}")
	GlGoodsCart findObjectsByid(Integer id,Integer userid);
	//扣除用户的余额
	@Update("update gl_user set money=money-#{money} where id =#{id}")
	int updateUserObject(Double money,Integer id);
	//删除购物车多条信息
	int deleteObjects(Integer[] ids,Integer userid);
	//写订单表
	@Insert("insert into gl_order values (null,#{type},#{dingdanhao},#{totalnumber},#{totalprice},#{userid},#{username},#{state},now())")
	int insertOrderObject(GlOrder order);
	//写订单商品表
	@Insert("insert into gl_order_goods values (null,#{dingdanhao},#{goodsid},#{goodsname},#{goodsprice},#{number},#{totalprice},#{userid},now())")
	int insertOrderGoodsObject(GlOrderGoods ordergoods);
	
 
	//根据订单号查询订单商品金额
		@Select("select sum(totalprice) from gl_order_goods where dingdanhao = #{dingdanhao}")
		double findOrderGoodsTotalMoney(String dingdanhao);
		
	
	//查询订单状态
	@Select("select * from gl_order where dingdanhao =#{dingdanhao} and userid=#{userid}")
	int findOrderState(String dingdanhao,Integer userid);
	//修改订单状态
	@Update("update gl_order set state=1 where dingdanhao =#{dingdanhao} and userid=#{userid}")
	int updateOrderState(String dingdanhao,Integer userid);
	//根据订单查询购物车的商品
	@Select("select goodsid from gl_order_goods where dingdanhao =#{dingdanhao} and userid=#{userid}")
  Integer[]	findOrderGoods(String dingdanhao,Integer userid);
}