/**
 * 漂流瓶字条类
 */
package com.TB.TBox.driftBottle.bean;

import java.util.Date;

public class Drift_note {
	private int driftId; //主键id
	private String title; //标题
	private String driftContent; //发送内容
	private String dcontent; //回复内容
	private int sendId; //发送者id
	private int receiveId; //受到者id
	private Date driftTime; //评回或发送瓶子的时间（据dflag而定）
	private int dflag; //决定评回或发送瓶子的时间 0：发瓶   1：评回
	private int orObv; //是否匿名 0：是 1：否
	private String identifier; //样式编号
	
	
	//set-get
	public int getDriftId() {
		return driftId;
	}
	public void setDriftId(int driftId) {
		this.driftId = driftId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDriftContent() {
		return driftContent;
	}
	public void setDriftContent(String driftContent) {
		this.driftContent = driftContent;
	}
	public String getDcontent() {
		return dcontent;
	}
	public void setDcontent(String dcontent) {
		this.dcontent = dcontent;
	}
	public int getSendId() {
		return sendId;
	}
	public void setSendId(int sendId) {
		this.sendId = sendId;
	}
	public int getReceiveId() {
		return receiveId;
	}
	public void setReceiveId(int receiveId) {
		this.receiveId = receiveId;
	}
	public Date getDriftTime() {
		return driftTime;
	}
	public void setDriftTime(Date driftTime) {
		this.driftTime = driftTime;
	}
	public int getDflag() {
		return dflag;
	}
	public void setDflag(int dflag) {
		this.dflag = dflag;
	}
	public int getOrObv() {
		return orObv;
	}
	public void setOrObv(int orObv) {
		this.orObv = orObv;
	}
	public String getIdentifier() {
		return identifier;
	}
	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}
	
	
}
