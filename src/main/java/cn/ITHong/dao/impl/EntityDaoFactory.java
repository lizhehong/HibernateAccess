package cn.ITHong.dao.impl;

public class EntityDaoFactory {
	public static <T> T getInstance(Class<T> t) throws Exception{
		return (T) t.newInstance();
	}
}
