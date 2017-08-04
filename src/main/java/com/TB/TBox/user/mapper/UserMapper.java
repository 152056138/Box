package com.TB.TBox.user.mapper;

import com.TB.TBox.user.bean.User;

public interface UserMapper {

	public void addUser(User user);
	public void createRole(User user);
	public User selectUserByID(int uid);
	public void updateRole(User user);
	public User selectUserByNumber(String number);
}
