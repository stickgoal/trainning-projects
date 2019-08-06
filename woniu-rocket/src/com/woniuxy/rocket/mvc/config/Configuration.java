package com.woniuxy.rocket.mvc.config;


/**
 * 配置信息
 */
public class Configuration {

    /**
     * 包扫描的基础包
     */
    private String basePackage;

    /**
     * 视图前缀
     */
    private String viewPrefix;

    /**
     * 视图后缀
     */
    private String viewSuffix;

	public String getBasePackage() {
		return basePackage;
	}

	public void setBasePackage(String basePackage) {
		this.basePackage = basePackage;
	}

	public String getViewPrefix() {
		return viewPrefix;
	}

	public void setViewPrefix(String viewPrefix) {
		this.viewPrefix = viewPrefix;
	}

	public String getViewSuffix() {
		return viewSuffix;
	}

	public void setViewSuffix(String viewSuffix) {
		this.viewSuffix = viewSuffix;
	}

	@Override
	public String toString() {
		return "Configuration [basePackage=" + basePackage + ", viewPrefix=" + viewPrefix + ", viewSuffix=" + viewSuffix
				+ "]";
	}

}
