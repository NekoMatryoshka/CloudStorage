package uk.ac.herts.jw17aca.cloudstorage.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import uk.ac.herts.jw17aca.cloudstorage.mapper.UserMapper;
import uk.ac.herts.jw17aca.cloudstorage.pojo.User;
import uk.ac.herts.jw17aca.cloudstorage.service.UserService;

@Transactional
@RunWith(SpringJUnit4ClassRunner.class)  
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class UserServiceTest {

	@Autowired
	private UserService service;
		
	@Test
	public void testLogin() {
		User user = service.login("123456@123.com", "123456");
		assertEquals(user.getId(), 17l);
	}
	
	@Test
	@Rollback(true)
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
