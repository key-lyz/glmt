package com.gl.pj.sys.service;

import java.util.Date;

import org.springframework.transaction.annotation.Transactional;

import com.gl.pj.common.vo.PunchClock;


@Transactional
public interface PunchClockManager {
    int up_out(PunchClock punchClock);
    int add_in(PunchClock punchClock);
    int late_result(PunchClock punchClock);
    PunchClock if_punchin(PunchClock punchClock);
    PunchClock if_punchout(PunchClock punchClock);
}