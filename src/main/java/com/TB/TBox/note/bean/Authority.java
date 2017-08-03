/**
 * 权限类
 * 判定字条的可见权限
 */
package com.TB.TBox.note.bean;

public class Authority {
	public int noteId; //相关的字条id
	public String fid; //是否可见用户id（可见用户or不可见用户，据obvious判定）
	public int obvious; //0：设置可见用户，其他用户不可见；1：设置不可见用户，其他用户可见
	
	
	//set-get
	public int getNoteId() {
		return noteId;
	}
	public void setNoteId(int noteId) {
		this.noteId = noteId;
	}
	public String getFid() {
		return fid;
	}
	public void setFid(String fid) {
		this.fid = fid;
	}
	public int getObvious() {
		return obvious;
	}
	public void setObvious(int obvious) {
		this.obvious = obvious;
	}
	

	
}
