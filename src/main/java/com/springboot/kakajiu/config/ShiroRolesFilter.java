package com.springboot.kakajiu.config;

import com.springboot.kakajiu.pojo.User;
import com.springboot.kakajiu.utils.JwtUtils;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.CollectionUtils;
import org.apache.shiro.web.filter.authz.RolesAuthorizationFilter;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Set;

public class ShiroRolesFilter extends RolesAuthorizationFilter {
    @Override
    public boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) throws IOException {
        try {
            HttpServletRequest httpRequest = (HttpServletRequest) request;
            String token = httpRequest.getHeader("Authorization").substring("Bearer ".length());
            String userRoles = JwtUtils.validationToken(token).getRoles();
            String[] rolesArray = (String[]) ((String[]) mappedValue);
            if (rolesArray != null && rolesArray.length != 0) {
                Set<String> roles = CollectionUtils.asSet(rolesArray);
                return roles.stream().allMatch(userRoles::contains);
            } else {
                return true;
            }
        }catch (NullPointerException e){
            return false;
        }
    }

    @Override
    protected boolean preHandle(ServletRequest servletRequest, ServletResponse servletResponse) throws Exception {
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        if (request.getMethod().equals(RequestMethod.OPTIONS.name())){
            String curOrigin = request.getHeader("Origin");
            response.setHeader("Access-Control-Allow-Origin", curOrigin == null ? "true" : curOrigin);
            response.setHeader("Access-Control-Allow-Credentials", "true");
            response.setHeader("Access-Control-Allow-Methods", "POST, GET, PATCH, DELETE, PUT");
            response.setHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept, Authorization");
            return false;
        }
        return super.preHandle(request, response);
    }
}
