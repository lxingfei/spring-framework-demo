package com.leh.shiro.realm;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @Auther: leh
 * @Date: 2019/10/12 18:02
 * @Description: 自定义realm
 */
public class CustomRealm extends AuthorizingRealm {

    private static final String SALT = "xingfei";

    Map<String, String> userMap = new HashMap<String, String>(16);

    Set<String> roleSet = new HashSet<String>();

    Set<String> permissionSet = new HashSet<String>();

    {
        userMap.put("xingfei", "5410022235633c9a10c003d412c006bf");//加密后的密码
        super.setName("customRealm");
    }

    {
        roleSet.add("admin");
        roleSet.add("user");
    }

    {
        permissionSet.add("user:update");
        permissionSet.add("user:delete");
    }


    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        String userName = (String) principalCollection.getPrimaryPrincipal();
        Set<String> roles = getRolesByUserName(userName);
        Set<String> permissions = getPermissionsByUserName(userName);
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        authorizationInfo.setRoles(roles);
        authorizationInfo.setStringPermissions(permissions);
        return authorizationInfo;
    }


    /**
     * 模拟从数据库或缓存中获取角色数据
     * @param userName
     * @return
     */
    private Set<String> getRolesByUserName(String userName) {
        return roleSet;
    }

    /**
     * 模拟从数据库或缓存中获取权限数据
     * @param userName
     * @return
     */
    private Set<String> getPermissionsByUserName(String userName) {
        return permissionSet;
    }


    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws
            AuthenticationException {
        //1 从主体传过来的认证信息中，获取用户名
        String userName = (String) authenticationToken.getPrincipal();

        //2 通过用户名到数据库中获取凭证
        String password = getPasswordByUserName(userName);

        if (StringUtils.isBlank(password)) {
            return null;
        }

        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
                userName, password, "customRealm");
        //设置校验凭证用到的盐
        authenticationInfo.setCredentialsSalt(ByteSource.Util.bytes(SALT));
        return authenticationInfo;
    }

    /**
     * 模拟从数据库或缓存中获取凭证
     *
     * @param userName
     * @return
     */
    private String getPasswordByUserName(String userName) {
        return userMap.get(userName);
    }

    public static void main(String[] args) {
        //密码 + 盐
        Md5Hash md5Hash = new Md5Hash("123456", SALT);
        System.out.println(md5Hash);
    }
}
