package uk.ac.herts.jw17aca.cloudstorage.pojo;

public class Disk {
	
	private long id;
	private long userId;
	private float size;
	private float usedSize;
	private int fileNumber;
	private long rootDirectoryId;
	
	private static final float INITIAL_TOTAL_SIZE = 1024*1024*5;  
	
	public Disk() {}
	
	public Disk(long userId, long rootDirectoryId) {
		this.userId = userId;
		this.size = INITIAL_TOTAL_SIZE; 
		this.rootDirectoryId = rootDirectoryId;
	}
	
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
	
	public long getRootDirectoryId() {
		return rootDirectoryId;
	}
	public void setRootDirectoryId(long rootDirectoryId) {
		this.rootDirectoryId = rootDirectoryId;
	}

	@Override
	public String toString() {
		return "Disk [id=" + id + ", userId=" + userId + ", size=" + size + ", usedSize=" + usedSize + ", fileNumber="
				+ fileNumber + ", rootDirectoryId=" + rootDirectoryId + "]";
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

//alter table disk_ add rootDirectory int(11);
