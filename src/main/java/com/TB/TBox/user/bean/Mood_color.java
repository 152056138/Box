/**
 * 用户心情-颜色设定类
 */
package com.TB.TBox.user.bean;

import org.springframework.stereotype.Component;

@Component
public class Mood_color {
	private int uid; //用户id
	private String happy;//开心
	private String angry; //生气
	private String sad; //伤心
	private String scard; //害怕
	private String commen; //一般
	
	//set-get
	public int getUid() {
		return uid;
	}
	public void setUid(int uid) {
		this.uid = uid;
	}
	public String getHappy() {
		return happy;
	}
	public void setHappy(String happy) {
		this.happy = happy;
	}
	public String getAngry() {
		return angry;
	}
	public void setAngry(String angry) {
		this.angry = angry;
	}
	public String getSad() {
		return sad;
	}
	public void setSad(String sad) {
		this.sad = sad;
	}
	public String getScard() {
		return scard;
	}
	public void setScard(String scard) {
		this.scard = scard;
	}
	public String getCommen() {
		return commen;
	}
	public void setCommen(String commen) {
		this.commen = commen;
	}
	
	
}
