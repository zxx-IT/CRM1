package com.atguigu.ssm.crm.mappers;

import java.util.List;
import java.util.Map;

public interface ReportMapper {

	long getTotalElements(Map<String, Object> mybatisParams);

	List<Map<String, Object>> getContent(Map<String, Object> mybatisParams);

	long getTotalElementsForConsist(Map<String, Object> mybatisParams);

	List<Map<String, Object>> getContentForConsist(Map<String, Object> mybatisParams);

	long getTotalElementsForService(Map<String, Object> mybatisParams);

	List<Map<String, Object>> getContentForService(Map<String, Object> mybatisParams);

	List<Map<String, Object>> getDataSetForService();

	List<Map<String, Object>> getDataForConsist(Map<String, Object> paramMap);

	List<Map<String, Object>> getDataSetForPay();

}
