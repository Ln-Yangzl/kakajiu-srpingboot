package com.springboot.kakajiu.config;

import org.apache.shiro.mgt.DefaultSessionStorageEvaluator;
import org.apache.shiro.mgt.DefaultSubjectDAO;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author zlyang
 * @date 2021-10-31
 * @discription config for shiro 设置过滤器
 */
@Configuration
public class ShiroConfig {
    /**
     * ShiroFilterFactoryBean
     * @param defaultWebSecurityManager
     * @return
     */
    @Bean
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(@Autowired DefaultWebSecurityManager defaultWebSecurityManager){
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        //设置安全管理器
        shiroFilterFactoryBean.setSecurityManager(defaultWebSecurityManager);
        Map<String, Filter> filters = new HashMap<>(2);
        //添加 shiroAuthFilter 的拦截器，不要使用 Spring 来管理 Bean
        filters.put("authFilter", new ShiroAuthFilter());
        shiroFilterFactoryBean.setFilters(filters);

        //添加shiro的内置过滤器
        /**
         * anno: 无需认证即可访问
         * authc: 必须认证才可访问
         * user: 必须拥有 记住我 功能才能使用
         * perms: 拥有对摸个资源的权限才能访问
         * role: 拥有某个角色权限才能访问
         */
        // 一定要用 LinkedHashMap，HashMap 顺序不一定按照 put 的顺序，拦截匹配规则是从上往下的
        LinkedHashMap<String, String> filterMap = new LinkedHashMap<>();

        filterMap.put("/testauth", "authFilter");
        filterMap.put("/api/login", "anon");
        filterMap.put("/api/frontpage", "anon");
        filterMap.put("/api/tags", "anon");
        filterMap.put("/api/tagvideo", "anon");
        filterMap.put("/api/**", "authFilter");
//        filterMap.put("/api/getinvitekey", "roles[teacher]");
//        filterMap.put("/api/setinvitekey", "roles[teacher]");

        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterMap);

        // 设置登陆页面
        shiroFilterFactoryBean.setLoginUrl("/noauth");
        // 设置未授权页面
        shiroFilterFactoryBean.setUnauthorizedUrl("/noauth");

        return shiroFilterFactoryBean;
    }



    /**
     * DefaultWebSecurityManager
     * @param shiroRealm
     * @return
     */
    @Bean
    public DefaultWebSecurityManager getDefaultWebSecurityManager(@Autowired ShiroRealm shiroRealm){
        DefaultWebSecurityManager defaultWebSecurityManager = new DefaultWebSecurityManager();
        // 关联realm
        defaultWebSecurityManager.setRealm(shiroRealm);

        // 关闭 Session
        // shiro.ini 方式参考 http://shiro.apache.org/session-management.html#disabling-subject-state-session-storage
        DefaultSessionStorageEvaluator defaultSessionStorageEvaluator = new DefaultSessionStorageEvaluator();
        defaultSessionStorageEvaluator.setSessionStorageEnabled(false);
        DefaultSubjectDAO subjectDAO = new DefaultSubjectDAO();
        subjectDAO.setSessionStorageEvaluator(defaultSessionStorageEvaluator);
        defaultWebSecurityManager.setSubjectDAO(subjectDAO);

        return defaultWebSecurityManager;
    }

    /**
     * create Realm
     * @return
     */
    @Bean(name="shiroRealm")
    public ShiroRealm shiroRealm(){
        return new ShiroRealm();
    }

}
