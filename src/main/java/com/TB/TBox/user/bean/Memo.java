package com.TB.TBox.user.bean;

import org.springframework.stereotype.Component;

import com.google.gson.Gson;
@Component
public class Memo {
private int memoId;
private int uid;
private int fid;
private String memoName;
private String friendContent;

private static Gson gson = new Gson();

//json序列化
public String toJson(){
	return gson.toJson(this);
}

public int getMemoId() {
	return memoId;
}

public void setMemoId(int memoId) {
	this.memoId = memoId;
}

public int getUid() {
	return uid;
}

public void setUid(int uid) {
	this.uid = uid;
}

public int getFid() {
	return fid;
}

public void setFid(int fid) {
	this.fid = fid;
}

public String getMemoName() {
	return memoName;
}

public void setMemoName(String memoName) {
	this.memoName = memoName;
}

public String getFriendContent() {
	return friendContent;
}

public void setFriendContent(String friendContent) {
	this.friendContent = friendContent;
}

}
