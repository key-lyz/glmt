package com.gl.pj.sys.entity;

import java.io.Serializable;
import java.sql.Date;

import lombok.Data;
import lombok.NoArgsConstructor;
/**
 * @充值记录实体类
 * @author Chenjinbo
 *
 */
@Data
@NoArgsConstructor
public class GlPay implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9053120069442124913L;
	private Integer id; //ID
	private String dingdanhao; //订单号
	private Integer userid; //用户ID
	private String username; //用户名
	private Double money; //充值金额
	private Integer state; //充值状态
	private Date paytime;//充值时间
	private Date createtime;//创建时间
	

}
