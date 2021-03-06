package com.leh;

import com.leh.aop.dao.MemberDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@ContextConfiguration(locations={"classpath*:application-context.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class AopMemberDaoTest {
	
	@Autowired
	MemberDao memberDao;
	
	@Test
	public void testInsert(){
		memberDao.insert();
	}
	
}
