package com.springboot.kakajiu.service;

import com.springboot.kakajiu.entity.ShiroAuthToken;
import com.springboot.kakajiu.pojo.User;
import com.springboot.kakajiu.utils.JwtUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.stereotype.Service;

/**
 * @author zlyang
 * @date 2021-10-31
 * @discription 用户授权和认证
 */
@Service
public class ShiroRealm extends AuthorizingRealm {
    @Override
    public boolean supports(AuthenticationToken authenticationToken) {
        return authenticationToken instanceof ShiroAuthToken;
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        User user = JwtUtils.validationToken(principalCollection.toString());
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        if(!user.getRoles().isEmpty()){
            info.addRole(user.getRoles());
        }
        return info;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        ShiroAuthToken shiroAuthToken = (ShiroAuthToken) authenticationToken;
        String token = shiroAuthToken.getPrincipal().toString();
        User user = JwtUtils.validationToken(token);
        if(user == null || user.getUsername() == null){
            throw new AuthenticationException("Token 无效");
        }
        return new SimpleAuthenticationInfo(token, token, "ShiroRealm");
    }
}
