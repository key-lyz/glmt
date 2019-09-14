package com.gl.pj.common.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MyPicConfig implements WebMvcConfigurer {
 
 public void addResourceHandlers(ResourceHandlerRegistry registry) {
 
   //  registry.addResourceHandler("/upload/**").addResourceLocations("classpath:/static/upload/");

 }
 }
 