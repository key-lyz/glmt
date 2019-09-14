package com.gl.pj.sys.service;

import java.util.List;

import com.gl.pj.common.vo.ClockDate;

public interface CheckTimeService {

	List<ClockDate> findClockDateById(Integer userid);
}
