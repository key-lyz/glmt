package com.gl.pj.sys.service.impl;

import java.util.List;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gl.pj.common.exception.ServiceException;
import com.gl.pj.sys.dao.GlJobsDao;
import com.gl.pj.sys.entity.GlJobs;
import com.gl.pj.sys.service.GlJobsService;

import lombok.extern.slf4j.Slf4j;
/**
 * 
 * @职位实现类
 * @author tedu
 *
 */
@Slf4j
@Service

public class GlJobsImpl  implements GlJobsService{

	@Autowired
	private GlJobsDao dao;  //注入职位Dao
	/**
	 * @获取职位数量
	 * @name 搜索关键字
	 */
	@RequiresPermissions("admin")
	@Override
	public int findObjectCount(String name) {
		 int rows = dao.findObjectCount(name);
		return rows;
	}
	/**
	 * @获取职位列表
	 * @name 搜索关键字
	 */
	@RequiresPermissions("admin")
	@Override
	public List<GlJobs> findObject(String name,Integer page,Integer limit) {
		/**每页显示数*/
		int pageSize=limit;
 		/**开始位置*/
 		int startIndex=(page-1)*pageSize;
 		List<GlJobs> list = dao.findObject(name,startIndex,limit);
		return list;
	}
	/**
	 * @删除职位
	 * @ids 职位ID
	 */
	@RequiresPermissions("admin")
	@Override
	public int deleteObject(Integer[] ids) {
		if (ids.length <0 )
			throw new ServiceException("非法ID值");
		try {
			int rows = dao.deleteObject(ids);
			
		} catch (Exception e) {
			throw new ServiceException("删除失败");
		}
		
		
		return 0;
	}
	/**
	 * @添加职位
	 * @jobs 实体类
	 */
	@RequiresPermissions("admin")
	@Override
	public int insertObject(GlJobs jobs) {
		if (jobs.getName()==null || jobs.getName()=="" )
			throw new ServiceException("请输入职位名称");
		try {
			int rows = dao.insertObject(jobs);
			
		} catch (Exception e) {
			throw new ServiceException("添加职位失败");
		}
		return 0;
	}
	/**
	 * @修改职位
	 * @jobs 实体类
	 */
	@RequiresPermissions("admin")
	@Override
	public int updateObject(GlJobs jobs) {
		if (jobs.getName()==null || jobs.getName()=="" )
			throw new ServiceException("请输入职位名称");
		try {
			int rows = dao.updateObject(jobs);
			
		} catch (Exception e) {
			throw new ServiceException("修改职位失败");
		}
		return 0;
	}

}
