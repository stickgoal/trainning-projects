package com.woniuxy.rocket.orm.core;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import com.woniuxy.rocket.orm.meta.MetaExtractor;
import com.woniuxy.rocket.orm.meta.TableMapping;
import com.woniuxy.rocket.orm.support.DbUtils;
import com.woniuxy.rocket.orm.support.PackageScan;
import com.woniuxy.rocket.orm.support.PropertiesUtil;

public class SessionFactory {

	Configuration configuration = new Configuration();
	
	Map<String,TableMapping> metaMap = new HashMap<>();

	// 启动阶段
	public SessionFactory(String configFileLocation) {
		// 解析配置文件
		loadConfig(configFileLocation);
		
		// 扫描实体类并解析映射对象
		scanMappingInfo();
		
		//初始化连接池
		DbUtils.init(configuration.getUrl(),
				configuration.getUsername(), 
				configuration.getPassword(),
				configuration.getDriverClassName()
				);
	}

	private void scanMappingInfo() {
		//实体类包路径
		String entityPackage = configuration.getEntityPackage();
		List<Class> entityClassList = PackageScan.getClassesIn(entityPackage);
		
		for(Class c : entityClassList) {
			metaMap.put(c.getSimpleName(), MetaExtractor.extract(c));
		}
		
	}

	private void loadConfig(String configFileLocation) {
		Properties p = PropertiesUtil.load(configFileLocation);
		configuration.setUrl(p.getProperty("jdbc.url"));
		configuration.setUsername(p.getProperty("jdbc.username"));
		configuration.setPassword(p.getProperty("jdbc.password"));
		configuration.setDriverClassName(p.getProperty("jdbc.driverClassName"));
		configuration.setDialect(p.getProperty("orm.sql.dialect"));
		configuration.setEntityPackage(p.getProperty("orm.entityPackage"));

		System.out.println("配置解析完成" + configuration);
	}

	// 运行时阶段
	public Session openSession() {
		return new SessionImpl(this);
	}

	public Configuration getConfiguration() {
		return configuration;
	}
	public Map<String, TableMapping> getMetaMap() {
		return metaMap;
	}

	@Override
	public String toString() {
		return "SessionFactory [configuration=" + configuration + ", metaMap=" + metaMap + "]";
	}
	
}
