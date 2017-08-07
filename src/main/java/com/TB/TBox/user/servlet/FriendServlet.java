package com.TB.TBox.user.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
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

import com.TB.TBox.prosceniumBean.ProsceniumFriend;
import com.TB.TBox.user.bean.Friends;
import com.TB.TBox.user.bean.Memo;
import com.TB.TBox.user.bean.User;
import com.TB.TBox.user.service.FriendService;
import com.TB.TBox.user.service.UserService;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

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
	@Autowired
	private Memo memo;
	@Autowired
	private ProsceniumFriend prosceniumFriend;

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
	 * 
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
		map.put("uid", uid);
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

	/**
	 * 查询好友（模糊查询）
	 * 
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping(value = "/selectFriend", method = RequestMethod.POST)
	public void selectFriend(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String formuid = request.getParameter("uid");
		int uid = Integer.parseInt(formuid);
		String selectName = request.getParameter("selectName");
		List<Friends> friendList = new ArrayList<Friends>();
		List<Integer> fids = new ArrayList<Integer>();
		// 判断是账号查询还是备注查询
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("uid", uid);
		map.put("recoverFriend", 0);
		map.put("friendUsername", selectName);
		friendList = friendService.selectFriendsByUsername(map);
		map.remove("friendUsername");
		map.put("friendNumber", selectName);
		getClass();
		friendList.addAll(friendService.selectFriendsByNumber(map));
		map.remove("friendNumber");
		map.put("friendNickname", selectName);
		friendList.addAll(friendService.selectFriendsByNickname(map));
		HashSet h = new HashSet(friendList);
		friendList.clear();
		friendList.addAll(h);
		for (Friends friend : friendList) {
			fids.add(friend.getFid());
		}
		response.setContentType("text/json");
		PrintWriter out = response.getWriter();
		out.print(gson.toJson(fids));
		out.flush();
		out.close();

	}

	/**
	 * 查询删除的好友(不知道用不用得到)
	 * 
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping(value = "/selectDeleteFriend", method = RequestMethod.POST)
	public void selectDeleteFriend(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String formuid = request.getParameter("uid");
		int uid = Integer.parseInt(formuid);
		Map map = new HashMap<String, Object>();
		map.put("uid", uid);
		map.put("recoverFriend", 1);
		List<Friends> friendsList = friendService.selectAllFriends(map);
		if (friendsList.isEmpty()) {
			response.setContentType("text/json");
			PrintWriter out = response.getWriter();
			out.print("您还没有删除的好友！");
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

	// ==========================================================================
	@RequestMapping(value = "/addMemo", method = RequestMethod.POST)
	public void addMemo(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String formuid = request.getParameter("uid");
		String formfid = request.getParameter("fid");
		String memoName = request.getParameter("memoName");
		String friendContent = request.getParameter("friendContent");
		int uid = Integer.parseInt(formuid);
		int fid = Integer.parseInt(formfid);
		memo.setFid(fid);
		memo.setUid(uid);
		memo.setFriendContent(friendContent);
		memo.setMemoName(memoName);
		friendService.addMemo(memo);
		response.setContentType("text/json");
		PrintWriter out = response.getWriter();
		out.print("添加成功！");
		out.flush();
		out.close();
	}

	/**
	 * 修改便签信息（支持批量修改）
	 * 
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping(value = "/updateMemo", method = RequestMethod.POST)
	public void updateMemo(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// 得到json MemiId串
		String formMemoIds = request.getParameter("memoIds");
		String memoName = request.getParameter("memoName");
		String friendContent = request.getParameter("friendContent");
		// 解析成当前模型，但是模型中只有MemiId有值
		List<Memo> memoList = gson.fromJson(formMemoIds, new TypeToken<List<Memo>>() {
		}.getType());
		for (Memo memo : memoList) {
			memo = friendService.selectMemoById(memo.getMemoId());
			memo.setMemoName(memoName);
			memo.setFriendContent(friendContent);
			friendService.updateMemo(memo);
		}

		response.setContentType("text/json");
		PrintWriter out = response.getWriter();
		out.print("修改成功！");
		out.flush();
		out.close();
	}

	/**
	 * 删除便签信息
	 * 
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping(value = "/deleteMemo", method = RequestMethod.POST)
	public void deleteMemo(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String formMemoId = request.getParameter("memoId");
		int memoId = Integer.parseInt(formMemoId);
		friendService.deleteMemo(memoId);
	}

	// =========================================================================
	/**
	 * 查看好友详细信息
	 * 
	 * @param request
	 * @param response
	 * @throws IOException
	 */

	@RequestMapping(value = "/selectFriendData", method = RequestMethod.POST)
	public void selectFriendData(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String formuid = request.getParameter("uid");
		int uid = Integer.parseInt(formuid);
		String friendNumber = request.getParameter("friendNumber");
		String formfid = request.getParameter("fid");
		int fid = Integer.parseInt(formfid);
		Map map = new HashMap<String, Object>();
		map.put("uid", uid);
		map.put("fid", fid);
		prosceniumFriend.setUser(userService.selectUserByNumber(friendNumber));
		prosceniumFriend.setFriendUsername(friendService.selectFriendByFid(fid).getFriendUsername());
		prosceniumFriend.setMemoList(friendService.selectMemo(map));
		response.setContentType("text/json");
		PrintWriter out = response.getWriter();
		out.print(gson.toJson(prosceniumFriend));
		out.flush();
		out.close();
	}

	@Test
	public void test() {

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

		// String formuid = ("2");
		// int uid = Integer.parseInt(formuid);
		// Map map = new HashMap<String, Object>();
		// map.put("uid", uid);
		// map.put("recoverFriend", 1);
		// List<Friends> friendsList = friendService.selectAllFriends(map);
		// System.out.println(friendsList.size()+"=======================================");
		// if (friendsList.isEmpty()) {
		// System.out.println("您还没有好友哦，快去添加几个好友吧！");
		// } else {
		// System.out.println(friendsList.size());
		// System.out.println(gson.toJson(friendsList));
		// }

		// String formFid = "5";
		// int fid = Integer.parseInt(formFid);
		// friend = friendService.selectFriendByFid(fid);
		// friend.setRecoverFriend(1);
		// System.out.println(friend.toString());
		// friendService.deleteFriend(friend);
		// log.info(friend.toJson());

		// String formuid=("1");
		// int uid = Integer.parseInt(formuid);
		// String selectName = ("丑八怪");
		// //判断是账号查询还是备注查询
		// Map<String,Object> map = new HashMap<String,Object>();
		// map.put("uid",uid);
		// map.put("recoverFriend", 0);
		//
		// Pattern pattern = Pattern.compile("[0-9]*");
		// if (pattern.matcher(selectName).matches()){
		// map.put("friendNumber", selectName);
		// friend=friendService.selectFriendsByNumber(map);
		// if(friend==null){
		// System.out.println("您查询的好友不存在！");
		// }else{
		// log.info(friend.toJson());
		// }
		// }else{
		// map.put("friendUsername", selectName);
		// friend=friendService.selectFriendsByUsername(map);
		// if(friend==null){
		// System.out.println("您查询的好友不存在！");
		// }else{
		// log.info(friend.toJson());
		// }
		// }
		UserService userService = new UserService();
		FriendService friendService = new FriendService();
		// Friends friend = new Friends();
		User user = new User();
		// ProsceniumFriend prosceniumFriend = new ProsceniumFriend();
		// String formuid = ("1");
		// int uid =Integer.parseInt(formuid) ;
		// String friendNumber = ("1139346699");
		// String formfid = ("1");
		// int fid = Integer.parseInt(formfid);
		// Map map = new HashMap<String, Object>();
		// map.put("uid", uid);
		// map.put("fid", fid);
		// user = userService.selectUserByNumber(friendNumber);
		// prosceniumFriend.setUser(user);
		// prosceniumFriend.setFriendUsername(friendService.selectFriendByFid(fid).getFriendUsername());
		// prosceniumFriend.setMemoList(friendService.selectMemo(map));
		// log.info(gson.toJson(prosceniumFriend));
		//

		String formuid = ("1");
		int uid = Integer.parseInt(formuid);
		String selectName = ("2");
		List<Friends> friendList = new ArrayList<Friends>();
		// 判断是账号查询还是备注查询
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("uid", uid);
		map.put("recoverFriend", 0);
		map.put("friendUsername", selectName);
		friendList = friendService.selectFriendsByUsername(map);
		map.remove("friendUsername");
		map.put("friendNumber", selectName);
		getClass();
		friendList.addAll(friendService.selectFriendsByNumber(map));
		map.remove("friendNumber");
		map.put("friendNickname", selectName);
		friendList.addAll(friendService.selectFriendsByNickname(map));
		HashSet h = new HashSet(friendList);
		friendList.clear();
		friendList.addAll(h);
		System.out.println(friendList.size() + "-------------------------------------------------");
		for (Friends friend : friendList) {
			System.out.println(friend.toJson());
		}
		System.out.println(friendList.size() + "-------------------------------------------------");
		List<Integer> fids = new ArrayList<Integer>();
		for (Friends friend : friendList) {
			fids.add(friend.getFid());
		}

		for (int fid : fids) {
			System.out.println(fid);
		}
		System.out.println(fids.size() + "-------------------------------------------------");

	}

}
