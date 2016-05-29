package com.atguigu.ssm.crm.handler;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.atguigu.ssm.crm.entity.Role;
import com.atguigu.ssm.crm.model.Page;
import com.atguigu.ssm.crm.service.AuthorityService;
import com.atguigu.ssm.crm.service.RoleService;

@RequestMapping("/role")
@Controller
public class RoleHandler {
	@Autowired
	private RoleService roleService;
	@Autowired
	private AuthorityService authorityService;
	@RequestMapping("/list")
	public String list(@RequestParam(value="pageNo", required=false)String pageNoStr, Map<String, Object> map){
		int pageNo = 1;
		try {
			pageNo = Integer.parseInt(pageNoStr);
		} catch (NumberFormatException e) {
		}
		Page<Role> page = roleService.getPage(pageNo);
		map.put("page", page);
		return "role/list";
	}
	
	@RequestMapping(value="/create", method=RequestMethod.POST)
	public String create(Role role){
			roleService.save(role);
		return "redirect:/role/list";
	}
	
	@RequestMapping("/delete/{id}")
	public String delete(@PathVariable("id")Integer id){
		roleService.delete(id);
		return "redirect:/role/list";
	}
	@RequestMapping("/assign/{id}")
	public String assign(@PathVariable("id")Long id,Map<String, Object> map){
		Role role = roleService.getRoleById(id);
		List<Integer> idList = roleService.getCurrentAuth(id);
		role.setIdList(idList);
		map.put("role", role);
		map.put("parentAuthorities", authorityService.getAllAuthorities());
		return "role/assign";
	}
	@RequestMapping(value="/assign", method=RequestMethod.POST)
	public String assign(Role role){
		List<Integer> idList = role.getIdList();
		roleService.deleteRoleAuth(role.getId());
		for (Integer authId : idList) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("roleId", role.getId());
			map.put("authId", authId);
			roleService.saveRoleAuth(map);
		}
		return "redirect:/role/list";
	}
}
