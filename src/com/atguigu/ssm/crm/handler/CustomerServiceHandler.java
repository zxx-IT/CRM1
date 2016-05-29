package com.atguigu.ssm.crm.handler;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.util.WebUtils;

import com.atguigu.ssm.crm.entity.Customer;
import com.atguigu.ssm.crm.model.Page;
import com.atguigu.ssm.crm.service.CustomerService;
import com.atguigu.ssm.crm.service.CustomerServiceService;
import com.atguigu.ssm.crm.service.UserService;
import com.atguigu.ssm.crm.utils.DataProcessUtils;

@Controller
@RequestMapping("/service")
public class CustomerServiceHandler {
	@Autowired
	private CustomerServiceService customerServiceService;
	@Autowired
	private CustomerService customerService;
	@Autowired
	private UserService userService;
	@RequestMapping("/input")
	public String input(Map<String, Object> map){
		List<Customer> customers = customerService.getAllCustomers();
		map.put("customers", customers);
		List<String> serviceList =  customerService.getAllServiceTypes();
		map.put("serviceTypes", serviceList);
		return "service/input";
	}
	
	@RequestMapping("/allot/list")
	public String allotList(@RequestParam(value="pageNo", required=false)String pageNoStr, Map<String, Object> map, HttpServletRequest request){
		generatePage(pageNoStr, map, request);
		map.put("users", userService.getUserList());
		return "/service/allot/list";
	}
	
	@RequestMapping("/create")
	public String create(com.atguigu.ssm.crm.entity.CustomerService customerService, Map<String, Object> map){
		customerServiceService.save(customerService);
		map.put("message", "服务创建成功，请继续操作！");
		return "service/input";
	}
	@ResponseBody
	@RequestMapping("/allot")
	
	public String allot(com.atguigu.ssm.crm.entity.CustomerService customerService, Long allotId){
		//select 标签不支持级联属性的赋值
		customerServiceService.allot(customerService, allotId);
		return "1";
	} 
	@RequestMapping("/delete")
	public String delete(Long id){
		customerServiceService.delete(id);
		return "redirect:/service/allot/list";
	}
	
	@RequestMapping("/deal/list")
	public String dealList(@RequestParam(value="pageNo", required=false)String pageNoStr, Map<String, Object> map, HttpServletRequest request){
		generatePage(pageNoStr, map, request);
		return "service/deal/list";
	}
	
	@RequestMapping("/deal")
	public String deal(Long id, Map<String, Object> map){
		com.atguigu.ssm.crm.entity.CustomerService customerService2 = customerServiceService.getCustomerServiceById(id);
		map.put("service", customerService2);
		return "service/deal/deal";
	}
	@RequestMapping(value="/deal",method=RequestMethod.POST)
	public String deal(com.atguigu.ssm.crm.entity.CustomerService customerService){
		this.customerServiceService.updateService(customerService);
		return "redirect:/service/deal/list";
	}
	@RequestMapping("/feedback/list")
	public String fbList(@RequestParam(value="pageNo", required=false)String pageNoStr, Map<String, Object> map, HttpServletRequest request){
		generatePage(pageNoStr, map, request);
		return "service/feedback/list";
	}
	
	@RequestMapping("/feedback")
	public String feedBack(Long id, Map<String, Object> map){
		com.atguigu.ssm.crm.entity.CustomerService customerService2 = customerServiceService.getCustomerServiceById(id);
		map.put("service", customerService2);
		return "service/feedback/feedback";
	}
	@RequestMapping(value="/feedback", method=RequestMethod.POST)
	public String feedback(com.atguigu.ssm.crm.entity.CustomerService customerService){
		this.customerServiceService.updateService(customerService);
		return "redirect:/service/feedback/list";
	}
	
	@RequestMapping("/archive/list")
	public String archiveList(@RequestParam(value="pageNo", required=false)String pageNoStr, Map<String, Object> map, HttpServletRequest request){
		generatePage(pageNoStr, map, request);
		return "service/archive/list";
	}
	
	@RequestMapping("/archive")
	public String arcive(Long id, Map<String, Object> map){
		com.atguigu.ssm.crm.entity.CustomerService customerService2 = customerServiceService.getCustomerServiceById(id);
		map.put("service", customerService2);
		return "service/archive/archive";
	}
	public void generatePage(String pageNoStr, Map<String, Object> map, HttpServletRequest request){
		int pageNo = 1;
		try {
			pageNo = Integer.parseInt(pageNoStr);
		} catch (NumberFormatException e) {
		}

		Map<String, Object> params = WebUtils.getParametersStartingWith(
				request, "search_");
		String queryString = DataProcessUtils.encodeParamsToQueryString(params);
		map.put("queryString", queryString);
		
		Page<com.atguigu.ssm.crm.entity.CustomerService> page = customerServiceService.getPage(pageNo, params);
		map.put("page", page);
	}
}