/**
 * 权限类
 * 判定字条的可见权限
 * 每对应一个字条的权限关系，开头都会有一个fid为0的记录，此记录用来查找确认此字条是以什么方式（obvious）设置权限
 */
package com.TB.TBox.note.bean;

import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class Authority {
	public int noteId; //相关的字条id
	public List<Integer> fidList;  //此字段仅为接收前台供的id集合
	public Integer fid;   //是否可见用户id（可见用户or不可见用户，据obvious判定）
	public int obvious; //0：设置可见用户，其他用户不可见；1：设置不可见用户，其他用户可见
	
	//set-get
	
	public int getNoteId() {
		return noteId;
	}
	
	public Integer getFid() {
		return fid;
	}

	public void setFid(Integer fid) {
		this.fid = fid;
	}

	public void setNoteId(int noteId) {
		this.noteId = noteId;
	}
	
	public List<Integer> getFidList() {
		return fidList;
	}
	public void setFidList(List<Integer> fidList) {
		this.fidList = fidList;
	}
	public int getObvious() {
		return obvious;
	}
	public void setObvious(int obvious) {
		this.obvious = obvious;
	}
	

	
}
