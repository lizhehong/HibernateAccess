package cn.ITHong.dao.impl;


import static org.junit.Assert.*;

import java.io.Serializable;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;








import cn.ITHong.dao.EntityDao;

import cn.ITHong.domain.User;


public class EntityDaoImplTest {
	private EntityDao<User> uDao = new EntityDaoImpl<User>();
	/**
	 * 查找所有的数据
	 * */
	 
	public void testAllQuery() {
		List<User> uList = uDao.getAllEntity(User.class);
		assertNotNull(uList);
	}
	 
	public void testFindByID() throws Exception{
		Long id = 1L;
		EntityDao<User> uDao = EntityDaoFactory.getInstance(EntityDaoImpl.class);
		User user = uDao.findByID(User.class, id);
		assertEquals(id, user.getUid());	
	}
	 
	public void testSaveEntity(){
		User user = new User();
		user.setUsername("hong");
		user.setPassword("99");
		user.setSex("1");
		user.setPhone("110");
		user.setEmail("273257774@qq.com");
		uDao.saveEntity(user);
		
	}
	 
	public void testUpdateEntry(){
		User user = uDao.findByID(User.class, 1L);
		user.setEmail("111");
		uDao.updateEntry(user);
	}
	
	public void testDeleteEntry(){
		uDao.deleteEntity(User.class, 2L);
	}
	/**
	 * 测试用户登陆
	 * 没有用户返回空
	 * */

	public void testFindEntity(){
		User user = uDao.findEntity(User.class, "小hong", "99");
		assertNotNull(user);
	}
}
