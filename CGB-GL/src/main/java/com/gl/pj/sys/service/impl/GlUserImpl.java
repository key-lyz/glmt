package com.gl.pj.sys.service.impl;

import java.util.List;
import java.util.UUID;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gl.pj.common.exception.ServiceException;
import com.gl.pj.common.vo.GlUserVo;
import com.gl.pj.sys.dao.GlUserDao;
import com.gl.pj.sys.entity.GlUser;
import com.gl.pj.sys.service.GlUserService;
/**
 * @用户实现类
 * 
 * @author tedu
 *
 */
@Service
public class GlUserImpl implements GlUserService {
	
	@Autowired
	private GlUserDao userDao;
   /**
    * @添加用户
    */
	@Override
	public int insertObject(GlUser userinfo) {
		int rows;
		String newsalt = UUID.randomUUID().toString(); //新盐值生成
		SimpleHash salt = new SimpleHash("MD5", userinfo.getPassword(), newsalt);  
		String password = salt.toHex();
		userinfo.setPassword(password);
		userinfo.setSalt(newsalt);
		
		 GlUser checkuser = userDao.findUserByUserName(userinfo.getUsername());
		 if (checkuser!=null)	throw new ServiceException("添加用户失败:该用户名已存在");
		 
		try {
		
			rows = userDao.insertObject(userinfo);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException("添加用户失败");
		}
		return rows;
	}
	/**
	 * @查询用户数量
	 */
	@Override
	public int findCount(String key) {
		int rows = userDao.findCount(key);
		return rows;
	}
	/**
	 * @查询用户列表
	 */
	@Override
	public List<GlUserVo> findAll(String key) {
		List<GlUserVo> list = userDao.findAll(key);
		return list;
	}
	/**
	 * @修改用户状态
	 */
	@Override
	public int updateStatus(Integer id, Integer status) {
		int rows = userDao.updateStatus(id, status);
		return rows;
	}
    /**
     * @删除用户
     */
	@Override
	public int delObject(Integer... ids) {
		 int rows = userDao.delObject(ids);
		return rows;
	}
	/**
	 * @修改用户密码
	 */
	@Override
	public int updatepassword(String old_password,String new_password,String re_new_password) {
		
		if (old_password==null || old_password=="")
			throw new IllegalArgumentException("请输入原密码");
		if (new_password==null || new_password=="")
			throw new IllegalArgumentException("请输入新密码");
		if (re_new_password==null || re_new_password=="")
			throw new IllegalArgumentException("请输入确认密码");
		if (!(re_new_password.equals(new_password)))
			throw new IllegalArgumentException("两次密码不一致");
		
		//获取登陆用户
		GlUser user=(GlUser)SecurityUtils.getSubject().getPrincipal();
		Integer userid = user.getId();
		if (user==null)  throw  new ServiceException("用户不存在"); 
		//查询当前用户密码
 		String userpassword = user.getPassword();
		String salt = user.getSalt();
		SimpleHash sh = new SimpleHash("MD5", old_password, salt);
		old_password = sh.toHex();
		
		if (!userpassword.equals(old_password))
			throw new IllegalArgumentException("原密码不正确");
		
		try {
			String newsalt = UUID.randomUUID().toString(); //新盐值生成
			SimpleHash newsh = new SimpleHash("MD5", new_password, newsalt);  
			new_password=newsh.toHex();
			userDao.updatepassword(userid, new_password,newsalt);
		} catch (Exception e) {
			e.printStackTrace();
			throw  new ServiceException("用户密码修改失败"); 
		}
		return 0;
	}
	/**
	 * @修改用户
	 */
	@Override
	public int updateObject(GlUser userinfo) {
		GlUser checkuser = userDao.FindObjectByid(userinfo.getId());
		if (checkuser==null   ) throw  new ServiceException("修改失败:用户不存在"); 
		
		if (userinfo.getPassword()!=null && userinfo.getPassword()!="") {
			String newsalt = UUID.randomUUID().toString(); //新盐值生成
			SimpleHash sh = new SimpleHash("MD5", userinfo.getPassword(), newsalt);
		    String password = sh.toHex();
		    userinfo.setPassword(password);
		    userinfo.setSalt(newsalt);
		}
		
		try {
			int rows = userDao.updateObject(userinfo);
		} catch (Exception e) {
			throw  new ServiceException("用户修改失败"); 
		}
		
		return 0;
	}
	
	
	

}
