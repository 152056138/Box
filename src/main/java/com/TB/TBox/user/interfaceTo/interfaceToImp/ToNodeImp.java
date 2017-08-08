package com.TB.TBox.user.interfaceTo.interfaceToImp;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.TB.TBox.user.bean.User;
import com.TB.TBox.user.interfaceTo.ToNodeInterface;
import com.TB.TBox.user.service.UserService;
import com.TB.base.mybatisUtils.SessionFactory;

@Component
public class ToNodeImp implements ToNodeInterface {
	@Autowired
	private User user;
	@Autowired
	private UserService userService;
	
	SessionFactory sessionFactory = new SessionFactory();
	/**
	 * 通过好友的fid查询到好友对应的uid
	 * @param friendNumber
	 * @return
	 */
	public int selectFriendUid(String friendNumber){
		SqlSession session = sessionFactory.getSession();
		user = userService.selectUserByNumber(friendNumber);
		int friendUid =user.getUid(); 
		return friendUid;
		
	}
}
