package com.leh.aop.aspect;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

/**
 * @Auther: leh
 * @Date: 2019/9/9 15:20
 * @Description:
 * 把这个类声明为一个切面:
 * 1、需要把该类放入到容器中（就是加上@Component注解）
 * 2、再声明为一个切面（加上@AspectJ注解）
 */
@Order(2)       //切面的优先级 在类上使用注解@Order（1），括号中的数字越小，优先级越高
@Component      //声明这个类是被SpringIOC容器来管理的，如果不声明，就无法做到自动织入
@Aspect         //这个类被声明为是一个需要动态织入到我们的虚拟切面中的类
public class AnnotationAspect {
    private final static Logger LOG = Logger.getLogger(AnnotationAspect.class);

    /**
     * 重用切面的切点表达式
     *
     * 定义一个方法，用于声明切入点表达式
     *一般的，该方法中不需要添加其他的代码
     * 使用@Pointcut来声明切入点表达式
     * 后面的其他通知直接使用方法名来引用切入点表达式 execution中是AspectJ表达式
     */
    @Pointcut("execution(* com.leh.aop.service..*(..))")
    public void pointcutConfig() {
    }

    /**
     * 声明该方法为一个前置通知:在目标方法开始之前执行
     *
     * @param joinPoint
     */
    @Before(value = "pointcutConfig()")
    public void beforeMethod(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getName();
        List<Object> args = Arrays.asList(joinPoint.getArgs());
        System.out.println("AnnotationAspect beforeMethod " + methodName + " start with " + args);
    }


    /**
     * 后置通知，就是在目标方法执行之后（无论是否发生异常）执行的通知
     * 后置通知中不能访问目标方法的返回结果
     *
     * @param joinPoint
     */
    @After(value = "pointcutConfig()")
    public void afterMethod(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getName();
        List<Object> args = Arrays.asList(joinPoint.getArgs());
        System.out.println("AnnotationAspect afterMethod " + methodName + " end with " + args);
    }

    /**
     * 返回通知，在方法正常结束之后执行的代码
     * 返回通知是可以访问到方法的返回值的
     *
     * @param joinPoint
     * @param result
     */
    @AfterReturning(value = "pointcutConfig()", returning = "result")
    public void afterReturning(JoinPoint joinPoint, Object result) {
        String methodName = joinPoint.getSignature().getName();
        List<Object> args = Arrays.asList(joinPoint.getArgs());
        System.out.println("AnnotationAspect afterReturning " + methodName + " end with " + result);
    }


    /**
     * 返回异常通知，返回抛出异常的时候执行的通知，可以获得返回的异常
     * 可以访问到异常对象，且可以指定在出现特定异常的时候再执行通知代码
     */

    @AfterThrowing(value = "pointcutConfig()", throwing = "ex")
    public void afterThrowing(JoinPoint joinPoint, Exception ex) {
        String methodName = joinPoint.getSignature().getName();
        List<Object> args = Arrays.asList(joinPoint.getArgs());
        System.out.println("AnnotationAspect afterThrowing " + methodName + " end with " + ex);
    }

    /**
     * 环绕通知需要携带ProceedingJoinPoint类型的参数
     * 环绕通知类似于动态代理的全过程，这个类型ProceedingJoinPoint的参数可以决定是否执行目标方法
     * 且环绕通知必须有返回值，返回值即为目标方法返回值
     *
     * @param proceedingJoinPoint
     */
    @Around(value = "pointcutConfig()")
    public void around(ProceedingJoinPoint proceedingJoinPoint) {
        Object result = null;
        String methodName = proceedingJoinPoint.getSignature().getName();
        Object[] args = proceedingJoinPoint.getArgs();

        //执行目标方法
        try {
            //前置通知
            System.out.println("环绕 beforeMethod " + methodName + " start with " + args);

            result = proceedingJoinPoint.proceed();

            //返回通知
            System.out.println("环绕 afterMethod " + methodName + " end with " + result);
        } catch (Throwable throwable) {
            //异常通知
            System.out.println("环绕 afterThrowing " + methodName + " exception with " + throwable);
            //throwable.printStackTrace();
        }

        //后置通知
        System.out.println("环绕 afterMethod " + methodName + " end with " + args);
        return;
    }


}
