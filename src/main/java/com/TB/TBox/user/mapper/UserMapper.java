package com.TB.TBox.user.mapper;

import java.util.List;

import com.TB.TBox.user.bean.Mood_color;
import com.TB.TBox.user.bean.User;

public interface UserMapper {

	public void addUser(User user);
	public void createRole(User user);
	public User selectUserByID(int uid);
	public void updateRole(User user);
	public User selectUserByNumber(String number);
	public List<User> selectUserByUsername(String username);
	//用户心情模块操作
	public void addUserMoodColor(Mood_color mood_color);
	
	public void updateMoodColor(Mood_color mood_color);
	
	public Mood_color selectUserMoodColor(int uid);
}
