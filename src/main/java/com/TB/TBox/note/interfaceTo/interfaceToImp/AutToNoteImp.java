package com.TB.TBox.note.interfaceTo.interfaceToImp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.TB.TBox.note.bean.Authority;
import com.TB.TBox.note.bean.Note;
import com.TB.TBox.note.interfaceTo.IAutToNode;
import com.TB.TBox.note.service.AuthorityService;
import com.TB.TBox.note.service.NoteService;
import com.TB.TBox.user.interfaceTo.ToNodeInterface;
import com.TB.TBox.user.interfaceTo.interfaceToImp.ToNodeImp;
import com.TB.base.page.PageBean;
import com.TB.base.page.SystemContext;
import com.alibaba.fastjson.JSON;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
@Component
public class AutToNoteImp implements IAutToNode {
	@Autowired
	private NoteService noteService;
	@Autowired
	private AuthorityService authorityService;
	@Autowired
	private ToNodeInterface toNodeInterface ;

	/*
	 * 设置分页数据
	 */
	private Map<String, Object> setPageDate() {
		// 设置数据
		SystemContext.setPageSize(7);
		SystemContext.setOrder("desc");
		SystemContext.setSort("id");
		int pageSize = SystemContext.getPageSize();
		String order = SystemContext.getOrder();
		String sort = SystemContext.getSort();
		// 将数据封装入map
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("pageSize", pageSize);
		map.put("order", order);
		map.put("sort", sort);
		return map;
	}

	/**
	 * 判断用户对某亲友的字条是否有权限
	 */
	public List<Note> getAutNote(int friUid, String myuserNunber, int noteId) {
		List<Note> autNoteList = new ArrayList<Note>();
		/*
		 * 获取要查看亲友的所有纸条
		 */

		Map<String, Object> pageDate = setPageDate();
		SystemContext.setPageOffset(noteId);
		int pageOffset = SystemContext.getPageOffset();
		pageDate.put("pageOffset", pageOffset);
		/*
		 * 获得查询id
		 */
		pageDate.put("uid", friUid);
		// 获得分页后的noteList
		PageBean<Note> notePage = noteService.schMyNoteall(pageDate);
		List<Note> friNoteList = notePage.getDatas();

		// List<Note> friNoteList = new ArrayList<Note>();
		// friNoteList = noteService.schSbNoteLim(friUid);
		/*
		 * 遍历之,判断对纸条是否有权限，是就加入List返回
		 */
		for (Note note : friNoteList) {
			// 获取到权限关系集合
			List<Authority> authorityList = authorityService.schAutByid(note.getNoteId());
			// 遍历权限关系集合，判断my是否有权限
			int flag = 1; // obvious==1时的标志位
			for (Authority authority : authorityList) {
				// 判断设置权限的方式
				int obvious = authority.getObvious();
				// 其实对于同一字条的obvious都是一样的，进入循环后只会在if或else其中一个中循环判断
				if (obvious == 0) {
					// 获取对应纸条权限记录的friendNumber
					String friendNumber = authority.getFriendNumber();
					// 对比查出的userNumber和当前是否相符，是则有权限note放入返回集合，结束此字条的权限判断；到最后都不符就结束继续下一轮；
					if (friendNumber.equals(myuserNunber)) {
						autNoteList.add(note);
						break;
					}

				} else {
					// 获取对应纸条权限记录的friendNumber
					String friendNumber = authority.getFriendNumber();
					// 对比查出的uid和当前是否相符，是则没权限直接结束；到最后都不符则有权限,flag == 0；
					if (friendNumber.equals(myuserNunber)) {
						break;
					} else {
						flag = 0;
					}
				}
			}
			// obvious==1时满足权限添加note进list
			if (flag == 0) {
				autNoteList.add(note);
			}
		}
		return autNoteList;
	}

