package com.gl.pj.common.vo;

import java.io.Serializable;
import java.sql.Date;

import lombok.Data;
import lombok.NoArgsConstructor;
/**
 * 
 * @商品列表VO类  此类主要包装了分类名称
 * @author Chenjinbo
 *
 */
@Data
@NoArgsConstructor
public class GlGoodsVo implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8332158856140419914L;
	private Integer id; //商品ID
	private String name; //商品名称
	private String image; //商品图片
	private Integer classid; //分类ID
	private String classname; //分类名称
	private Double price; //商品价格
	private Integer stock; //商品库存
	private String remark; //商品备注



}
