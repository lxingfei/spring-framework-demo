package com.leh;

import com.leh.aop.service.MemberManagerService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@ContextConfiguration(locations={"classpath*:application-context.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class AopMemberManagerServiceTest {
	
	@Autowired
    MemberManagerService memberManagerService;
	
	@Test
	public void testAdd(){
		memberManagerService.add(null);
	}
	
	
	/*
	    做事务代理的时候
        TransactionManager来管理事务操作（切面）
        DataSource ，SessionFactory(DataSource)
        DataSource 包含了连接信息，事物的提交或者回滚一些基础功能
        通过连接点是可以获取到方法（切点）具体操作哪个DataSource
        通过切面通知类型，去执行DataSource的功能方法
	*/

	@Test
	//@Ignore
	public void testRemove(){
		try {
			memberManagerService.remove(0);
		} catch (Exception e) {
			//e.printStackTrace();
		}

		/*
			output:
			第一步校验参数, validateArgs[0]
			第二步, 拦截方法, intercept methodmethod-execution
			环绕 beforeMethod remove start with [Ljava.lang.Object;@f68f0dc
			AnnotationAspect beforeMethod remove start with [0]
			[INFO ] [16:26:10] com.leh.aop.aspect.LogAspect - LogAspect 调用方法之前执行 >>>>>>execution(boolean com.leh.aop.service.MemberManagerService.remove(long))
			[INFO ] [16:26:10] com.leh.aop.service.MemberManagerService - 删除用户
			[INFO ] [16:26:10] com.leh.aop.aspect.LogAspect - LogAspect 调用方法之后执行 >>>>>>execution(boolean com.leh.aop.service.MemberManagerService.remove(long))
			[INFO ] [16:26:10] com.leh.aop.aspect.LogAspect - LogAspect 抛出异常之后执行 >>>>>>execution(boolean com.leh.aop.service.MemberManagerService.remove(long))
			环绕 afterThrowing remove exception with java.lang.Exception: 这是我们自己跑出来的异常
			环绕 afterMethod remove end with [Ljava.lang.Object;@f68f0dc
			AnnotationAspect afterMethod remove end with [0]
			AnnotationAspect afterReturning remove end with null
		 */
	}
	
	public void testModify(){
		memberManagerService.modify(null);
	}
	
	public void testQuery(){
		memberManagerService.query("");
	}
	
}
