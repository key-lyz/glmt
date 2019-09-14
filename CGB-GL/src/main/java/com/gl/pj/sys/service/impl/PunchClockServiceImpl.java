package com.gl.pj.sys.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gl.pj.common.exception.ServiceException;
import com.gl.pj.common.vo.JsonType;
import com.gl.pj.common.vo.PunchClock;
import com.gl.pj.sys.dao.PunchClockDao;
import com.gl.pj.sys.service.PunchClockManager;

@Service
@Transactional
public class PunchClockServiceImpl implements PunchClockManager {
    @Autowired
    private PunchClockDao punchClockDao;
    @Override
    public int up_out(PunchClock punchClock) {
        return punchClockDao.up_out(punchClock);
    }
    @Override
    public int add_in(PunchClock punchClock) {
        return punchClockDao.add_in(punchClock);
    }
    @Override
    public PunchClock if_punchin(PunchClock punchClock) {
        return punchClockDao.if_punchin(punchClock);
    }
    @Override
    public PunchClock if_punchout(PunchClock punchClock) {
        return punchClockDao.if_punchout(punchClock);
    }
    @Override
    public int late_result(PunchClock punchClock) {
        return punchClockDao.late_result(punchClock);
    }
}