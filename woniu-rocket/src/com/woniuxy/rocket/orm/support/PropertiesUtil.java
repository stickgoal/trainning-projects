package com.woniuxy.rocket.orm.support;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import com.woniuxy.rocket.orm.exceptions.SupportException;

public class PropertiesUtil {

	public static Properties load(String location) {
		InputStream in = PropertiesUtil.class.getResourceAsStream(location);
		Properties p = new Properties();
		try {
			p.load(in);
		} catch (IOException e) {
			throw new SupportException("解析配置文件出错",e);
		}
		return p;
	}
	
}
