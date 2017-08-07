package com.TB.TBox.prosceniumBean;

import java.util.List;

import org.springframework.stereotype.Component;

import com.TB.TBox.user.bean.Memo;
import com.TB.TBox.user.bean.User;
@Component
public class ProsceniumFriend {
private User user;
private String friendUsername;
private List<Memo> memoList;
public User getUser() {
	return user;
}
public void setUser(User user) {
	this.user = user;
}
public String getFriendUsername() {
	return friendUsername;
}
public void setFriendUsername(String friendUsername) {
	this.friendUsername = friendUsername;
}
public List<Memo> getMemoList() {
	return memoList;
}
public void setMemoList(List<Memo> memoList) {
	this.memoList = memoList;
}

}
