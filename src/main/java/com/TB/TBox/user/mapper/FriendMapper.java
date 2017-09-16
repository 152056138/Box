package com.TB.TBox.user.mapper;

import java.util.List;
import java.util.Map;

import com.TB.TBox.user.bean.Friends;
import com.TB.TBox.user.bean.Memo;

public interface FriendMapper {
	public void addFriend(Friends friend);

	public List<Friends> selectAllFriends(Map<String,Object> map);

	public void updateFriendName(Friends friend);
	
	public Friends selectFriendByUidAndNumber(Map<String,Object> map);

	public void deleteFriend(Friends friend);

	public Friends selectFriendByFid(int fid);

	public List<Friends> selectFriendsByNickname(Map<String,Object> map);

	public List<Friends> selectFriendsByUsername(Map<String,Object> map);

	public List<Friends> selectFriendsByNumber(Map<String,Object> map);

	public void addMemo(Memo memo);

	public void updateMemo(Memo memo);

	public void deleteMemo(int memoId);

	public List<Memo> selectMemo(Map<String,Object> map);

	public Memo selectMemoById(int memeoId);

}
