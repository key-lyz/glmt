package com.gl.pj.sys.dao;


import org.apache.ibatis.annotations.Mapper;
import com.gl.pj.common.vo.PunchClock;
@Mapper
public interface PunchClockDao {
    //打卡
    int up_out(PunchClock punchClock);
    //签退
    int add_in(PunchClock punchClock);
   //迟到原因备注
    int late_result(PunchClock punchClock);
    //查询当前用户是否已经打卡
    PunchClock if_punchin(PunchClock punchClock);
    //查询当前用户是否已经签退
    PunchClock if_punchout(PunchClock punchClock);
    
}