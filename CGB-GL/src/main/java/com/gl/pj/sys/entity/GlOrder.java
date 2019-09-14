package com.gl.pj.sys.entity;

import java.io.Serializable;
import java.sql.Date;

import com.gl.pj.common.util.DingdanhaoUtil;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class GlOrder implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8395468890399894785L;
	private Integer id;  //
	private String  type;// 结算类型
	private String dingdanhao; //订单号
	private Integer totalnumber; //订单总数量
	private Double totalprice; //订单总价格
	private Integer userid; //下单用户ID
	private String username; //下单用户
	private Integer state;//订单状态  0= 结算 1=已结算
	private Date date; //下单时间

}
