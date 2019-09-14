package com.gl.pj.sys.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gl.pj.sys.entity.GlOrder;
import com.gl.pj.sys.service.EchartsService;

@RestController("/")
public class EchartsController {
 @Autowired
 private EchartsService echartsService;
    @RequestMapping(value = "EcharsShow")
    public List<GlOrder> find() {
       List<GlOrder> list = echartsService.findObject();
       
        return list;
    }
    
    @GetMapping(value = "Echars.do")
    public String echarts4(Model model){
        System.err.println("========开始");
        return "Echars";
    }

}