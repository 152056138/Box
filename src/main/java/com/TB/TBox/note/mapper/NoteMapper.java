/**
 * note类的映射器
 */
package com.TB.TBox.note.mapper;

import java.util.List;
import java.util.Map;

import com.TB.TBox.dataBean.ImageResp;
import com.TB.TBox.note.bean.Note;

public interface NoteMapper {
	/*
	 * 添加纸条
	 */
	public void addNote(Note note);
	
	/*
	 * 修改点赞数
	 */
	public void updgoodNum(Map<String, Object> val);
	
	/*
	 * 修改扔鸡蛋数
	 */
	public void updegg(Map<String, Object> val);
	
	/*
	 * 删除纸条
	 */
	public void delNotebyId(int noteId);
	
	
	/*
	 * 查看纸条
	 */
	//查询我的所有字条
	public List<Note> schMyNoteall(int uid);
	//按id查找
	public Note schNotebyId(int noteId);
	//按uid和time查找noteid
	public int schnote(Map<String, Object> val);
	
	/*
	 * 储存图片
	 */
	public void addpho(ImageResp image);
	
	/*
	 * 查找图片
	 */
	public List<byte[]> selImage(int noteId);
}
