package com.TB.TBox.user.service;



import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.TB.TBox.user.bean.Mood_color;
import com.TB.TBox.user.bean.User;
import com.TB.TBox.user.mapper.UserMapper;
import com.TB.base.mybatisUtils.SessionFactory;




@Service
public class UserService implements UserMapper{
	Logger log = Logger.getLogger(UserService.class);
		SessionFactory sessionFactory = new SessionFactory();
	@Autowired
	private Mood_color mood_color;

		/**
		 * 注册账号
		 */
	public void addUser(User user) {
		SqlSession session =sessionFactory.getSession();
		try {
			UserMapper userOperation = session.getMapper(UserMapper.class);
			userOperation.addUser(user);
			session.commit();
			System.out.println("当前增加的用户 id为:" + user.getUid());
		} finally {
			session.close();
		}
		
	}


	/**
	 * 创建角色
	 */
	public void createRole(User user) {
		SqlSession session =sessionFactory.getSession();
		try {
			UserMapper userOperation = session.getMapper(UserMapper.class);
			userOperation.createRole(user);
			session.commit();
		} finally {
		session.close();
		}
		
	}
	
/**
 * 按手机号查询
 */
	public User selectUserByPhone(String phone) {
		SqlSession session =sessionFactory.getSession();
		UserMapper userOperation = session.getMapper(UserMapper.class);
		User user = userOperation.selectUserByPhone(phone);
		return user;
	}
	
	
	/**
	 * 按id查询
	 */
	public User selectUserByID(int uid) {
		SqlSession session =sessionFactory.getSession();
			UserMapper userOperation = session.getMapper(UserMapper.class);
			User user = userOperation.selectUserByID(uid);
		return user;
	}
	
	/**
	 * 修改信息（修改密码，修改二级密码，修改角色信息）
	 */
	public void updateRole(User user) {
		SqlSession session =sessionFactory.getSession();
		try {
			UserMapper userOperation = session.getMapper(UserMapper.class);
			userOperation.updateRole(user);
			session.commit();
		} finally {
			session.close();
		}
	}
	
	/**
	 * 按账号查询
	 */
	public User selectUserByNumber(String number) {
		SqlSession session =sessionFactory.getSession();
		UserMapper userOperation = session.getMapper(UserMapper.class);
		User user = userOperation.selectUserByNumber(number);
		if(user==null){
			System.out.println("账号不存在");
			return null;
		}else{
			return user;	
		}
	
	}
	
	//===============================================================
			//按角色姓名查询（用于好友模块）
			public List<User> selectUserByUsername(String username) {
				SqlSession session =sessionFactory.getSession();
				List<User> userList = new ArrayList<User>();
				UserMapper userOperation = session.getMapper(UserMapper.class);
					userList = userOperation.selectUserByUsername(username);
				return userList;
			}
			
			
			//模糊查询（用于添加好友）
			public List<User> selectUserByVagueUsername(String username) {
				SqlSession session =sessionFactory.getSession();
				List<User> userList = new ArrayList<User>();
				UserMapper userOperation = session.getMapper(UserMapper.class);
				userList = userOperation.selectUserByVagueUsername(username);
				return userList;
			}

		//模糊查询（用于添加好友）
			public List<User> selectUserByByVagueNumber(String number) {
				SqlSession session =sessionFactory.getSession();
				List<User> userList = new ArrayList<User>();
				UserMapper userOperation = session.getMapper(UserMapper.class);
				userList = userOperation.selectUserByByVagueNumber(number);
				return userList;
			}
			
			//模糊查询（用于添加好友）
			public List<User> selectUserByVaguePhone(String phone) {
				SqlSession session =sessionFactory.getSession();
				List<User> userList = new ArrayList<User>();
				UserMapper userOperation = session.getMapper(UserMapper.class);
				userList = userOperation.selectUserByVaguePhone(phone);
				return userList;
			}
	//=============================================================
	//添加用户心情颜色
	public void addUserMoodColor(Mood_color mood_color) {
		SqlSession session =sessionFactory.getSession();
		try {
			UserMapper userOperation = session.getMapper(UserMapper.class);
			userOperation.addUserMoodColor(mood_color);
			session.commit();
		} finally {
			session.close();
		}
		
	}

//修改用户心情颜色
	public void updateMoodColor(Mood_color mood_color) {
		SqlSession session =sessionFactory.getSession();
		try {
			UserMapper userOperation = session.getMapper(UserMapper.class);
			userOperation.updateMoodColor(mood_color);
			session.commit();
		} catch (Exception e) {
			session.close();		}
		
	}

//查看用户心情颜色
	public Mood_color selectUserMoodColor(int uid) {
		SqlSession session =sessionFactory.getSession();
		UserMapper userOperation = session.getMapper(UserMapper.class);
		return mood_color = userOperation.selectUserMoodColor(uid);		
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	//测试方法
	@Test
	public void userTest() throws IOException{
	//User user = new User("1234567890", "123321", "12324345664", "山西省")	;
	UserService userService = new UserService();
	//userService.addUser(user);
//	 File file = new File("C:/Users/MrDu/Desktop/2323.jpg");
//	 byte[] b=null;
//	 try {
//		InputStream photoStream = new FileInputStream(file);
//		try {
//			 b= IOUtils.toByteArray(photoStream);
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	} catch (FileNotFoundException e) {
//		// TODO Auto-generated catch block
//		e.printStackTrace();
//	}
//		User user = new User(1,"趣多多", "水瓶座", "A", "没有什么不同", "2017-7-1", b, "看书，听音乐", "程序员", "男", "213231", 1);
		User user = userService.selectUserByID(1);
		//System.out.println(user.toString());
		//userService.createRole(user);
//		log.info(user);
//		OutputStream out = new FileOutputStream("C:/Users/MrDu/Desktop/2324.jpg");
//		out.write(user.getUfacing());
		//user.setPhone("15735185214");
	//	System.out.println(user.toString());
		
	//User user = userService.selectUserByNumber("1334610525");
		user.setPassword("erererer");
		userService.updateRole(user);
		log.info(user.toJson());
		
	}




	


	
}
