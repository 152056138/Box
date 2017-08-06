package com.TB.TBox.user.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.TB.TBox.user.bean.Friends;
import com.TB.TBox.user.bean.User;
import com.TB.TBox.user.service.FriendService;
import com.TB.TBox.user.service.UserService;
import com.google.gson.Gson;

@Controller
@RequestMapping("/friend")
@Scope("prototype")
public class FriendServlet {
	Gson gson = new Gson();
	Logger log = Logger.getLogger(FriendServlet.class);
	DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	@Autowired
	private UserService userService;
	@Autowired
	private FriendService friendService;
	@Autowired
	private User user;
	@Autowired
	private Friends friend;

	/**
	 * 添加好友
	 * 
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping(value = "/addFriend", method = RequestMethod.POST)
	public void addFriend(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String number = request.getParameter("number");
		String formuid = request.getParameter("uid");
		int uid = Integer.parseInt(formuid);
		friend.setUid(uid);
		String formcid = request.getParameter("cid");
		int cid = Integer.parseInt(formcid);
		friend.setCid(cid);
		user = userService.selectUserByNumber(number);
		friend.setFriendNumber(user.getNumber());
		friend.setFacing(user.getUfacing());
		friend.setFriendNickname(user.getUsername());
		String friendUsername = request.getParameter("friendUsername");
		friend.setFriendUsername(friendUsername);
		int recoverFriend = 0;
		friend.setRecoverFriend(recoverFriend);
		Date date = new Date();
		String time = format.format(date);
		System.out.println(time);
		friend.setFriendTime(time);
		friendService.addFriend(friend);
		response.setContentType("text/json");
		PrintWriter out = response.getWriter();
		out.print("添加成功！");
		out.flush();
		out.close();
	}

	/**
	 * 修改好友备注
	 * 
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping(value = "/updateFriendName", method = RequestMethod.POST)
	public void updateFriendName(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String formFid = request.getParameter("fid");
		int fid = Integer.parseInt(formFid);
		String friendUsername = request.getParameter("friendUsername");
		friend = friendService.selectFriendByFid(fid);
		friend.setFriendUsername(friendUsername);
		friendService.updateFriendName(friend);
	}
	
	/**
	 * 删除好友
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping(value = "/deleteFriend", method = RequestMethod.POST)
	public void deleteFriend(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String formFid = request.getParameter("fid");
		int fid = Integer.parseInt(formFid);
		friend = friendService.selectFriendByFid(fid);
		friend.setRecoverFriend(1);
		friendService.deleteFriend(friend);
	}


	/**
	 * 查询所有好友
	 * 
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping(value = "/selectAllFriends", method = RequestMethod.POST)
	public void selectAllFriends(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String formuid = request.getParameter("uid");
		int uid = Integer.parseInt(formuid);
		Map map = new HashMap<String, Object>();
		map.put("uid", 1);
		map.put("recoverFriend", 0);
		List<Friends> friendsList = friendService.selectAllFriends(map);
		if (friendsList.isEmpty()) {
			response.setContentType("text/json");
			PrintWriter out = response.getWriter();
			out.print("您还没有好友哦，快去添加几个好友吧！");
			out.flush();
			out.close();
		} else {
			response.setContentType("text/json");
			PrintWriter out = response.getWriter();
			out.print(gson.toJson(friendsList));
			out.flush();
			out.close();
		}
	}

	@Test
	public void test() {
		UserService userService = new UserService();
		FriendService friendService = new FriendService();
		Friends friend = new Friends();
		User user = new User();
		// String number = ("12345678912");
		// String formuid = ("2");
		// int uid = Integer.parseInt(formuid);
		// friend.setUid(uid);
		// String formcid = ("4");
		// int cid = Integer.parseInt(formcid);
		// friend.setCid(cid);
		// user = userService.selectUserByNumber(number);
		// friend.setFriendNumber(user.getNumber());
		// friend.setFacing(user.getUfacing());
		// friend.setFriendNickname(user.getUsername());
		// String friendUsername = ("小可爱");
		// friend.setFriendUsername(friendUsername);
		// int recoverFriend = 0;
		// friend.setRecoverFriend(recoverFriend);
		// Date date = new Date();
		// String time = format.format(date);
		// System.out.println(time);
		// friend.setFriendTime(time);
		// friendService.addFriend(friend);
		// log.info(friend.toJson());

		// String formFid = ("5");
		// int fid = Integer.parseInt(formFid);
		// String friendUsername = ("丑八怪");
		// friend = friendService.selectFriendByFid(fid);
		// friend.setFriendUsername(friendUsername);
		// friendService.updateFriendName(friend);
		// log.info(friend.toJson());

//		String formuid = ("3");
//		int uid = Integer.parseInt(formuid);
//		Map map = new HashMap<String, Object>();
//		map.put("uid", uid);
//		map.put("recoverFriend", 0);
//		List<Friends> friendsList = friendService.selectAllFriends(map);
//		if (friendsList.isEmpty()) {
//			System.out.println("您还没有好友哦，快去添加几个好友吧！");
//		} else {
//			System.out.println(friendsList.size());
//			System.out.println(gson.toJson(friendsList));
//		}
		
		String formFid = "5";
		int fid = Integer.parseInt(formFid);
		friend = friendService.selectFriendByFid(fid);
		friend.setRecoverFriend(1);
		System.out.println(friend.toString());
		friendService.deleteFriend(friend);
		log.info(friend.toJson());
		
	}

}
