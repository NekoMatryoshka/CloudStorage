package uk.ac.herts.jw17aca.cloudstorage.pojo;

public class Disk {
	
	private long id;
	private long userId;
	private float size;
	private float usedSize;
	private int fileNumber;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
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
	
	public float getUsedSize() {
		return usedSize;
	}
	public void setUsedSize(float usedSize) {
		this.usedSize = usedSize;
	}
	
	public int getFileNumber() {
		return fileNumber;
	}
	public void setFileNumber(int fileNumber) {
		this.fileNumber = fileNumber;
	}
	
}

//database sql commands

//create table disk_(
//id int(11) not null auto_increment,
//userId int(11) not null,
//size decimal(11,3) not null,
//usedSize decimal(11,3) default 0,
//fileNumber int(11) default 0,
//primary key (id)
//)ENGINE=InnoDB DEFAULT CHARSET utf8 COLLATE utf8_general_ci;
