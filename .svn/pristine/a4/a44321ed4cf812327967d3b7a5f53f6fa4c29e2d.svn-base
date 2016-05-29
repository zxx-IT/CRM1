package com.atguigu.ssm.crm.mappers;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Update;

import com.atguigu.ssm.crm.entity.CustomerDrain;

public interface CustomerDrainMapper {

	long getTotalElements(Map<String, Object> mybatisParams);

	List<CustomerDrain> getContent(Map<String, Object> mybatisParams);

	CustomerDrain getCustomerDrainById(Long id);

	void update(CustomerDrain customerDrain);

	void updateStatus(Map<String, Object> map);
	
	@Update("{call check_drain}")
	void callcheckDrainProcedure();
}
