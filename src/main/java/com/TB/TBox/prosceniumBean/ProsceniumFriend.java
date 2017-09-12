package com.TB.TBox.prosceniumBean;

import java.util.List;

import org.springframework.stereotype.Component;

import com.TB.TBox.user.bean.Memo;
import com.TB.TBox.user.bean.User;
@Component
public class ProsceniumFriend {
private String proId;
private String head;//头像
private String phone;//手机号
private String friendUsername;//备注
private List<Memo> memoList;

public String getProId() {
	return proId;
}
public void setProId(String proId) {
	this.proId = proId;
}
public String getHead() {
	return head;
}
public void setHead(String head) {
	this.head = head;
}
public String getPhone() {
	return phone;
}
public void setPhone(String phone) {
	this.phone = phone;
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
