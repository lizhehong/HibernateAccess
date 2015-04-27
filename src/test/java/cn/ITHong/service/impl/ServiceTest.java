package cn.ITHong.service.impl;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Test;

import cn.ITHong.domain.Permission;
import cn.ITHong.domain.Role;
import cn.ITHong.domain.User;
import cn.ITHong.util.HibernateUtils;

public class ServiceTest extends HibernateUtils{
	static{
		url="cn/ITHong/domain/hibernate.cfg.xml";
	}
	
	public void test() {
		Long id = 1L;
		Session session = sessionFactory.getCurrentSession();
		Transaction transaction = session.beginTransaction();
		
		List<User> uList = session.createQuery("from User u left outer join fetch u.rSet r left outer join fetch r.pSet p").list();
		Set<User> uSet = new HashSet<User>(uList);
		for(User user:uSet){
			Set<Role>rSet = user.getrSet();
			for(Role role:rSet){
				System.out.println(user.getUsername()+":"+role.getName());
			}
			
		}
		transaction.commit();
			
	}
	/**
	 * 错误的测试
	 * */
	
	public void test2() {
		Long id = 1L;
		Session session = sessionFactory.getCurrentSession();
		Transaction transaction = session.beginTransaction();
		
		List<Role> rList = session.createQuery("from Role where r.uSet.uid=1").list();
		Set<Role> rSet = new HashSet<Role>(rList);
		for(Role role:rSet){
			System.out.println(role.getName());
		}
		transaction.commit();
			
	}
	/**
	 * 左外连接
	 * 不能适应一个用户有多个角色
	 * */
	@Test
	public void test3(){
		Session session = sessionFactory.getCurrentSession();
		Transaction transaction = session.beginTransaction();
		/**
		 * 三表连查 用户 角色 权限
		 * */
		List<User> uList = session.createQuery("from User u left outer join fetch u.rSet r left outer join fetch r.pSet p").list();
		
		Set<User> uSet = new HashSet<User>(uList);
		
		Map<String, List<Permission>> userPermissionMap = new HashMap<String, List<Permission>>();
		for(User user:uSet){
			//对应得到用户的所有角色
			Set<Role> rSet = user.getrSet();
			for(Role role:rSet){
				//对应得到用户的所有角色的对应权限
				Set<Permission> pSet = role.getpSet();
				//得到list形式的权限集合
				List<Permission> pList = new ArrayList<Permission>(pSet);
				//因为user.getUsername()是重复的 而我们需要的而是 同一个用户名字 username 然后添加这个用户所有的权限
				//而这种写法 当一个用户有两个角色的时候 后一个进来填充的角色会覆盖掉前面的角色 也就是造成 得到权限的遗漏
				userPermissionMap.put(user.getUsername(), pList);
			}
		}
		System.out.println(userPermissionMap);
		transaction.commit();
	}
	/**
	 * map 当Key相同时 使用put 则会覆盖哦
	 * */
	@Test
	public void testMap(){
		Map<String, String> map = new HashMap<String, String>();
		map.put("1", "10");
		map.put("1", "9");
		System.out.println(map.get("1"));
	}
	/**
	 * 解决 利用	map<用户名,对应用户权限的Set> 存在的 一个用户名 对应 多个角色的问题	
	 * 已经能解决 一个用户多个角色
	 * */
	@Test
	public void test4(){
		Session session = sessionFactory.getCurrentSession();
		Transaction transaction = session.beginTransaction();
		/**
		 * 三表连查 用户 角色 权限
		 * */
		List<User> uList = session.createQuery("from User u left outer join fetch u.rSet r left outer join fetch r.pSet p").list();
		
		Set<User> uSet = new HashSet<User>(uList);
		
		Map<String, List<Permission>> userPermissionMap = new HashMap<String, List<Permission>>();
		for(User user:uSet){
			//对应得到用户的所有角色
			//因为一个用户有多个角色	一个角色有多个权限	角色对应的权限有所相同
			//1.得到一个用户的 所有角色
			Set<Role> rSet = user.getrSet();
			//2.遍历所有角色 创建一个新的set用于放permission
			Set<Permission> pSet = new HashSet<Permission>();
			for(Role role:rSet){
				pSet.addAll(role.getpSet());
			}
			//3.填充角色权限 权限应该放入set中 因为不需要重复
			userPermissionMap.put(user.getUsername(),new ArrayList<Permission>(pSet) );
			
		}
		System.out.println(userPermissionMap);
		transaction.commit();
	}
}
