package com.atguigu.ssm.crm.test;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.commons.lang.StringUtils;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.atguigu.ssm.crm.entity.Authority;
import com.atguigu.ssm.crm.mappers.AuthorityMapper;
import com.atguigu.ssm.crm.mappers.CustomerDrainMapper;
import com.atguigu.ssm.crm.mappers.ReportMapper;
import com.atguigu.ssm.crm.service.CustomerService;


public class TestBase {
	ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
	@Test
	public void test12(){
		ReportMapper bean = ctx.getBean(ReportMapper.class);
		Map<String, Object> mybatisParams = new HashMap<String, Object>();
		mybatisParams.put("firstIndex", 1);
		mybatisParams.put("endIndex", 3);
		mybatisParams.put("type", "credit");
		List<Map<String, Object>> content2 = bean.getContent(mybatisParams);
		System.err.println(content2.get(0).getClass());;
		/*for (Customer customer : content2) {
			System.out.println(customer);
		}*/
//		List<Customer> content = bean.getContentForConsist(mybatisParams);
		System.out.println("hello");
	}
	@Test
	public void testState(){
		CustomerService customerService = ctx.getBean(CustomerService.class);
		customerService.updateState(141L, "流失");
	}
	@Test
	public void testDelay(){
		CustomerDrainMapper drainMapper = ctx.getBean(CustomerDrainMapper.class);
	}
	@Test
	public void testAuth(){
		AuthorityMapper authorityMapper = ctx.getBean(AuthorityMapper.class);
		List<Authority> allAuthorities = authorityMapper.getAllAuthorities();
		for (Authority authority : allAuthorities) {
			List<Authority> subAuthorities = authority.getSubAuthorities();
			for (Authority authority2 : subAuthorities) {
				System.out.println(authority2.getName());
			}
		}
	}
	@Test
	public void testDataSource() throws SQLException{
		DataSource dataSource = ctx.getBean(DataSource.class);
		Connection connection = dataSource.getConnection();
		System.out.println(connection);
	}
	@Test
	public void test1(){
		short s1 = 1;
		s1 = (short) (s1 + 1);
		System.out.println(s1);
	}
	
	@Test
	public void test(){
		String t1 = "select_like_hello";
		String before = StringUtils.substringBefore(t1, "_");
		System.out.println(before);//select
		String substring = StringUtils.substring(before, 0, before.length()-1);
		System.out.println(substring);
	}
}
