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
	 * 查询某一个好友,看是否是删除的好友
	 */
	@Override
	public Friends selectFriendByUidAndNumber(Map<String,Object> map) {
		SqlSession session = sessionFactory.getSession();
		Friends friend = new Friends();
		FriendMapper friendOperation = session.getMapper(FriendMapper.class);
		friend = friendOperation.selectFriendByUidAndNumber(map);
		// System.out.println(friend.toString());
		return friend;
	   
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
		Friends friend = new  Friends();	
		FriendService friendService = new FriendService();
		//UserService userService = new UserService();
		Map<String,Object> map =new  HashMap<String, Object>();
		map.put("uid", 20);
		map.put("friendNumber","6942171882");
		map.put("recoverFriend", 1);
		friend = friendService.selectFriendByUidAndNumber(map);
		log.info(friend.getFriendNickname());
		
	}

	
}
