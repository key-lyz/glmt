package com.gl.pj.sys.controller;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.gl.pj.common.vo.JsonType;
import com.gl.pj.common.vo.PunchClock;
import com.gl.pj.sys.entity.GlUser;
import com.gl.pj.sys.service.PunchClockManager;

@Controller
@RequestMapping(value = "punch_clock")
public class PunchClockController {
 
    @Autowired
    private PunchClockManager punchClockManager;
 
 
    @RequestMapping(value = "punchClock")
    public ModelAndView punchClock(HttpServletRequest request,
                                   HttpServletResponse response) {
        ModelAndView mv = new ModelAndView("/clock");
        return mv;
    }
 
    @RequestMapping(value = "lateinfo")
    public ModelAndView lateinfo(HttpServletRequest request,
                                 HttpServletResponse response) {
        ModelAndView mv = new ModelAndView("/lateinfo");
        return mv;
    }
 
    //获取打卡时间
    @RequestMapping(value = "in_time.do")
    @ResponseBody
    public JsonType in_time() throws Exception {
        String in_time = "";
        //获取当前操作的用户
        GlUser user = (GlUser) SecurityUtils.getSubject().getPrincipal();//集成shiro
        //user类里面只需要用户id和用户名，各位自由发挥
        PunchClock punchClock = new PunchClock();
        SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");//打卡时间格式
        punchClock.setUserid(user.getId());
        PunchClock pc = punchClockManager.if_punchin(punchClock);
        
        if(pc == null){
             in_time = "当前暂未打卡!";
        }else {
            
        	in_time =format.format(pc.getPunchinTime());
        	
        }
        return new JsonType(in_time);
    }
    //获取签退时间
    @RequestMapping(value = "out_time.do")
    @ResponseBody
    public JsonType out_time() throws Exception {
        String out_time = "";
        //获取当前操作的用户
        GlUser user = (GlUser) SecurityUtils.getSubject().getPrincipal();
        PunchClock punchClock = new PunchClock();
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");//考勤时间格式化
        SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");//打卡时间格式化
        punchClock.setAttendanceTime(dateFormat.parse(dateFormat.format(date)));
        punchClock.setUserid(user.getId());
        PunchClock pc = punchClockManager.if_punchout(punchClock);
        if(pc.getPunchoutTime() == null){
            out_time = "当前暂未签退！";
        }else {
            out_time = format.format(pc.getPunchoutTime());
        }
        return new JsonType(out_time);
    }
 
    //上班打卡
    @RequestMapping(value = "punch_in.do")
    @ResponseBody
    public int punch_in(String loginaddress, HttpServletRequest request) throws Exception {
        //操作记录条数，初始化为0
        int resultTotal = 0;
        //获取用户IP地址
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        if (ip == null || ip.length() == 0 || ip.indexOf(":") > -1) {
            try {
                ip = InetAddress.getLocalHost().getHostAddress();
            } catch (UnknownHostException e) {
                ip = null;
            }
        }
        System.out.println("daka ");

        //获取当前操作的用户
        GlUser user = (GlUser) SecurityUtils.getSubject().getPrincipal();
       
        //打卡控制
        PunchClock punchClock = new PunchClock();
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");//考勤时间格式化
        SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");//打卡时间格式化
        Date inTime = format.parse("09:20:00");
        Date outTime = format.parse("24:00:00");
        
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        System.out.println(df.format(new Date()));// new Date()为获取当前系统时间
        
        punchClock.setPunchinTime(format.parse(format.format(date)));
        punchClock.setUserid(user.getId());
        punchClock.setDevelopername(user.getUsername());
        Date nowTime = format.parse(format.format(date));//当前时分秒
        punchClock.setAttendanceTime(dateFormat.parse(dateFormat.format(date)));
        punchClock.setUserip(ip);
        punchClock.setLoginaddress(loginaddress);
        //先查询用户是否已经打过卡
        if ( punchClockManager.if_punchin(punchClock) == null) {
            if (nowTime.before(outTime) && nowTime.after(inTime)) {//迟到
                punchClockManager.add_in(punchClock);
                resultTotal = -2;
            } else if (nowTime.after(outTime)) {//缺席
                resultTotal = -3;
            } else if (nowTime.before(inTime)){
                resultTotal = punchClockManager.add_in(punchClock);
            }
        }else{
            resultTotal = -4; //已经打过卡了
        }
        return resultTotal;
    }

    //迟到说明情况
    @RequestMapping(value = "late.do")
    @ResponseBody
    public int late(String remark) throws Exception {
        //获取当前操作的用户
        GlUser user = (GlUser) SecurityUtils.getSubject().getPrincipal();
        int resultTotal = 0;
        PunchClock punchClock = new PunchClock();
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");//考勤时间格式化
        punchClock.setAttendanceTime(dateFormat.parse(dateFormat.format(date)));
        punchClock.setUserid(user.getId());
        punchClock.setRemark(remark);
        resultTotal = punchClockManager.late_result(punchClock);
        return resultTotal;
    }

 
  //下班签退
    @RequestMapping(value = "punch_out.do")
    @ResponseBody
    public int Punch_out(HttpServletRequest request) throws Exception {
    	
        //获取当前操作的用户
        GlUser user = (GlUser) SecurityUtils.getSubject().getPrincipal();
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");//考勤时间格式化
        SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");//打卡时间格式化
        PunchClock punchClock = new PunchClock();
        punchClock.setPunchoutTime(format.parse(format.format(date)));
        Date nowTime = format.parse(format.format(date));//当前时分秒
        punchClock.setUserid(user.getId());
        int resultTotal = 0;
        Date inTime = format.parse("9:00:00");
        //防止用户重复签退
        if(punchClockManager.if_punchout(punchClock).getPunchoutTime() == null) {
            if (nowTime.before(inTime)) {//早退提示
                resultTotal = -2;
            } else if (nowTime.after(inTime)){
                resultTotal = punchClockManager.up_out(punchClock);
            }
        }else{
            resultTotal = -3;
        }
        return resultTotal;
    }
}