	/**
	 * 得到用户对全部亲友有权限的字条
	 */
	public List<Note> getAllAutNote(List<Integer> friUidList, String myuserNunber, int noteId) {
		List<Note> allNoteList = new ArrayList<Note>();
		List<Note> autNoteList = new ArrayList<Note>();
		/*
		 * 获取要查看亲友的所有纸条
		 */
		allNoteList = noteService.schAllNote();
		for (Note note : allNoteList) {
			for (int friUid : friUidList) {
				if (friUid == note.getUser().getUid()) {
					// 获取到权限关系集合
					List<Authority> authorityList = authorityService.schAutByid(note.getNoteId());
					// 遍历权限关系集合，判断my是否有权限
					int flag = 1; // obvious==1时的标志位
					for (Authority authority : authorityList) {
						// 判断设置权限的方式
						int obvious = authority.getObvious();
						// 其实对于同一字条的obvious都是一样的，进入循环后只会在if或else其中一个中循环判断
						if (obvious == 0) {
							// 获取对应纸条权限记录的friendNumber
							String friendNumber = authority.getFriendNumber();
							// 对比查出的userNumber和当前是否相符，是则有权限note放入返回集合，结束此字条的权限判断；到最后都不符就结束继续下一轮；
							if (friendNumber.equals(myuserNunber)) {
								autNoteList.add(note);
								break;
							}

						} else {
							// 获取对应纸条权限记录的friendNumber
							String friendNumber = authority.getFriendNumber();
							// 对比查出的uid和当前是否相符，是则没权限直接结束；到最后都不符则有权限,flag == 0；
							if (friendNumber.equals(myuserNunber)) {
								break;
							} else {
								flag = 0;
							}
						}
					}
					// obvious==1时满足权限添加note进list
					if (flag == 0) {
						autNoteList.add(note);
					}
				}
			}
		}

		//
		//
		// Map<String, Object> pageDate = setPageDate();
		// SystemContext.setPageOffset(noteId);
		// int pageOffset = SystemContext.getPageOffset();
		// pageDate.put("pageOffset", pageOffset);
		// /*
		// * 获得查询id
		// */
		// pageDate.put("uid", friUid);
		// //获得分页后的noteList
		// PageBean<Note> notePage = noteService.schMyNoteall(pageDate);
		// List<Note> friNoteList = notePage.getDatas();
//===========================================================================
		//
		//// List<Note> friNoteList = new ArrayList<Note>();
		//// friNoteList = noteService.schSbNoteLim(friUid);
		// /*
		// * 遍历之,判断对纸条是否有权限，是就加入List返回
		// */
		// for(Note note : friNoteList){
		// //获取到权限关系集合
		// List<Authority> authorityList =
		// authorityService.schAutByid(note.getNoteId());
		// //遍历权限关系集合，判断my是否有权限
		// int flag = 1; //obvious==1时的标志位
		// for(Authority authority : authorityList){
		// //判断设置权限的方式
		// int obvious = authority.getObvious();
		// //其实对于同一字条的obvious都是一样的，进入循环后只会在if或else其中一个中循环判断
		// if(obvious == 0){
		// //获取对应纸条权限记录的friendNumber
		// String friendNumber = authority.getFriendNumber();
		// //对比查出的userNumber和当前是否相符，是则有权限note放入返回集合，结束此字条的权限判断；到最后都不符就结束继续下一轮；
		// if(friendNumber.equals(myuserNunber)){
		// autNoteList.add(note);
		// break;
		// }
		//
		// }
		// else{
		// //获取对应纸条权限记录的friendNumber
		// String friendNumber = authority.getFriendNumber();
		// //对比查出的uid和当前是否相符，是则没权限直接结束；到最后都不符则有权限,flag == 0；
		// if(friendNumber.equals(myuserNunber)){
		// break;
		// }
		// else{
		// flag = 0;
		// }
		// }
		// }
		// //obvious==1时满足权限添加note进list
		// if(flag == 0){
		// autNoteList.add(note);
		// }
		// }
		return autNoteList;
	}

	/**
	 * 设置权限
	 */
	public void setAuthority(int noteId, String friendNumber, int obvious) {
		/*
		 * 接收数据
		 */
		// 将json字串转换为list
		Gson gson = new Gson();
		// 将json中的fid数组取出以其类对象的形式保存
		List<String> autList = JSON.parseArray(friendNumber, String.class);
		// 定义和fidList等长的List存储权限类,来批量插入
		List<Authority> authorityList = new ArrayList<Authority>();
		for (int i = 0; i < autList.size(); i++) {
			// if(i==0){
			// Authority authority = new
			// Authority();//需每次循环都新建一个对象，不然从头到尾都只是改变一个对象的值
			// //每对应一个字条的权限关系，开头都会有一个FriendNumber为0-0的记录，此记录用来查找确认此字条是以什么方式（obvious）设置权限
			// authority.setFriendNumber("0-0");;
			// authority.setNoteId(noteId);
			// authority.setObvious(obvious);
			// authorityList.add(authority);
			// continue;
			// }
			Authority authority = new Authority();
			authority.setFriendNumber(autList.get(i));
			authority.setNoteId(noteId);
			authority.setObvious(obvious);
			authorityList.add(authority);
		}
		/*
		 * 存储数据库
		 */
		for(Authority a : authorityList){
			authorityService.setAut(a);
		}

	}

}
