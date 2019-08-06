package com.woniuxy.rocket.orm.meta;

import java.util.List;
import java.util.Map;

/**
 * 表和实体的映射信息信息
 */
public class TableMapping {

    /**
     * 表名
     */
    private String tableName;

    /**
     * 主键列名
     */
    private String pkName;

    /**
     * 实体类
     */
    private Class entityClass;

    /**
     * 主键类型
     */
    private Class pkType;

    /**
     * 列映射列表
     */
    private List<ColumnMapping> columnMappings;

    /**
     * 持有从 字段名=>列映射对象的 对应关系
     */
    private Map<String,ColumnMapping> columnNameMapping;

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public String getPkName() {
		return pkName;
	}

	public void setPkName(String pkName) {
		this.pkName = pkName;
	}

	public Class getEntityClass() {
		return entityClass;
	}

	public void setEntityClass(Class entityClass) {
		this.entityClass = entityClass;
	}

	public Class getPkType() {
		return pkType;
	}

	public void setPkType(Class pkType) {
		this.pkType = pkType;
	}

	public List<ColumnMapping> getColumnMappings() {
		return columnMappings;
	}

	public void setColumnMappings(List<ColumnMapping> columnMappings) {
		this.columnMappings = columnMappings;
	}

	public Map<String, ColumnMapping> getColumnNameMapping() {
		return columnNameMapping;
	}

	public void setColumnNameMapping(Map<String, ColumnMapping> columnNameMapping) {
		this.columnNameMapping = columnNameMapping;
	}

	@Override
	public String toString() {
		return "TableMapping [tableName=" + tableName + ", pkName=" + pkName + ", entityClass=" + entityClass
				+ ", pkType=" + pkType + ", columnMappings=" + columnMappings + ", columnNameMapping="
				+ columnNameMapping + "]";
	}
    
    
}
