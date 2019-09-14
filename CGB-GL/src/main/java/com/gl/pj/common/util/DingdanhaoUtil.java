package com.gl.pj.common.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
/**
 * 
 * @生成订单号工具类
 * @author Chenjinbo
 *
 */
public class DingdanhaoUtil {
	  public static String getOrdernumber() {
			Random random = new Random();
		    SimpleDateFormat allTime = new SimpleDateFormat("YYYYMMddHHmmSSS");
		    String subjectno = allTime.format(new Date())+random.nextInt(10);       
		    String ddh= subjectno+random.nextInt(10);
		  return ddh;
	  }
	  
	  
		
}
