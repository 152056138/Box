/**
 * 好友列表类
 */
package com.TB.TBox.user.bean;

import com.google.gson.Gson;

public class Friends {
	private int fid; //好友列表id 主键
	private String friendNumber; //好友账号
	private String friendUsername; //好友名称
	private int cid; //所在分类id
	private String friendTime; //加为好友时间
	private String friendNickname; //好友昵称
	private byte[] facing; //好友头像
	private int uid; //好友列表拥有者id（此用户）
	private int recoverFriend; //删除好友是不会真正的删除数据，而是此标志位变化表示
	private String friendContent;//用户给好友的便签，用来记录一些事情
	
	private static Gson gson = new Gson();
	
	//json序列化
	public String toJson(){
		return gson.toJson(this);
	}
	//set-get
	public int getFid() {
		return fid;
	}
	public void setFid(int fid) {
		this.fid = fid;
	}
	public String getFriendNumber() {
		return friendNumber;
	}
	public void setFriendNumber(String friendNumber) {
		this.friendNumber = friendNumber;
	}
	public String getFriendUsername() {
		return friendUsername;
	}
	public void setFriendUsername(String friendUsername) {
		this.friendUsername = friendUsername;
	}
	public int getCid() {
		return cid;
	}
	public void setCid(int cid) {
		this.cid = cid;
	}
	public String getFriendTime() {
		return friendTime;
	}
	public void setFriendTime(String friendTime) {
		this.friendTime = friendTime;
	}
	public String getFriendNickname() {
		return friendNickname;
	}
	public void setFriendNickname(String friendNickname) {
		this.friendNickname = friendNickname;
	}
	public byte[] getFacing() {
		return facing;
	}
	public void setFacing(byte[] facing) {
		this.facing = facing;
	}
	public int getUid() {
		return uid;
	}
	public void setUid(int uid) {
		this.uid = uid;
	}
	public int getRecoverFriend() {
		return recoverFriend;
	}
	public void setRecoverFriend(int recoverFriend) {
		this.recoverFriend = recoverFriend;
	}
	public String getFriendContent() {
		return friendContent;
	}
	public void setFriendContent(String friendContent) {
		this.friendContent = friendContent;
	}
	
}
