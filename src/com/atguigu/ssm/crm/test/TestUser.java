package com.atguigu.ssm.crm.test;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.atguigu.ssm.crm.entity.User;
import com.atguigu.ssm.crm.mappers.UserMapper;

public class TestUser {
	ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
	@Test
	public void testGetByName(){
		UserMapper userMapper = ctx.getBean(UserMapper.class);
		User user = userMapper.getByName("bcde");
		System.out.println(user);
		System.out.println(user.getRole());
	}
}
