/**
 * note类的映射器
 */
package com.TB.TBox.note.mapper;

import java.util.List;
import java.util.Map;

import com.TB.TBox.dataBean.ImageResp;
import com.TB.TBox.note.bean.Good;
import com.TB.TBox.note.bean.Note;

public interface NoteMapper {
	/**
	 * 添加纸条
	 */
	public void addNote(Note note);
	
	/**
	 * 修改点赞数
	 */
	public void updgoodNum(Map<String, Object> val);
	
	/**
	 * 修改扔鸡蛋数
	 */
	public void updegg(Map<String, Object> val);
	
	/**
	 * 删除纸条
	 */
	public void delNotebyId(int noteId);
	
	
	/**
	 * 查看纸条
	 */
	//按分页查询我的所有字条
	public List<Note> schMyNoteall(Map<String, Object> val);
	
	//不按分页查询某人的所有字条
	public List<Note> schSbNoteall(int uid);
	
	//不按分页查询所有纸条
	public List<Note> schAllNote();
	
	//按id查找
	public Note schNotebyId(int noteId);
	
	//按uid和time查找noteid
	public int schnote(Map<String, Object> val);
	
	
	
	/**
	 * 储存图片
	 */
	public void addpho(ImageResp image);
	
	/**
	 * 查找图片
	 */
	public List<String> selImage(int noteId);
	/**
	 * 添加关联的点赞用户
	 * @param val noteId 和 userNum
	 */
	public void updgoodUser(Map<String, Object> val);
	/**
	 * 遍历得到相应纸条点赞用户
	 * @param val noteId 和 userNum
	 * @return
	 */
	public Good schgoodUser(Map<String, Object> val);
}
