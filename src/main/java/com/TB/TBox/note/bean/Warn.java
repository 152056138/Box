/**
 * 提醒字条类
 */
package com.TB.TBox.note.bean;

import java.util.Date;

public class Warn {
	private int wid; //提醒字条id
	private String wcintent; //提醒内容
	private Date wtime; //提醒时间
	private String wto; //被提醒人
	private int wfrom; //提醒人（此用户）
	
	//set-get
	public int getWid() {
		return wid;
	}
	public void setWid(int wid) {
		this.wid = wid;
	}
	public String getWcintent() {
		return wcintent;
	}
	public void setWcintent(String wcintent) {
		this.wcintent = wcintent;
	}
	public Date getWtime() {
		return wtime;
	}
	public void setWtime(Date wtime) {
		this.wtime = wtime;
	}
	public String getWto() {
		return wto;
	}
	public void setWto(String wto) {
		this.wto = wto;
	}
	public int getWfrom() {
		return wfrom;
	}
	public void setWfrom(int wfrom) {
		this.wfrom = wfrom;
	}
	
	
}
