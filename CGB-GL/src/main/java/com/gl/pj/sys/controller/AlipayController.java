package com.gl.pj.sys.controller;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
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

import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.gl.alipay.AlipayConfig;
import com.gl.pj.common.exception.ServiceException;
import com.gl.pj.common.util.DingdanhaoUtil;
import com.gl.pj.sys.dao.GlGoodsCartDao;
import com.gl.pj.sys.dao.GlPayDao;
import com.gl.pj.sys.entity.GlGoodsCart;
import com.gl.pj.sys.entity.GlOrder;
import com.gl.pj.sys.entity.GlOrderGoods;
import com.gl.pj.sys.entity.GlUser;
import com.gl.pj.sys.service.GlGoodsService;
@Controller
public class AlipayController {
    private static final Logger LOGGER = LoggerFactory.getLogger(AlipayController.class);
    
    @Autowired
    GlPayDao paydao;
	@Autowired
	GlGoodsCartDao cartdao; //注入购物车接口
	@Autowired
	GlGoodsService goodsService;  //注入商品业务层
    
    /**
     * 对应官方例子   alipay.trade.page.pay.jsp
     *
     * @Description: 前往支付宝第三方网关进行支付
     * Copyright: Copyright (c) 2019
     * @Classname AlipayController
     * @Description notify_url 和 return_url 需要外网可以访问，建议内网穿透
     * @Date 2019/6/18 20:40
     * @Created by 张哈哈
     */
    @PostMapping("/alipay/doPay")
    @ResponseBody
    public String goAlipay(Integer[] param,Double money, HttpServletRequest request, HttpServletRequest response) throws Exception {
    	StringBuffer url = request.getRequestURL();  
    	String tempContextUrl = url.delete(url.length() - request.getRequestURI().length(), url.length()).append("/").toString();  
    	System.out.println("param:"+param);
    	  GlOrder order =new GlOrder();
			DingdanhaoUtil  ddh=new DingdanhaoUtil();
			String dingdan = ddh.getOrdernumber();//生成订单号
			
    	String returnurl=tempContextUrl+"alipay/alipayReturnNotice";
    	String notifyurl=tempContextUrl+"alipay/alipayNotifyNotice";

     	//获取登陆用户
		GlUser user=(GlUser)SecurityUtils.getSubject().getPrincipal();
		Integer userid = user.getId();
		String username=user.getUsername();
 		 //验证购物车是否还存在
	    for (Integer caid : param) {
	    	 GlGoodsCart cartinfo = cartdao.findObjectsByid(caid, userid);
	    	 if (cartinfo==null)
	    		 throw new ServiceException("该商品已结算");
	    }
	    //1.获取当前购物车的number信息
		//   Double getCartMoney = cartdao.findCartMoney(param, userid);
		   int getCartNumber = cartdao.findCartNumber(param, userid);
	//    System.out.println(getCartMoney);
		    //写入订单表
		   
		 
			 //3.2写入订单商品表  
		    GlOrderGoods orderGoods=new GlOrderGoods();
		    for (Integer caid : param) {
		    	//3.2.1获取购物车的相关信息
		    	 GlGoodsCart cartinfo = cartdao.findObjectsByid(caid, userid);
				   Double CartMoney = cartdao.findCartMoneyById(caid, userid);
				   System.out.println("jine:"+CartMoney);
				   int CartNumber = cartdao.findCartNumberById(caid, userid);
				   Double totalMoney=CartMoney*CartNumber;
				   String goodsname = (String)goodsService.findGoodsValueByid("name", cartinfo.getGoodsid());
		    	orderGoods.setDingdanhao(dingdan);
		    	orderGoods.setGoodsid(cartinfo.getGoodsid());
		    	orderGoods.setGoodsname(goodsname);
		    	orderGoods.setGoodsprice(CartMoney);
		    	orderGoods.setNumber(CartNumber);
		    	orderGoods.setTotalprice(totalMoney);
		    	orderGoods.setUserid(userid);
		    	cartdao.insertOrderGoodsObject(orderGoods);
			}
		    
			 //根据商品计算总金额
	    	 double totalmoney = cartdao.findOrderGoodsTotalMoney(dingdan);
		    order.setDingdanhao(dingdan); 
		    order.setTotalnumber(getCartNumber);
		    order.setTotalprice(totalmoney); 
		    order.setUserid(userid);
		    order.setUsername(user.getUsername());
		    order.setState(0);
		    order.setType("支付宝");
		    cartdao.insertOrderObject(order);
		 
	    
        //获得初始化的AlipayClient
        AlipayClient alipayClient = new DefaultAlipayClient(
                AlipayConfig.gatewayUrl,
                AlipayConfig.app_id,
                AlipayConfig.merchant_private_key,
                "json",
                AlipayConfig.charset,
                AlipayConfig.alipay_public_key,
                AlipayConfig.sign_type);
        //设置请求参数
        AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();
        alipayRequest.setReturnUrl(returnurl);
        alipayRequest.setNotifyUrl(notifyurl);
 
        DecimalFormat dec = new DecimalFormat("0.00");
        String s= dec.format(totalmoney);
       
        //商户订单号，商户网站订单系统中唯一订单号，必填
        String out_trade_no = dingdan;
        //付款金额，必填
        System.out.println("付款金额"+s);
        String total_amount =  new String(s.getBytes("ISO-8859-1"),"UTF-8");
        //订单名称，必填
        String subject ="购物车结算";
        //商品描述，可空
        String body = Arrays.toString(param);
        // 该笔订单允许的最晚付款时间，逾期将关闭交易。取值范围：1m～15d。m-分钟，h-小时，d-天，1c-当天（1c-当天的情况下，无论交易何时创建，都在0点关闭）。 该参数数值不接受小数点， 如 1.5h，可转换为 90m。
        String timeout_express = "10m";
       
        //例子去官方api找
        alipayRequest.setBizContent("{\"out_trade_no\":\"" + out_trade_no + "\","
                + "\"total_amount\":\"" + total_amount + "\","
                + "\"subject\":\"" + subject + "\","
                + "\"body\":\"" + body + "\","
                + "\"timeout_express\":\"" + timeout_express + "\","
                + "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}");

        //请求
        String result = alipayClient.pageExecute(alipayRequest).getBody();

        return result;
    }
    
    
 
