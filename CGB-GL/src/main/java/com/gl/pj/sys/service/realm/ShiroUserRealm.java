package com.gl.pj.sys.service.realm;

import java.util.HashSet;
import java.util.Set;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gl.pj.sys.dao.GlUserDao;
import com.gl.pj.sys.entity.GlUser;

import lombok.extern.slf4j.Slf4j;
@Service
@Slf4j
public class ShiroUserRealm extends AuthorizingRealm {
	@Autowired
	private GlUserDao sysUserDao;

	/** 设置加密算法及加密次数等*/
	@Override
	public void setCredentialsMatcher(CredentialsMatcher credentialsMatcher) {
	   HashedCredentialsMatcher hcm=new HashedCredentialsMatcher();
	   hcm.setHashAlgorithmName("MD5");
	   hcm.setHashIterations(1); //加密次数
		super.setCredentialsMatcher( hcm);
	}
	
	/** 负责授权信息的获取及封装*/
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		log.info("*********************************infolog*******************************");
			//1.获取用户登录ID
		    GlUser user = (GlUser)principals.getPrimaryPrincipal();
		    Integer userId = user.getId();
			//2.基于用户id获取用户拥有的角色(sys_user_roles)
		    //3.基于角色id获取菜单id(sys_role_menus)
			//4.基于菜单id获取权限标识(sys_menus)
			//5.对权限标识信息进行封装并返回
		    Set<String> set=new HashSet<>();
		    	if(userId==1){
		    		set.add("admin");
		    }
		     SimpleAuthorizationInfo info= new SimpleAuthorizationInfo();
		   	    info.setStringPermissions(set);
		   		return info;//返回给授权管理器
	}
	/** 负责认证信息的获取及封装*/
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		//1.获取用户名
		UsernamePasswordToken upToken= (UsernamePasswordToken)token;
		String username=upToken.getUsername();
		//2.基于用户名查询数据库
		GlUser userinfo = sysUserDao.findUserByUserName(username);
		System.out.println(userinfo);
 		//3.判断用户是否存在
		if (userinfo==null)
			throw new UnknownAccountException();
		//4.判断用户是否被禁用
		if (userinfo.getOperation()==1)
			throw new LockedAccountException();
		
		//5.封装用户信息并返回
		ByteSource credentialsSalt= ByteSource.Util.bytes(userinfo.getSalt());
		System.out.println("封装信息"+credentialsSalt);
		
		
		SimpleAuthenticationInfo info=
				new SimpleAuthenticationInfo(
						userinfo,//principal (身份)
						userinfo.getPassword(),//hashedCredentials 已加密的密码
						credentialsSalt, //credentialsSalt
						getName());//realName
				//6.返回封装结果
				return info;//返回值会传递给认证管理器(后续
		
	}

}
