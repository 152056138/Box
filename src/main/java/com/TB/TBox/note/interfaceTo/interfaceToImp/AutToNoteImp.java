package com.TB.TBox.note.interfaceTo.interfaceToImp;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.TB.TBox.note.bean.Authority;
import com.TB.TBox.note.bean.Note;
import com.TB.TBox.note.interfaceTo.IAutToNode;
import com.TB.TBox.note.service.AuthorityService;
import com.TB.TBox.note.service.NoteService;
import com.TB.TBox.user.interfaceTo.ToNodeInterface;

public class AutToNoteImp implements IAutToNode {
	@Autowired
	private NoteService noteService;
	@Autowired
	private AuthorityService authorityService;
	@Autowired
	private ToNodeInterface toNodeInterface;
	/**
	 * 判断用户对某亲友的字条是否有权限
	 */
	public List<Note> getAutNote(int friUid, String myuserNunber) {
		List<Note> autNoteList = new ArrayList<Note>();
		/*
		 * 获取要查看亲友的所有纸条
		 */
		List<Note> friNoteList = new ArrayList<Note>();
		friNoteList = noteService.schSbNoteLim(friUid);
		/*
		 * 遍历之,判断对纸条是否有权限，是就加入List返回
		 */
		for(Note note : friNoteList){
			//获取到权限关系集合
			List<Authority> authorityList = authorityService.schAutByid(note.getNoteId());
			//遍历权限关系集合，判断my是否有权限
			int flag = 1; //obvious==1时的标志位
			for(Authority authority : authorityList){
				//判断设置权限的方式
				int obvious = authority.getObvious();
				//其实对于同一字条的obvious都是一样的，进入循环后只会在if或else其中一个中循环判断
				if(obvious == 0){
					//获取对应纸条权限记录的friendNumber
					String friendNumber = authority.getFriendNumber();
					//对比查出的uid和当前是否相符，是则有权限note放入返回集合，结束此字条的权限判断；到最后都不符就结束继续下一轮；
					if(friendNumber.equals(myuserNunber)){
						autNoteList.add(note);
						break;
					}
					
				}
				else{
					//获取对应纸条权限记录的friendNumber
					String friendNumber = authority.getFriendNumber();
					//对比查出的uid和当前是否相符，是则没权限直接结束；到最后都不符则有权限,flag == 0；
					if(friendNumber.equals(myuserNunber)){
						break;
					}
					else{
						flag = 0;
					}
				}
			}
			//obvious==1时满足权限添加note进list
			if(flag == 0){
				autNoteList.add(note);
			}
		}
		return autNoteList;
	}

}
