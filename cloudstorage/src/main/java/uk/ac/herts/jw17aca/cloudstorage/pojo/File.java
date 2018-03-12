package uk.ac.herts.jw17aca.cloudstorage.pojo;

import org.apache.commons.lang.StringUtils;

public class File {

	private int id;
	private String name;
	private String directory;
	private int ownerId;
	private float size;
//	private DateTime dateTime;

	public int getId() {
		return id;
	}

	public void setId(int id) {
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

	public int getOwnerId() {
		return ownerId;
	}

	public void setOwnerId(int ownerId) {
		this.ownerId = ownerId;
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
