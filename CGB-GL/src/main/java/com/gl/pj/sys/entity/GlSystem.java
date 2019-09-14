package com.gl.pj.sys.entity;

import java.io.Serializable;

import lombok.Data;
import lombok.NoArgsConstructor;

 @Data
 @NoArgsConstructor
public class GlSystem implements Serializable {
	
	/**
	 * @系统设置类
	 */
	private static final long serialVersionUID = -5052047112744856983L;
	private String sitename; //网站名称
	private String sitekey; //网站关键字
	private String sitedesc;//网站描述
	private String sitenotice;//网站公告
	
}
