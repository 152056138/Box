/*
 * 权限类对纸条类接口
 */
package com.TB.TBox.note.interfaceTo;

import java.util.List;

import com.TB.TBox.note.bean.Note;

public interface IAutToNode {
	/*
	 * 判断用户对某亲友的字条是否有权限
	 * 并返回有权限的字条集合
	 */
	public List<Note> getAutNote(int friUid,String myuserNunber);
}
