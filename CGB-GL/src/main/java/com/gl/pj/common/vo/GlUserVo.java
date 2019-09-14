package com.gl.pj.common.vo;

import java.io.Serializable;
import java.sql.Date;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class GlUserVo  implements Serializable{
 /**
	 * 
	 */
	private static final long serialVersionUID = 2055625141355809879L;
private Integer id;//用户ID
 private String username;//用户名
 private String password;//用户密码(MD5)
 private String salt; //盐值
 private Double money; //用户余额
 private Integer store;//店铺ID
 private String storename;//店铺名称
 private Integer jobid;  //职位ID
 private String jobname;  //职位名称
 private String email;//邮箱
 private String phone;//手机
 private Date createTime;//添加时间
 private Integer operation;//状态
 private String jurisdiction;//权限
	
}
