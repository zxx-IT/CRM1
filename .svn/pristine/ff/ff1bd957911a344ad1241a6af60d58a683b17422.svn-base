package com.atguigu.ssm.crm.service;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.atguigu.ssm.crm.entity.Contact;
import com.atguigu.ssm.crm.entity.Customer;
import com.atguigu.ssm.crm.entity.SalesChance;
import com.atguigu.ssm.crm.mappers.ContactMapper;
import com.atguigu.ssm.crm.mappers.CustomerMapper;
import com.atguigu.ssm.crm.mappers.SalesChanceMapper;
import com.atguigu.ssm.crm.model.Page;
import com.atguigu.ssm.crm.orm.PropertyFilter;

@Service
@Transactional
public class SalesChanceService {
	@Autowired
	private SalesChanceMapper salesChanceMapper;
	@Autowired
	private CustomerMapper customerMapper;
	@Autowired
	private ContactMapper contactMapper;
	public Page<SalesChance> getPage(int pageNo, Map<String, Object> params) {
		Page<SalesChance> page = new Page<>();
		//设置页码
		page.setPageNo(pageNo);
		Map<String, Object> mybatisParams = PropertyFilter.parseRequestParams2MybatisParams(params);
//		int totalElements = (int) salesChanceMapper.getTotalElements();
		int totalElements = (int) salesChanceMapper.getTotalElements2(mybatisParams);
		page.setTotalElements(totalElements);
		
		int firstIndex = ((pageNo - 1) * page.getPageSize()) + 1;
		int endIndex = firstIndex + page.getPageSize();
		mybatisParams.put("firstIndex", firstIndex);
		mybatisParams.put("endIndex", endIndex);
		List<SalesChance> content = salesChanceMapper.getContent2(mybatisParams);
		page.setContent(content);
		return page;
	}
	
	public void save(SalesChance chance){
		salesChanceMapper.save(chance);
	}

	public SalesChance get(Integer id) {
		return salesChanceMapper.get(id);
	}

	public  void update(SalesChance chance) {
		salesChanceMapper.update(chance);
	}

	public void delete(Integer id) {
		salesChanceMapper.delete(id);
	}

	public void dispath(SalesChance chance) {
		salesChanceMapper.dispatch(chance);
		
	}

	public Page<SalesChance> getChancePage(int pageNo,
			Map<String, Object> params, Long id) {
		Page<SalesChance> page = new Page<>();
		//设置页码
		page.setPageNo(pageNo);
		Map<String, Object> mybatisParams = PropertyFilter.parseRequestParams2MybatisParams(params);
		mybatisParams.put("userId", id);
		int totalElements = (int) salesChanceMapper.getTotalChances(mybatisParams);
		page.setTotalElements(totalElements);
		
		int firstIndex = ((pageNo - 1) * page.getPageSize()) + 1;
		int endIndex = firstIndex + page.getPageSize();
		mybatisParams.put("firstIndex", firstIndex);
		mybatisParams.put("endIndex", endIndex);
		List<SalesChance> content = salesChanceMapper.getPlanContent(mybatisParams);
		page.setContent(content);
		return page;
	}
	public SalesChance getPlanChance(Integer id){
		return salesChanceMapper.getPlanChance(id);
	}

	public void finish(Integer id) {
		SalesChance chance = salesChanceMapper.get(id);
		chance.setStatus(3);
		salesChanceMapper.updateStatus(chance);
		
		Customer customer = new Customer();
		customer.setName(chance.getCustName());
		customer.setNo(UUID.randomUUID().toString()); 
		customer.setState("正常"); 
		customerMapper.save(customer);
		
		//3. 插入一条 Contact
		Contact contact = new Contact();
		contact.setCustomer(customer);
		contact.setName(chance.getContact());
		contact.setTel(chance.getContactTel()); 
		contactMapper.save(contact);
		
	}

	public void stop(Integer id) {
		SalesChance chance = salesChanceMapper.get(id);
		chance.setStatus(4);
		salesChanceMapper.updateStatus(chance);
	}
}
