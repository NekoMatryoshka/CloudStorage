package uk.ac.herts.jw17aca.cloudstorage.test;

import static org.junit.Assert.*;

import org.junit.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import uk.ac.herts.jw17aca.cloudstorage.mapper.*;
import uk.ac.herts.jw17aca.cloudstorage.pojo.*;
import uk.ac.herts.jw17aca.cloudstorage.service.*;

public class UserServiceTest1 {

	private UserService service;

	@Before
	public void setUp() {
		ApplicationContext ac = new FileSystemXmlApplicationContext("classpath:applicationContext.xml");
		service = (UserService) ac.getBean("UserService");
	}

	@Test
	public void testLogin() {
		User user = service.login("123456@123.com", "123456");
		assertEquals(user.getId(), 13l);
	}

	@Test
	public void testRegister() {
		User user = new User();
		user.setEmail("test");
		user.setPassword("test");
		user.setUsername("test");
		service.register(user);
		assertTrue(service.login("test", "test") != null);
	}

	@Test
	public void testCheckEmail() {
		assertTrue(!service.checkEmail("123456@123.com"));
	}

}
