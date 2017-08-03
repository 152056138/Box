package com.TB.TBox.user.service;

import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;
import org.junit.Test;

import com.TB.TBox.user.bean.User;
import com.TB.TBox.user.mapper.UserMapper;
import com.TB.base.mybatisUtils.SessionFactory;



public class UserService implements UserMapper{
	Logger log = Logger.getLogger(UserService.class);
		SessionFactory sessionFactory = new SessionFactory();
		SqlSession session =sessionFactory.getSession();

	public void addUser(User user) {
		try {
			UserMapper userOperation = session.getMapper(UserMapper.class);
			userOperation.addUser(user);
			session.commit();
			System.out.println("当前增加的用户 id为:" + user.getUid());
		} finally {
			session.close();
		}
		
	}

	public void createRole(User user) {
//		try {
//			UserMapper userOperation = session.getMapper(UserMapper.class);
//			User sqluser = userOperation.selectUserByID(2);
//			
//			userOperation.updateUser(user);
//			System.out.println("修改成功！");
//			session.commit();
//		} finally {
//			session.close();
//		}
//		
	}

	public User selectUserByID(int uid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Test
	public void userTest(){
	/*User user = new User("1234567890", "123321", "12324345664", "山西省")	;
	UserService userService = new UserService();
	userService.addUser(user);*/
		log.info("jkfsjdkfl");
	}
}
