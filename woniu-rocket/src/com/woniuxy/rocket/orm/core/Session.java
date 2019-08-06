package com.woniuxy.rocket.orm.core;

import java.util.List;

/**
 * 数据库会话映射
 * @author Administrator
 *
 */
public interface Session {
	
	/**
	 * 保存
	 * @param obj 
	 * @return
	 */
	int save(Object obj);
	

	/**
	 * 删除
	 * @param <T>
	 * @param id
	 * @return
	 */
	<T> int delete(Class<T> entityClass,int id); 
	
	/**
	 *更新
	 * @param object
	 * @return 
	 */
	int update(Object object);
	

	/**
	 * 根据ID 查找
	 * @param <T>
	 * @param entityClass
	 * @param id
	 * @return
	 */
	<T> T get(Class<T> entityClass,int id); 
	
	/**
	 * 查找出所有对象
	 * @param <T>
	 * @param entityClass
	 * @return
	 */
	<T> List<T> findAll(Class<T> entityClass);
	
	/**
	 * 根据SQL查询多个对象
	 * @param <T>
	 * @param sql
	 * @param entityClass
	 * @param params
	 * @return
	 */
	<T> List<T> query(String sql,Class<T> entityClass,Object... params);
	
	/**
	 * 根据sql查询单个对象
	 * @param <T>
	 * @param sql
	 * @param entityClass
	 * @param params
	 * @return
	 */
	<T> T queryOne(String sql,Class<T> entityClass,Object... params);
	
	
	
}
