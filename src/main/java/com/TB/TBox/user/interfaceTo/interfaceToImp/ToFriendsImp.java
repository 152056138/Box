package com.TB.TBox.user.interfaceTo.interfaceToImp;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.TB.TBox.user.bean.User;
import com.TB.TBox.user.interfaceTo.ToFriendsInterface;

import com.TB.TBox.user.service.UserService;


public class ToFriendsImp implements ToFriendsInterface {
	@Autowired
 private UserService userService ;
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List<User> vagueSelectUsers(String selectName) {
		List<User> userList = new ArrayList<User>();
		userList = userService.selectUserByByVagueNumber(selectName);
		userList.addAll(userService.selectUserByVagueUsername(selectName));
		HashSet h = new HashSet(userList);
		userList.clear();
		userList.addAll(h);
		return userList;
	}

	
		

}
