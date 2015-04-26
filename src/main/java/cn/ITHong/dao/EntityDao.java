package cn.ITHong.dao;

import java.io.Serializable;
import java.util.List;

public interface EntityDao<T> {
	public  List<T> getAllEntity(Class<T> classt);
	public <T>T findByID(Class<T> t,Serializable id);
	public void saveEntity(T t);
	public <T> void updateEntry(T t);
	public <T> void deleteEntity(Class<T>T,Serializable id);
}
