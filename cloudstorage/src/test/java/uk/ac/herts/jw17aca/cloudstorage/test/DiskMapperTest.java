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

import uk.ac.herts.jw17aca.cloudstorage.mapper.*;
import uk.ac.herts.jw17aca.cloudstorage.pojo.*;

@Transactional
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:applicationContext.xml" })
public class DiskMapperTest {

	@Autowired
	private DiskMapper mapper;

	@Test
	public void testSelectById() {
		Disk disk = mapper.selectByUserId(17);
		assertEquals(disk.getId(), 6);
	}
	
	@Test
	@Rollback(true)
	public void testAdd() {
		Disk disk = new Disk(1, 1);
		mapper.add(disk);
		assertEquals(mapper.selectByUserId(1).getRootDirectoryId(), 1);
	}
	
	@Test
	@Rollback(true)
	public void testDeleteByUserId() {
		mapper.deleteByUserId(17);
		assertTrue(mapper.selectByUserId(17)==null);
	}
	
	@Test
	@Rollback(true)
	public void testUpdateByUserId() {
		Disk disk = mapper.selectByUserId(17);
		disk.setUsedSize(100);
		mapper.updateByUserId(disk);
		assertEquals(mapper.selectByUserId(17).getUsedSize(), 100f, 0f);
	}
	
}
