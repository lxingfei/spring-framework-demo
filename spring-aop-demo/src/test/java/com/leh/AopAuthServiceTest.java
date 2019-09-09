package com.leh;

import com.leh.aop.service.AuthService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @Auther: leh
 * @Date: 2019/9/9 14:48
 * @Description:
 */
@ContextConfiguration(locations = {"classpath*:application-context.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class AopAuthServiceTest {

    @Autowired
    AuthService authService;

    @Test
    public void testLogin() {
        authService.login("", "");
    }

        /*
            output:
            第一步校验参数, validateArgs[, ]
            第二步, 拦截方法, intercept methodmethod-execution
            环绕 beforeMethod login start with [Ljava.lang.Object;@5dafbe45
            beforeMethod login start with [, ]
            [INFO ] [16:13:23] com.leh.aop.aspect.LogAspect - LogAspect 调用方法之前执行 >>>>>>execution(void com.leh.aop.service.AuthService.login(String,String))
            [INFO ] [16:13:23] com.leh.aop.service.AuthService - 用户登录
            [INFO ] [16:13:23] com.leh.aop.aspect.LogAspect - LogAspect 调用方法之后执行 >>>>>>execution(void com.leh.aop.service.AuthService.login(String,String))
            [INFO ] [16:13:23] com.leh.aop.aspect.LogAspect - LogAspect 调用方法执行成功之后执行 >>>>>>execution(void com.leh.aop.service.AuthService.login(String,String)), 获取返回值null
            环绕 afterMethod login end with null
            环绕 afterMethod login end with [Ljava.lang.Object;@5dafbe45
            afterMethod login end with [, ]
            afterReturning login end with null
        */




    @Test
    public void testLogout() {
        authService.logout("");

        /*
            output：
            第一步校验参数, validateArgs[]
            第二步, 拦截方法, intercept methodmethod-execution
            环绕 beforeMethod logout start with [Ljava.lang.Object;@2254127a
            beforeMethod logout start with []
            [INFO ] [16:11:20] com.leh.aop.aspect.LogAspect - LogAspect 调用方法之前执行 >>>>>>execution(void com.leh.aop.service.AuthService.logout(String))
            [INFO ] [16:11:20] com.leh.aop.service.AuthService - 注销登录
            [INFO ] [16:11:20] com.leh.aop.aspect.LogAspect - LogAspect 调用方法之后执行 >>>>>>execution(void com.leh.aop.service.AuthService.logout(String))
            [INFO ] [16:11:20] com.leh.aop.aspect.LogAspect - LogAspect 调用方法执行成功之后执行 >>>>>>execution(void com.leh.aop.service.AuthService.logout(String)), 获取返回值null
            环绕 afterMethod logout end with null
            环绕 afterMethod logout end with [Ljava.lang.Object;@2254127a
            afterMethod logout end with []
            afterReturning logout end with null
         */
    }
}
