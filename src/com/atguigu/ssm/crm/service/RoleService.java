package com.atguigu.ssm.crm.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.atguigu.ssm.crm.entity.Authority;
import com.atguigu.ssm.crm.entity.Role;
import com.atguigu.ssm.crm.mappers.RoleMapper;
import com.atguigu.ssm.crm.model.Page;

@Service
@Transactional
public class RoleService {
	@Autowired
	private RoleMapper roleMapper;

	public List<Role> getAllRoles() {
		return roleMapper.getAllRoles();
	}

	public Page<Role> getPage(int pageNo) {
		Page<Role> page = new Page<>();
		page.setPageNo(pageNo);
		
		int totalElements = (int)roleMapper.getTotalElements();
		
		page.setTotalElements(totalElements);
		Map<String, Object> map = new HashMap<String, Object>();
		
		int firstIndex = ((pageNo - 1) * page.getPageSize()) + 1;
		int endIndex = firstIndex + page.getPageSize();
		
		map.put("firstIndex", firstIndex);
		map.put("endIndex", endIndex);
		List<Role> content = roleMapper.getContent(map);
		page.setContent(content);
		return page;
	}

	public Role getRoleById(Long id) {
		return roleMapper.getRoleById(id);
	}

	public void update(Role role) {
		roleMapper.update(role);
	}

	public void save(Role role) {
		roleMapper.save(role);
	}

	public void delete(Integer id) {
		roleMapper.delete(id);
	}

	public void saveRoleAuth(Map<String, Object> map) {
		roleMapper.saveRoleAuth(map);
	}

	public void deleteRoleAuth(Long id) {
		roleMapper.deleteRoleAuth(id);
	}

	public List<Integer> getCurrentAuth(Long id) {
		return roleMapper.getCurrentAuth(id);
	}

	public List<Authority> getAuthorityById(Long roleId) {
		return roleMapper.getAuthorityById(roleId);
	}
	
	
}
