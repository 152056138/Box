/**
 * 提醒字条类
 */
package com.TB.TBox.note.bean;

import java.util.Date;

import org.springframework.stereotype.Component;
@Component
public class Warn {
	private int wid; //提醒字条id
	private String wcontent; //提醒内容
	private String wtime; //提醒时间
	private String wto; //被提醒人
	private String wfrom; //提醒人（此用户）
	
	
	public Warn(){}
	
	public Warn(String wcintent, String wtime, String wto, String wfrom) {
		super();
		this.wcontent = wcintent;
		this.wtime = wtime;
		this.wto = wto;
		this.wfrom = wfrom;
	}
	//set-get
	public int getWid() {
		return wid;
	}
	public void setWid(int wid) {
		this.wid = wid;
	}
	public String getWcontent() {
		return wcontent;
	}
	public void setWcontent(String wcintent) {
		this.wcontent = wcintent;
	}
	public String getWtime() {
		return wtime;
	}
	public void setWtime(String wtime) {
		this.wtime = wtime;
	}
	public String getWto() {
		return wto;
	}
	public void setWto(String wto) {
		this.wto = wto;
	}
	public String getWfrom() {
		return wfrom;
	}
	public void setWfrom(String wfrom) {
		this.wfrom = wfrom;
	}
	
	
}
