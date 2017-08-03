import org.apache.ibatis.session.SqlSession;

import com.TB.TBox.user.bean.User;
import com.TB.TBox.user.mapper.UserMapper;
import com.TB.base.mybatisUtils.SessionFactory;

public class UserTest {
	
	static SessionFactory sessionFactory = new SessionFactory();
	 static SqlSession session =sessionFactory.getSession();
public static void main(String[] args) {
	User user = new User("12345678912", "123321", "12324345664", "山西省")	;
	try {
		UserMapper userOperation = session.getMapper(UserMapper.class);
		userOperation.addUser(user);
		session.commit();
		System.out.println("当前增加的用户 id为:" + user.getUid());
	} finally {
		session.close();
	}
}
}
