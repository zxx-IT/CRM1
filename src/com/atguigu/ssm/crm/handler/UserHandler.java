package com.atguigu.ssm.crm.handler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.WebUtils;

import com.atguigu.ssm.crm.entity.Authority;
import com.atguigu.ssm.crm.entity.Role;
import com.atguigu.ssm.crm.entity.User;
import com.atguigu.ssm.crm.model.Navigation;
import com.atguigu.ssm.crm.model.Page;
import com.atguigu.ssm.crm.service.RoleService;
import com.atguigu.ssm.crm.service.UserService;
import com.atguigu.ssm.crm.utils.DataProcessUtils;

@Controller
@RequestMapping("/user")
public class UserHandler {

	@Autowired
	private UserService userService;
	@Autowired
	private ResourceBundleMessageSource messageSource;
	@Autowired
	private RoleService roleService;
	
	@ResponseBody
	@RequestMapping("/navigate")
	public List<Navigation> navigate(HttpServletRequest request){
		String contextPath = request.getContextPath();
		
		List<Navigation> navigations = new ArrayList<>();
		
		Navigation top = new Navigation();
		top.setText("客户关系管理系统");
		top.setId(Long.MAX_VALUE);
		navigations.add(top);
		
		//1. 获取登录的 User 对象
		User user = (User) SecurityUtils.getSubject().getPrincipals().getPrimaryPrincipal();
		
		//创建一个 Map. 用来保存父权限对应的 Navigation 对象
		Map<Long, Navigation> parentNavitations = new HashMap<Long, Navigation>();
		
		//2. 遍历其所有的 Authority
		for(Authority authority: user.getRole().getAuthorities()){
			//3. 创建子权限所对应的 Navigation 对象
			Navigation navigation = new Navigation();
			navigation.setId(authority.getId());
			navigation.setText(authority.getDisplayName());
			navigation.setUrl(contextPath + authority.getUrl());
			
			//4. 把子权限对应的 Navigation 加入到其对应的父权限的 Navigation 的 children 中.
			Authority parentAuthority = authority.getParentAuthority();
			Navigation parentNavigation = parentNavitations.get(parentAuthority.getId());
			
			//若父权限对应的 Navigation 对象不存在. 则创建新的 Navigation 对象. 
			if(parentNavigation == null){
				parentNavigation = new Navigation();
				parentNavigation.setId(parentAuthority.getId());
				parentNavigation.setText(parentAuthority.getDisplayName());
				parentNavigation.setState("closed");
				
				//把父权限对应的 Navigation 放入到顶级 Navigation 的 children 中. 
				top.getChildren().add(parentNavigation);
				parentNavitations.put(parentAuthority.getId(), parentNavigation);
			}
			
			parentNavigation.getChildren().add(navigation);
		}
		
		return navigations;
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(@RequestParam("username") String name,
			@RequestParam("password") String password, Locale locale,
			RedirectAttributes attributes, HttpSession session) {

		Subject currentUser = SecurityUtils.getSubject();
		if (!currentUser.isAuthenticated()) {
			UsernamePasswordToken token = new UsernamePasswordToken(name, password);
			token.setRememberMe(true);
			try {
				currentUser.login(token);
				User user = (User) SecurityUtils.getSubject().getPrincipal();
				session.setAttribute("user", user);
			} catch (AuthenticationException e) {
				System.out.println("登录失败" + e.getMessage());
				String code = "error.user.login";
				Object[] args = null;
				String message = messageSource.getMessage(code, args, locale);
				attributes.addFlashAttribute("message", message);
				attributes.addFlashAttribute("username", name);
				return "redirect:/index";
			}
		}

		return "redirect:/success";

	}

	@RequestMapping("/list")
	public String list(
			@RequestParam(value = "pageNo", required = false) String pageNoStr,
			HttpServletRequest request, Map<String, Object> map) {
		int pageNo = 1;
		try {
			pageNo = Integer.parseInt(pageNoStr);
		} catch (NumberFormatException e) {
		}

		Map<String, Object> params = WebUtils.getParametersStartingWith(
				request, "search_");
		String queryString = DataProcessUtils.encodeParamsToQueryString(params);
		map.put("queryString", queryString);
		Page<User> page = userService.getPage(pageNo, params);
		map.put("page", page);
		return "user/list";
	}

	@RequestMapping("/create")
	public String input(
			@RequestParam(value = "id", required = false) Integer id,
			Map<String, Object> map) {
		if (id != null) {
			map.put("user", userService.getUserById(id));
		} else {
			map.put("user", new User());
		}
		List<Role> roles = roleService.getAllRoles();
		map.put("roles", roles);
		List<Integer> allStatus = new ArrayList<>();
		allStatus.add(0);
		allStatus.add(1);
		map.put("allStatus", allStatus);
		return "user/input";
	}

	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public String create(User user) {
		if (user.getId() != null) {
			userService.update(user);
		} else {
			userService.save(user);
		}
		return "redirect:/user/list";
	}

	@RequestMapping("delete")
	public String delete(Integer id) {
		userService.delete(id);
		return "redirect:/user/list";
	}
}
