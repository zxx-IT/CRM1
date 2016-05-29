package com.atguigu.ssm.crm.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.atguigu.ssm.crm.entity.Product;
import com.atguigu.ssm.crm.mappers.ProductMapper;
import com.atguigu.ssm.crm.model.Page;
import com.atguigu.ssm.crm.orm.PropertyFilter;

@Service
@Transactional
public class ProductService {
	@Autowired
	private ProductMapper productMapper;

	public Page<Product> getPage(int pageNo, Map<String, Object> params) {
		Page<Product> page = new Page<>();
		//设置页码
		page.setPageNo(pageNo);
		Map<String, Object> mybatisParams = PropertyFilter.parseRequestParams2MybatisParams(params);
		int totalElements = (int) productMapper.getTotalElements(mybatisParams);
		page.setTotalElements(totalElements);
		
		int firstIndex = ((pageNo - 1) * page.getPageSize()) + 1;
		int endIndex = firstIndex + page.getPageSize();
		mybatisParams.put("firstIndex", firstIndex);
		mybatisParams.put("endIndex", endIndex);
		List<Product> content = productMapper.getContent(mybatisParams);
		page.setContent(content);
		return page;
	}

	public Product getProductById(Long id) {
		return productMapper.getProductById(id);
	}

	public void update(Product product) {
		productMapper.update(product);
	}

	public void save(Product product) {
		productMapper.save(product);
	}

	public void delete(Integer id) {
		productMapper.delete(id);
	}

	public List<Product> getProductList() {
		return productMapper.getProductList();
	}
	
}
