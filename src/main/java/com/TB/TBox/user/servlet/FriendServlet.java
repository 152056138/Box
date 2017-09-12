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
import com.TB.TBox.user.interfaceTo.ToFriendsInterface;
import com.TB.TBox.user.interfaceTo.interfaceToImp.ToFriendsImp;
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
	@Autowired
	private ToFriendsInterface toFriendsInterface = new ToFriendsImp();

	/**
	 * 添加好友之前的模糊查询
	 * 
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping(value = "/vagueSelectFriend", method = RequestMethod.POST)
	public void vagueSelectFriend(HttpServletRequest request, HttpServletResponse response) throws IOException {
		request.setCharacterEncoding("utf-8");
		String selectName = request.getParameter("selectName");
		String uuid = request.getParameter("uid");
				int uid  = Integer.parseInt(uuid);
		List<User> userList = new ArrayList<User>();
		userList = toFriendsInterface.vagueSelectUsers(selectName);
		List<User> newUserList = new ArrayList<>();
		for(User user:userList){
			if(uid ==user.getUid())
				continue;
			user.setPassword("");
			newUserList.add(user);
		}
		response.setContentType("text/json;charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.print(gson.toJson(newUserList));
		out.flush();
		out.close();
	}

	/**
	 * 添加好友(没有判断是否已经是好友)
	 * 
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping(value = "/addFriend", method = RequestMethod.POST)
	public void addFriend(HttpServletRequest request, HttpServletResponse response) throws IOException {
		request.setCharacterEncoding("utf-8");
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
		response.setContentType("text/json;charset=UTF-8");
		PrintWriter out = response.getWriter();
		//返回添加好友的信息；
		out.print(gson.toJson(friend));
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
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("uid", uid);
		map.put("recoverFriend", 0);
		List<Friends> friendsList = friendService.selectAllFriends(map);
		if (friendsList.isEmpty()) {
			response.setContentType("text/json;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.print("您还没有好友哦，快去添加几个好友吧！");
			out.flush();
			out.close();
		} else {
			response.setContentType("text/json;charset=UTF-8");
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
	 * 好友账号，用户昵称，好友备注
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/selectFriend", method = RequestMethod.POST)
	public void selectFriend(HttpServletRequest request, HttpServletResponse response) throws IOException {
		request.setCharacterEncoding("utf-8");
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
		response.setContentType("text/json;charset=UTF-8");
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
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("uid", uid);
		map.put("recoverFriend", 1);
		List<Friends> friendsList = friendService.selectAllFriends(map);
		if (friendsList.isEmpty()) {
			response.setContentType("text/json;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.print("您还没有删除的好友！");
			out.flush();
			out.close();
		} else {
			response.setContentType("text/json;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.print(gson.toJson(friendsList));
			out.flush();
			out.close();
		}
	}

	// ===============================好友便签模块===========================================
	@RequestMapping(value = "/addMemo", method = RequestMethod.POST)
	public void addMemo(HttpServletRequest request, HttpServletResponse response) throws IOException {
		request.setCharacterEncoding("utf-8");
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
		response.setContentType("text/json;charset=UTF-8");
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
		String memoId = request.getParameter("memoId");
		String formMemoName = request.getParameter("memoName");
		String formFriendContent = request.getParameter("friendContent");
		// 解析成当前模型，但是模型中只有MemiId有值
		List<Memo> memoList = gson.fromJson(memoId, new TypeToken<List<Memo>>() {
		}.getType());
		
		List<Memo> memoListMemoName = gson.fromJson(formMemoName, new TypeToken<List<Memo>>() {
		}.getType());
		
		List<Memo> memoListFriendContent = gson.fromJson(formFriendContent, new TypeToken<List<Memo>>() {
		}.getType());
			for(int i=0;i<memoListMemoName.size();i++)
			{
			memo = friendService.selectMemoById(memoList.get(i).getMemoId());
			memo.setMemoName(memoListMemoName.get(i).getMemoName());
			memo.setFriendContent(memoListFriendContent.get(i).getFriendContent());
			friendService.updateMemo(memo);
		}
		response.setContentType("text/json;charset=UTF-8");
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
		request.setCharacterEncoding("utf-8");
		String formuid = request.getParameter("uid");
		int uid = Integer.parseInt(formuid);
		
		String friendNumber = request.getParameter("friendNumber");
		String formfid = request.getParameter("fid");
		int fid = Integer.parseInt(formfid);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("uid", uid);
		map.put("fid", fid);
		List<Memo> memoList = new ArrayList<>();
		memoList = friendService.selectMemo(map);
		for(Memo memo : memoList){
			log.info(gson.toJson(memo));
		}
		prosceniumFriend = getMemo(userService.selectUserByNumber(friendNumber), memoList, friendService.selectFriendByFid(fid).getFriendUsername(),formuid+friendNumber);
		response.setContentType("text/json;charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.print(gson.toJson(prosceniumFriend));
		out.flush();
		out.close();
	}

	public ProsceniumFriend getMemo(User user,List<Memo> memoList,String friendUsername,String proId){
		ProsceniumFriend PF = new ProsceniumFriend();
		List<Memo> memoList3 = new ArrayList<>();//为了让账号显示再前面需要将便签置后
		//设置ProId
		PF.setProId(proId);
		//封装头像和手机号
		PF.setHead(user.getUfacing());
		PF.setPhone(user.getPhone());
		PF.setFriendUsername(friendUsername);
		// ---
		Memo memo1 = new Memo();
		memo1.setMemoName("账号");
		memo1.setFriendContent(user.getNumber());
		memo1.setMemoId(-1);
		memoList3.add(memo1);
		// ---
		
			Memo memo2 = new Memo();
			memo2.setMemoName("生日");
			if(user.getBirthday()!=null && user.getAge()!=0){
				memo2.setFriendContent(user.getBirthday() + "（" + user.getAge() + "岁）");
			}
			else {
				memo2.setFriendContent(null);
			}
			memo2.setMemoId(-1);
			memoList3.add(memo2);
		
		// ---
		Memo memo3 = new Memo();
		memo3.setMemoName("职业");
		memo3.setFriendContent(user.getJob());
		memo3.setMemoId(-1);
		memoList3.add(memo3);
		// ---
		Memo memo4 = new Memo();
		memo4.setMemoName("兴趣");
		memo4.setFriendContent(user.getHobby());
		memo4.setMemoId(-1);
		memoList3.add(memo4);
		// ---
		Memo memo5 = new Memo();
		memo5.setMemoName("所在地");
		memo5.setFriendContent(user.getPlace());
		memo5.setMemoId(-1);
		memoList3.add(memo5);
		// ---
		Memo memo6 = new Memo();
		memo6.setMemoName("其他");
		if(user.getConstellation()!=null &&user.getBlood()!=null){
			memo6.setFriendContent(user.getConstellation() + "  " + user.getBlood());
		}
		else if(user.getConstellation()!=null &&user.getBlood()==null){
			memo6.setFriendContent(user.getConstellation() );
		}
		else if(user.getConstellation()==null &&user.getBlood()!=null){
			memo6.setFriendContent(user.getBlood());
		}
		else{
			memo6.setFriendContent(null);
		}
		memo6.setMemoId(-1);
		memoList3.add(memo6);
		for(Memo memo:memoList){
			log.info(gson.toJson(memo));
		}
		//封装便签集合
		memoList3 = judgeNull(memoList3);
		for(Memo m1 : memoList){
			memoList3.add(m1);
		}
		PF.setMemoList(memoList3);
		return PF;
	}
	
	public List<Memo> judgeNull(List<Memo> memoList){
		List<Memo> memoList2  = new ArrayList<>();
		for(Memo m:memoList){
			if(m.getFriendContent() == null ){continue;}
			else{
				memoList2.add(m);
			}
		}
		for(Memo m : memoList2){
			log.debug(m);
		}
		return memoList2;
	}
}

