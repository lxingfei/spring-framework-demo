package com.leh.shiro;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.realm.SimpleAccountRealm;
import org.apache.shiro.subject.Subject;
import org.junit.Before;
import org.junit.Test;

/**
 * @Auther: leh
 * @Date: 2019/10/12 14:48
 * @Description: 角色授权
 */
public class AuthorizerTest {

    private SimpleAccountRealm simpleAccountRealm = new SimpleAccountRealm();

    @Before
    public void before(){
        simpleAccountRealm.addAccount("xingfei", "123456", "admin", "user");
    }

    @Test
    public void authorizer(){
        // 1 构建securityManager环境
        DefaultSecurityManager defaultSecurityManager = new DefaultSecurityManager();

        defaultSecurityManager.setRealm(simpleAccountRealm);

        // 2 主体授权
        SecurityUtils.setSecurityManager(defaultSecurityManager);
        Subject subject = SecurityUtils.getSubject();

        UsernamePasswordToken token = new UsernamePasswordToken("xingfei", "123456");

        subject.login(token);

        System.out.println("isAuthenticated:" + subject.isAuthenticated());

        System.out.println("判断用户xingfei是否是 admin 和 user 角色");

        subject.checkRoles("admin","user");


    }
}
