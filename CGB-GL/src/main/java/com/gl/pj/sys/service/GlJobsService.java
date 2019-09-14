package com.gl.pj.sys.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.gl.pj.sys.entity.GlJobs;
/**
 * @职位业务接口
 * @author Chenjinbo
 *
 */
public interface GlJobsService {

	//查询职位数量
	int findObjectCount(@Param("jobname")String name);
	//查询职位列表
	List<GlJobs> findObject(@Param("jobname") String name,Integer page,Integer limit);
	//添加职位
	int insertObject(GlJobs jobs);
	//修改职位
	int updateObject(GlJobs jobs);
	//删除职位
	int deleteObject(@Param("ids")Integer[] ids);
	
}
