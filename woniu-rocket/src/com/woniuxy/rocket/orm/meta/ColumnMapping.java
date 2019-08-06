package com.woniuxy.rocket.orm.meta;



public class ColumnMapping {
    /**
     * 对象属性名
     */
    private String property;

    /**
     * 对象属性类型
     */
    private Class propertyType;

    /**
     * 数据库表列名
     */
    private String column;

    /**
     * 数据库表列类型
     */
    private String columnType;

    /**
     * 是否为主键
     */
    private boolean isPk;

	public String getProperty() {
		return property;
	}

	public void setProperty(String property) {
		this.property = property;
	}

	public Class getPropertyType() {
		return propertyType;
	}

	public void setPropertyType(Class propertyType) {
		this.propertyType = propertyType;
	}

	public String getColumn() {
		return column;
	}

	public void setColumn(String column) {
		this.column = column;
	}

	public String getColumnType() {
		return columnType;
	}

	public void setColumnType(String columnType) {
		this.columnType = columnType;
	}

	public boolean isPk() {
		return isPk;
	}

	public void setPk(boolean isPk) {
		this.isPk = isPk;
	}

	@Override
	public String toString() {
		return "ColumnMapping [property=" + property + ", propertyType=" + propertyType + ", column=" + column
				+ ", columnType=" + columnType + ", isPk=" + isPk + "]";
	}
    
    

}
