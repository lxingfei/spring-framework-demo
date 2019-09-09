package com.leh.aop.aspect;

import org.aspectj.lang.JoinPoint;

import java.util.Arrays;

/**
 * 正常情况下，基于注解的声明要优于基于XML的声明，通过@AspectJ注解，
 * 可以与Aspect切面相兼容，而基于XML配置则是Spring专有的。
 * 由于@AspectJ得到越来越多AOP框架支持，所以用的更多
 * @Auther: leh
 * @Date: 2019/9/9 15:43
 * @Description: 可以使用@Order指定切面的优先级，值越小优先级越高
 */

public class ValidationAspect {

    public void validateArgs(JoinPoint joinPoint) {
        System.out.println("第一步校验参数, validateArgs" + Arrays.asList(joinPoint.getArgs()));
    }
}
