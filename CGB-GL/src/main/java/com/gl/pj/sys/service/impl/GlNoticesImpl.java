package com.gl.pj.sys.service.impl;
import java.sql.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.gl.pj.common.exception.ServiceException;
import com.gl.pj.sys.dao.GlNoticesDao;
import com.gl.pj.sys.entity.GlNotices;
import com.gl.pj.sys.service.GlNoticesService;

@Service
public class GlNoticesImpl implements GlNoticesService {

	@Autowired
	private GlNoticesDao glNoticesDao;

	/**添加公告**/
	@Override
	public int insertNotices(GlNotices notices) {
		if (notices.getContacts()=="") throw new ServiceException("公告内容不能为空");
		try {
			int rows = glNoticesDao.insertNotices(notices);
			System.out.println(rows);
		} catch (Exception e) {
			throw new ServiceException("添加公告失败");
		}
		return 0;
	}

	/**删除公告**/
	@Override
	public int deleteNotices(Integer[] ids) {
		int rows = glNoticesDao.deleteNotices(ids);
		return rows;
	}

	/**查询历史公告**/
	@Override
	public List<GlNotices> findPageObjects(Integer id, String contacts, Date createtime) {
		List<GlNotices> result = glNoticesDao.findPageObjects(id, contacts, createtime);
		return result;
	}

}
