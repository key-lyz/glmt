package com.gl.pj.sys.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gl.pj.sys.dao.EchartsDao;
import com.gl.pj.sys.entity.GlOrder;
import com.gl.pj.sys.service.EchartsService;

@Service
public class EchartsServiceImpl implements EchartsService {

	@Autowired
	private EchartsDao echartsDao;
	@Override
	public List<GlOrder> findObject() {
		List<GlOrder> echarts = echartsDao.repeatsingle();
		return echarts;
	}

}
