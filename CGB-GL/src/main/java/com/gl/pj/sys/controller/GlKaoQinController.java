package com.gl.pj.sys.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gl.pj.common.vo.JsonType;
import com.gl.pj.sys.dao.GlKaoqinDao;
import com.gl.pj.sys.entity.GlKaoqin;
@Controller
@RequestMapping("/kaoqin/")
public class GlKaoQinController {
   @Autowired
   GlKaoqinDao kaoqindao;
   @RequestMapping("doFindObject")
    @ResponseBody
    public JsonType doFindObject(String key,Integer limit ,Integer page,String riqi)  {
     System.out.println("sousuo"+key);
 		/**开始位置*/
 		int startIndex=(page-1)*limit;
	 	List<GlKaoqin> list = kaoqindao.findObject(key,limit,startIndex,riqi);
		int total = kaoqindao.findObjectCount(key,riqi);
          return new JsonType(list,total);
    }
    
  
 

}
 