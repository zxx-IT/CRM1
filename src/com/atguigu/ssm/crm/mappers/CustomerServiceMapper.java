package com.atguigu.ssm.crm.mappers;

import java.util.List;
import java.util.Map;

import com.atguigu.ssm.crm.entity.CustomerService;


public interface CustomerServiceMapper {

	void save(CustomerService customerService);

	long getTotalElements(Map<String, Object> mybatisParams);

	List<CustomerService> getContent(Map<String, Object> mybatisParams);

	void delete(Long id);

	void allot(CustomerService customerService);

	CustomerService getCustomerServiceById(Long id);

	void updateService(CustomerService customerService);

}
