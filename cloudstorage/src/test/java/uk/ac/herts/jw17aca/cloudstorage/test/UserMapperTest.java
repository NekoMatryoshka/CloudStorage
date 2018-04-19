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

import uk.ac.herts.jw17aca.cloudstorage.pojo.*;
import uk.ac.herts.jw17aca.cloudstorage.mapper.*;

@Transactional
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:applicationContext.xml" })
public class UserMapperTest {

	@Autowired
	private UserMapper mapper;

	@Test
	public void testSelectById() {
		User user = mapper.selectById(17);
		assertEquals(user.getUsername(), "123456");
	}

	@Test
	public void testSelectByEmail() {
		User user = mapper.selectByEmail("123456@123.com");
		assertEquals(user.getUsername(), "123456");
	}

	@Test
	@Rollback(true)
	public void testAdd() {
		User user = new User();
		user.setEmail("test");
		user.setPassword("test");
		user.setUsername("test");
		mapper.add(user);
		assertTrue(mapper.selectByEmail("test") != null);
	}
	
	@Test
	@Rollback(true)
	public void testDelete() {
		mapper.delete(17);
		assertTrue(mapper.selectByEmail("test") == null);
	}
	
	@Test
	@Rollback(true)
	public void testUpdate() {
		User user = mapper.selectById(17);
		user.setEmail("test");
		mapper.update(user);
		assertTrue(mapper.selectByEmail("test") != null);
	}
	
}
