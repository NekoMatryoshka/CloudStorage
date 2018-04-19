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

import uk.ac.herts.jw17aca.cloudstorage.pojo.Disk;
import uk.ac.herts.jw17aca.cloudstorage.pojo.File;
import uk.ac.herts.jw17aca.cloudstorage.service.DiskService;

@Transactional
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:applicationContext.xml" })
public class DiskServiceTest {

	@Autowired
	private DiskService service;

	@Test
	public void testLoadDiskInfo() {
		Disk disk = service.loadDiskInfo(17);
		assertEquals(disk.getId(), 6);
	}

	@Test
	@Rollback(true)
	public void testDeleteFile() {
		boolean result = false;
		File file = service.getFile(20);
		result = (file != null);
		service.deleteFile(20);
		file = service.getFile(20);
		result = (file == null);
		assertTrue(result);
	}

	@Test
	public void testGetFile() {
		File file = service.getFile(20);
		assertTrue(file != null);
	}

	@Test
	public void testIsEnoughSpace() {
		File file = new File();
		file.setSize(10 * 1024 * 1024);
		file.setUserId(17);
		assertTrue(!service.isEnoughSpace(file));
	}

	@Test
	public void testLoadFileList() {
		File file = service.loadFileList(16).get(0);
		assertEquals(file.getId(), 21);
	}

	@Test
	@Rollback(true)
	public void testMkDir() {
		int count1 = service.loadFileList(15).size();
		service.mkDir(15, "test");
		int count2 = service.loadFileList(15).size();
		assertEquals(count1, count2 - 1);
	}

	@Test
	@Rollback(true)
	public void testUploadFile() {
		File file = new File(15, "test", "test", true, 17, 0, new Date(), "");
		int count1 = service.loadFileList(15).size();
		service.uploadFile(file);
		int count2 = service.loadFileList(15).size();
		assertEquals(count1, count2 - 1);
	}

	@Test
	@Rollback(true)
	public void testRenameFile() {
		service.renameFile(21, "test");
		String name = service.getFile(21).getName();
		assertTrue(name.equals("test"));
	}

	@Test
	@Rollback(true)
	public void testMoveFile() {
		int count151 = service.loadFileList(15).size();
		int count161 = service.loadFileList(16).size();
		service.moveFile(21, 15);
		int count152 = service.loadFileList(15).size();
		int count162 = service.loadFileList(16).size();
		assertTrue((count151 + count161 == count152 + count162) && (count151 != count152));
	}

}
