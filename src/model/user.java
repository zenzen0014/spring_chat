package model;

public class user {
	int id;
	String name;
	String regist_date;
	int isactive;
	int recordstatus;
	
	String socket_name;
	
	public String getSocket_name() {
		return socket_name;
	}
	public void setSocket_name(String socket_name) {
		this.socket_name = socket_name;
	}
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
	public String getRegist_date() {
		return regist_date;
	}
	public void setRegist_date(String regist_date) {
		this.regist_date = regist_date;
	}
	public int getIsactive() {
		return isactive;
	}
	public void setIsactive(int isactive) {
		this.isactive = isactive;
	}
	public int getRecordstatus() {
		return recordstatus;
	}
	public void setRecordstatus(int recordstatus) {
		this.recordstatus = recordstatus;
	}
}
