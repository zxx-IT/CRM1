package com.atguigu.ssm.crm.mappers;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.atguigu.ssm.crm.entity.Contact;
import com.atguigu.ssm.crm.entity.Customer;
import com.atguigu.ssm.crm.entity.Dict;

public interface CustomerMapper {

	void save(Customer customer);

	long getTotalElements(Map<String, Object> mybatisParams);

	List<Customer> getContent(Map<String, Object> mybatisParams);

	List<Dict> getDictList();

	List<Dict> getLevels();

	List<Contact> getManagerList();

	List<Dict> getStatisfieList();

	List<Dict> getCretditList();

	void saveCreate(Customer customer);

	Customer getCustomerById(Integer id);

	void update(Customer customer);

	void updateState(@Param("id")Long id, @Param("status")String status);

	List<Customer> getAllCustomers();

	List<String> getAllServiceTypes();

}
