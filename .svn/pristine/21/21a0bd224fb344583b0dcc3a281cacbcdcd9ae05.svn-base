package com.atguigu.ssm.crm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.atguigu.ssm.crm.entity.Authority;
import com.atguigu.ssm.crm.mappers.AuthorityMapper;

@Service
@Transactional
public class AuthorityService {
	@Autowired
	private AuthorityMapper authorityMapper;

	public List<Authority> getAllAuthorities() {
		return authorityMapper.getAllAuthorities();
	}
	
}
