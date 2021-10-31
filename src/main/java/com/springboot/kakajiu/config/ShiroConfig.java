package com.springboot.kakajiu.config;

import com.springboot.kakajiu.service.ShiroRealm;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;
import java.util.LinkedHashMap;

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

        //添加shiro的内置过滤器
        /**
         * anno: 无需认证即可访问
         * authc: 必须认证才可访问
         * user: 必须拥有 记住我 功能才能使用
         * perms: 拥有对摸个资源的权限才能访问
         * role: 拥有某个角色权限才能访问
         */

        LinkedHashMap<String, String> filterMap = new LinkedHashMap<>();

        filterMap.put("/testauth", "authc");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterMap);

        // 设置登陆页面
        shiroFilterFactoryBean.setLoginUrl("/login");
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

        return defaultWebSecurityManager;
    }

    /**
     * create Realm
     * @return
     */
    @Bean(name="userRealm")
    public ShiroRealm userRealm(){
        return new ShiroRealm();
    }

}
