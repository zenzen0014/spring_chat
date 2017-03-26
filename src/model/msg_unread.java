package model;

public class msg_unread {
	int uid_from;
	int total_unread;
	String cdate;
	String tmsg;
	
	public String getTmsg() {
		return tmsg;
	}
	public void setTmsg(String tmsg) {
		this.tmsg = tmsg;
	}
	public int getUid_from() {
		return uid_from;
	}
	public void setUid_from(int uid_from) {
		this.uid_from = uid_from;
	}
	public int getTotal_unread() {
		return total_unread;
	}
	public void setTotal_unread(int total_unread) {
		this.total_unread = total_unread;
	}
	public String getCdate() {
		return cdate;
	}
	public void setCdate(String cdate) {
		this.cdate = cdate;
	}
	
	
	
}
