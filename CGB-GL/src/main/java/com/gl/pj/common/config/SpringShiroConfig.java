package com.gl.pj.common.config;
import java.util.LinkedHashMap;

import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.cache.MemoryConstrainedCacheManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Configuration 注解描述的类为一个配置对象,
 * 此对象也会交给spring管理
 */

@Configuration
public class SpringShiroConfig {
	@Bean
	public SecurityManager newSecurityManager(@Autowired Realm realm) {
		DefaultWebSecurityManager sManager=  new DefaultWebSecurityManager();
		 sManager.setRealm(realm);
		return sManager;
	}
	
	@Bean("shiroFilterFactory")
	 public ShiroFilterFactoryBean newShiroFilterFactoryBean(
			 @Autowired SecurityManager securityManager) {
		 ShiroFilterFactoryBean sfBean=
		 new ShiroFilterFactoryBean();
		 sfBean.setSecurityManager(securityManager);
		 sfBean.setLoginUrl("/login");
		 //定义map指定请求过滤规则(哪些资源允许匿名访问,哪些必须认证访问)
		 LinkedHashMap<String,String> map=
				 new LinkedHashMap<>();
		 //静态资源允许匿名访问:"anon"
		 map.put("/assets/**","anon");
		 map.put("/css/**","anon");
		 map.put("/fonts/**","anon");
		 map.put("/js/**","anon");
		 map.put("/layui/**","anon");
		 map.put("/img/**","anon");
		 map.put("/module/**","anon");
		 map.put("/img/**","anon");
		 map.put("/ueditor/**","anon");
 		 map.put("/ajax/doLogin","anon");
 		 map.put("/upload/**","anon");
		 map.put("/logout","logout");
 		 //除了匿名访问的资源,其它都要认证("authc")后访问
		 map.put("/**","user");
		 
		 sfBean.setFilterChainDefinitionMap(map);
		 return sfBean;
	 }
	
	//==========配置授权===========
	//1.配置一个Shiro中Bean对象的生命周期管理器
	/*
	 * @Bean("lifecycleBeanPostProcessor") public LifecycleBeanPostProcessor
	 * newLifecycleBeanPostProcessor() {
	 * 
	 * return new LifecycleBeanPostProcessor(); }
	 */
	 //为目标对象(方法上有@RequiresPermissions注解的)创建代理对象,通过代理对象调用通知 实现授权检测
//	 @DependsOn("lifecycleBeanPostProcessor")
	 @Bean
		public DefaultAdvisorAutoProxyCreator newDefaultAdvisorAutoProxyCreator() {
			 return new DefaultAdvisorAutoProxyCreator();
	}
	 /**
	  * 此对象负责定义切入点以及通知增强
	  * @param securityManager
	  * @return
	  */
	 @Bean
	 public AuthorizationAttributeSourceAdvisor 
	 newAuthorizationAttributeSourceAdvisor(@Autowired SecurityManager securityManager) {
  AuthorizationAttributeSourceAdvisor advisor= new AuthorizationAttributeSourceAdvisor();
	 		 return advisor;
	 }
	
	 //配置shiro缓存对象
	 @Bean
	 public CacheManager newCacheManager(){
	 		 return new MemoryConstrainedCacheManager();
	 	 }
	  @Bean
	  public SecurityManager newSecurityManager(
	  			@Autowired Realm realm,
	  			@Autowired CacheManager cacheManager) {
	  		 DefaultWebSecurityManager sManager= new DefaultWebSecurityManager();
	  		 sManager.setRealm(realm);
	  		 sManager.setCacheManager(cacheManager);
			 sManager.setRememberMeManager(newRememberMeManager());	  	
			 sManager.setSessionManager(newSessionManager());
	  		 return sManager;
	  	 }
	 
	  //配置cookie对象
	     @Bean
		 public CookieRememberMeManager newRememberMeManager() {
			 SimpleCookie c=new SimpleCookie("rememberMe");
			 c.setMaxAge(10*60);
			 CookieRememberMeManager cManager=
			 new CookieRememberMeManager();
			 cManager.setCookie(c);
			 return cManager;
		 }
		//cookie存储时长
	     public DefaultWebSessionManager newSessionManager() {
			 DefaultWebSessionManager sManager=
					 new DefaultWebSessionManager();
			 sManager.setGlobalSessionTimeout(60*60*1000);
			 return sManager;
		 }
	
}
