package com.leh.aop.aspect;


import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;

/**
 *
 * AOP
 * @Auther: leh
 * @Date: 2019/9/9 09:40
 * @Description: 基于XML配置文件的方式
 */
public class LogAspect {

    private static final Logger LOG = Logger.getLogger(LogAspect.class);

    public void before(JoinPoint joinPoint){
        LOG.info("LogAspect 调用方法之前执行 >>>>>>" + joinPoint);
    }

    public void after(JoinPoint joinPoint){
        LOG.info("LogAspect 调用方法之后执行 >>>>>>" + joinPoint);
    }

    public void afterReturn(JoinPoint joinPoint, Object result){
        LOG.info("LogAspect 调用方法执行成功之后执行 >>>>>>" + joinPoint + ", 获取返回值" + result);
    }

    public void afterThrow(JoinPoint joinPoint, Exception ex){
        LOG.info("LogAspect 抛出异常之后执行 >>>>>>" + joinPoint);
    }

}
