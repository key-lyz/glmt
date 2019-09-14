package com.gl.pj.common.web;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gl.pj.common.exception.ServiceException;
import com.gl.pj.common.vo.JsonType;
/**
 * 
 * @异常信息输出格式化
 * @author tedu
 *
 */
@ControllerAdvice
public class GlobalExceptionHandler {
	
		@ExceptionHandler(ServiceException.class)
		@ResponseBody
		public JsonType doHandleRuntimeException(
				RuntimeException e) {
			//输出异常的的详细信息到控制台
			e.printStackTrace();
			//封装异常基本信息
			return new JsonType(e);
		}

}
