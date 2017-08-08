package com.TB.TBox.user.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.TB.TBox.user.bean.Friends;
import com.TB.TBox.user.bean.Memo;
import com.TB.TBox.user.mapper.FriendMapper;
import com.TB.base.mybatisUtils.SessionFactory;

@Service
public class FriendService implements FriendMapper {
	Logger log = Logger.getLogger(FriendService.class);
	SessionFactory sessionFactory = new SessionFactory();
	@Autowired
	private Memo memo;
	
	/**
	 * 添加好友
	 */
	public void addFriend(Friends friend) {
		SqlSession session = sessionFactory.getSession();
		try {
			FriendMapper friendOperation = session.getMapper(FriendMapper.class);
			friendOperation.addFriend(friend);
			session.commit();
		} finally {
			session.close();
		}
	}

	/**
	 * 查询所有好友
	 */
	public List<Friends> selectAllFriends(Map<String,Object> map) {
		SqlSession session = sessionFactory.getSession();
		List<Friends> friendList = new ArrayList<Friends>();
		try {
			FriendMapper friendOperation = session.getMapper(FriendMapper.class);
			friendList = friendOperation.selectAllFriends(map);
		} finally {

		}
		return friendList;
	}

	/**
	 * 修改好友备注
	 */
	public void updateFriendName(Friends friend) {
		SqlSession session = sessionFactory.getSession();
		try {
			FriendMapper friendOperation = session.getMapper(FriendMapper.class);
			friendOperation.updateFriendName(friend);
			session.commit();
		} finally {

			session.close();
		}

	}

	/**
	 * 删除好友
	 */
	public void deleteFriend(Friends friend) {
		SqlSession session = sessionFactory.getSession();
		try {
			FriendMapper friendOperation = session.getMapper(FriendMapper.class);
			friendOperation.deleteFriend(friend);
			session.commit();
		} finally {

			session.close();
		}

	}

	/**
	 * 通过id查询好友
	 */
	public Friends selectFriendByFid(int fid) {
		SqlSession session = sessionFactory.getSession();
		FriendMapper friendOperation = session.getMapper(FriendMapper.class);
		Friends friend = friendOperation.selectFriendByFid(fid);
		return friend;
	}

	/**
	 * 通过备注查询好友
	 */
	public List<Friends> selectFriendsByUsername(Map<String,Object> map) {
		SqlSession session = sessionFactory.getSession();
		List<Friends> friendList = new ArrayList<Friends>();
		FriendMapper friendOperation = session.getMapper(FriendMapper.class);
		friendList = friendOperation.selectFriendsByUsername(map);
		return friendList;
	}

	/**
	 * 通过账号查询好友
	 */
	public List<Friends> selectFriendsByNumber(Map<String,Object> map) {
		SqlSession session = sessionFactory.getSession();
		List<Friends> friendList = new ArrayList<Friends>();
		FriendMapper friendOperation = session.getMapper(FriendMapper.class);
		friendList = friendOperation.selectFriendsByNumber(map);
		return friendList;
	}

	// 通过名字查询
	public List<Friends> selectFriendsByNickname(Map<String,Object> map) {
		SqlSession session = sessionFactory.getSession();
		List<Friends> friendList = new ArrayList<Friends>();
		FriendMapper friendOperation = session.getMapper(FriendMapper.class);
		friendList = friendOperation.selectFriendsByNickname(map);
		return friendList;
	}

	// ======================好友便签部分=========================
	public void addMemo(Memo memo) {
		SqlSession session = sessionFactory.getSession();
		try {
			FriendMapper memoOperation = session.getMapper(FriendMapper.class);
			memoOperation.addMemo(memo);
			session.commit();
		} finally {
			session.close();
		}

	}

	public void updateMemo(Memo memo) {
		SqlSession session = sessionFactory.getSession();
		try {
			FriendMapper memoOperation = session.getMapper(FriendMapper.class);
			memoOperation.updateMemo(memo);
			session.commit();
		} finally {

			session.close();
		}

	}

	public void deleteMemo(int memoId) {
		SqlSession session = sessionFactory.getSession();
		try {
			FriendMapper memoOperation = session.getMapper(FriendMapper.class);
			memoOperation.deleteMemo(memoId);
			session.commit();
		} finally {
			session.close();
		}
	}

	public Memo selectMemoById(int memeoId) {
		SqlSession session = sessionFactory.getSession();
		FriendMapper memoOperation = session.getMapper(FriendMapper.class);
		memo = memoOperation.selectMemoById(memeoId);
		return memo;
	}

	public List<Memo> selectMemo(Map<String,Object> map) {
		SqlSession session = sessionFactory.getSession();
		List<Memo> memeoList = new ArrayList<Memo>();
		try {
			FriendMapper memoOperation = session.getMapper(FriendMapper.class);
			memeoList = memoOperation.selectMemo(map);
		} finally {

			System.out.println("查询完毕！");
		}
		return memeoList;
	}

	
	@Test
	public void test() {
		// Date date=new Date();
		// DateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		// String time=format.format(date);
		// System.out.println(time);
		FriendService friendService = new FriendService();
		UserService userService = new UserService();
		// User user = userService.selectUserByID(1);
		// Friends friend = new Friends("2094434681", "王磊", 4, time, "Brienty",
		// user.getUfacing(), 1,1);
		// friendServlet.addFriend(friend);
		// log.info(friend);

		// Map map = new HashMap<String, Object>();
		// map.put("uid", 1);
		// map.put("cid", 4);
		// map.put("recoverFriend", 1);
		// List<Friends> friends = friendService.selectAllFriends(map);
		// System.out.println(friends.size() +
		// "=================================================");
		// for (Friends friend : friends) {
		// log.info(friend.toJson());
		// }
		List<Memo> memeos = new ArrayList<Memo>();
		Memo memo = new Memo();
		// memo.setFid(1);
		// memo.setUid(1);
		// memo.setMemoName("脾气");
		// memo.setFriendContent("温顺尔雅");
		// friendService.addMemo(memo);

		// Map map = new HashMap<String, Object>();
		// map.put("uid", 1);
		// map.put("fid", 1);
		// memeos = friendService.selectMemo(map);
		// System.out.println(memeos.size());
		// for(Memo memeo : memeos){
		// System.out.println(memeo.toJson());
		// }

		// memo = friendService.selectMemoById(1);
		// System.out.println(memo.toJson());
		// memo.setFriendContent("骚的一B");
		// friendService.updateMemo(memo);
		// log.info(memo.toJson());

		Map<String,Object> map = new HashMap<String, Object>();
		map.put("uid", 1);
		map.put("recoverFriend", 0);
		map.put("friendNickname", "y");
		List<Friends> friendList = new ArrayList<Friends>();
		friendList = friendService.selectFriendsByNickname(map);
		System.out.println(friendList.size());
		for (Friends friend : friendList) {
			System.out.println(friend.toJson());
		}
	}
}
