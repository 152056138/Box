/**
 * 纸条类控制层
 */

package com.TB.TBox.note.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartRequest;

import com.TB.TBox.dataBean.ImageResp;
import com.TB.TBox.dataUtils.FileUploadThread;
import com.TB.TBox.dataUtils.FileUploadUtil;
import com.TB.TBox.note.bean.Evaluate;
import com.TB.TBox.note.bean.Note;
import com.TB.TBox.note.interfaceTo.IAutToNode;
import com.TB.TBox.note.interfaceTo.interfaceToImp.AutToNoteImp;
import com.TB.TBox.note.service.EvaluateService;
import com.TB.TBox.note.service.NoteService;
import com.TB.TBox.user.bean.User;
import com.TB.TBox.user.interfaceTo.ToNodeInterface;
import com.TB.TBox.user.interfaceTo.interfaceToImp.ToNodeImp;
import com.TB.base.page.PageBean;
import com.TB.base.page.SystemContext;
import com.google.gson.Gson;

@Controller
@RequestMapping("/note")
@Scope("prototype")
public class NoteServlet {
	@Autowired
	private NoteService noteService;
	@Autowired
	private FileUploadUtil fileUtil;
	@Autowired
	private ImageResp image;
	@Autowired
	private IAutToNode autToNode ;
	@Autowired
	private ToNodeInterface toNodeInterface ;
	@Autowired
	EvaluateService evaluateService;

	/*
	 * 设置分页数据
	 */
	private Map<String, Object> setPageDate() {
		// 设置数据
		SystemContext.setPageSize(7);
		SystemContext.setOrder("desc");
		SystemContext.setSort("noteId");
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
	 * 发布纸条
	 * 
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping(value = "/addNote", method = RequestMethod.POST)
	public void addNote(HttpServletRequest request, HttpServletResponse response,  MultipartRequest re)
			throws IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		 List<String> b3List = new ArrayList<>();
		// 从前台接收数据
		User user = new User();
		int uid = Integer.parseInt(request.getParameter("uid"));
		user.setUid(uid);
		int mood = Integer.parseInt(request.getParameter("mood"));
		String noteAdout = request.getParameter("noteAdout");
		String noteContent = request.getParameter("noteContent");
		String friendNumberList = request.getParameter("friendNumberList");
		int obvious = Integer.parseInt(request.getParameter("obvious"));
		String locate = request.getParameter("locate");
		SimpleDateFormat sdt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String time = sdt.format(new Date());
		Note note = new Note(mood, noteAdout, noteContent, time, user,locate);

		 String userNumber = toNodeInterface.selectUserNumber(uid);

		//保存到数据库
		noteService.addNote(note);
		Map<String, Object> val = new HashMap<String, Object>();
		val.put("uid", uid);
		val.put("time", time);
		final int noteId = noteService.schNote(val);// 获得刚存储的字条的id，以存储图片,设置权限
		note.setNoteId(noteId);
		//设置权限
			autToNode.setAuthority(noteId, friendNumberList, obvious);
		// 接收图片数据
//			FileUploadThread ft=new FileUploadThread(re,userNumber,noteId);
//			ft.start();
//			b3List = ft.getB3List();
			b3List = fileUtil.MultiPartFileUpLoad( re,  userNumber,  noteId);
		for (String b3 : b3List) {
//			int index = b3.lastIndexOf(".");
//			b3 = b3.substring(0, index)+"_"+i+".jpg";
			image = new ImageResp(noteId, b3);
			noteService.addImage(image);
		}
		note.setImageList(b3List);
		// 数据响应到前台
		note.setImageList(b3List);
		response.setContentType("text/json");
		PrintWriter out = response.getWriter();
		out.print(note.toJson());
		out.flush();
		out.close();
	}

