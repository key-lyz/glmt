package com.gl.pj.common.vo;

import lombok.Data;
import lombok.NoArgsConstructor;
/**
 * 
 * @通用Json格式返回
 * @author Chenjinbo
 *
 */
@Data
@NoArgsConstructor
public class JsonType {
	Integer code=0; //0代表成功//1代表失败
	Object list;  //数据列表
	Integer total; //数据总数量
	String msg="ok"; //返回提示
	
	/**
	 * 
	 * @构造方法可传入数据及总数量
	 * @param list
	 * @param total
	 */
	public  JsonType(Object list,Integer total)
    {
   	 this.list=list;
   	 this.total=total;
    }
	
	/**
	 * @构造方法可传入异常且将code 改为1
	 * @param e
	 */
     public  JsonType(Throwable e)
     {
    	 this.code=1;
    	 this.msg=e.getMessage();
     }
     /**
      * @构造方法传入提示
      * @param msg
      */
     public  JsonType(String msg)
     {
    	 this.msg=msg;
     }
	
	
	
}
