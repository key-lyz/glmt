package com.gl.pj.sys.service.impl;

import java.util.List;

import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gl.pj.common.vo.GlOrderVo;
import com.gl.pj.sys.dao.GlOrderDao;
import com.gl.pj.sys.entity.GlOrder;
import com.gl.pj.sys.entity.GlUser;
import com.gl.pj.sys.service.GlOrderService;
@Service
public class GlOrderImpl implements  GlOrderService {
	@Autowired
	private GlOrderDao dao;  //注入订单接口层
 
	/**
	 * @查询订单数量
	 * 
	 */
	@Override
	public int findCount(String dingdanhao) {
		//获取登陆用户
				GlUser user=(GlUser)SecurityUtils.getSubject().getPrincipal();
				Integer userid = user.getId();
				
		 int rows = dao.findOrderCount(dingdanhao,userid);
		return rows;
	}
	/**
	 * @查询所有订单
	 * @param dingdanhao
	 * @return
	 */
	@Override
	public List<GlOrderVo> findAll(String dingdanhao,Integer page,Integer limit) {
		//获取登陆用户
		GlUser user=(GlUser)SecurityUtils.getSubject().getPrincipal();
		Integer userid = user.getId();
		/**每页显示数*/
		int pageSize=limit;
 		/**开始位置*/
 		int startIndex=(page-1)*pageSize;
 		 List<GlOrderVo> OrderGoods = dao.findUserOrder(userid,dingdanhao, startIndex,pageSize);
 		return OrderGoods;
	}

	 

}
