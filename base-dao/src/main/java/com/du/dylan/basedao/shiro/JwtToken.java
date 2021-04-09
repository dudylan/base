package com.du.dylan.basedao.shiro;

import org.apache.shiro.authc.AuthenticationToken;

/**
 * 这里我们自定义了一个AuthenticationToken----JwtToken。
 * 因为在Reaml认证方法中，我们是对 Token进行认证的。至于 UsernamePasswordToken （Shiro 中自带），
 * 我们需要 对 username 和 password 认证时就可以用它
 * @author dylan
 */
public class JwtToken implements AuthenticationToken {
    private String token;

    public JwtToken(String token) {
        this.token = token;
    }

    @Override
    public Object getPrincipal() {
        return token;
    }
    @Override
    public Object getCredentials() {
        return token;
    }
}
