package com.TB.TBox.user.interfaceTo;

import java.util.List;

import org.springframework.stereotype.Service;

import com.TB.TBox.user.bean.User;


@Service
public interface ToFriendsInterface {
	public List<User> vagueSelectUsers(String selectName);
	
}
