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
	}
	
	public void testModify(){
		memberManagerService.modify(null);
	}
	
	public void testQuery(){
		memberManagerService.query("");
	}
	
}
