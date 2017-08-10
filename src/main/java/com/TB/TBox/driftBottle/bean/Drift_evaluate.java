package com.TB.TBox.driftBottle.bean;

import org.springframework.stereotype.Component;

@Component
public class Drift_evaluate {
private int drif_evaluateId;
private int drifCommentId;
private String drifCommentTime;
private int drifIfObv;
private String drifContent;
private int driftId;

//get-set
public int getDrif_evaluateId() {
	return drif_evaluateId;
}
public void setDrif_evaluateId(int drif_evaluateId) {
	this.drif_evaluateId = drif_evaluateId;
}

public int getDrifCommentId() {
	return drifCommentId;
}
public void setDrifCommentId(int drifCommentId) {
	this.drifCommentId = drifCommentId;
}
public String getDirfCommentTime() {
	return drifCommentTime;
}
public void setDirfCommentTime(String dirfCommentTime) {
	this.drifCommentTime = dirfCommentTime;
}
public int getDrifIfObv() {
	return drifIfObv;
}
public void setDrifIfObv(int drifIfObv) {
	this.drifIfObv = drifIfObv;
}
public String getDrifContent() {
	return drifContent;
}
public void setDrifContent(String drifContent) {
	this.drifContent = drifContent;
}
public int getDriftId() {
	return driftId;
}
public void setDriftId(int driftId) {
	this.driftId = driftId;
}



}
