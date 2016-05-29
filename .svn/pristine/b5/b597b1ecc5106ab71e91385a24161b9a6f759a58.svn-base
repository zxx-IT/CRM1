package com.atguigu.ssm.crm.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atguigu.ssm.crm.entity.CustomerService;
import com.atguigu.ssm.crm.entity.User;
import com.atguigu.ssm.crm.mappers.CustomerServiceMapper;
import com.atguigu.ssm.crm.model.Page;
import com.atguigu.ssm.crm.orm.PropertyFilter;

@Service
public class CustomerServiceService {
	@Autowired
	private CustomerServiceMapper customerServiceMapper;

	public void save(CustomerService customerService) {
		customerServiceMapper.save(customerService);
	}

	public Page<CustomerService> getPage(int pageNo,
			Map<String, Object> params) {
			Page<CustomerService> page = new Page<>();
			//设置页码
			page.setPageNo(pageNo);
			Map<String, Object> mybatisParams = PropertyFilter.parseRequestParams2MybatisParams(params);
			int totalElements = (int) customerServiceMapper.getTotalElements(mybatisParams);
			page.setTotalElements(totalElements);
			
			int firstIndex = ((pageNo - 1) * page.getPageSize()) + 1;
			int endIndex = firstIndex + page.getPageSize();
			mybatisParams.put("firstIndex", firstIndex);
			mybatisParams.put("endIndex", endIndex);
			List<CustomerService> content = customerServiceMapper.getContent(mybatisParams);
			page.setContent(content);
			return page;
	}

	public void delete(Long id) {
		customerServiceMapper.delete(id);
	}

	public void allot(com.atguigu.ssm.crm.entity.CustomerService customerService, Long allotId) {
		User user = new User();
		user.setId(allotId);
		customerService.setAllotTo(user);
		customerService.setAllotDate(new Date());
		customerServiceMapper.allot(customerService);
	}

	public CustomerService getCustomerServiceById(Long id) {
		return customerServiceMapper.getCustomerServiceById(id);
	}

	public void updateService(CustomerService customerService) {
		customerServiceMapper.updateService(customerService);
	}
	
}
