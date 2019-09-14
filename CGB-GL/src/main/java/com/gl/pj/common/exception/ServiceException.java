package com.gl.pj.common.exception;
/**
 * 
 * @业务层异常显示类
 * @author tedu
 *
 */
public class ServiceException extends RuntimeException{
	private static final long serialVersionUID = 8908808589367133387L;
	public ServiceException() {
		super();
	}
	public ServiceException(String message) {
		super(message);
	}
	public ServiceException(Throwable cause) {
		super(cause);
	}
}
