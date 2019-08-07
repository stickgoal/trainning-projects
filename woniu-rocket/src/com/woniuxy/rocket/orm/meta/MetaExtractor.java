package com.woniuxy.rocket.orm.meta;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.woniuxy.rocket.orm.annotation.Column;
import com.woniuxy.rocket.orm.annotation.Entity;
import com.woniuxy.rocket.orm.annotation.Id;
import com.woniuxy.rocket.orm.exceptions.EntityNotManagedException;

/**
 * 元数据提取器
 * @author Lucas
 *
 */
public class MetaExtractor {
	
	/**
	 * 从实体类中提取出映射关系
	 * @param entityClass
	 * @return
	 */
	public static TableMapping extract(Class entityClass) {
		  if (!entityClass.isAnnotationPresent(Entity.class)) {
	            throw new EntityNotManagedException("[" + entityClass.getCanonicalName() + "]没有加@Entity注释，它是一个实体吗？");
	        }

	        TableMapping mapping = new TableMapping();
	        final Entity entityAnno = (Entity) entityClass.getAnnotation(Entity.class);
	        mapping.setEntityClass(entityClass);
	        mapping.setTableName(entityAnno.tableName());
	        List<ColumnMapping> columnMappings = new ArrayList<>();
	        Map<String,ColumnMapping> columnNameMapping = new HashMap<>();


	        Field[] declaredFields = entityClass.getDeclaredFields();
	        for (Field field : declaredFields) {
	            ColumnMapping cm = new ColumnMapping();

	           if (field.isAnnotationPresent(Column.class)) {

	                Column columnAnno = field.getAnnotation(Column.class);

	                cm.setProperty(field.getName());
	                cm.setPropertyType(field.getType());
	                cm.setColumn(columnAnno.columnName());
	                cm.setColumnType(getJDBCType(field.getType()));

	                columnMappings.add(cm);
	                columnNameMapping.put(columnAnno.columnName(),cm);

	               if (field.isAnnotationPresent(Id.class)) {
	            	   Id id = field.getAnnotation(Id.class);
	                   cm.setPk(true);
	                   cm.setPkAuto(id.auto());
	                   mapping.setPkName(columnAnno.columnName());
	                   mapping.setPkType(field.getType());

	               }
	            } else {
	                System.out.println("[" + field + "]没有被@Column注解，忽略");
	            }
	        }

	        mapping.setColumnNameMapping(columnNameMapping);
	        mapping.setColumnMappings(columnMappings);

	        return mapping;
	}
	
	 /**
     * @param type
     * @return
     */
    private static String getJDBCType(Class<?> type) {
        //TODO 根据java类的字段类型获取到对应的JDBC类型
        return null;
    }
	
	
}
