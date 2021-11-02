package com.springboot.kakajiu.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.springboot.kakajiu.entity.ShiroAuthToken;
import com.springboot.kakajiu.entity.SimpleResponseInfo;
import com.springboot.kakajiu.pojo.User;
import com.springboot.kakajiu.utils.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.web.filter.authc.BasicHttpAuthenticationFilter;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.web.bind.annotation.RequestMethod;

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
@Slf4j
public class ShiroAuthFilter extends BasicHttpAuthenticationFilter {

    protected static final String AUTHORIZATION_HEADER = "Authorization";

    protected static final String BEARER = "Bearer ";

    private String token;

    /**
     * @discripition 解决跨域的时候请求头信息被拦截的问题
     * @param servletRequest
     * @param servletResponse
     * @return
     * @throws Exception
     */
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
        log.info("isAccessAllowed called! get authzheader: " + this.getAuthzHeader(request));
        if(this.getAuthzHeader(request)!=null){
            try{
                executeLogin(request, response);
                log.info("Token auth success!");
                return true;
            }catch (Exception e){
                this.shiroAuthResponse(response, false, "token 无效");
                log.warn("Token auth failed! 无效token");
                return false;
            }
        } else {
            this.shiroAuthResponse(response, false, "token 缺失");
            log.warn("Token auth failed! 缺失token");
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
