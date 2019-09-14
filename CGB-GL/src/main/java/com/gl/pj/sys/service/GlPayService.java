package com.gl.pj.sys.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.gl.pj.sys.entity.GlJobs;
import com.gl.pj.sys.entity.GlPay;
/**
 * @职位业务接口
 * @author Chenjinbo
 *
 */
public interface GlPayService {
	/** 查询用户充值记录数量 */
	int findUserObjectCount(Integer userid,String key);
	/** 查询用户充值记录*/
 	List<GlPay> findUserPayObject(Integer userid,
 					        String key,
 					        Integer page,
 					        Integer limit);
}
