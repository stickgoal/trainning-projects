package com.woniuxy.rocket.orm.support;

import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import com.woniuxy.rocket.orm.meta.ColumnMapping;
import com.woniuxy.rocket.orm.meta.TableMapping;
import com.woniuxy.rocket.orm.support.DbUtils.Adapter;

public class CommonAdapter<T> implements Adapter<T> {
	
	private Class<T> entityClass;
	
	private TableMapping mapping;
	
	

	public CommonAdapter(Class<T> entityClass, TableMapping mapping) {
		super();
		this.entityClass = entityClass;
		this.mapping = mapping;
	}
	
	@Override
	public T adapt(ResultSet rs) {
		T t = null;
		try {
			t = entityClass.newInstance();
			ResultSetMetaData rsmd = rs.getMetaData();
			for(int i=0;i<rsmd.getColumnCount();i++) {
				String columnName = rsmd.getColumnName(i+1);
				ColumnMapping columnMapping = mapping.getColumnNameMapping().get(columnName);
				String property = columnMapping.getProperty();
				Field f = entityClass.getDeclaredField(property);
				f.setAccessible(true);
				f.set(t, rs.getObject(i+1));
			}
		} catch (InstantiationException | IllegalAccessException | SQLException | NoSuchFieldException | SecurityException e) {
			e.printStackTrace();
		}
		return t;
	}

}
