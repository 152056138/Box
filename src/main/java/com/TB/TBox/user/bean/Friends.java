/**
 * 好友列表类
 */
package com.TB.TBox.user.bean;

import java.sql.Blob;
import java.util.Date;

public class Friends {
	private int fid; //好友列表id 主键
	private String friendNumber; //好友账号
	private String friendUsername; //好友名称
	private int cid; //所在分类id
	private Date friendTime; //加为好友时间
	private String friendNickname; //好友昵称
	private Blob facing; //好友头像
	private int uid; //好友列表拥有者id（此用户）
	private int recoverFriend; //删除好友是不会真正的删除数据，而是此标志位变化表示
	
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
	public Date getFriendTime() {
		return friendTime;
	}
	public void setFriendTime(Date friendTime) {
		this.friendTime = friendTime;
	}
	public String getFriendNickname() {
		return friendNickname;
	}
	public void setFriendNickname(String friendNickname) {
		this.friendNickname = friendNickname;
	}
	public Blob getFacing() {
		return facing;
	}
	public void setFacing(Blob facing) {
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
	
}
