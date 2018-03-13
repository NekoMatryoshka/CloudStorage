package uk.ac.herts.jw17aca.cloudstorage.pojo;

import org.apache.commons.lang.StringUtils;

public class File {

	private long id;
	private String name;
	private String directory;
	private long userId;
	private long diskId;
	private float size;
	private String createDate;
	
	//lockFile function/password
	//md5 function
	//description function
	//share function/url
	
	public long getdiskId() {
		return diskId;
	}
	public void setdiskId(long diskId) {
		this.diskId = diskId;
	}
	
	public String getCreateDate() {
		return createDate;
	}

	public void getCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDirectory() {
		return directory;
	}

	public void setDirectory(String directory) {
		this.directory = directory;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public float getSize() {
		return size;
	}

	public void setSize(float size) {
		this.size = size;
	}

	public String getPath() {
		return directory + name;
	}

	public void setPath(String path) {
		this.directory = StringUtils.substringBeforeLast(path, "/") + "/";
		this.directory = StringUtils.substringAfterLast(path, "/");
	}

	public String getExtension() {
		return StringUtils.substringAfterLast(name, ".");
	}

	public void setExtension(String extension) {
		this.name = StringUtils.substringBeforeLast(name, ".") + extension;
	}
}
