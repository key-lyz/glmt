package com.gl.pj.sys.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.gl.pj.common.vo.ClockDate;

@Mapper
public interface CheckTimeDao {

	//根据userid查询考勤日期，考勤时长，考勤用户名
	@Select("SELECT TIMEDIFF(punchoutTime,punchinTime),attendanceTime,developername from punch_clock WHERE userid=#{userid}")
	List<ClockDate> findClockDateById(Integer userid);
	
}
