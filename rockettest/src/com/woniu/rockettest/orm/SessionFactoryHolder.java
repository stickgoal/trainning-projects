package com.woniu.rockettest.orm;

import com.woniuxy.rocket.orm.core.Session;
import com.woniuxy.rocket.orm.core.SessionFactory;

public class SessionFactoryHolder {
	
	private static SessionFactory sessionFactory;
	
	public static void init() {
		System.out.println("初始化sessionFactory...");
		synchronized (SessionFactory.class) {
			sessionFactory = new SessionFactory("/orm.properties");
		}
	}
	
	public static SessionFactory get() {
		return sessionFactory;
	}
}
