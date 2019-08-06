package com.woniuxy.rocket.orm.core;

public class Configuration {
	  /**
     * 实体所在的包
     */
    private String entityPackage;

    /**
     * 数据库URL
     */
    private String url;

    /**
     * 数据库用户名
     */
    private String username;

    /**
     * 数据库密码
     */
    private String password;

    /**
     * 数据库驱动类名
     */
    private String driverClassName;

    /**
     * sql方言
     */
    private String dialect;
    

	public String getEntityPackage() {
		return entityPackage;
	}


	public void setEntityPackage(String entityPackage) {
		this.entityPackage = entityPackage;
	}


	public String getUrl() {
		return url;
	}


	public void setUrl(String url) {
		this.url = url;
	}


	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public String getDriverClassName() {
		return driverClassName;
	}


	public void setDriverClassName(String driverClassName) {
		this.driverClassName = driverClassName;
	}


	public String getDialect() {
		return dialect;
	}


	public void setDialect(String dialect) {
		this.dialect = dialect;
	}


	@Override
	public String toString() {
		return "Configuration [entityPackage=" + entityPackage + ", url=" + url + ", username=" + username
				+ ", password=" + password + ", driverClassName=" + driverClassName + ", dialect=" + dialect + "]";
	}
    
    
}
