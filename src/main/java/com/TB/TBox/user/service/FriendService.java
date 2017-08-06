package com.TB.TBox.user.service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.springframework.stereotype.Service;

import com.TB.TBox.user.bean.Friends;
import com.TB.TBox.user.bean.User;
import com.TB.TBox.user.mapper.FriendMapper;
import com.TB.base.mybatisUtils.SessionFactory;
@Service
public class FriendService implements FriendMapper{
	Logger log = Logger.getLogger(FriendService.class);
	SessionFactory sessionFactory = new SessionFactory();
	
	/**
	 * 添加亲友
	 */
	public void addFriend(Friends friend) {
		SqlSession session =sessionFactory.getSession();
		try {
			FriendMapper friendOperation = session.getMapper(FriendMapper.class);
			friendOperation.addFriend(friend);;
			session.commit();
		} finally {
			session.close();
		}
	}

	/**
	 * 查询所有好友
	 */
	public List<Friends> selectAllFriends(Map map) {
		SqlSession session =sessionFactory.getSession();
		List<Friends> friendList = new  ArrayList<Friends>();
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
		SqlSession session =sessionFactory.getSession();
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
		SqlSession session =sessionFactory.getSession();
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
		SqlSession session =sessionFactory.getSession();
		FriendMapper friendOperation = session.getMapper(FriendMapper.class);
		Friends friend =friendOperation.selectFriendByFid(fid);
		return friend;
	}
	
	
	
	@Test
	public void test(){
//		Date date=new Date();
//		  DateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//		  String time=format.format(date);
//		  System.out.println(time);
		FriendService friendService = new FriendService();
		UserService userService = new UserService();
//		User user = userService.selectUserByID(1);
//		Friends friend = new Friends("2094434681", "王磊", 4, time, "Brienty", user.getUfacing(), 1,1);
//		friendServlet.addFriend(friend);
//		log.info(friend);
		
		
		Map map = new HashMap<String, Object>();
		map.put("uid",1);
		map.put("cid", 4);
		map.put("recoverFriend", 1);
		List<Friends> friends =friendService.selectAllFriends(map);
		System.out.println(friends.size()+"=================================================");
		for(Friends friend:friends){
			log.info(friend.toJson());
		}
	}


	

}
