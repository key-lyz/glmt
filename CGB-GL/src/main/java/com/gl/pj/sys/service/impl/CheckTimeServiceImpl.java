package com.gl.pj.sys.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gl.pj.common.vo.ClockDate;
import com.gl.pj.sys.dao.CheckTimeDao;
import com.gl.pj.sys.service.CheckTimeService;
@Service
public class CheckTimeServiceImpl implements CheckTimeService {
	@Autowired
     private CheckTimeDao checkTimeDao;

	@Override
	public List<ClockDate> findClockDateById(Integer userid) {
		if (userid==null) {
			throw new IllegalArgumentException("当前用户不合法");
		}
		List<ClockDate> list = checkTimeDao.findClockDateById(userid);
		return  list;
	}

}
