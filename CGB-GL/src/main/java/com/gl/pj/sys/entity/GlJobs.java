package com.gl.pj.sys.entity;

import java.io.Serializable;

import lombok.Data;
import lombok.NoArgsConstructor;
/**
 * @职位实体类
 * @author Chenjinbo
 *
 */
@Data
@NoArgsConstructor
public class GlJobs implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5918452009736179407L;

	private Integer id;//职位ID
	private String name;//职位名称
}
