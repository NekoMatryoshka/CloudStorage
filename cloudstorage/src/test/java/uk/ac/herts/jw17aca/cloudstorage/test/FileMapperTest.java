package uk.ac.herts.jw17aca.cloudstorage.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import uk.ac.herts.jw17aca.cloudstorage.mapper.*;
import uk.ac.herts.jw17aca.cloudstorage.pojo.*;

@Transactional
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:applicationContext.xml" })
public class FileMapperTest {

	@Autowired
	private FileMapper mapper;

	@Test
	@Rollback(true)
	public void testAdd() {
		File file = new File(15, "test", "test", true, 17, 0, new Date(), "");
		mapper.add(file);
		assertTrue(mapper.selectByFuzzyName("test").get(0) != null);
	}

	@Test
	@Rollback(true)
	public void testSelectByFuzzyName() {
		assertEquals(mapper.selectByFuzzyName("123456").get(0).getSize(), 0, 0);
	}

	@Test
	@Rollback(true)
	public void testDelete() {
		mapper.delete(15);
		assertTrue(mapper.selectById(15) == null);
	}

	@Test
	public void testGetTotalUsedSizeByUserId() {
		assertEquals(mapper.getTotalUsedSizeByUserId(1), 0, 0);
	}

	@Test
	public void testSelectByParentId() {
		assertTrue(mapper.selectByParentId(15).size() > 0);
	}

	@Test
	public void testSelectRootDirectoryByUserId() {
		assertEquals(mapper.selectRootDirectoryByUserId(17), 15);
	}

	@Test
	@Rollback(true)
	public void testUpdate() {
		File file = mapper.selectById(15);
		file.setName("test");
		mapper.update(file);
		assertEquals(mapper.selectById(15).getName(), "test");
	}

}
