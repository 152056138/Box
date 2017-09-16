/**
 * 每个字条的评回类
 */
package com.TB.TBox.note.bean;

import java.util.Date;


import org.springframework.stereotype.Component;

import com.google.gson.Gson;
@Component
public class Evaluate {
	private int eid; //评回id
	private int noteId; //被评纸条id
	private int replyId; //回复人id
	private int commentId; //评论人id
	private int ifObv; //是否匿名0：是  1：否
	private String commentTime; //评回时间
	private String econtent; //评回内容
	private int eflag; //标志位 1：为评价纸条 2：为回复评价的纸条 3：为回复回复的纸条
	private String commentNum; //评论人账号
	private String replyNum; // 回复人账号
	private int replyEid; //被回复的评回id
	
	
	public Evaluate(){}
	
	public Evaluate(int noteId, int replyId, int commentId,String commentNum,String replyNum, int ifObv, String commentTime, String econtent, int eflag,int replyEid) {
		super();
		this.noteId = noteId;
		this.replyId = replyId;
		this.commentId = commentId;
		this.ifObv = ifObv;
		this.commentTime = commentTime;
		this.econtent = econtent;
		this.eflag = eflag;
		this.commentNum = commentNum;
		this.replyNum = replyNum;
		this.replyEid = replyEid;
	}
	//set-get
	
	public int getEid() {
		return eid;
	}

	public int getReplyEid() {
		return replyEid;
	}

	public void setReplyEid(int replyEid) {
		this.replyEid = replyEid;
	}

	public String getCommentNum() {
		return commentNum;
	}

	public void setCommentNum(String commentNum) {
		this.commentNum = commentNum;
	}

	public String getReplyNum() {
		return replyNum;
	}

	public void setReplyNum(String replyNum) {
		this.replyNum = replyNum;
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
	public String getCommentTime() {
		return commentTime;
	}
	public void setCommentTime(String commentTime) {
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
	
	public String toJson(){
		 Gson gson = new Gson();
		return gson.toJson(this);
	}
	
}
