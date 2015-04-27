package cn.ITHong.dao;

import java.io.Serializable;
import java.util.List;

public interface EntityDao<T> {
	/**
	 * 找到所有实体
	 * */
	public  List<T> getAllEntity(Class<T> classt);
	/**
	 * 通过ID 查找实体
	 * */
	public <T>T findByID(Class<T> t,Serializable id);
	/**
	 * 保存实体
	 * */
	public void saveEntity(T t);
	/**
	 * 更新实体
	 * */
	public <T> void updateEntry(T t);
	/**
	 * 删除实体
	 * */
	public <T> void deleteEntity(Class<T>T,Serializable id);
	/**
	 * 通过名字和密码查询
	 * */
	public <T> T findEntity(Class<T>T,String username,String password);
}
