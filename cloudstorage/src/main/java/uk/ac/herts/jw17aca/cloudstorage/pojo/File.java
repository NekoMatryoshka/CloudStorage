package uk.ac.herts.jw17aca.cloudstorage.pojo;

import java.util.Date;

public class File {

	private long id;
	private long parentId;
	private String name;
	private String directory;
	private boolean isDir;
	private long userId;
	private float size;
	private Date createDate;
	private String serverLocation;
	
	// lockFile function/password
	// md5 function
	// description function
	// share function/url
	
	public File() {}

	public File(long parentId, String name, String directory, boolean isDir, long userId, float size,
			Date createDate, String serverLocation) {
		this.parentId = parentId;
		this.name = name;
		this.directory = directory;
		this.isDir = isDir;
		this.userId = userId;
		this.size = size;
		this.createDate = createDate;
		this.serverLocation = serverLocation;
	}
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	
	public long getParentId() {
		return parentId;
	}
	public void setParentId(long parentId) {
		this.parentId = parentId;
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
	
	public boolean isDir() {
		return isDir;
	}
	public void setDir(boolean isDir) {
		this.isDir = isDir;
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
	
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	
	public String getServerLocation() {
		return serverLocation;
	}
	public void setServerLocation(String serverLocation) {
		this.serverLocation = serverLocation;
	}

}

//database sql commands

//create table file_(
//id int(11) not null auto_increment,
//parentId int(11) not null,
//name_ varchar(256) not null,
//directory_ varchar(256) not null,
//isDir bool not null,
//userId int(11) not null,
//size decimal(11,3) not null,
//createDate date not null,
//serverLocation varchar(256) not null,
//primary key (id)
//)ENGINE=InnoDB DEFAULT CHARSET utf8 COLLATE utf8_general_ci;
