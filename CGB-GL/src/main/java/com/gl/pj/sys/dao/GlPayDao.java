package com.gl.pj.sys.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.gl.pj.sys.entity.GlPay;
/**
 * 
 * @支付记录Dao层
 * @author Chenjinbo
 *
 */
@Mapper
public interface GlPayDao {
	/** 查询用户充值记录数量 */
	int findUserObjectCount(@Param("userid")Integer userid,@Param("key")String key);
	/** 查询用户充值记录*/
 	List<GlPay> findUserPayObject(@Param("userid")Integer userid,
 					        @Param("key")String key,
 					        @Param("page")Integer page,
 					        @Param("limit")Integer limit);
 	
	
	
	/** 添加支付记录 */
	@Insert("insert into gl_pay values (null,#{dingdanhao},null,#{userid},#{username},#{money},0,null,now())")
	 int insertObject(GlPay pay);
	/** 查询记录 */
	@Select("select * from gl_pay  where dingdanhao = #{dingdanhao}")
	GlPay findObject(String dingdanhao);
 
	/** 修改状态及其他 */
	@Update("update gl_pay set state=#{state},state=#{state},tradeno=#{tradeno},paytime=now() where dingdanhao =#{dingdanhao}")
	int updateObject(String dingdanhao,Integer state,String tradeno );
	/** 增加用户余额*/
	@Update("update gl_user set money=money+#{money} where id =#{userid}")
	int updateUserObject(Integer userid,Double money);
}