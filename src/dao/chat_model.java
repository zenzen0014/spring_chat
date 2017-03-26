package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import model.chat;
import model.msg_unread;
import model.user;
import service.ChatSocket;
import utils.JdbcUtils_C3P0;

public class chat_model {
	static DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	static String cdate = dateFormat.format(new Date());
	
	public String add_user(user cchat) {

		Connection conn = null;
		PreparedStatement ps = null;
		
		System.out.println(cchat.getRegist_date());
		
		String sql = "insert into user(name,regist_date,isactive,recordstatus)"
				+ "values(?,?,?,?)";
		
		try {
			conn = JdbcUtils_C3P0.getConnection();
			ps = conn.prepareStatement(sql);

			ps.setString(1, cchat.getName());
			ps.setString(2, cchat.getRegist_date());
			ps.setInt(3, cchat.getIsactive());
			ps.setInt(4, cchat.getRecordstatus());
			ps.executeUpdate();			
			
			return "ok";

		} catch (SQLException e) {
			e.printStackTrace();
			return "error";
			
		} finally {
			JdbcUtils_C3P0.release(conn, ps, null);
		}
		
	}

	public ArrayList<user> get_contact_list() throws SQLException{
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		String sql = "SELECT id, name, DATE_FORMAT(regist_date,' %a %d %b %Y') as regist_date, isactive, recordstatus FROM user where recordstatus = 1";
		try {
			conn = JdbcUtils_C3P0.getConnection();
			ps = conn.prepareStatement(sql);
			
			ArrayList<user> result = new ArrayList<user>();
			rs = ps.executeQuery();
			while(rs.next()){
				user cchat = new user();
				cchat.setId(rs.getInt(1));
				cchat.setName(rs.getString(2));
				cchat.setRegist_date(rs.getString(3));
				cchat.setIsactive(rs.getInt(4));
				cchat.setRecordstatus(rs.getInt(5));
				
				result.add(cchat);
		    }
			return result;

		} catch (SQLException e) {
			e.printStackTrace();
			throw new SQLException("EXCEPTION");
		} finally {
			JdbcUtils_C3P0.release(conn, ps, rs);
		}
	}

	public int get_id_by_name(String name) throws SQLException{
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		String sql = "select * from user where name=? limit 1";
		try {
			conn = JdbcUtils_C3P0.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, name);
			System.out.println(sql);
			rs = ps.executeQuery();
			user cchat = new user();
			while(rs.next()){
			    cchat.setId(rs.getInt(1));
				cchat.setName(rs.getString(2));
				cchat.setRegist_date(rs.getString(3));
				cchat.setIsactive(rs.getInt(4));
				cchat.setRecordstatus(rs.getInt(5));
			}
			return cchat.getId();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new SQLException("EXCEPTION");
		} finally {
			JdbcUtils_C3P0.release(conn, ps, rs);
		}
		
	}

	public ArrayList<msg_unread> get_msg_unread(String uid_from, String uid_my) throws SQLException{
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int total, cuid_from;
		
		String sql = "select count(*) as total_unread, uid_from, (select max(chatdate) from chat where uid_from='"+uid_from+"')chatdates, (select text from chat where uid_from='"+uid_from+"' order by id desc limit 1)msg from chat where uid_to = '"+uid_my+"' and uid_from ='"+uid_from+"' and isdelete = 0 and isread = 0";
		try {
			conn = JdbcUtils_C3P0.getConnection();
			ps = conn.prepareStatement(sql);
			
			ArrayList<msg_unread> result = new ArrayList<msg_unread>();
			rs = ps.executeQuery();
			while(rs.next()){
				msg_unread mur = new msg_unread();
				mur.setTotal_unread(rs.getInt(1));
				mur.setUid_from(rs.getInt(2));
				mur.setCdate(rs.getString(3));
				mur.setTmsg(rs.getString(4));
				result.add(mur);
		    }
			return result;

		} catch (SQLException e) {
			e.printStackTrace();
			throw new SQLException("EXCEPTION");
		} finally {
			JdbcUtils_C3P0.release(conn, ps, rs);
		}
	}

	public ArrayList<chat> read_msg(String uid_from, String uid_me) throws SQLException{
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		String sql = "select * from chat where (uid_to = '"+uid_me+"' and uid_from = '"+uid_from+"') or (uid_from = '"+uid_me+"' and uid_to = '"+uid_from+"') and isdelete = 0 order by chatdate asc";
		

		try {
			conn = JdbcUtils_C3P0.getConnection();
			ps = conn.prepareStatement(sql);
			
			ArrayList<chat> result = new ArrayList<chat>();
			rs = ps.executeQuery();
			while(rs.next()){
				chat c = new chat();
				c.setId(rs.getInt(1));
				c.setUid_to(rs.getString(2));
				c.setUid_from(rs.getString(3));
				c.setChatdate(rs.getString(4));
				c.setText(rs.getString(5));
				c.setIsread(rs.getInt(6));
				c.setIsdelete(rs.getInt(7));
				c.setIsgroup(rs.getInt(8));
				result.add(c);
		    }
			return result;

		} catch (SQLException e) {
			e.printStackTrace();
			throw new SQLException("EXCEPTION");
		} finally {
			JdbcUtils_C3P0.release(conn, ps, rs);
		}
		
	}

	public String sent_msg(chat cchat) {
		Connection conn = null;
		PreparedStatement ps = null;
		
		
		String sql = "insert into chat(uid_to, uid_from, chatdate, text, isread, isdelete, isgroup)"
				+ "values(?,?,?,?,?,?,?)";
		
		try {
			conn = JdbcUtils_C3P0.getConnection();
			ps = conn.prepareStatement(sql);

			ps.setString(1, cchat.getUid_to());
			ps.setString(2, cchat.getUid_from());
			ps.setString(3, cchat.getChatdate());
			ps.setString(4, cchat.getText());
			ps.setInt(5, cchat.getIsread());
			ps.setInt(6, cchat.getIsdelete());
			ps.setInt(7, cchat.getIsgroup());
			ps.executeUpdate();			
			
			return "ok";

		} catch (SQLException e) {
			e.printStackTrace();
			return "error";
			
		} finally {
			JdbcUtils_C3P0.release(conn, ps, null);
		}
	}

	public void set_isread(String uid_from, String uid_me) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "update chat set isread = 1 where uid_to='"+uid_me+"' and uid_from='"+uid_from+"'" ;
		System.out.println(sql);
		try {
			conn = JdbcUtils_C3P0.getConnection();
			ps = conn.prepareStatement(sql);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtils_C3P0.release(conn, ps, rs);
		}
	}
	
	
	


	
}
