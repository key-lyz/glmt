package com.gl.pj.sys.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gl.pj.common.exception.ServiceException;
import com.gl.pj.sys.dao.GlGoodsClassDao;
import com.gl.pj.sys.dao.GlSystemDao;
import com.gl.pj.sys.entity.GlGoodsClass;
import com.gl.pj.sys.entity.GlSystem;
import com.gl.pj.sys.service.GlGoodsClassService;
import com.gl.pj.sys.service.GlSystemService;
/**
 * 
 * @系统设置业务层实现类
 * @author Chenjinbo
 *
 */
@Service
public class GlSystemImpl implements  GlSystemService {
	@Autowired
	GlSystemDao dao;
	@Override
	public int updateSystem(GlSystem system) {
	 int rows = dao.updateSystem(system);
		return 0;
	}
	@Override
	public GlSystem findSystem() {
		GlSystem list = dao.findSystem();
		return list;
	}
 
	 

}
