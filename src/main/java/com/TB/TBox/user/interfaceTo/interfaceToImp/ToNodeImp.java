package com.TB.TBox.user.interfaceTo.interfaceToImp;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.TB.TBox.user.bean.Friends;
import com.TB.TBox.user.bean.User;
import com.TB.TBox.user.interfaceTo.ToNodeInterface;
import com.TB.TBox.user.service.FriendService;
import com.TB.TBox.user.service.UserService;
import com.TB.base.mybatisUtils.SessionFactory;

@Controller
@Scope("prototype")
public class ToNodeImp implements ToNodeInterface {
	@Autowired
	private User user;
	@Autowired
	private UserService userService;
	@Autowired
	private FriendService friendService;
	/**
	 * 通过好友的friendnumber查询到好友对应的uid
	 * @param friendNumber
	 * @return
	 */
	public int selectFriendUid(String friendNumber){
		user = userService.selectUserByNumber(friendNumber);
		int friendUid =user.getUid(); 
		return friendUid;
		
	}
	
	/**
	 * 查询到所有该用户好友对应的uid为纸条模块提供
	 * Map中有两个参数一个为用户的uid一个为标识符用来判断是否这个好友被删除了0代表没有删除，1代表删除了
	 */
	public List<Integer> selectAllFriendUid(Map<String,Object> map) {
		List<Friends> friendList = friendService.selectAllFriends(map);
		List<Integer> allFriendUid = new ArrayList<Integer>();
		for(Friends friend:friendList){
			allFriendUid.add(userService.selectUserByNumber(friend.getFriendNumber()).getUid());
		}
		return allFriendUid;
	}

	public String selectUserNumber(int uid) {
		user = userService.selectUserByID(uid);
		String userNumber = user.getNumber();
		return userNumber;
	}
}
