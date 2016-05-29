package com.atguigu.ssm.crm.handler;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.util.WebUtils;

import com.atguigu.ssm.crm.entity.Product;
import com.atguigu.ssm.crm.model.Page;
import com.atguigu.ssm.crm.service.ProductService;
import com.atguigu.ssm.crm.utils.DataProcessUtils;

@Controller
@RequestMapping("/product")
public class ProductHandler {
	@Autowired
	private ProductService productService;
	
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
		
		Page<Product> page = productService.getPage(pageNo, params);
		
		map.put("page", page);
		return "/product/list";
	}
	
	@RequestMapping("/create")
	public String input(@RequestParam(value="id", required=false)Long id, Map<String, Object> map){
		if(id != null){
			map.put("product", productService.getProductById(id));
		}else {
			map.put("product", new Product());
		}
		return "product/input";
	}
	
	@RequestMapping(value="/create", method=RequestMethod.POST)
	public String create(Product product){
		if (product.getId() != null) {
			productService.update(product);
		}else {
			productService.save(product);
		}
		return "redirect:/product/list";
	}
	@RequestMapping("/delete")
	public String delete(@RequestParam("id")Integer id){
		productService.delete(id);
		return "redirect:/product/list";
	}
}
