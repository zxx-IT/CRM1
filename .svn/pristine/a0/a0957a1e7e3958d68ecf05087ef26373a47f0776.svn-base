package com.atguigu.ssm.crm.handler;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.WebUtils;

import com.atguigu.ssm.crm.entity.SalesChance;
import com.atguigu.ssm.crm.entity.SalesPlan;
import com.atguigu.ssm.crm.entity.User;
import com.atguigu.ssm.crm.model.Page;
import com.atguigu.ssm.crm.service.PlanService;
import com.atguigu.ssm.crm.service.SalesChanceService;
import com.atguigu.ssm.crm.utils.DataProcessUtils;

@RequestMapping("/plan")
@Controller
public class ChancePlanHandler {
	@Autowired
	private SalesChanceService salesChanceService;
	@Autowired
	private PlanService planService;
	@RequestMapping("/list")
	public String list(@RequestParam(value = "pageNo", required = false) String pageNoStr,
			Map<String, Object> map, HttpServletRequest request, HttpSession session, RedirectAttributes attributes){
		int pageNo = 1;
		try {
			pageNo = Integer.parseInt(pageNoStr);
		} catch (NumberFormatException e) {
		}

		Map<String, Object> params = WebUtils.getParametersStartingWith(
				request, "search_");
		String queryString = DataProcessUtils.encodeParamsToQueryString(params);
		map.put("queryString", queryString);
		
		User user = (User) SecurityUtils.getSubject().getPrincipal();
		Long id = user.getId();
		
		Page<SalesChance> page = salesChanceService.getChancePage(pageNo, params, id);

		map.put("page", page);
		return "/plan/list";
	}
	
	@RequestMapping(value="/make", method=RequestMethod.GET)
	public String make(Map<String, Object> map, @RequestParam("id")Integer id){
		SalesChance planChance = salesChanceService.getPlanChance(id);
		
		map.put("chance", planChance);
		return "/plan/make";
	}
	@RequestMapping(value="/make", method=RequestMethod.POST)
	public String make(SalesPlan plan, RedirectAttributes attributes){
		planService.save(plan);
		attributes.addFlashAttribute("message", "新建计划成功");
		return "redirect:/plan/make?id=" + plan.getChance().getId();
	}
	@RequestMapping("/make-ajax")
	@ResponseBody
	public String make(@RequestParam("id") Long id, 
			@RequestParam("todo") String todo){
		SalesPlan plan = planService.get(id);
		plan.setTodo(todo);
		planService.update(plan);
		return "1";
	}

	@RequestMapping("/delete-ajax")
	@ResponseBody
	public String delete(@RequestParam("id") Long id){
		planService.delete(id); 
		return "1";
	}
	@RequestMapping(value="/execution", method=RequestMethod.GET)
	public String execute(@RequestParam("id") Integer id, 
			Model model){
		model.addAttribute("chance", salesChanceService.getPlanChance(id)); 
		return "/plan/exec";
	}
	@RequestMapping("/execute")
	public String execute(@RequestParam("id") Long id, @RequestParam("result") String result, 
			Model model){
		SalesPlan plan = planService.get(id);
		plan.setResult(result);
		planService.update(plan);
		model.addAttribute("chance", plan.getChance());  
		return "/plan/exec";
	}
}
