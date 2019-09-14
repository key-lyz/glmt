package com.gl.pj.sys.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.gl.pj.common.vo.GlUserVo;
import com.gl.pj.sys.entity.GlUser;

@Mapper
public interface GlUserDao {
	//根据用户ID获取用户对象
	@Select("select *from gl_user where id =#{id}")
	GlUser FindObjectByid(@Param("id")Integer id);
	
	//根据用户名获取用户对象
	@Select("select *  from gl_user where username =#{username}")
 	GlUser findUserByUserName(@Param("username")String username);
    
	//修改用户密码
	@Update("update gl_user set password=#{password},salt=#{salt} where id  =#{id}")
	int updatepassword(Integer id,String password,String salt);
	
	
	//删除用户
 	int delObject(@Param("ids")Integer... ids);
	
	//修改用户状态
	@Update("update gl_user set operation = #{status} where id =#{id}")
	int updateStatus(Integer id,Integer status );
	
	//查询用户总数量
	int findCount(@Param("keyworks")String key);
	//查询用户列表
	List<GlUserVo> findAll(@Param("keyworks")String key);
	
	//添加用户
	@Insert("insert into gl_user values (null,#{username},#{password},#{salt},0,#{store},#{jobid},#{email},#{phone},now(),#{operation},null)")
	int insertObject(GlUser userinfo);
	//修改用户
	int updateObject(GlUser userinfo);

}
