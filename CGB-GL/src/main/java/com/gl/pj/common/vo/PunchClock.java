package com.gl.pj.common.vo;


import java.util.Date;

	public class PunchClock {
	    private Integer id;//标识
	    private Integer userid;//用户ID
	    private Date punchinTime;//打卡时间
	    private Date punchoutTime;//签退时间
	    private Date attendanceTime;//考勤时间
	    private String remark;//迟到原因备注
	    private String userip;//ip地址
	    private String loginaddress;//登录地址
	    private String developername;//用户名
		public Integer getId() {
			return id;
		}
		public void setId(Integer id) {
			this.id = id;
		}
		public Integer getUserid() {
			return userid;
		}
		public void setUserid(Integer userid) {
			this.userid = userid;
		}
		public Date getPunchinTime() {
			return punchinTime;
		}
		public void setPunchinTime(Date punchinTime) {
			this.punchinTime = punchinTime;
		}
		public Date getPunchoutTime() {
			return punchoutTime;
		}
		public void setPunchoutTime(Date punchoutTime) {
			this.punchoutTime = punchoutTime;
		}
		public Date getAttendanceTime() {
			return attendanceTime;
		}
		public void setAttendanceTime(Date attendanceTime) {
			this.attendanceTime = attendanceTime;
		}
		public String getRemark() {
			return remark;
		}
		public void setRemark(String remark) {
			this.remark = remark;
		}
		public String getUserip() {
			return userip;
		}
		public void setUserip(String userip) {
			this.userip = userip;
		}
		public String getLoginaddress() {
			return loginaddress;
		}
		public void setLoginaddress(String loginaddress) {
			this.loginaddress = loginaddress;
		}
		public String getDevelopername() {
			return developername;
		}
		public void setDevelopername(String developername) {
			this.developername = developername;
		}
		@Override
		public String toString() {
			return "PunchClock [id=" + id + ", userid=" + userid + ", punchinTime=" + punchinTime + ", punchoutTime="
					+ punchoutTime + ", attendanceTime=" + attendanceTime + ", remark=" + remark + ", userip=" + userip
					+ ", loginaddress=" + loginaddress + ", developername=" + developername + "]";
		}
   
}
