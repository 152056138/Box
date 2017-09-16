/*
 * 权限类对纸条类接口
 */
package com.TB.TBox.note.interfaceTo;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Component;

import com.TB.TBox.note.bean.Note;
@Component
public interface IAutToNode {
	/**
	 * 判断用户对某亲友的字条是否有权限
	 * 并返回有权限的字条集合
	 */
	public List<Note> getAutNote(int friUid,String myuserNunber,int noteId);
	
	/**
	 * 因为要获取全部有权限的note，而又不能通过获取friUid的方式遍历判断获取，
	 * 因为字条要根据时间显示，每个人是字条交错发布的，但是跟时间走的（noteId）
	 * 所以获取全部后在判断那条是不是朋友的同时又有权限的
	 */
	public List<Note> getAllAutNote(List<Integer> friUidList,String myuserNunber,int noteId);
	/**
	 * 设置权限
	 */
	public void setAuthority(int noteId,String friendNumberList,int obvious);
}
