package com.leh.shiro.realm;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.realm.jdbc.JdbcRealm;
import org.apache.shiro.subject.Subject;
import org.junit.Test;


/**
 * @Auther: leh
 * @Date: 2019/10/12 15:23
 * @Description:
 */
public class JdbcRealmTest {

    private DruidDataSource dataSource = new DruidDataSource();

    {
        dataSource.setUrl("jdbc:mysql://localhost:3306/test");
        dataSource.setUsername("root");
        dataSource.setPassword("123456");
    }

    @Test
    public void auth(){

        // 1 构建securityManager环境
        DefaultSecurityManager defaultSecurityManager = new DefaultSecurityManager();

        JdbcRealm realm = new JdbcRealm();

        realm.setDataSource(dataSource);

        String authenticationSql = "select password from dh_user where username = ?";
        realm.setAuthenticationQuery(authenticationSql);

        String roleSql = "select rolename from dh_user_role where username = ?";
        realm.setUserRolesQuery(roleSql);

        String permissionSql = "select permission from dh_role_permission where rolename = ?";
        realm.setPermissionsQuery(permissionSql);

        //默认false未开启权限查找开关
        realm.setPermissionsLookupEnabled(true);

        defaultSecurityManager.setRealm(realm);

        // 2 主体提交认证请求
        SecurityUtils.setSecurityManager(defaultSecurityManager);
        Subject subject = SecurityUtils.getSubject();

        UsernamePasswordToken token = new UsernamePasswordToken("xingfei", "123456");

        subject.login(token);

        System.out.println("isAuthenticated:" + subject.isAuthenticated());

        subject.checkRoles("admin");

        subject.checkPermissions("user:delete","user:update");

    }
}
