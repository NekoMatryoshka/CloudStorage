package uk.ac.herts.jw17aca.cloudstorage.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import uk.ac.herts.jw17aca.cloudstorage.mapper.UserMapper;
import uk.ac.herts.jw17aca.cloudstorage.pojo.User;

public class UserMapperTest {
	
	private static ApplicationContext ac;
	
	static {
		ac = new ClassPathXmlApplicationContext("resources/applicationContext.xml");
	}

	public static void main(String[] args) {
		UserMapper mapper = (UserMapper) ac.getBean("UserMapper");
		User user = mapper.selectByEmail("952523131@qq.com");

		System.out.println(user.toString());
	}
	
}
