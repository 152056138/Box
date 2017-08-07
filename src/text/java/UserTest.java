import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.regex.Pattern;

import org.apache.commons.io.IOUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import com.TB.TBox.user.bean.User;
import com.TB.TBox.user.mapper.UserMapper;
import com.TB.TBox.user.service.UserService;
import com.TB.base.mybatisUtils.SessionFactory;

public class UserTest {
	
	static SessionFactory sessionFactory = new SessionFactory();
	 static SqlSession session =sessionFactory.getSession();
	 static UserService userService = new UserService();
	 @Test
public void a() {
//	User user = new User("123456789", "123321", "12324364", "山西省")	;
//	try {
//		UserMapper userOperation = session.getMapper(UserMapper.class);
//		userOperation.addUser(user);
//		session.commit();
//		System.out.println("当前增加的用户 id为:" + user.getUid());
//	} finally {
//		session.close();
//	}
	
//	User user = new User(1,"趣多多", "水瓶座", "A", "没有什么不同", "2017-7-1", null, "看书，听音乐", "程序员", "男", "213231", 1);
//	userService.createRole(user);
//	System.out.println(user.toString());
	
//	 File file = new File("C:/Users/MrDu/Desktop/2323.jpg");
//	 try {
//		InputStream photoStream = new FileInputStream(file);
//		try {
//			byte[] b = IOUtils.toByteArray(photoStream);
//			System.out.println(b);
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//	} catch (FileNotFoundException e) {
//		// TODO Auto-generated catch block
//		e.printStackTrace();
//	}
		 
//		 User user = new User();
//		 String number = "123321";
//			String password = "123321";
//			String repassword = "123321";
//			String phone = "123";
//			String place = "123";
//		 if ((number.isEmpty()) || (password .isEmpty()) || (phone .isEmpty()) || (phone .isEmpty())) {
//			 System.out.println("用户注册信息填写不完整,请填写完整！");
//			} else {
//				// 判断注册账号是不是是数字串
//				Pattern pattern = Pattern.compile("[0-9]*");
//				if (pattern.matcher(number).matches()) {
//					// 判断用户的注册账号是不是是6-11位
//					if ((number.length() >= 6) && (number.length() <= 11)) {
//						// 判断密码的长度
//						if (password.length() < 6) {
//							System.out.println("密码位数太少，最少为6位！");
//							// 重复密码和密码是否一致
//						} else if (number.equals(repassword)) {
//							user.setNumber(number);
//							user.setPassword(password);
//							user.setPhone(phone);
//							user.setPlace(place);
//						userService.addUser(user);
//							System.out.println("注册成功"+user.toJson());
//							
//						} else {
//							
//							System.out.println("密码和重复密码不一致！");
//						}
//					} else {
//						System.out.println("注册账号应为6-11位的数字！");
//					}
//				} else {
//					System.out.println("注册账号应为6-11位的数字,不能含有其他字符！");
//				}
//
//			}
//		 User user = new User();
//		 String number="1334610525";
//			String password = "erererer";
//			user = userService.selectUserByNumber(number);
//			if(user==null){
//				System.out.println("您输入的账号不存在！");
//			}else{
//				if(password.equals(user.getPassword())){
//					System.out.println("登陆成功！");
//				}else{
//					System.out.println("您输入的密码不正确！");
//				}
//			}
		 
			//获取一个随机数
			double rand = Math.random();
			//将随机数转换为字符串
			String str = String.valueOf(rand).replace("0.", "");
			//截取字符串
			String newStr = str.substring(0, 10);
			System.out.println(newStr);
		}
}

