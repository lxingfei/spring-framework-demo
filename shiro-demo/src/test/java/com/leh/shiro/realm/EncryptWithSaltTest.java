package com.leh.shiro.realm;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.subject.Subject;
import org.junit.Test;

/**
 * @Auther: leh
 * @Date: 2019/10/12 14:48
 * @Description: 认证（登陆）
 */
public class EncryptWithSaltTest {

    @Test
    public void authentication(){
        // 1 构建securityManager环境
        DefaultSecurityManager defaultSecurityManager = new DefaultSecurityManager();

        CustomRealm realm = new CustomRealm();

        HashedCredentialsMatcher matcher = new HashedCredentialsMatcher();
        //算法名
        matcher.setHashAlgorithmName("md5");
        //加密次数
        matcher.setHashIterations(1);

        realm.setCredentialsMatcher(matcher);

        defaultSecurityManager.setRealm(realm);

        // 2 主体提交认证请求
        SecurityUtils.setSecurityManager(defaultSecurityManager);
        Subject subject = SecurityUtils.getSubject();

        UsernamePasswordToken token = new UsernamePasswordToken("xingfei", "123456");

        subject.login(token);

        System.out.println("isAuthenticated:" + subject.isAuthenticated());
    }
}
