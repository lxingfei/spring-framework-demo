package com.leh.aop.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @Auther: leh
 * @Date: 2019/9/9 16:00
 * @Description:
 */
@Order(1)
@Component
@Aspect
public class InterceptorAspect {

    @Before(value = "AnnotationAspect.pointcutConfig()")
    public void intercept(JoinPoint joinPoint) {
        System.out.println("第二步, 拦截方法, intercept method" + joinPoint.getKind());
    }
}
