package com.atguigu.ssm.crm.handler;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.util.WebUtils;

import com.atguigu.ssm.crm.entity.Contact;
import com.atguigu.ssm.crm.model.Page;
import com.atguigu.ssm.crm.service.ContactService;
import com.atguigu.ssm.crm.service.CustomerService;

@Controller
@RequestMapping("/contact")
public class ContactHandler {
	@Autowired
	private ContactService contactService;
	@Autowired
	private CustomerService customerService;
	@RequestMapping("/list")
	public String list(@RequestParam("customerid")Integer id, @RequestParam(value = "pageNo", required = false) String pageNoStr,
			Map<String, Object> map, HttpServletRequest request){
		int pageNo = 1;
		try {
			pageNo = Integer.parseInt(pageNoStr);
		} catch (NumberFormatException e) {
		}

		Map<String, Object> params = WebUtils.getParametersStartingWith(
				request, "search_");
		String custIdStr = request.getParameter("customerid");
		String queryString = "";
		if (custIdStr != null) {
			queryString = "customerid=" + custIdStr;
		}
		map.put("queryString", queryString);
		
		Page<Contact> page = contactService.getPage(pageNo, params, id);
		
		map.put("page", page);
		map.put("customer", customerService.getCustomerById(id));
		return "/contact/list";
	}
	
	@RequestMapping("/create")
	public String create(Map<String ,Object> map){
		map.put("contact", new Contact());
		return "/contact/input";
	}
	@RequestMapping(value="/create", method=RequestMethod.POST)
	public String create(Contact contact,@RequestParam("customer.id")Integer id){
		if (contact.getId() == null) {
			contactService.saveCreate(contact);
		}else {
			contactService.update(contact);
		}
		return "redirect:/contact/list?customerid=" + id;
	}
	@RequestMapping(value="/toEditPage", method=RequestMethod.GET)
	public String toEditPage(@RequestParam("id")Integer id, Map<String, Object> map){
		Contact contact = contactService.getContactById(id);
		map.put("contact", contact);
		return "/contact/input";
	}
	@RequestMapping("delete")
	public String delete(@RequestParam("id")Integer id, @RequestParam("customerid")Integer customerid){
		contactService.delete(id);
		return "redirect:/contact/list?customerid=" + customerid;
	}
}
