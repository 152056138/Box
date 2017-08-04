/**
 * note类的映射器
 */
package com.TB.TBox.note.mapper;

import java.util.List;

import com.TB.TBox.note.bean.Note;

public interface NoteMapper {
	/*
	 * 添加纸条
	 */
	public void addNote(Note note);
	
	/*
	 * 删除纸条
	 */
	public void delNotebyId(int noteId);
	
	
	/*
	 * 查看纸条
	 */
	//显示全部
	public List<Note> schNoteall();
	//按id查找
	public Note schNotebyId(int noteId);
	
}
