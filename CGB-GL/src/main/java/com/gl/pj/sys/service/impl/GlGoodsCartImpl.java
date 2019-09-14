package com.gl.pj.sys.service.impl;

import java.util.List;

import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gl.pj.common.exception.ServiceException;
import com.gl.pj.common.util.DingdanhaoUtil;
import com.gl.pj.sys.dao.GlGoodsCartDao;
import com.gl.pj.sys.dao.GlGoodsDao;
import com.gl.pj.sys.dao.GlUserDao;
import com.gl.pj.sys.entity.GlGoodsCart;
import com.gl.pj.sys.entity.GlOrder;
import com.gl.pj.sys.entity.GlOrderGoods;
import com.gl.pj.sys.entity.GlUser;
import com.gl.pj.sys.service.GlGoodsCartService;
import com.gl.pj.sys.service.GlGoodsService;
/**
 * 
 * @购物车业务层实现类
 * @author Chenjinbo
 *
 */
@Service
public class GlGoodsCartImpl implements  GlGoodsCartService {
	@Autowired
	GlGoodsCartDao dao; //注入购物车接口
 
	@Autowired
	GlGoodsService goodsService;  //注入商品业务层
	@Autowired
	GlUserDao userdao;//注入用户接口层对象
 
	/**
	 * @添加购物车
	 */
	
