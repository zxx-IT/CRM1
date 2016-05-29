package com.atguigu.ssm.crm.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atguigu.ssm.crm.entity.Contact;
import com.atguigu.ssm.crm.mappers.ContactMapper;
import com.atguigu.ssm.crm.model.Page;
import com.atguigu.ssm.crm.orm.PropertyFilter;

@Service
public class ContactService {
	@Autowired
	private ContactMapper contactMapper;

	public Page<Contact> getPage(int pageNo, Map<String, Object> params,
			Integer id) {
		Page<Contact> page = new Page<>();
		//设置页码
		page.setPageNo(pageNo);
		Map<String, Object> mybatisParams = PropertyFilter.parseRequestParams2MybatisParams(params);
		mybatisParams.put("customerid", id);
		int totalElements = (int) contactMapper.getTotalElements(mybatisParams);
		page.setTotalElements(totalElements);
		
		int firstIndex = ((pageNo - 1) * page.getPageSize()) + 1;
		int endIndex = firstIndex + page.getPageSize();
		mybatisParams.put("firstIndex", firstIndex);
		mybatisParams.put("endIndex", endIndex);
		List<Contact> content = contactMapper.getContent(mybatisParams);
		page.setContent(content);
		return page;
	}

	public void saveCreate(Contact contact) {
		contactMapper.saveCreate(contact);
	}

	public Contact getContactById(Integer id) {
		return contactMapper.getContactById(id);
	}

	public void update(Contact contact) {
		contactMapper.update(contact);
	}

	public void delete(Integer id) {
		contactMapper.delete(id);
	}
	
}
