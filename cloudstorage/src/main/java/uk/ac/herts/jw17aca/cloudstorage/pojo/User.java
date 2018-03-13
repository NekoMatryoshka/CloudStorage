package uk.ac.herts.jw17aca.cloudstorage.pojo;

public class User {

	private long id;
	private String username;
	private String password;
	private String email;

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String toString() {
		return getId() + ":" + "username:" + getUsername() + ":" + "email:" + getEmail() + ":" + "password:"
				+ getPassword();
	}
}

// database sql commands

// create table user_(
// id int(11) not null auto_increment,
// username varchar(256) not null,
// password varchar(256) not null,
// email varchar(256) not null,
// primary key (id)
// )ENGINE=InnoDB DEFAULT CHARSET utf8 COLLATE utf8_general_ci;

// insert into user_(id,username,password,email)
// values(1,'user',123,'952523131@qq.com');
