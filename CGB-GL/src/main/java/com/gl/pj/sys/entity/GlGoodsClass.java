package com.gl.pj.sys.entity;

import java.io.Serializable;
import java.sql.Date;

import lombok.Data;
import lombok.NoArgsConstructor;
/**
 * @商品分类实体类
 * @author Chenjinbo
 *
 */
@Data
@NoArgsConstructor
public class GlGoodsClass  implements Serializable {
 /**
	 * 
	 */
	private static final long serialVersionUID = 1435798334983820374L;
private Integer id; //分类ID
 private Integer pid; //父类ID
 private String name; //分类名称
 
	
}
