package com.gl.pj.sys.entity;

import java.io.Serializable;
import java.sql.Date;

import lombok.Data;
import lombok.NoArgsConstructor;
/**
 * @购物车实体类
 * @author Chenjinbo
 *
 */
@Data
@NoArgsConstructor
public class GlGoodsCart implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8736245079948354063L;
	private Integer id; //ID
	private Integer userid; //用户ID
	private Integer goodsid; //商品ID
	private String goodsname; //商品名称
	private Double goodsprice; //商品单价
	private Integer number; //购买数量
	private Double totalprice;//总金额
 	private Date data; //加入时间



}