	/**
	 * 点赞方法
	 * 
	 * @throws IOException
	 */
	@RequestMapping(value = "/getGoodNum", method = RequestMethod.POST)
	public void getGoodNum(HttpServletRequest request, HttpServletResponse response) throws IOException {
		request.setCharacterEncoding("utf-8");
		// 获得当前纸条id
		int noteId = Integer.parseInt(request.getParameter("noteId"));
		String userNum = request.getParameter("userNum");//点赞用户
		Map<String, Object> val1 = new HashMap<String, Object>();
		val1.put("userNum", userNum);
		val1.put("noteId", noteId);
		int goodNum;
		//查找是否点过赞
		if(noteService.selgoodUser(val1)!=null){
			goodNum = noteService.schNotebyId(noteId).getGoodNum() - 1;
		}
		else{
			goodNum = noteService.schNotebyId(noteId).getGoodNum() + 1;
		}
		Map<String, Object> val = new HashMap<String, Object>();
		val.put("goodNum", goodNum);
		val.put("noteId", noteId);
		// 修改数据库
		noteService.updGoodNum(val);
		// 响应给前台
		Gson gson = new Gson();
		response.setContentType("text/json");
		PrintWriter out = response.getWriter();
		out.print(gson.toJson(goodNum));
		out.flush();
		out.close();
	}

	/**
	 * 扔鸡蛋方法
	 * 
	 * @throws IOException
	 */
	@RequestMapping(value = "/getEgg", method = RequestMethod.POST)
	public void getEgg(HttpServletRequest request, HttpServletResponse response) throws IOException {
		request.setCharacterEncoding("utf-8");
		// 获得当前点赞数和当前纸条id
		int noteId = Integer.parseInt(request.getParameter("noteId"));
		String userNum = request.getParameter("userNum");
		int egg = noteService.schNotebyId(noteId).getEgg() + 1;
		Map<String, Object> val = new HashMap<String, Object>();
		val.put("egg", egg);
		val.put("noteId", noteId);
		val.put("userNum", userNum);
		// 修改数据库
		noteService.updGoodNum(val);
		//添加点赞关联
		// 响应给前台
		Gson gson = new Gson();
		response.setContentType("text/json");
		PrintWriter out = response.getWriter();
		out.print(gson.toJson(egg));
		out.flush();
		out.close();

	}

	/**
	 * 显示用户的所有字条
	 * @param request
	 * @param response
	 * @throws IOException 
	 */
	@RequestMapping(value="/showMyAllNote", method = RequestMethod.POST)
	public void showMyAllNote(HttpServletRequest request,HttpServletResponse response) throws IOException{
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		/*
		 * 设置分页数据
		 */
		Map<String, Object> pageDate = setPageDate();
		int noteId = 0;
		noteId = Integer.parseInt(request.getParameter("noteId"));
		SystemContext.setPageOffset(noteId);
		int pageOffset = SystemContext.getPageOffset();
		pageDate.put("pageOffset", pageOffset);
		/*
		 * 获得查询id
		 */
		int uid = Integer.parseInt(request.getParameter("uid"));
		pageDate.put("uid", uid);
		//获得noteList
		PageBean<Note> notePage = noteService.schMyNoteall(pageDate);
		//设置纸条评回
		List<Note> noteList = notePage.getDatas();
		for(Note note : noteList){
			String usernum = note.getUser().getNumber();
			User user = new User();
			user.setNumber(usernum);
			note.setUser(user);
			List<Evaluate> evaluateLst = new ArrayList<>();
			evaluateLst = evaluateService.showEva(note.getNoteId());
			note.setEvaluate(evaluateLst);
		}
		//设置图片属性
		for(int i = 0;i < noteList.size();i++){
			noteList.get(i).setImageList(noteService.sehImage(noteList.get(i).getNoteId()));
		}
		/*
		 * 数据响应到前台
		 */
		Gson gson = new Gson();
		response.setContentType("text/json");
		PrintWriter out = response.getWriter();
		out.print(gson.toJson(noteList));
		out.flush();
		out.close();
	}
	
