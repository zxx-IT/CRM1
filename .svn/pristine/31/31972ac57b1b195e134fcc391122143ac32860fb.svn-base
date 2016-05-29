package com.atguigu.ssm.crm.handler;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.util.WebUtils;

import com.atguigu.ssm.crm.entity.Dict;
import com.atguigu.ssm.crm.model.Page;
import com.atguigu.ssm.crm.service.DictService;
import com.atguigu.ssm.crm.utils.DataProcessUtils;

@Controller
@RequestMapping("/dict")
public class DictHandler {
	@Autowired
	private DictService dictService;
	
	@RequestMapping("/list")
	public String list (@RequestParam(value="pageNo", required=false)String pageNoStr, Map<String, Object> map, HttpServletRequest request){
		int pageNo = 1;
		try {
			pageNo = Integer.parseInt(pageNoStr);
		} catch (NumberFormatException e) {
		}

		Map<String, Object> params = WebUtils.getParametersStartingWith(
				request, "search_");
		String queryString = DataProcessUtils.encodeParamsToQueryString(params);
		map.put("queryString", queryString);
		
		Page<Dict> page = dictService.getPage(pageNo, params);
		
		map.put("page", page);
		
		return "/dict/list";
	}
	
	@RequestMapping("/create")
	public String input(Map<String, Object> map, @RequestParam(value="id", required=false)Integer id){
		if (id != null) {
			Dict dict = dictService.getDictById(id);
			System.out.println("===" + dict.getItem());
			map.put("dict", dict);
		}else {
			map.put("dict", new Dict());
		}
		return "/dict/input";
	}
	@RequestMapping(value="/create", method=RequestMethod.POST)
	public String create(Dict dict, @RequestParam(value="id", required=false)Integer id){
		if (id == null) {
			dictService.save(dict);
		}else {
			dictService.update(dict);
		}
		
		return "redirect:/dict/list";
	}
	@RequestMapping("/delete")
	public String delete(@RequestParam("id")Integer id){
		dictService.delete(id);
		return "redirect:/dict/list";
	}
}
