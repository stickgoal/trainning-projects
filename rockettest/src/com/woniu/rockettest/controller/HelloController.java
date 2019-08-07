package com.woniu.rockettest.controller;

import javax.xml.ws.RequestWrapper;

import com.woniuxy.rocket.mvc.annotations.LittleController;
import com.woniuxy.rocket.mvc.annotations.LittleRequestMapping;
import com.woniuxy.rocket.mvc.annotations.LittleRequestParam;
import com.woniuxy.rocket.mvc.annotations.LittleResponseBody;
import com.woniuxy.rocket.mvc.mvc.ModelMap;

@LittleController
public class HelloController {

	@LittleRequestMapping("h1")
	@LittleResponseBody
	public ModelMap sayHi(@LittleRequestParam(value="username",required = true) String username,ModelMap modelMap) {
		modelMap.put("username", username);
		return  modelMap;
	}
	
	@LittleRequestMapping("h2")
	public String sayHi2(@LittleRequestParam(value="username",required = true) String username,ModelMap modelMap) {
		modelMap.put("username", username);
		return  "hello";
	}
	
}