	/**
	 * 显示某个亲友的自己有权限看的字条
	 * @param request
	 * @param response
	 * @throws IOException 
	 */
	@RequestMapping(value="/showOneFriNote", method = RequestMethod.POST)
	public void showOneFriNote(HttpServletRequest request,HttpServletResponse response) throws IOException{
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		//获取要看的亲友的Uid和当前用户的账号和分页的“脚标”noteId
		String myuserNunber = request.getParameter("myuserNunber");
		int friUid = Integer.parseInt(request.getParameter("friUid"));
		int noteId = 0;
		noteId = Integer.parseInt(request.getParameter("noteId"));
		try {
			//调用接口得到可看的noteList
			List<Note> autNoteList = autToNode.getAutNote(friUid, myuserNunber,noteId);
			//设置纸条评回
			for(Note note : autNoteList){
				String usernum = note.getUser().getNumber();
				User user = new User();
				user.setNumber(usernum);
				note.setUser(user);
				List<Evaluate> evaluateLst = new ArrayList<>();
				evaluateLst = evaluateService.showEva(note.getNoteId());
				note.setEvaluate(evaluateLst);
			}
			//设置图片属性
			for(int i = 0;i < autNoteList.size();i++){
				autNoteList.get(i).setImageList(noteService.sehImage(autNoteList.get(i).getNoteId()));
			}
			//数据响应到前台
			Gson gson = new Gson();
			response.setContentType("text/json");
			PrintWriter out = response.getWriter();
			out.print(gson.toJson(autNoteList));
			out.flush();
			out.close();
		} catch (Exception e) {
			// TODO: handle exception
			//数据响应到前台
			Gson gson = new Gson();
			response.setContentType("text/json");
			PrintWriter out = response.getWriter();
			out.print("仅自己可见");
			out.flush();
			out.close();
		}
		
	}
	
	/**
	 * 显示所有亲友的自己有权限看的字条
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping(value="/showAllfriNote", method = RequestMethod.POST)
	public void showAllfriNote(HttpServletRequest request,HttpServletResponse response) throws IOException{
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		List<Note> allNoteList = new ArrayList<Note>();
		//获取当前用户的Uid和当前用户的账号和分页的“脚标”noteId
		int myUid = Integer.parseInt(request.getParameter("uid"));
		String myuserNunber = request.getParameter("myuserNunber");
		int noteId = Integer.parseInt(request.getParameter("noteId"));//人工分页的判断脚标
		//查出用户的所有好友uid

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("uid", myUid);
		map.put("recoverFriend", 0);
		List<Integer> friUidList = toNodeInterface.selectAllFriendUid(map) ;

		allNoteList = autToNode.getAllAutNote(friUidList, myuserNunber, noteId);
		//设置纸条评回
		for(Note note : allNoteList){
			String usernum = note.getUser().getNumber();
			User user = new User();
			user.setNumber(usernum);
			note.setUser(user);
			List<Evaluate> evaluateLst = new ArrayList<>();
			evaluateLst = evaluateService.showEva(note.getNoteId());
			note.setEvaluate(evaluateLst);
		}
		//设置图片属性
		for(int i = 0;i < allNoteList.size();i++){
			allNoteList.get(i).setImageList(noteService.sehImage(allNoteList.get(i).getNoteId()));

		}
//		//查出所有好友的有权限的noteList再集合为一个总的allNoteList
//		
//		List<Note> noteList = new ArrayList<Note>();
//		for(int friUid : friUidList){
//			noteList = autToNode.getAutNote(friUid, myuserNunber);
//			for(Note note :noteList){
//				allNoteList.add(note);
//			}
//		}
		//数据响应到前台
		Gson gson = new Gson();
		response.setContentType("text/json");
		PrintWriter out = response.getWriter();
		out.print(gson.toJson(allNoteList));
		out.flush();
		out.close();

	}
}
