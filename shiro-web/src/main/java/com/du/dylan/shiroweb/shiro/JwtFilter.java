package com.du.dylan.shiroweb.shiro;

import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.web.filter.authc.BasicHttpAuthenticationFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * 因为要整合了 JWT ，我们需要自定义过滤器 JWTFilter。
 * JWTFilter 继承了 BasicHttpAuthenticationFilter，并部分原方法进行了重写。
 *
 * 该过滤器主要有三步：
 * 1.检验请求头是否带有 Token: ((HttpServletRequest) request).getHeader(“Token”)
 * 2.如果带有 Token ，则执行 Shiro 中的 login() 方法，该方法将导致：
 * 将 Token 提交到 Realm 中进行验证（执行自定义的Reaml中的方法）；
 * 如果没有 Token，则说明当前状态为游客状态或者其他一些不需要进行认证的接口
 * 3.如果在 Token 校验的过程中出现错误，如：Token 校验失败，
 * 那么我会将该请求视为认证不通过，则重定向到 /unauthorized/**
 * @author Sakura
 */
@Slf4j
public class JwtFilter extends BasicHttpAuthenticationFilter {

    /**
     * 如果请求头带有token,则对token进行检查,否则直接放行
     */
    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
        //判断请求头是否带有token
        if (isLoginAttempt(request,response)){
            //如果存在token,则进入executeLogin()方法执行登入，并检测 token 的正确性
            try {
                executeLogin(request,response);
            } catch (Exception e) {
                log.error("Error! {}", e.getMessage());
                responseError(response, e.getMessage());
            }
        }
        // 如果不存在 token ，则可能是执行登录操作/游客访问状态，所以直接放行
        return true;
    }


    /**
     * 检测 header 中是否包含token
     */
    @Override
    protected boolean isLoginAttempt(ServletRequest request, ServletResponse response) {
        return getTokenFromRequest(request) !=null;
    }


    /**
     * 执行登录操作
     */
    @Override
    protected boolean executeLogin(ServletRequest request, ServletResponse response) throws Exception{
        String token = getTokenFromRequest(request);
        JwtToken jwtToken = new JwtToken(token);
        // 提交给 realm 进行登录,如果错误,会抛出异常
        getSubject(request,response).login(jwtToken);
        // 如果没有抛出异常,则代表登录成功,返回true
        return true;
    }

    /**
     * 从请求中获取token
     * @param request
     * @return
     */
    private String getTokenFromRequest(ServletRequest request) {
        HttpServletRequest req = (HttpServletRequest) request;
        return req.getHeader("token");
    }

    /**
     * 非法请求将跳转到 "/unauthorized/**"
     */
    private void responseError(ServletResponse response, String message) {
        try {
            HttpServletResponse resp = (HttpServletResponse) response;
            // 设置编码，否则中文字符在重定向时会变为空字符串
            message = URLEncoder.encode(message, "UTF-8");
            resp.sendRedirect("/unauthorized");
        } catch (UnsupportedEncodingException e) {
            log.error("Error! {}", e.getMessage());
        } catch (IOException e) {
            log.error("Error! {}", e.getMessage());
        }
    }
}
