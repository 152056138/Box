package com.TB.TBox.push.bean;

import org.springframework.stereotype.Component;

@Component
public class PushMsg {
private int uid;
private String channelId;
public int getUid() {
	return uid;
}
public void setUid(int uid) {
	this.uid = uid;
}
public String getChannelId() {
	return channelId;
}
public void setChannelId(String channelId) {
	this.channelId = channelId;
}


}
