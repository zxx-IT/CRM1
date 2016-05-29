package com.atguigu.ssm.crm.mappers;

import java.util.List;
import java.util.Map;

import com.atguigu.ssm.crm.entity.Order;

public interface OrderMapper {

	long getTotalElements(Map<String, Object> mybatisParams);

	List<Order> getContent(Map<String, Object> mybatisParams);

	Order getOrderDetails(Integer orderId);

	Order getList(Integer orderId);

}
