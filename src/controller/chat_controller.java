package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import dao.chat_model;
import model.Response;
import model.chat;
import model.msg_unread;
import model.user;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import service.ChatSocket;

@Controller
public class chat_controller {
	
	static DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	static String cdate = dateFormat.format(new Date());
	
	
	
	//@RequestMapping(value="/create_user")
	//public @ResponseBody String create_user(@RequestParam String name){

	@RequestMapping(value = "/create_user", method = RequestMethod.POST)
	@ResponseBody
	public void create_user(HttpServletRequest request,
				HttpServletResponse response) throws IOException, SQLException {
		 
		 response.setCharacterEncoding("UTF-8");
		 response.setContentType("text/json");
		 
		 String name = request.getParameter("name");
		 
		 System.out.println(name);

		 user cchat = new user();
		 cchat.setName(name);
		 cchat.setRegist_date(cdate);
		 cchat.setIsactive(1);
		 cchat.setRecordstatus(1);
		 
		 chat_model cm = new chat_model();
		 String status = cm.add_user(cchat);
		 int max_id = cm.get_id_by_name(name);
		 
		 cchat.setId(max_id);
		 
		 PrintWriter out = response.getWriter();
		 
		 if(status == "ok"){
			 ChatSocket cs = new ChatSocket();
			 cs.broadcast("get_new_user|"+cchat.getName()+"|"+cchat.getIsactive()+"|"+cchat.getRecordstatus());
			 cchat.setSocket_name(name);
			 
			 Response rs = new Response(0, "Success", cchat);
			 JSONObject json = JSONObject.fromObject(rs);
			 System.out.println("success");out.print(json);
		 }else{
			 Response rs = new Response(5, "Failed", new Object());
			 JSONObject json = JSONObject.fromObject(rs);
			 System.out.println("failed");out.print(json);
		 }
	}
	

	
	@RequestMapping(value = "/get_contact_list", method = RequestMethod.GET)
	@ResponseBody
	public void get_contact_list(HttpServletRequest request,
				HttpServletResponse response) throws IOException, SQLException {
		 
		 response.setCharacterEncoding("UTF-8");
		 response.setContentType("text/json");
		 
		 chat_model cm = new chat_model();
		 ArrayList<user> requestList = cm.get_contact_list();
		 
		 PrintWriter out = response.getWriter();
		 JSONArray result = JSONArray.fromObject(requestList);
		 Response rs = new Response(0, "", result);
		 JSONObject json = JSONObject.fromObject(rs);
		 out.print(json);
	}
	
	
	
	@RequestMapping(value = "/get_msg_unread", method = RequestMethod.POST)
	@ResponseBody
	public void get_msg_unread(HttpServletRequest request,
				HttpServletResponse response) throws IOException, SQLException {
		 
		 response.setCharacterEncoding("UTF-8");
		 response.setContentType("text/json");
		 PrintWriter out = response.getWriter();
		 
		 String uid_from = request.getParameter("uid_from");
		 String uid_my = request.getParameter("uid_my");
		 
		 chat_model cm = new chat_model();
		 ArrayList<msg_unread> requestList = cm.get_msg_unread(uid_from, uid_my);

		 JSONArray result = JSONArray.fromObject(requestList);
		 Response rs = new Response(0, "", result);
		 JSONObject json = JSONObject.fromObject(rs);
		 out.print(json);		 	
	}
	
	
	@RequestMapping(value = "/read_msg", method = RequestMethod.POST)
	@ResponseBody
	public void read_msg(HttpServletRequest request,
				HttpServletResponse response) throws IOException, SQLException {
		 
		 response.setCharacterEncoding("UTF-8");
		 response.setContentType("text/json");
		 PrintWriter out = response.getWriter();
		 
		 String uid_from = request.getParameter("uid_from");
		 String uid_me = request.getParameter("uid_me");
		 

		 chat_model cm = new chat_model();
		 ArrayList<chat> requestList = cm.read_msg(uid_from, uid_me);
		 cm.set_isread(uid_from, uid_me);

		 JSONArray result = JSONArray.fromObject(requestList);
		 Response rs = new Response(0, "", result);
		 JSONObject json = JSONObject.fromObject(rs);
	
		 out.print(json);
	}
	
	
	@RequestMapping(value = "/sent_msg", method = RequestMethod.POST)
	@ResponseBody
	public void sent_msg(HttpServletRequest request,
				HttpServletResponse response) throws IOException, SQLException {
		 
		 response.setCharacterEncoding("UTF-8");
		 response.setContentType("text/json");
		 PrintWriter out = response.getWriter();
		 
		 String uid_to = request.getParameter("uid_to");
		 String uid_me = request.getParameter("uid_me");
		 String text = request.getParameter("text");
		 
		 chat cchat = new chat();
		 cchat.setUid_from(uid_me);
		 cchat.setUid_to(uid_to);
		 cchat.setText(text);
		 cchat.setChatdate(cdate);
		 cchat.setIsread(0);
		 cchat.setIsdelete(0);
		 cchat.setIsgroup(0);
		 
		 chat_model cm = new chat_model();
		 String status = cm.sent_msg(cchat);
		 if(status == "ok"){
			 ChatSocket cs = new ChatSocket();
			 cs.broadcast("get_new_user|"+cchat.getUid_from()+"|"+cchat.getUid_to());
			 
			 Response rs = new Response(0, "Success", cchat);
			 JSONObject json = JSONObject.fromObject(rs);
			 System.out.println("success");out.print(json);
		 }else{
			 Response rs = new Response(5, "Failed", new Object());
			 JSONObject json = JSONObject.fromObject(rs);
			 System.out.println("failed");out.print(json);
		 }
	}

	
}
