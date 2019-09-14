package com.gl.pj.sys.entity;

import java.io.Serializable;
import java.sql.Date;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @考勤表实体类
 * @author Chenjinbo
 *
 */
@Data
@NoArgsConstructor
public class GlKaoqin  implements Serializable{/**
	 * 
	 */
	private static final long serialVersionUID = 987273754544597683L;
	
	 private Integer id;
	 private Integer userid;
	 private String punchinTime;
	 private String punchoutTime;
	 private String remark;
	 private Date attendanceTime;
	 private String userip;
	 private String loginaddress;
	 private String developername;
	 
	 
	 
	 
	

}
