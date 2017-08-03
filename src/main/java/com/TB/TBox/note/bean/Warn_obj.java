/**
 * 被提醒的对象表
 */
package com.TB.TBox.note.bean;

public class Warn_obj {
	private int wid; //提醒字条的id
	private String wto; //被提醒人（存在好友类别）
	private String wphone; //被提醒人（不存在好友列表）
	
	//set-get
	public int getWid() {
		return wid;
	}
	public void setWid(int wid) {
		this.wid = wid;
	}
	public String getWto() {
		return wto;
	}
	public void setWto(String wto) {
		this.wto = wto;
	}
	public String getWphone() {
		return wphone;
	}
	public void setWphone(String wphone) {
		this.wphone = wphone;
	}
	
}
