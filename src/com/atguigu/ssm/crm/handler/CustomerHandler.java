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
import com.atguigu.ssm.crm.entity.Dict;
import com.atguigu.ssm.crm.model.Page;
import com.atguigu.ssm.crm.service.CustomerService;
import com.atguigu.ssm.crm.utils.DataProcessUtils;

@Controller
@RequestMapping("/customer")
public class CustomerHandler {
	@Autowired
	private CustomerService customerService;

	@RequestMapping("/list")
	public String list(
			@RequestParam(value = "pageNo", required = false) String pageNoStr,
			Map<String, Object> map, HttpServletRequest request) {
		int pageNo = 1;
		try {
			pageNo = Integer.parseInt(pageNoStr);
		} catch (NumberFormatException e) {
		}

		Map<String, Object> params = WebUtils.getParametersStartingWith(
				request, "search_");
		String queryString = DataProcessUtils.encodeParamsToQueryString(params);
		map.put("queryString", queryString);

		Page<Customer> page = customerService.getPage(pageNo, params);
		List<Dict> levels = customerService.getLevels();
		map.put("levels", levels);
		List<Dict> regions = customerService.getDictList();
		map.put("regions", regions);
		map.put("page", page);
		return "/customer/list";
	}
	@RequestMapping("/create")
	public String create(Map<String, Object> map, @RequestParam("id")Integer id){
		Customer customer = customerService.getCustomerById(id);
		map.put("customer", customer);
		map.put("regions", customerService.getDictList());
		map.put("levels", customerService.getLevels());
		map.put("managers", customerService.getManagerList());
		//满意度
		map.put("satisfies", customerService.getStatisfieList());
		//信用度
		map.put("credits", customerService.getCreditList());
		return "/customer/input";
	}
	
	@RequestMapping(value="/create", method=RequestMethod.POST)
	public String create(Customer customer){
		customerService.update(customer);
		return "redirect:/customer/list";
	}
	@ResponseBody
	@RequestMapping(value="/delete", method=RequestMethod.DELETE)
	public String delete(@RequestParam("id")Long id){
		customerService.updateState(id, "删除");
		return "1";
	}
}
