package com.atguigu.ssm.crm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.atguigu.ssm.crm.entity.SalesPlan;
import com.atguigu.ssm.crm.mappers.PlanMapper;

@Service
@Transactional
public class PlanService {
	@Autowired
	private PlanMapper planMapper;
	
	public void save(SalesPlan plan){
		planMapper.save(plan);
	}

	public void delete(Long id) {
		planMapper.delete(id);
	}

	public SalesPlan get(Long id) {
		return planMapper.get(id);
	}

	public void update(SalesPlan plan) {
		planMapper.update(plan);
	}
}
