package model;

public class chat {
	int id;
	String uid_to;
	String uid_from;
	public String getUid_to() {
		return uid_to;
	}
	public void setUid_to(String uid_to) {
		this.uid_to = uid_to;
	}
	public String getUid_from() {
		return uid_from;
	}
	public void setUid_from(String uid_from) {
		this.uid_from = uid_from;
	}
	String chatdate;
	String text;
	int isread;
	int isdelete;
	int isgroup;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public String getChatdate() {
		return chatdate;
	}
	public void setChatdate(String chatdate) {
		this.chatdate = chatdate;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public int getIsread() {
		return isread;
	}
	public void setIsread(int isread) {
		this.isread = isread;
	}
	public int getIsdelete() {
		return isdelete;
	}
	public void setIsdelete(int isdelete) {
		this.isdelete = isdelete;
	}
	public int getIsgroup() {
		return isgroup;
	}
	public void setIsgroup(int isgroup) {
		this.isgroup = isgroup;
	}
	
	
}
