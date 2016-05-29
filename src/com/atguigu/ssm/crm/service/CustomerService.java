package com.atguigu.ssm.crm.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.atguigu.ssm.crm.entity.Contact;
import com.atguigu.ssm.crm.entity.Customer;
import com.atguigu.ssm.crm.entity.Dict;
import com.atguigu.ssm.crm.mappers.CustomerMapper;
import com.atguigu.ssm.crm.model.Page;
import com.atguigu.ssm.crm.orm.PropertyFilter;

@Service
@Transactional
public class CustomerService {
	@Autowired
	private CustomerMapper customerMapper;

	public Page<Customer> getPage(int pageNo, Map<String, Object> params) {
		Page<Customer> page = new Page<>();
		//设置页码
		page.setPageNo(pageNo);
		Map<String, Object> mybatisParams = PropertyFilter.parseRequestParams2MybatisParams(params);
//		int totalElements = (int) salesChanceMapper.getTotalElements();
		int totalElements = (int) customerMapper.getTotalElements(mybatisParams);
		page.setTotalElements(totalElements);
		
		int firstIndex = ((pageNo - 1) * page.getPageSize()) + 1;
		int endIndex = firstIndex + page.getPageSize();
		mybatisParams.put("firstIndex", firstIndex);
		mybatisParams.put("endIndex", endIndex);
		List<Customer> content = customerMapper.getContent(mybatisParams);
		page.setContent(content);
		return page;
	}

	public List<Dict> getDictList() {
		return customerMapper.getDictList();
	}

	public List<Dict> getLevels() {
		
		return customerMapper.getLevels();
	}

	public List<Contact> getManagerList() {
		return customerMapper.getManagerList();
	}

	public List<Dict> getStatisfieList() {
		return customerMapper.getStatisfieList();
	}

	public List<Dict> getCreditList() {
		return customerMapper.getCretditList();
	}

	public void saveCreate(Customer customer) {
		customerMapper.saveCreate(customer);
	}


	public Customer getCustomerById(Integer id) {
		
		return customerMapper.getCustomerById(id);
	}

	public void update(Customer customer) {
		customerMapper.update(customer);
	}

	public void updateState(Long id, String status) {
		customerMapper.updateState(id, status);
	}

	public List<Customer> getAllCustomers() {
		return customerMapper.getAllCustomers();
	}

	public List<String> getAllServiceTypes() {
		return customerMapper.getAllServiceTypes();
	}
	
}
