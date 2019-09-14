package com.gl.pj.sys.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gl.pj.common.exception.ServiceException;
import com.gl.pj.sys.dao.GlJobsDao;
import com.gl.pj.sys.dao.GlPayDao;
import com.gl.pj.sys.entity.GlJobs;
import com.gl.pj.sys.entity.GlPay;
import com.gl.pj.sys.service.GlJobsService;
import com.gl.pj.sys.service.GlPayService;

import lombok.extern.slf4j.Slf4j;
/**
 * 
 * @充值记录实现类
 * @author Chenjinbo
 *
 */
@Slf4j
@Service
public class GlPayImpl  implements GlPayService{
	@Autowired
	private GlPayDao dao;
	/**
	 * @获取充值记录数量
	 * @key 搜索关键字
	 */
	@Override
	public int findUserObjectCount(Integer userid, String key) {
	
		int rows = dao.findUserObjectCount(userid, key);
		return rows;
	}
	/**
	 * @获取充值列表
	 * @key 搜索关键字
	 */
	@Override
	public List<GlPay> findUserPayObject(Integer userid, String key, Integer page, Integer limit) {
		/**每页显示数*/
		int pageSize=limit;
 		/**开始位置*/
 		int startIndex=(page-1)*pageSize;
 		List<GlPay> list = dao.findUserPayObject(userid,key,startIndex,limit);
		return list;
		
	} 
 

}
