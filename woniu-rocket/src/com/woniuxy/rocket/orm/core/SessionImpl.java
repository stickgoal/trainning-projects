package com.woniuxy.rocket.orm.core;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import com.woniuxy.rocket.orm.exceptions.EntityNotManagedException;
import com.woniuxy.rocket.orm.exceptions.PersistException;
import com.woniuxy.rocket.orm.meta.ColumnMapping;
import com.woniuxy.rocket.orm.meta.TableMapping;
import com.woniuxy.rocket.orm.support.CommonAdapter;
import com.woniuxy.rocket.orm.support.DbUtils;
import com.woniuxy.rocket.orm.support.SqlGenerator;

@SuppressWarnings("rawtypes")
public class SessionImpl implements Session {

	private SessionFactory sessionFactory;

	public SessionImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public int save(Object obj) {

		String entityName = obj.getClass().getSimpleName();
		TableMapping mapping = getMapping(entityName);

		String sql = SqlGenerator.insert(mapping);

		return new DbUtils().update(sql, getParams(mapping, obj));
	}

	private Object[] getParams(TableMapping mapping, Object obj) {
		Class entityClass = obj.getClass();
		List params = new ArrayList();
		try {

			for (ColumnMapping cm : mapping.getColumnMappings()) {
				Field f = entityClass.getDeclaredField(cm.getProperty());
				f.setAccessible(true);

				params.add(f.get(obj));
			}
		} catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException e) {
			throw new PersistException("反射获取字段值出错", e);
		}

		return params.toArray();
	}

	private TableMapping getMapping(String entityName) {
		final TableMapping mapping = sessionFactory.metaMap.get(entityName);
		if (mapping == null) {
			throw new EntityNotManagedException(entityName + "未被管理");
		}
		return mapping;
	}

	@Override
	public <T> int delete(Class<T> entityClass,int id) {
		TableMapping mapping = getMapping(entityClass.getSimpleName());

		String sql = SqlGenerator.deleteById(mapping);

		return new DbUtils().update(sql, id);
	}

	@Override
	public int update(Object object) {
		TableMapping mapping = getMapping(object.getClass().getSimpleName());

		String sql = SqlGenerator.updateById(mapping);

		return new DbUtils().update(sql, getUpdateByIdParams(mapping, object));
	}

	private Object[] getUpdateByIdParams(TableMapping mapping, Object obj) {
		Class entityClass = obj.getClass();
		List params = new ArrayList();
		try {
			int pkValue = -1;
			for (ColumnMapping cm : mapping.getColumnMappings()) {
				if (cm.isPk()) {
					Field f = entityClass.getDeclaredField(cm.getProperty());
					f.setAccessible(true);
					params.add(f.get(obj));
				}else {
					Field f = entityClass.getDeclaredField(cm.getProperty());
					f.setAccessible(true);
					pkValue=f.getInt(obj);
				}
			}
			//updateById时，最后的一个字段是主键
			params.add(pkValue);

		} catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException e) {
			throw new PersistException("反射获取字段值出错", e);
		}

		return params.toArray();
	}

	@Override
	public <T> T get(Class<T> entityClass, int id) {

		TableMapping mapping = getMapping(entityClass.getSimpleName());

		String sql = SqlGenerator.byId(mapping);

		return new DbUtils().selectOne(sql, new CommonAdapter<T>(entityClass, mapping), id);
	}

	@Override
	public <T> List<T> findAll(Class<T> entityClass) {
		TableMapping mapping = getMapping(entityClass.getSimpleName());
		String sql = SqlGenerator.all(mapping);
		return new DbUtils().selectList(sql, new CommonAdapter<T>(entityClass, mapping));
	}

	@Override
	public <T> List<T> query(String sql, Class<T> entityClass, Object... params) {
		TableMapping mapping = getMapping(entityClass.getSimpleName());

		return new DbUtils().selectList(sql, new CommonAdapter<T>(entityClass, mapping), params);
	}

	@Override
	public <T> T queryOne(String sql, Class<T> entityClass, Object... params) {
		TableMapping mapping = getMapping(entityClass.getSimpleName());
		return new DbUtils().selectOne(sql, new CommonAdapter<T>(entityClass, mapping), params);
	}

}
