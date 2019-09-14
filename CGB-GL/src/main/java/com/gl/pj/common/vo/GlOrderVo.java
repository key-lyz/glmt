package com.gl.pj.common.vo;

import java.io.Serializable;
import java.sql.Date;

import com.gl.pj.common.util.DingdanhaoUtil;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class GlOrderVo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8395468890399894785L;
	private Integer id;  //
	private String dingdanhao; //订单号
	private String dd; //订单号
 	private Double totalprice; //订单总价格
 	private Object goodsinfo; //商品信息
 	private Integer userid; //下单用户ID
	private String username; //下单用户
	private Date date; //下单时间

}
