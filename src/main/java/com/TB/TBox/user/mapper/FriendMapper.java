package com.TB.TBox.user.mapper;

import java.util.List;
import java.util.Map;

import com.TB.TBox.user.bean.Friends;

public interface FriendMapper {
public void addFriend(Friends friend);
public List<Friends> selectAllFriends(Map map);
public void updateFriendName(Friends friend);
public void deleteFriend(Friends friend);
public Friends selectFriendByFid(int fid);

}
