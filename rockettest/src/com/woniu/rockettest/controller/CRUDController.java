package com.woniu.rockettest.controller;

import java.util.Date;
import java.util.List;

import com.woniu.rockettest.orm.SessionFactoryHolder;
import com.woniu.rockettest.orm.entity.User;
import com.woniuxy.rocket.mvc.annotations.LittleController;
import com.woniuxy.rocket.mvc.annotations.LittleRequestMapping;
import com.woniuxy.rocket.mvc.annotations.LittleRequestParam;
import com.woniuxy.rocket.mvc.mvc.ModelMap;

@LittleController
public class CRUDController {
	
	@LittleRequestMapping("user/toAdd")
	public String toAdd() {
		return "user/add";
	}
	
	@LittleRequestMapping("user/add")
	public String add(@LittleRequestParam("username") String username,
			@LittleRequestParam("password") String password) {
		
		User user = new User();
		user.setUsername(username);
		user.setPassword(password);
		user.setRegisterTime(new Date());
		SessionFactoryHolder.get().openSession().save(user);
		
		return "user/addSuccess";
	}
	
	@LittleRequestMapping("user/toList")
	public String toList(ModelMap modelMap) {
		List<User> users = SessionFactoryHolder.get().openSession().findAll(User.class);
		modelMap.put("users", users);
		return "user/list";
	}
	
}
