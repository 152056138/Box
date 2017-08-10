/**
 * 漂流瓶字条类
 */
package com.TB.TBox.driftBottle.bean;

import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class Drift_note {
	private int driftId; // 主键id
	private String title; // 标题
	private String driftContent; // 发送内容
	private int sendId; // 发送者id
	private int identifier; // 样式编号
	private String driftTime;//漂流瓶开始时间
	private int checkTheQuantity;//查看量
	private int hate;//是否被厌恶
	private List<Drift_evaluate> drift_evaluateList;//评论集合

	// set-get
	
	public int getDriftId() {
		return driftId;
	}

	public List<Drift_evaluate> getDrift_evaluateList() {
		return drift_evaluateList;
	}

	public void setDrift_evaluateList(List<Drift_evaluate> drift_evaluateList) {
		this.drift_evaluateList = drift_evaluateList;
	}

	public int getCheckTheQuantity() {
		return checkTheQuantity;
	}

	public void setCheckTheQuantity(int checkTheQuantity) {
		this.checkTheQuantity = checkTheQuantity;
	}

	public int getHate() {
		return hate;
	}

	public void setHate(int hate) {
		this.hate = hate;
	}

	public String getDriftTime() {
		return driftTime;
	}

	public void setDriftTime(String driftTime) {
		this.driftTime = driftTime;
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

	public int getSendId() {
		return sendId;
	}

	public void setSendId(int sendId) {
		this.sendId = sendId;
	}

	public int getIdentifier() {
		return identifier;
	}

	public void setIdentifier(int identifier) {
		this.identifier = identifier;
	}

}
