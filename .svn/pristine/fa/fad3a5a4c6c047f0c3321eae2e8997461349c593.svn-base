package com.atguigu.ssm.crm.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.atguigu.ssm.crm.entity.Dict;
import com.atguigu.ssm.crm.mappers.DictMapper;
import com.atguigu.ssm.crm.model.Page;
import com.atguigu.ssm.crm.orm.PropertyFilter;

@Service
@Transactional
public class DictService {
	@Autowired
	private DictMapper dictMapper;

	public Page<Dict> getPage(int pageNo, Map<String, Object> params) {
		Page<Dict> page = new Page<>();
		//设置页码
		page.setPageNo(pageNo);
		Map<String, Object> mybatisParams = PropertyFilter.parseRequestParams2MybatisParams(params);
		int totalElements = (int) dictMapper.getTotalElements(mybatisParams);
		page.setTotalElements(totalElements);
		
		int firstIndex = ((pageNo - 1) * page.getPageSize()) + 1;
		int endIndex = firstIndex + page.getPageSize();
		mybatisParams.put("firstIndex", firstIndex);
		mybatisParams.put("endIndex", endIndex);
		List<Dict> content = dictMapper.getContent(mybatisParams);
		page.setContent(content);
		return page;
	}

	public void save(Dict dict) {
		dictMapper.save(dict);
	}

	public Dict getDictById(Integer id) {
		return dictMapper.getDictById(id);
	}

	public void update(Dict dict) {
		dictMapper.update(dict);
	}

	public void delete(Integer id) {
		dictMapper.delete(id);
	}
}
