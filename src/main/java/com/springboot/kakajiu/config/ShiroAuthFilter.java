package com.springboot.kakajiu.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.springboot.kakajiu.entity.ShiroAuthToken;
import com.springboot.kakajiu.entity.SimpleResponseInfo;
import com.springboot.kakajiu.pojo.User;
import com.springboot.kakajiu.utils.JwtUtils;
import org.apache.shiro.web.filter.authc.BasicHttpAuthenticationFilter;
import org.apache.shiro.web.util.WebUtils;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author zlyang
 * @date 2021-11-1
 * @discription Shiro 的拦截器，拦截和验证 Token 的有效性
 */
public class ShiroAuthFilter extends BasicHttpAuthenticationFilter {

    protected static final String AUTHORIZATION_HEADER = "Authorization";

    protected static final String BEARER = "Bearer ";

    private String token;

    @Override
    protected boolean executeLogin(ServletRequest request, ServletResponse response) throws Exception {

        this.getSubject(request, response).login(new ShiroAuthToken(this.token));
        return true;
    }

    @Override
    protected String getAuthzHeader(ServletRequest request) {
        try{
            HttpServletRequest httpRequest = WebUtils.toHttp(request);
            this.token = httpRequest.getHeader(AUTHORIZATION_HEADER).substring(BEARER.length());
            return this.token;
        }catch (Exception e){
            return null;
        }
    }

    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
        if(this.getAuthzHeader(request)!=null){
            try{
                executeLogin(request, response);
                return true;
            }catch (Exception e){
                this.shiroAuthResponse(response, false, "token 无效");
                return false;
            }
        } else {
            this.shiroAuthResponse(response, false, "token 缺失");
            return false;
        }
    }

    private void shiroAuthResponse(ServletResponse response, boolean valid, String info){
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        if(!valid){
            httpServletResponse.setContentType("application/json;charset=UTF-8");

            try {
                PrintWriter writer = httpServletResponse.getWriter();
                ObjectMapper mapper = new ObjectMapper();
                SimpleResponseInfo responseInfo = new SimpleResponseInfo();
                responseInfo.setStatus(-1);
                responseInfo.setError(info);
                writer.write(mapper.writeValueAsString(responseInfo));
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }


}
