package com.atguigu.ssm.crm.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.atguigu.ssm.crm.entity.User;
import com.atguigu.ssm.crm.mappers.UserMapper;
import com.atguigu.ssm.crm.model.Page;
import com.atguigu.ssm.crm.orm.PropertyFilter;

@Service
@Transactional
public class UserService {
	@Autowired
	private UserMapper userMapper;
	
	public User login(String name, String password){
		
		User user = userMapper.getByName(name);
		if (user != null && password.equals(user.getPassword()) && user.getEnabled() == 1) {
			return user;
		}
		
		return null;
	}
	public List<User> getUserList(){
		return userMapper.getUserList();
	}
	public Page<User> getPage(int pageNo, Map<String, Object> params) {
		Page<User> page = new Page<>();
		page.setPageNo(pageNo);
		
		Map<String, Object> mybatisParams = PropertyFilter.parseRequestParams2MybatisParams(params);
		
		int totalElements = (int)userMapper.getTotalElements(mybatisParams);
		page.setTotalElements(totalElements);
		int firstIndex = ((pageNo - 1) * page.getPageSize()) + 1;
		int endIndex = firstIndex + page.getPageSize();
		
		mybatisParams.put("firstIndex", firstIndex);
		mybatisParams.put("endIndex", endIndex);
		
		List<User> content = userMapper.getContent(mybatisParams);
		page.setContent(content);
		return page;
	}
	public User getUserById(Integer id) {
		return userMapper.getUserById(id);
	}
	public void update(User user) {
		userMapper.update(user);
	}
	public void save(User user) {
		userMapper.save(user);
	}
	public void delete(Integer id) {
		userMapper.delete(id);
	}
	public User getUserByName(String username) {
		return userMapper.getUserByName(username);
	}
}
