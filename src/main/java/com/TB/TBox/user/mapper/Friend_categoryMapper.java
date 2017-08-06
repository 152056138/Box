package com.TB.TBox.user.mapper;

import java.util.List;

import com.TB.TBox.user.bean.Friend_category;

public interface Friend_categoryMapper {
	
	public void addFriend_category(Friend_category friend_category);
	
	public List<Friend_category> selectAllFriend_category();
}
