/**
 * 提醒字条类
 */
package com.TB.TBox.note.bean;

import java.util.Date;

import org.springframework.stereotype.Component;
@Component
public class Warn {
	private int wid; //提醒字条id
	private String wcintent; //提醒内容
	private String wtime; //提醒时间
	private String wto; //被提醒人
	private int wfrom; //提醒人（此用户）
	private String wphone;
	private int status;//没有提醒为0，触发提醒为1，提醒完毕为2
	
	public Warn(){}
	
	
	public Warn( String wcontent, String wtime, String wto, int wfrom, String wphone, int status) {
		super();
		
		this.wcintent = wcontent;
		this.wtime = wtime;
		this.wto = wto;
		this.wfrom = wfrom;
		this.wphone = wphone;
		this.status = status;
	}


	public String getWphone() {
		return wphone;
	}

	public void setWphone(String wphone) {
		this.wphone = wphone;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	//set-get
	public int getWid() {
		return wid;
	}
	public void setWid(int wid) {
		this.wid = wid;
	}
	public String getWcontent() {
		return wcintent;
	}
	public void setWcontent(String wcintent) {
		this.wcintent = wcintent;
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

	public int getWfrom() {
		return wfrom;
	}

	public void setWfrom(int wfrom) {
		this.wfrom = wfrom;
	}
	
	
	
}
