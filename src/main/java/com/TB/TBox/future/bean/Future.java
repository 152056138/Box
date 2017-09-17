package com.TB.TBox.future.bean;

import org.springframework.stereotype.Component;

@Component
public class Future {
private int aid;//主键
private int afrom;//用户的uid
private String afterAcontent;//内容
private String abegin;//开始时间
private String aend;//推送时间
private int astatus;//0代表还没有推送，1代表已经推送了,2代表查询完毕

//get-set
public int getAid() {
	return aid;
}
public void setAid(int aid) {
	this.aid = aid;
}
public int getAfrom() {
	return afrom;
}
public void setAfrom(int afrom) {
	this.afrom = afrom;
}
public String getAfterAcontent() {
	return afterAcontent;
}
public void setAfterAcontent(String afterAcontent) {
	this.afterAcontent = afterAcontent;
}
public String getAbegin() {
	return abegin;
}
public void setAbegin(String abegin) {
	this.abegin = abegin;
}
public String getAend() {
	return aend;
}
public void setAend(String aend) {
	this.aend = aend;
}
public int getAstatus() {
	return astatus;
}
public void setAstatus(int astatus) {
	this.astatus = astatus;
}




}
