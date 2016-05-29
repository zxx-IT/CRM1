package com.atguigu.ssm.crm.mappers;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.atguigu.ssm.crm.entity.SalesChance;

public interface SalesChanceMapper {
	List<SalesChance> getContent(@Param("firstIndex")int firstIndex, @Param("endIndex")int endIndex);
	
	long getTotalChances();

	long getTotalElements();
	
	void save(SalesChance chance);

	SalesChance get(Integer id);

	void update(SalesChance chance);

	void delete(Integer id);

	long getTotalElements2(Map<String, Object> mybatisParams);

	List<SalesChance> getContent2(Map<String, Object> mybatisParams);

	void dispatch(SalesChance chance);

	List<SalesChance> getPlanContent(Map<String, Object> mybatisParams);

	long getTotalChances(Map<String, Object> mybatisParams);
	
	SalesChance getPlanChance(Integer id);

	void updateStatus(SalesChance chance);
}
