package com.du.dylan.basedao.shiro;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.du.dylan.basedao.entity.DPermission;
import com.du.dylan.basedao.entity.DRole;
import com.du.dylan.basedao.entity.DUser;
import com.du.dylan.basedao.service.IDPermissionService;
import com.du.dylan.basedao.service.IDRoleService;
import com.du.dylan.basedao.service.IDUserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Slf4j
public class AccountRealm extends AuthorizingRealm {

    @Autowired
    private IDUserService userService;

    @Autowired
    private IDRoleService roleService;

    @Autowired
    private IDPermissionService permissionService;

    @Override
    public boolean supports(AuthenticationToken token){
        return token instanceof JwtToken;
    }
    /**
     * 授权
     * 从 Token 中获取 username ，然后根据 username 可获取用户信息（角色、权限等）并添加到 AuthorizationInfo 中。
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {

        log.info("用户授权中");
        //获取当前登录的用户信息
        String phone = JwtUtil.getPkIdFromToken(principalCollection.toString());
        DUser a = userService.getOne(new QueryWrapper<DUser>().eq("phone",phone));
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();

        DRole role = roleService.getById(a.getRoleId());
        info.addRole(role.getRoleName());
        List<DPermission> list = permissionService.list(new QueryWrapper<DPermission>().select("permission_code").eq("role_id",a.getRoleId()));
        Set<String> perms = new HashSet<>();
        for (int i = 0; i < list.size(); i++) {
            perms.add(list.get(i).getPermissionCode());
        }
        //设置权限
        info.addStringPermissions(perms);
        return info;
    }

    /**
     * 认证
     * 拿到从 executeLogin() 方法中传过来的 Token，并对 Token 检验是否有效、用户是否存在以及是否封号
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        log.info("身份认证");
        //这里的token是从JwtFilter的executeLogin()方法传递过来的
        String token = (String) authenticationToken.getCredentials();
        //解密
        String username = JwtUtil.getPkIdFromToken(token);
        //从数据库汇总获取对应的用户名和面
        DUser a = userService.getOne(new QueryWrapper<DUser>().eq("phone",username));
        if (StringUtils.isEmpty(username) || !JwtUtil.verify(token,username)){
            log.error("token 认证失败");
            throw new AuthenticationException("token 认证失败");
        }
        if (null == a){
            log.error("账号或密码错误");
            throw new AuthenticationException("账号或密码错误");
        }
        log.info("用户{}认证成功！",a.getPassword());
        return new SimpleAuthenticationInfo(token, token, getName());
    }
}
