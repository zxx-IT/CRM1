package com.atguigu.ssm.crm.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.atguigu.ssm.crm.entity.CustomerDrain;
import com.atguigu.ssm.crm.mappers.CustomerDrainMapper;
import com.atguigu.ssm.crm.model.Page;
import com.atguigu.ssm.crm.orm.PropertyFilter;

@Service
@Transactional
public class CustomerDrainService {
	@Autowired
	private CustomerDrainMapper customerDrainMapper;

	public Page<CustomerDrain> getPage(int pageNo, Map<String, Object> params) {
		Page<CustomerDrain> page = new Page<>();
		//设置页码
		page.setPageNo(pageNo);
		Map<String, Object> mybatisParams = PropertyFilter.parseRequestParams2MybatisParams(params);
		int totalElements = (int) customerDrainMapper.getTotalElements(mybatisParams);
		page.setTotalElements(totalElements);
		
		int firstIndex = ((pageNo - 1) * page.getPageSize()) + 1;
		int endIndex = firstIndex + page.getPageSize();
		mybatisParams.put("firstIndex", firstIndex);
		mybatisParams.put("endIndex", endIndex);
		List<CustomerDrain> content = customerDrainMapper.getContent(mybatisParams);
		page.setContent(content);
		return page;
	}

	public CustomerDrain getCustomerDrainById(Long id) {
		return customerDrainMapper.getCustomerDrainById(id);
	}

	public void update(CustomerDrain customerDrain) {
		customerDrainMapper.update(customerDrain);
	}

	public void updateStatus(Map<String, Object> map) {
		customerDrainMapper.updateStatus(map);
	}
	
	public void callcheckDrainProcedure(){
		System.out.println(new Date());
		customerDrainMapper.callcheckDrainProcedure();
	}
}
