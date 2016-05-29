package com.atguigu.ssm.crm.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.atguigu.ssm.crm.entity.Storage;
import com.atguigu.ssm.crm.mappers.StorageMapper;
import com.atguigu.ssm.crm.model.Page;
import com.atguigu.ssm.crm.orm.PropertyFilter;

@Service
@Transactional
public class StorageService {
	@Autowired
	private StorageMapper storageMapper;

	public Page<Storage> getPage(int pageNo, Map<String, Object> params) {
		Page<Storage> page = new Page<>();
		page.setPageNo(pageNo);
		
		Map<String, Object> mybatisParams = PropertyFilter.parseRequestParams2MybatisParams(params);
		
		int totalElements = (int)storageMapper.getTotalElements(mybatisParams);
		page.setTotalElements(totalElements);
		int firstIndex = ((pageNo - 1) * page.getPageSize()) + 1;
		int endIndex = firstIndex + page.getPageSize();
		
		mybatisParams.put("firstIndex", firstIndex);
		mybatisParams.put("endIndex", endIndex);
		
		List<Storage> content = storageMapper.getContent(mybatisParams);
		page.setContent(content);
		return page;
	}

	public Storage getStorageById(Long id) {
		return storageMapper.getStorageById(id);
	}

	public void update(Storage storage) {
		storageMapper.update(storage);
	}

	public void save(Storage storage) {
		storageMapper.save(storage);
	}

	public void delete(Integer id) {
		storageMapper.delete(id);
	}
	
}
