package com.gl.pj.sys.entity;

import java.io.Serializable;
import java.sql.Date;

import lombok.Data;
import lombok.NoArgsConstructor;
/**
 * @店铺实体类
 * @author Chenjinbo
 *
 */
@Data
@NoArgsConstructor
public class GlStore  implements Serializable{
 /**
	 * 
	 */
	private static final long serialVersionUID = 4686697145725045415L;
/**
	 * 
	 */
private Integer id;//店铺ID
 private String name;//店铺名称
 private String addr;//店铺地址
 private Date date;//添加时间
	
}
