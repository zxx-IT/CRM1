package com.atguigu.ssm.crm.chart;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.atguigu.ssm.crm.service.ReportService;

@Controller
@RequestMapping("/chart")
public class ReportChartHandler {
	@Autowired
	private ReportService reportService;
	@RequestMapping("/consist")
	public String consistChart(Map<String ,Object> dataSet, String type){
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("type", type);
		List<Map<String, Object>> dataList = reportService.getDataForConsist(paramMap);
		if ("credit".equals(type)) {
			dataSet.put("name", "用户信用度统计图");
		}else if ("level".equals(type)) {
			dataSet.put("name", "用户等级统计图");
		}else if ("satify".equals(type)) {
			dataSet.put("name", "用户满意度统计图");
		}
		for (Map<String, Object> map : dataList) {
			Set<Entry<String,Object>> entrySet = map.entrySet();
			Iterator<Entry<String, Object>> iterator = entrySet.iterator();
			
			while (iterator.hasNext()) {
				BigDecimal count = (BigDecimal) iterator.next().getValue();
				if (iterator.hasNext()) {
					//用户评价或类型
					String evaluation  = (String) iterator.next().getValue();
					if ("☆".equals(evaluation)) {
						evaluation = "一星评价";
					}else if ("☆☆".equals(evaluation)) {
						evaluation = "二星评价";
					}else if ("☆☆☆".equals(evaluation)) {
						evaluation = "三星评价";
					}else if ("☆☆☆☆".equals(evaluation)) {
						evaluation = "四星评价";
					}else if ("☆☆☆☆☆".equals(evaluation)) {
						evaluation = "五星评价";
					}
					dataSet.put(evaluation, count.intValue());
				}else {
					dataSet.put("暂未分类", count.intValue());
				}
				
			}
		}
		
		return "chartView";
	}
	@RequestMapping("/pay")
	public String payChart(Map<String ,Object> dataSet){
		List<Map<String, Object>> paramList = reportService.getDataSetForPay();
		dataSet.put("name", "用户贡献统计表");
		for (Map<String, Object> map : paramList) {
			Set<Entry<String,Object>> entrySet = map.entrySet();
			Iterator<Entry<String, Object>> iterator = entrySet.iterator();
			while (iterator.hasNext()) {
				String  custName = (String) iterator.next().getValue();
				if (iterator.hasNext()) {
					BigDecimal sum = (BigDecimal) iterator.next().getValue();
					dataSet.put(custName, sum.intValue());
				}else {
					dataSet.put(custName, 0);
				}
			}
		}
		return "chartView";
	}
	@RequestMapping("/service")
	public String serviceChart(Map<String ,Object> dataSet){
		List<Map<String, Object>> dataList = reportService.getDataSetForService();
		dataSet.put("name", "用户服务分析统计图");
		for (Map<String, Object> map : dataList) {
			Set<Entry<String,Object>> entrySet = map.entrySet();
			Iterator<Entry<String, Object>> iterator = entrySet.iterator();
			
			while (iterator.hasNext()) {
				BigDecimal count = (BigDecimal) iterator.next().getValue();
				if (iterator.hasNext()) {
					String  licenceNo = (String) iterator.next().getValue();
					dataSet.put(licenceNo, count.intValue());
				}else {
					dataSet.put("暂无条目信息", count.intValue());
				}
			}
		}
		
		return "chartView";
	}
}
