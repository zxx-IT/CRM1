package com.atguigu.ssm.crm.mappers;

import java.util.List;
import java.util.Map;

import com.atguigu.ssm.crm.entity.User;

public interface UserMapper {
	
	User getByName(String name);

	List<User> getUserList();

	long getTotalElements(Map<String, Object> mybatisParams);

	List<User> getContent(Map<String, Object> mybatisParams);

	User getUserById(Integer id);

	void update(User user);

	void save(User user);

	void delete(Integer id);

	User getUserByName(String username);
}
