package com.atguigu.ssm.crm.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atguigu.ssm.crm.entity.Order;
import com.atguigu.ssm.crm.mappers.OrderMapper;
import com.atguigu.ssm.crm.model.Page;

@Service
public class OrderService {
	@Autowired
	private OrderMapper orderMapper;

	public Page<Order> getPage(int pageNo, Integer id,
			Map<String, Object> mybatisParams) {
		Page<Order> page = new Page<>();
		//设置页码
		page.setPageNo(pageNo);
		mybatisParams.put("customerid", id);
		int totalElements = (int) orderMapper.getTotalElements(mybatisParams);
		page.setTotalElements(totalElements);
		
		int firstIndex = ((pageNo - 1) * page.getPageSize()) + 1;
		int endIndex = firstIndex + page.getPageSize();
		mybatisParams.put("firstIndex", firstIndex);
		mybatisParams.put("endIndex", endIndex);
		List<Order> content = orderMapper.getContent(mybatisParams);
		page.setContent(content);
		return page;
	}

	public Order getOrderDetails(Integer orderId) {
		return orderMapper.getOrderDetails(orderId);
	}

	public Order getList(Integer orderId) {
		return orderMapper.getList(orderId);
	}
}
