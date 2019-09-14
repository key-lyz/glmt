package com.gl.pj.common.vo;

import java.util.Date;

import lombok.Data;

@Data
public class ClockDate {

	 private Integer userid;//用户ID
	 private String developername;//用户名
	 private Date attendanceTime;//考勤时间
	 private Long workTime;//考勤时长
}
