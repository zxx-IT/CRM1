package com.atguigu.ssm.crm.handler;

import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.util.WebUtils;

import com.atguigu.ssm.crm.entity.CustomerDrain;
import com.atguigu.ssm.crm.model.Page;
import com.atguigu.ssm.crm.service.CustomerDrainService;
import com.atguigu.ssm.crm.service.CustomerService;
import com.atguigu.ssm.crm.utils.DataProcessUtils;

@Controller
@RequestMapping("/drain")
public class CustomerDrainHandler {
	@Autowired
	private CustomerDrainService customerDrainService;
	@Autowired
	private CustomerService customerService;
	@RequestMapping("/list")
	public String list(@RequestParam(value="pageNo", required=false)String pageNoStr, Map<String, Object> map, HttpServletRequest request){
		int pageNo = 1;
		try {
			pageNo = Integer.parseInt(pageNoStr);
		} catch (NumberFormatException e) {
		}

		Map<String, Object> params = WebUtils.getParametersStartingWith(
				request, "search_");
		String queryString = DataProcessUtils.encodeParamsToQueryString(params);
		map.put("queryString", queryString);
		
		Page<CustomerDrain> page = customerDrainService.getPage(pageNo, params);
		
		map.put("page", page);
		return "drain/list";
	}
	
	@RequestMapping("/delay")
	public String delay(@RequestParam("id")Long id, Map<String, Object> map){
		CustomerDrain customerDrain = customerDrainService.getCustomerDrainById(id);
		map.put("drain", customerDrain);
		return "drain/delay";
	}
	@RequestMapping(value="/delay",method=RequestMethod.POST)
	public String delay(CustomerDrain customerDrain, @RequestParam("incredelay")String incredelay){
		customerDrain.setDelay(customerDrain.getDelay() + "`" + incredelay);
		customerDrainService.update(customerDrain);
		return "redirect:/drain/list";
	}
	
	@RequestMapping("/confirm")
	public String confirm(@RequestParam("id")Long id, Map<String, Object> map){
		CustomerDrain customerDrain = customerDrainService.getCustomerDrainById(id);
		
		map.put("drain", customerDrain);
		return "drain/confirm";
	}
	@RequestMapping(value="/confirm", method=RequestMethod.POST)
	public String confirm(@RequestParam("customerid")Long customerid, @RequestParam("id")Long id, @RequestParam("reason")String reason, Map<String, Object> map){
		map.put("id", id);
		map.put("date", new Date());
		map.put("reason", reason);
		map.put("status", "流失");
		customerDrainService.updateStatus(map);
		customerService.updateState(customerid, "流失");
		return "redirect:/drain/list";
	}
}
