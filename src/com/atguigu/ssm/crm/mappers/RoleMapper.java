package com.atguigu.ssm.crm.mappers;

import java.util.List;
import java.util.Map;

import com.atguigu.ssm.crm.entity.Authority;
import com.atguigu.ssm.crm.entity.Role;

public interface RoleMapper {

	List<Role> getAllRoles();

	long getTotalElements();

	List<Role> getContent(Map<String, Object> map);

	Role getRoleById(Long id);

	void update(Role role);

	void save(Role role);

	void delete(Integer id);

	void saveRoleAuth(Map<String, Object> map);

	void deleteRoleAuth(Long id);

	List<Integer> getCurrentAuth(Long id);

	List<Authority> getAuthorityById(Long roleId);

}
