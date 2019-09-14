package com.gl.pj.sys.entity;
import java.io.Serializable;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class GlNotices implements Serializable {
	
	private static final long serialVersionUID = 465202298474894521L;
	private Integer id;
	private String contacts;
	private String createtime;

}
