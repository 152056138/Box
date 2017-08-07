package com.TB.TBox.user.mapper;

import java.util.List;
import java.util.Map;

import com.TB.TBox.user.bean.Friends;
import com.TB.TBox.user.bean.Memo;

public interface FriendMapper {
	public void addFriend(Friends friend);

	public List<Friends> selectAllFriends(Map map);

	public void updateFriendName(Friends friend);

	public void deleteFriend(Friends friend);

	public Friends selectFriendByFid(int fid);

	public List<Friends> selectFriendsByNickname(Map map);

	public List<Friends> selectFriendsByUsername(Map map);

	public List<Friends> selectFriendsByNumber(Map map);

	public void addMemo(Memo memo);

	public void updateMemo(Memo memo);

	public void deleteMemo(int memoId);

	public List<Memo> selectMemo(Map map);

	public Memo selectMemoById(int memeoId);

}
