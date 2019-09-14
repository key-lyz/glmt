package com.gl.pj.sys.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.gl.pj.common.vo.UserCountVo;
/**
 * 
 * @员工统计Dao
 * @author Chenjinbo
 *
 */
@Mapper
public interface UserCountDao {
	
	//获取员工数量
 	int findUserCount(String key,String riqi);
	
	//统计
	List<UserCountVo> findUserMoneyCount(Integer page,Integer limit,String key,String riqi);
	 
}