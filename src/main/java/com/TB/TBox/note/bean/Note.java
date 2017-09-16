/**
 * 纸条类
 */
package com.TB.TBox.note.bean;

import java.util.List;

import org.springframework.stereotype.Component;

import com.TB.TBox.user.bean.User;
import com.google.gson.Gson;

@Component
public class Note {
	private int noteId; //纸条id
	private int mood; //此刻的心情
	private String noteAdout; //纸条关于谁
	private String noteContent; //内容
	private List<String> imageList; //此字段仅作为数据响应到前台
	private String time; //发布时间
	private int goodNum; //点赞数
	private int egg; //扔鸡蛋数
	private User user; // 写纸条的人
	private int highOpinion; //好评量
	private int lowOpinion; //坏评量
	private String locate;//发布纸条时的所在地
	private int opinionNumber; //评论总人数
	private List<Evaluate> evaluate; //相关评回
	
	private static Gson gson = new Gson();
	
	//构造函数
	public Note(){}
	
	public Note(int mood, String noteAdout, String noteContent, String time,
			 User user,String locate) {
		super();
		this.mood = mood;
		this.noteAdout = noteAdout;
		this.noteContent = noteContent;
		this.time = time;
		this.user = user;
		this.locate = locate;
	}

	//set-get
	
	public int getNoteId() {
		return noteId;
	}
	public String getLocate() {
		return locate;
	}

	public void setLocate(String locate) {
		this.locate = locate;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<Evaluate> getEvaluate() {
		return evaluate;
	}
	public void setEvaluate(List<Evaluate> evaluate) {
		this.evaluate = evaluate;
	}
	public List<String> getImageList() {
		return imageList;
	}
	public void setImageList(List<String> imageList) {
		this.imageList = imageList;
	}
	public void setNoteId(int noteId) {
		this.noteId = noteId;
	}
	public int getMood() {
		return mood;
	}
	public void setMood(int mood) {
		this.mood = mood;
	}
	public String getNoteAdout() {
		return noteAdout;
	}
	public void setNoteAdout(String noteAdout) {
		this.noteAdout = noteAdout;
	}
	public String getNoteContent() {
		return noteContent;
	}
	public void setNoteContent(String noteContent) {
		this.noteContent = noteContent;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public int getGoodNum() {
		return goodNum;
	}
	public void setGoodNum(int goodNum) {
		this.goodNum = goodNum;
	}
	public int getEgg() {
		return egg;
	}
	public void setEgg(int egg) {
		this.egg = egg;
	}
	
	public int getHighOpinion() {
		return highOpinion;
	}
	public void setHighOpinion(int highOpinion) {
		this.highOpinion = highOpinion;
	}
	public int getLowOpinion() {
		return lowOpinion;
	}
	public void setLowOpinion(int lowOpinion) {
		this.lowOpinion = lowOpinion;
	}
	public int getOpinionNumber() {
		return opinionNumber;
	}
	public void setOpinionNumber(int opinionNumber) {
		this.opinionNumber = opinionNumber;
	}
	/**
	 * 转化数据为json
	 * @return
	 */
	public String toJson(){
		return gson.toJson(this);
	}
}
