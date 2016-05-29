package com.atguigu.ssm.crm.handler;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.util.WebUtils;

import com.atguigu.ssm.crm.entity.CustomerDrain;
import com.atguigu.ssm.crm.model.Page;
import com.atguigu.ssm.crm.service.ReportService;
import com.atguigu.ssm.crm.utils.DataProcessUtils;

@RequestMapping("/report")
@Controller
public class ReportHandler {
	@Autowired
	private ReportService reportService;
	
	@RequestMapping("/pay")
	public String pay(@RequestParam(value="pageNo", required=false)String pageNoStr, Map<String, Object> map, HttpServletRequest request){
		int pageNo = 1;
		try {
			pageNo = Integer.parseInt(pageNoStr);
		} catch (NumberFormatException e) {
		}
		Map<String, Object> params = WebUtils.getParametersStartingWith(
				request, "search_");
		String queryString = DataProcessUtils.encodeParamsToQueryString(params);
		map.put("queryString", queryString);
		map.put("typee", "客户等级");
		
		Page<Map<String, Object>> page = reportService.getPage(pageNo, params);
		map.put("page", page);
		return "report/pay";
	}
	
	@RequestMapping("/consist")
	public String consist(@RequestParam(value="pageNo", required=false)String pageNoStr, Map<String, Object> map, HttpServletRequest request){
		int pageNo = 1;
		try {
			pageNo = Integer.parseInt(pageNoStr);
		} catch (NumberFormatException e) {
		}
		Map<String, Object> params = WebUtils.getParametersStartingWith(
				request, "search_");
		
		String queryString = DataProcessUtils.encodeParamsToQueryString(params);
		map.put("queryString", queryString);
		
		Page<Map<String, Object>> page = reportService.getPageForConsist(pageNo, params);
		
		map.put("page", page);
		return "report/consist";
	}
	
	@RequestMapping("/service")
	public String serviceList(@RequestParam(value="pageNo", required=false)String pageNoStr, Map<String, Object> map, HttpServletRequest request){
		int pageNo = 1;
		try {
			pageNo = Integer.parseInt(pageNoStr);
		} catch (NumberFormatException e) {
		}
		Map<String, Object> params = WebUtils.getParametersStartingWith(
				request, "search_");
		
		String queryString = DataProcessUtils.encodeParamsToQueryString(params);
		map.put("queryString", queryString);
		
		Page<Map<String, Object>> page = reportService.getPageForService(pageNo, params);
		
		map.put("page", page);
		return "report/service";
	}
	@RequestMapping("/drain")
	public String drain(@RequestParam(value="pageNo", required=false)String pageNoStr, Map<String, Object> map, HttpServletRequest request){
		int pageNo = 1;
		try {
			pageNo = Integer.parseInt(pageNoStr);
		} catch (NumberFormatException e) {
		}
		Map<String, Object> params = WebUtils.getParametersStartingWith(
				request, "search_");
		
		String queryString = DataProcessUtils.encodeParamsToQueryString(params);
		map.put("queryString", queryString);
		
		Page<CustomerDrain> page = reportService.getPageForDrain(pageNo, params);
		
		map.put("page", page);
		return "report/drain";
	}
}
