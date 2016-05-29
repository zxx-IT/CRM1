package com.atguigu.ssm.crm.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atguigu.ssm.crm.entity.CustomerActivity;
import com.atguigu.ssm.crm.mappers.ActivityMapper;
import com.atguigu.ssm.crm.model.Page;

@Service
public class ActivityService {
	@Autowired
	private ActivityMapper activityMapper;

	public Page<CustomerActivity> getPage(int pageNo, Integer id, Map<String, Object> mybatisParams) {
		Page<CustomerActivity> page = new Page<>();
		//设置页码
		page.setPageNo(pageNo);
		mybatisParams.put("customerid", id);
		int totalElements = (int) activityMapper.getTotalElements(mybatisParams);
		page.setTotalElements(totalElements);
		
		int firstIndex = ((pageNo - 1) * page.getPageSize()) + 1;
		int endIndex = firstIndex + page.getPageSize();
		mybatisParams.put("firstIndex", firstIndex);
		mybatisParams.put("endIndex", endIndex);
		List<CustomerActivity> content = activityMapper.getContent(mybatisParams);
		page.setContent(content);
		return page;
	}

	public CustomerActivity getActivityById(Integer id) {
		return activityMapper.getActivityById(id);
	}

	public void save(CustomerActivity activity) {
		activityMapper.save(activity);
	}

	public void update(CustomerActivity activity) {
		activityMapper.update(activity);
	}

	public void delete(Integer id) {
		activityMapper.delete(id);
	}
}
