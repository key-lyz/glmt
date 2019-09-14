package com.gl.pj.sys.controller;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.gl.alipay.AlipayConfig;
import com.gl.pj.common.exception.ServiceException;
import com.gl.pj.common.util.DingdanhaoUtil;
import com.gl.pj.common.vo.JsonType;
import com.gl.pj.sys.dao.GlPayDao;
import com.gl.pj.sys.entity.GlPay;
import com.gl.pj.sys.entity.GlUser;
import com.gl.pj.sys.service.GlPayService;
@Controller
@RequestMapping("/pay/")
public class GlPayController {
   @Autowired
   GlPayService service;
   @RequestMapping("doObject")
    @ResponseBody
    public JsonType doObject(String key,Integer limit ,Integer page)  {
    	//获取登陆用户
		GlUser user=(GlUser)SecurityUtils.getSubject().getPrincipal();
		Integer userid = user.getId();
		 System.out.println(limit);
		 System.out.println(page);
 		/**开始位置*/
 		int startIndex=(page-1)*limit;
 		System.out.println("开始位置"+startIndex);
	 	List<GlPay> list = service.findUserPayObject(userid, key, page, limit);
		int total = service.findUserObjectCount(userid, key);
		System.out.println("总数量"+total);
		 System.out.println("列表"+list);
        return new JsonType(list,total);
    }
    
  
 

}
 