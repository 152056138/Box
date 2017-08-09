/**
 * 图片库类
 */
package com.TB.TBox.dataBean;

import org.springframework.stereotype.Component;

@Component
public class ImageResp {
	private int noteId; //对应纸条id
	private String image;//存的图片
	
	public ImageResp(){}
	public ImageResp(int noteId, String image) {
		super();
		this.noteId = noteId;
		this.image = image;
	}
	//set-get
	public int getNoteId() {
		return noteId;
	}
	public void setNoteId(int noteId) {
		this.noteId = noteId;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	
	
}
