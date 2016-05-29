package com.atguigu.ssm.crm.test;

import java.util.List;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.atguigu.ssm.crm.entity.SalesChance;
import com.atguigu.ssm.crm.mappers.SalesChanceMapper;
import com.atguigu.ssm.crm.service.SalesChanceService;

public class TestSalesChances {
	ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
	SalesChanceService salesChanceService = ctx.getBean(SalesChanceService.class);
	@Test
	public void testList(){
		SalesChanceMapper salesChanceMapper = ctx.getBean(SalesChanceMapper.class);
		List<SalesChance> content = salesChanceMapper.getContent(1, 3);
		System.out.println(content.size());
	}
	@Test
	public void testPage(){
		/*Page<SalesChance> page = salesChanceService.getPage(2);
		System.out.println(page.getTotalElements());
		int size = page.getContent().size();
		System.out.println(size);*/
	}
}
