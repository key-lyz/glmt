package com.gl.pj.sys.entity;

import java.io.Serializable;
import java.sql.Date;

import lombok.Data;
import lombok.NoArgsConstructor;
/**
 * @订单商品实体类
 * @author Chenjinbo
 *
 */
@Data
@NoArgsConstructor
public class GlOrderGoods implements Serializable {
 
	/**
	 * 
	 */
	private static final long serialVersionUID = -4406368738674663210L;
	private Integer id; //ID
	private String dingdanhao; //订单号
	private Integer goodsid; //商品ID
	private String goodsname; //商品名称
	private Double goodsprice; //商品单价
	private Integer number; //购买数量
	private Double totalprice;//总金额
 	private Integer userid; //用户ID
 	private Date data; //加入时间



}
