/**
 * 每个字条的评回类
 */
package com.TB.TBox.note.bean;

import java.util.Date;

import org.springframework.stereotype.Component;
public class Evaluate {
	private int eid; //评回id
	private int noteId; //被评纸条id
	private int replyId; //回复人id
	private int commentId; //评论人id
	private int ifObv; //是否匿名0：是  1：否
	private Date commentTime; //评回时间
	private String econtent; //评回内容
	private int eflag; //标志位 1：为评价纸条 2：为回复评价的纸条 3：为回复回复的纸条
	
	
	//set-get
	public int getEid() {
		return eid;
	}
	public void setEid(int eid) {
		this.eid = eid;
	}
	public int getNoteId() {
		return noteId;
	}
	public void setNoteId(int noteId) {
		this.noteId = noteId;
	}
	public int getReplyId() {
		return replyId;
	}
	public void setReplyId(int replyId) {
		this.replyId = replyId;
	}
	public int getCommentId() {
		return commentId;
	}
	public void setCommentId(int commentId) {
		this.commentId = commentId;
	}
	public int getIfObv() {
		return ifObv;
	}
	public void setIfObv(int ifObv) {
		this.ifObv = ifObv;
	}
	public Date getCommentTime() {
		return commentTime;
	}
	public void setCommentTime(Date commentTime) {
		this.commentTime = commentTime;
	}
	public String getEcontent() {
		return econtent;
	}
	public void setEcontent(String econtent) {
		this.econtent = econtent;
	}
	public int getEflag() {
		return eflag;
	}
	public void setEflag(int eflag) {
		this.eflag = eflag;
	}
	
	
}
