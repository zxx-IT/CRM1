package com.atguigu.ssm.crm.handler;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.util.WebUtils;

import com.atguigu.ssm.crm.entity.Storage;
import com.atguigu.ssm.crm.model.Page;
import com.atguigu.ssm.crm.service.ProductService;
import com.atguigu.ssm.crm.service.StorageService;
import com.atguigu.ssm.crm.utils.DataProcessUtils;

@Controller
@RequestMapping("/storage")
public class StorageHandler {
	@Autowired
	private StorageService storageService;
	@Autowired
	private ProductService productService;
	@RequestMapping("/list")
	
	public String list(@RequestParam(value="pageNo", required=false)String pageNoStr, HttpServletRequest request, Map<String, Object> map){
		int pageNo = 1;
		try {
			pageNo = Integer.parseInt(pageNoStr);
		} catch (NumberFormatException e) {
		}
		
		Map<String, Object> params = WebUtils.getParametersStartingWith(request, "search_");
		String queryString = DataProcessUtils.encodeParamsToQueryString(params);
		map.put("queryString", queryString);
		Page<Storage> page = storageService.getPage(pageNo, params);
		map.put("page", page);
		return "storage/list";
	}
	
	@RequestMapping("/create")
	public String input(@RequestParam(value="id", required=false)Long id, Map<String, Object> map){
		if(id != null){
			Storage storage = storageService.getStorageById(id);
			map.put("storage", storage);
		}else {
			map.put("storage", new Storage());
		}
		map.put("products", productService.getProductList());
		return "storage/input";
	}
	@RequestMapping(value="/create", method=RequestMethod.POST)
	public String create(Storage storage, @RequestParam(value="incrementCount", required=false)Integer incrementCount){
		if (storage.getId() != null) {
			storage.setStockCount(storage.getStockCount() + incrementCount);
			storageService.update(storage);
		}else {
			storageService.save(storage);
		}
		return "redirect:/storage/list";
	}
	@RequestMapping("/delete")
	public String delete(@RequestParam("id")Integer id){
		storageService.delete(id);
		return "redirect:/storage/list";
	}
}
