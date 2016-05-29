package com.atguigu.ssm.crm.realm;

import java.util.HashSet;
import java.util.Set;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import com.atguigu.ssm.crm.entity.Authority;
import com.atguigu.ssm.crm.entity.User;
import com.atguigu.ssm.crm.service.RoleService;
import com.atguigu.ssm.crm.service.UserService;

public class CrmRealm extends AuthorizingRealm{
	
	@Autowired
	private UserService userService;
	@Autowired
	private RoleService roleService;
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(
			PrincipalCollection principals) {
		Object principal = principals.getPrimaryPrincipal();
		User user = (User)principal;
		/*Long roleId = user.getRole().getId();
		List<Authority> authList = roleService.getAuthorityById(roleId);
		for (Authority authority : authList) {
			String authName = authority.getName();
			if (!authName.startsWith("service") && !authName.startsWith("report")) {
				info.addRole(authName);
			}
		}*/
		
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
		Set<String> roles = new HashSet<>();
		for(Authority authority: user.getRole().getAuthorities()){
			roles.add(authority.getName());
		}
		System.out.println("principal: " + principal + ", roles: " + roles);
		info.addRoles(roles);
		System.out.println("principal = " + principal);
		
		
		return info;
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(
			AuthenticationToken token) throws AuthenticationException {
		UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken)token;
		
		String username = usernamePasswordToken.getUsername();
		
		System.out.println("利用  " + username + "来获取数据库中对应的记录");
		
		User user = userService.getUserByName(username);
		
		String password = user.getPassword();
		Object principal = user;
		String hashedCredentials = password;
	
		ByteSource credentialsSalt = ByteSource.Util.bytes(username);
		String realmName = getName();
		
		SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(principal, hashedCredentials, credentialsSalt, realmName);
		return info;
	}
	
	public static void main(String[] args) {
		String hashAlgorithmName = "MD5";
		Object credentials = "a";
		Object salt = ByteSource.Util.bytes("song");
		System.out.println(salt);
		int iterations = 1024;
		Object result = new SimpleHash(hashAlgorithmName, credentials, salt, iterations);
		System.out.println(result);
	}
}

