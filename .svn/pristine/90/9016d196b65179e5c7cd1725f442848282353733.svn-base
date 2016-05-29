package com.atguigu.ssm.crm.handler;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.atguigu.ssm.crm.entity.Customer;
import com.atguigu.ssm.crm.entity.CustomerActivity;
import com.atguigu.ssm.crm.model.Page;
import com.atguigu.ssm.crm.service.ActivityService;
import com.atguigu.ssm.crm.service.CustomerService;

@Controller
@RequestMapping("/activity")
public class ActivityHandler {
	@Autowired
	private ActivityService activityService;
	@Autowired
	private CustomerService customerService;
	@RequestMapping("/list")
	public String list(@RequestParam(value="customerid", required=false)Integer id, @RequestParam(value="pageNo", required=false)String pageNoStr, Map<String,Object> map, HttpServletRequest request){
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
		
		Page<CustomerActivity> page = activityService.getPage(pageNo, id,  params);
		Customer customer = customerService.getCustomerById(id);
		map.put("customer", customer);
		map.put("page", page);
		return "/activity/list";
	}
	
	@RequestMapping("create")
	public String create(@RequestParam(value="id", required=false)Integer id, Map<String, Object> map){
		if (id == null) {
			map.put("activity", new CustomerActivity());
		}else {
			map.put("activity", activityService.getActivityById(id));
		}
		return "/activity/input";
	}
	@RequestMapping(value="/create", method=RequestMethod.POST)
	public String create(CustomerActivity activity, @RequestParam("customer.id")Integer id){
		
		if (activity.getId() == null) {
			activityService.save(activity);
		}else {
			activityService.update(activity);
		}								
		return "redirect:/activity/list?customerid=" + id;
	}
	@RequestMapping("/delete")
	public String delete(@RequestParam("customerid")Integer customerid, @RequestParam("id")Integer id){
		activityService.delete(id);
		return "redirect:/activity/list?customerid=" + customerid;
	}
}
