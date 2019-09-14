package com.gl.pj.sys.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.gl.pj.common.util.ExcelUtils;
import com.gl.pj.common.vo.OrderCountVo;
import com.gl.pj.common.vo.UserCountVo;
import com.gl.pj.sys.dao.OrderCountDao;
import com.gl.pj.sys.dao.UserCountDao;
import com.gl.pj.sys.entity.ExcelData;

@RestController
@RequestMapping("/excel")
public class ExcelController {
	@Autowired
	OrderCountDao OrderCountDao;
	@Autowired
	UserCountDao userCountDao;
	
     @RequestMapping(value = "/userexport")
        public void userexcel(HttpServletResponse response,String key ,String riqi,Integer page,Integer limit) throws Exception {
    	 /**每页显示数*/
 		int pageSize=limit;
  		/**开始位置*/
  		int startIndex=(page-1)*pageSize;
  		List<UserCountVo> list = userCountDao.findUserMoneyCount(startIndex, pageSize, key, riqi);
  		
     	 ExcelData data = new ExcelData();
            data.setName("用户销售额统计");
            //添加表头
            List<String> titles = new ArrayList();
            //for(String title: excelInfo.getNames())
            titles.add("员工编号");
            titles.add("员工姓名");
            titles.add("销售额");
            data.setTitles(titles);
            //添加列
            List<List<Object>> rows = new ArrayList();
            List<Object> row = null;
            System.out.println("数据条数"+list.size());
            
         	for (UserCountVo uservo : list) {
        		 row=new ArrayList();
                 row.add(uservo.getId());
                 row.add(uservo.getName());
                 row.add(uservo.getXse());
                 rows.add(row);
                 
    		} 
          
        	
        
         data.setRows(rows);
            SimpleDateFormat fdate=new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
            String fileName=fdate.format(new Date())+"_user.xls";
            ExcelUtils.exportExcel(response, fileName, data);
        }
     
     
     @RequestMapping(value = "/export")
     public void excel(HttpServletResponse response,String key ,String year,Integer page,Integer limit) throws Exception {
 	 /**每页显示数*/
		int pageSize=limit;
		/**开始位置*/
		int startIndex=(page-1)*pageSize;
    List<OrderCountVo> orderif = OrderCountDao.findOrderCount(startIndex,pageSize,  key, year);
 	 ExcelData data = new ExcelData();
         data.setName("用户信息数据");
         //添加表头
         List<String> titles = new ArrayList();
         //for(String title: excelInfo.getNames())
         titles.add("商品编号");
         titles.add("商品名称");
         titles.add("一月份");
         titles.add("二月份");
         titles.add("三月份");
         titles.add("四月份");
         titles.add("五月份");
         titles.add("六月份");
         titles.add("七月份");
         titles.add("八月份");
         titles.add("九月份");
         titles.add("十月份");
         titles.add("十一月份");
         titles.add("十二月份");
         data.setTitles(titles);
         //添加列
         List<List<Object>> rows = new ArrayList();
         List<Object> row = null;
         System.out.println("数据条数"+orderif.size());
         
       
     	for (OrderCountVo orderCountVo : orderif) {
     		 row=new ArrayList();
             row.add(orderCountVo.getId());
              row.add(orderCountVo.getName());
              row.add(orderCountVo.getOne());
              row.add(orderCountVo.getTwo());
              row.add(orderCountVo.getThree());
              row.add(orderCountVo.getFour());
              row.add(orderCountVo.getFive());
              row.add(orderCountVo.getSix());
              row.add(orderCountVo.getSeven());
              row.add(orderCountVo.getEight());
              row.add(orderCountVo.getNine());
              row.add(orderCountVo.getTen());
              row.add(orderCountVo.getEleven());
              row.add(orderCountVo.getTwelve());
              rows.add(row);
              
 		} 
       
     	
     
      data.setRows(rows);
         SimpleDateFormat fdate=new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
         String fileName=fdate.format(new Date())+".xls";
         ExcelUtils.exportExcel(response, fileName, data);
     }
}