    /**
     * 
     * @同步请求
     * @param request
     * @param response
     * @return
     * @throws  Exception 
     * @throws Exception
     */
    @RequestMapping(value = "/alipay/alipayReturnNotice")
    @ResponseBody
    public String alipayReturnNotice(HttpServletRequest request, HttpServletRequest response) throws  Exception   {
     	//获取支付宝GET过来反馈信息
    	Map<String,String> params = new HashMap<String,String>();
    	Map requestParams = request.getParameterMap();
    	for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext();) {
    		String name = (String) iter.next();
    		String[] values = (String[]) requestParams.get(name);
    		String valueStr = "";
    		for (int i = 0; i < values.length; i++) {
    			valueStr = (i == values.length - 1) ? valueStr + values[i]
    					: valueStr + values[i] + ",";
    		}
    		//乱码解决，这段代码在出现乱码时使用。如果mysign和sign不相等也可以使用这段代码转化
    		valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
    		params.put(name, valueStr);
    	}
    	
    	//获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以下仅供参考)//
    	//商户订单号
    	String out_trade_no = new String(request.getParameter("out_trade_no").getBytes("ISO-8859-1"),"UTF-8");
    	//支付宝交易号
    	String trade_no = new String(request.getParameter("trade_no").getBytes("ISO-8859-1"),"UTF-8");
    	//交易状态
    	 String trade_status = (String) params.get("trade_status");
    	//获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以上仅供参考)//
    	//计算得出通知验证结果
        boolean verify_result = AlipaySignature.rsaCheckV1(params, AlipayConfig.alipay_public_key, AlipayConfig.charset, AlipayConfig.sign_type); //调用SDK验证签名
    	if(verify_result){ 
    		//获取登陆用户
    		GlUser user=(GlUser)SecurityUtils.getSubject().getPrincipal();
    		Integer userid = user.getId();
    		
    		//查询订单状态
    		int state = cartdao.findOrderState(out_trade_no, userid);
    		if (state==1) throw new ServiceException("订单已结算");

    		//修改订单状态
    		try {
    			int upstate = cartdao.updateOrderState(out_trade_no, userid);
    		  Integer[] gids = cartdao.findOrderGoods(out_trade_no, userid);
    			//删除购物车数据
    			 cartdao.deleteObjects(gids, userid);
    			
			} catch (Exception e) {
				e.printStackTrace();
				throw new ServiceException("修改订单状态失败");
			}
    		System.out.println("支付验证成功");
    		//写入订单
    	}else{
    		System.out.println("支付验证失败");
    		throw new ServiceException("支付验证失败");
    	}
		return "<html>\r\n" + 
				"<head>\r\n" + 
				"<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\r\n" + 
				"<meta name=\"viewport\" content=\"width=device-width\">\r\n" + 
				"<meta name=\"robots\" content=\"noindex, nofollow\" />\r\n" + 
				"<meta http-equiv=\"refresh\" content=\"6;url='/';\">\r\n" + 
				"<meta charset=\"UTF-8\">\r\n" + 
				"<!--[if IE 8]>\r\n" + 
				"<style>\r\n" + 
				".ie8 .alert-circle,.ie8 .alert-footer{display:none}.ie8 .alert-box{padding-top:75px}.ie8 .alert-sec-text{top:45px}\r\n" + 
				"</style>\r\n" + 
				"<![endif]-->\r\n" + 
				"\r\n" + 
				"<title>支付成功,请稍候...</title>\r\n" + 
				"<style>\r\n" + 
				"body{margin:0;padding:0;background-image: url(//cn.bing.com/az/hprichbg/rb/PenaNationalPalace_ZH-CN12058841312_1920x1080.jpg);background-repeat:no-repeat;font-family:Arial,'微软雅黑','宋体',sans-serif}.main{position:absolute;left:calc(50% - 200px);top:calc(50% - 13em)}.alert-box{display:none;position:relative;margin:auto;padding:180px 85px 22px;border-radius:10px 10px 0 0;background-color:rgba(255,255,255,.75);box-shadow:5px 9px 17px rgba(102,102,102,.75);width:286px;color:#FFF;text-align:center}.alert-box p{margin:0}.alert-circle{position:absolute;top:-50px;left:111px}.alert-sec-circle{stroke-dashoffset:0;stroke-dasharray:735;transition:stroke-dashoffset 1s linear}.alert-sec-text{position:absolute;top:11px;left:190px;width:76px;color:#000;font-size:68px}.alert-sec-unit{font-size:34px}.alert-body{margin:35px 0}.alert-head{color:#242424;font-size:28px}.alert-concent{margin:25px 0 14px;color:#7B7B7B;font-size:18px}.alert-concent p{line-height:27px}.alert-btn{display:block;border-radius:10px;background-color:#4AB0F7;height:55px;line-height:55px;width:286px;color:#FFF;font-size:20px;text-decoration:none;letter-spacing:2px}.alert-btn:hover{background-color:#6BC2FF}.alert-footer{margin:0 auto;height:42px;width:120px}.alert-footer-icon{float:left}.alert-footer-text{float:left;border-left:2px solid #EEE;padding:3px 0 0 5px;height:40px;color:#0B85CC;font-size:12px;text-align:left}.alert-footer-text p{color:#7A7A7A;font-size:22px;line-height:18px}\r\n" + 
				"</style>\r\n" + 
				" \r\n" + 
				"</head>\r\n" + 
				"<body class=\"ie8\" style=\"\">\r\n" + 
				"<div class=\"main\">\r\n" + 
				"	<div id=\"js-alert-box\" class=\"alert-box\" style=\"display:block\">\r\n" + 
				"		<svg class=\"alert-circle\" width=\"234\" height=\"234\"><circle cx=\"117\" cy=\"117\" r=\"108\" fill=\"#FFF\" stroke=\"#43AEFA\" stroke-width=\"17\"></circle><circle id=\"js-sec-circle\" class=\"alert-sec-circle\" cx=\"117\" cy=\"117\" r=\"108\" fill=\"transparent\" stroke=\"#F4F1F1\" stroke-width=\"18\" transform=\"rotate(-90 117 117)\" style=\"stroke-dashoffset:-514px\"></circle><text class=\"alert-sec-unit\" x=\"100\" y=\"172\" fill=\"#BDBDBD\">秒</text></svg>\r\n" + 
				"		<div id=\"js-sec-text\" class=\"alert-sec-text\">\r\n" + 
				"			3\r\n" + 
				"		</div>\r\n" + 
				"		<div class=\"alert-body\">\r\n" + 
				"			<div id=\"js-alert-head\" class=\"alert-head\">\r\n" + 
				"				支付成功,请稍候...			</div>\r\n" + 
				"			<div class=\"alert-concent\">\r\n" + 
				"				<p>\r\n" + 
				"					CGB1904....\r\n" + 
				"				</p>\r\n" + 
				"			</div>\r\n" + 
				"			<a id=\"js-alert-btn\" class=\"alert-btn\" href=\"/\">立即前往</a>\r\n" + 
				"		</div>\r\n" + 
				"		<div class=\"alert-footer clearfix\">\r\n" + 
				"			<svg width=\"46px\" height=\"42px\" class=\"alert-footer-icon\"><circle fill-rule=\"evenodd\" clip-rule=\"evenodd\" fill=\"#7B7B7B\" stroke=\"#DEDFE0\" stroke-width=\"2\" stroke-miterlimit=\"10\" cx=\"21.917\" cy=\"21.25\" r=\"17\"></circle><path fill=\"#FFF\" d=\"M22.907,27.83h-1.98l0.3-2.92c-0.37-0.22-0.61-0.63-0.61-1.1c0-0.71,0.58-1.29,1.3-1.29s1.3,0.58,1.3,1.29 c0,0.47-0.24,0.88-0.61,1.1L22.907,27.83z M18.327,17.51c0-1.98,1.61-3.59,3.59-3.59s3.59,1.61,3.59,3.59v2.59h-7.18V17.51z M27.687,20.1v-2.59c0-3.18-2.59-5.76-5.77-5.76s-5.76,2.58-5.76,5.76v2.59h-1.24v10.65h14V20.1H27.687z\"></path><circle fill-rule=\"evenodd\" clip-rule=\"evenodd\" fill=\"#FEFEFE\" cx=\"35.417\" cy=\"10.75\" r=\"6.5\"></circle><polygon fill=\"#7B7B7B\" stroke=\"#7B7B7B\" stroke-linecap=\"round\" stroke-linejoin=\"round\" stroke-miterlimit=\"10\" points=\"35.417,12.16 32.797,9.03 31.917,10.07 35.417,14.25 42.917,5.29 42.037,4.25 \"></polygon></svg>\r\n" + 
				"			<div class=\"alert-footer-text\">\r\n" + 
				"				<p>secure</p>安全加密\r\n" + 
				"			</div>\r\n" + 
				"		</div>\r\n" + 
				"	</div>\r\n" + 
				"</div>\r\n" + 
				"<script type=\"text/javascript\">\r\n" + 
				"function alertSet(e) {\r\n" + 
				"	\r\n" + 
				"	document.getElementById(\"js-alert-box\").style.display = \"block\", document.getElementById(\"js-alert-head\").innerHTML = e;\r\n" + 
				"	var t = 5,\r\n" + 
				"		n = document.getElementById(\"js-sec-circle\");\r\n" + 
				"	document.getElementById(\"js-sec-text\").innerHTML = t, setInterval(function() {\r\n" + 
				"\r\n" + 
				"		if (0 == t) ;\r\n" + 
				"		else {\r\n" + 
				"			t -= 1, document.getElementById(\"js-sec-text\").innerHTML = t;\r\n" + 
				"			var e = Math.round(t / 5 * 735);\r\n" + 
				"			n.style.strokeDashoffset = e - 735\r\n" + 
				"		}\r\n" + 
				"	}, 970)\r\n" + 
				"} </script>\r\n" + 
				"<script>alertSet(\"支付成功,请稍候...\");</script>\r\n" + 
				"</body>\r\n" + 
				"</html>";
    }

    
   
 

}
 