	@Override
	public int insertObject(GlGoodsCart cart) {
			if (cart.getGoodsid()==null)
			 throw new ServiceException("非法商品ID");
			if (cart.getNumber()==null)
			 throw new ServiceException("请输入购买数量");
			
			if  (cart.getNumber()<1)
				 throw new ServiceException("购买数量不正确");

		
			//获取登陆用户
			GlUser user=(GlUser)SecurityUtils.getSubject().getPrincipal();
			Integer userid = user.getId();
			
			//查询商品实时价格
			Double goodsprice=(Double) goodsService.findGoodsValueByid("price", cart.getGoodsid());//获取商品名称

			
			//验证当前商品是否存在购物车 存在的话则修改数量
			GlGoodsCart curcart = dao.findObjectsBygid(cart.getGoodsid(), userid,goodsprice);
	
			if (curcart!=null ) {
			Double cateprice = curcart.getGoodsprice();
			 if (goodsprice.equals(cateprice)) {
				//直接修改当前购物车信息
				try {
					dao.updateCartObject(cart.getNumber(),curcart.getId(), userid);
				} catch (Exception e) {
					e.printStackTrace();
					throw new ServiceException("二次增加购物车信息失败");
				}
				
				return 0;
			 }
				
			}
			
			 //根据商品ID获取相关信息
				String goodsname=(String) goodsService.findGoodsValueByid("name", cart.getGoodsid());//获取商品名称
				Integer num =cart.getNumber();  //获取下单数量
				Double price=	(Double) goodsService.findGoodsValueByid("price",cart.getGoodsid());//获取商品单价
				Double total=num*price; //计算总金额
				
			 //2.写入购物车
				cart.setGoodsname(goodsname);//写入商品名称
				cart.setGoodsprice(price);//写入商品单价
				cart.setTotalprice(total);//写入总金额
				cart.setUserid(userid);//写入用户ID
			try {
				int rows = dao.insertObject(cart);
			} catch (Exception e) {
				e.printStackTrace();
				 throw new ServiceException("添加进购物车失败");
			}
		
		return 0;
	}
	/**
	 * @查询购物车
	 */
	@Override
	public List<GlGoodsCart> findObject(Integer userid) {
		List<GlGoodsCart> list = dao.findObject(userid);
		return list;
	}
	/**
	 * @查询购物车数据数量
	 */
	@Override
	public int findCount(Integer userid) {
		 int rows = dao.findCount(userid);
		return rows;
	}
	/**
	 * @删除购物车信息
	 */
	@Override
	public int deleteObject(Integer id) {
		//获取登陆用户
		GlUser user=(GlUser)SecurityUtils.getSubject().getPrincipal();
		Integer userid = user.getId();
		
		if (id==null )  
		 throw new ServiceException("非法ID值");
		int rows = dao.deleteObject(id,userid);
		return 0;
	}
	/**
	 * @修改购物车数量信息
	 * 
	 */
	@Override
	public int updateObject(Integer id,Integer status) {
		//获取登陆用户
		GlUser user=(GlUser)SecurityUtils.getSubject().getPrincipal();
		Integer userid = user.getId();
		if (status==null )  
		 throw new ServiceException("非法操作");
		if (id==null )  
			 throw new ServiceException("非法ID值");	
		if (status==0)
		{
			//如果当前数量是1 则提示非法操作
			int number = dao.findObjectById(id, userid);
			//1.查询当前数量
			if (number==1)
				 throw new ServiceException("购买数量不得低于1");	
			int rows = dao.updateObjects(id, userid);
		}
		
		if (status==1)
		{int rows = dao.updateObject(id, userid);}
		return 0;
	}
	/**
	 * @结算购物车
	 */
	@Transactional(rollbackFor = Throwable.class) //开启事务
	@Override
	public int insertOrder(Integer[] cartid) {
		//获取登陆用户
				GlUser user=(GlUser)SecurityUtils.getSubject().getPrincipal();
				GlUser userinfo = userdao.findUserByUserName(user.getUsername());
				Integer userid = user.getId(); //用户ID
				Double usermoney = userinfo.getMoney(); //动态获取用户余额
			 //验证购物车是否还存在
			    for (Integer caid : cartid) {
			    	 GlGoodsCart cartinfo = dao.findObjectsByid(caid, userid);
			    	 if (cartinfo==null)
			    		 throw new ServiceException("该商品已结算");
			    }
	    //1.获取当前购物车的金额信息
			   Double getCartMoney = dao.findCartMoney(cartid, userid);
			   int getCartNumber = dao.findCartNumber(cartid, userid);
			   System.out.println("gouwuchejine"+getCartMoney);
	    //2.判断当前用户余额
	//		   if (usermoney<getCartMoney)
	//		 throw new ServiceException("结算失败:当前用户余额"+usermoney+"不足"+getCartMoney+"元,请充值");	
		//3.扣除当前余额
			   try {
		//		   int updatemoney = dao.updateUserObject(getCartMoney, userid); 
				   
				   GlOrder order =new GlOrder();
					DingdanhaoUtil  ddh=new DingdanhaoUtil();
					String dingdan = ddh.getOrdernumber();//生成订单号
					
					
				   //3.2写入订单商品表  
				    GlOrderGoods orderGoods=new GlOrderGoods();
				    for (Integer caid : cartid) {
				     
				    	//3.2.1获取购物车的相关信息
				    	 GlGoodsCart cartinfo = dao.findObjectsByid(caid, userid);
						   Double CartMoney = dao.findCartMoneyById(caid, userid);
						   int CartNumber = dao.findCartNumberById(caid, userid);
						   Double totalMoney=CartMoney*CartNumber;
						   String goodsname = (String)goodsService.findGoodsValueByid("name", cartinfo.getGoodsid());
				    	orderGoods.setDingdanhao(dingdan);
				    	orderGoods.setGoodsid(cartinfo.getGoodsid());
				    	orderGoods.setGoodsname(goodsname);
				    	orderGoods.setGoodsprice(CartMoney);
				    	orderGoods.setNumber(CartNumber);
				    	orderGoods.setTotalprice(totalMoney);
				    	orderGoods.setUserid(userid);
				    	 dao.insertOrderGoodsObject(orderGoods);
				  
					}
				  	 //根据商品计算总金额
			    	 double totalmoney = dao.findOrderGoodsTotalMoney(dingdan);
				    //3.1写入订单表
				    order.setDingdanhao(dingdan); 
				    order.setTotalnumber(getCartNumber);
				    order.setTotalprice(totalmoney); 
				    order.setUserid(userid);
				    order.setUsername(userinfo.getUsername());
				    order.setState(1);
				    order.setType("员工");
				   dao.insertOrderObject(order);
				 //3.3删除对应的购物车数据  
					   int delcart = dao.deleteObjects(cartid, userid);
			} catch (Exception e) {
				 throw new ServiceException("操作失败:有事务执行失败");	

			}
			 
			   
		return  0;
	}
 

}
