package com.atguigu.ssm.crm.handler;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.atguigu.ssm.crm.entity.Order;
import com.atguigu.ssm.crm.model.Page;
import com.atguigu.ssm.crm.service.OrderService;

@RequestMapping("/order")
@Controller
public class OrderHandler {
	@Autowired
	private OrderService orderService;
	
	@RequestMapping("/list")
	
	public String listString (@RequestParam(value="customerid", required=false)Integer id, @RequestParam(value="pageNo", required=false)String pageNoStr, Map<String,Object> map, HttpServletRequest request){
		int pageNo = 1;
		try {
			pageNo = Integer.parseInt(pageNoStr);
		} catch (NumberFormatException e) {
		}
		Map<String, Object> params = new HashMap<String, Object>();
		String custIdStr = request.getParameter("customerid");
		String queryString = "";
		if (custIdStr != null) {
			queryString = "customerid=" + custIdStr;
		}
		map.put("queryString", queryString);
		
		Page<Order> page = orderService.getPage(pageNo, id,  params);
		map.put("page", page);
		return "/order/list";
	}
	
	@RequestMapping("/details")
	public String details(@RequestParam("id")Integer orderId, Map<String , Object> map){
		Order orderDetails = orderService.getOrderDetails(orderId);
		
		map.put("order", orderDetails);
		return "/order/details";
	}
}
