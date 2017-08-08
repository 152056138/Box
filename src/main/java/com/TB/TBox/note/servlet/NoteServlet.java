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
import com.TB.TBox.dataUtils.FileUploadUtil;
import com.TB.TBox.note.bean.Note;
import com.TB.TBox.note.interfaceTo.IAutToNode;
import com.TB.TBox.note.interfaceTo.interfaceToImp.AutToNoteImp;
import com.TB.TBox.note.service.NoteService;
import com.TB.base.page.PageBean;
import com.TB.base.page.SystemContext;
import com.google.gson.Gson;

@Controller
@RequestMapping("/noteServlet")
@Scope("prototype")
public class NoteServlet {
	@Autowired
	private NoteService noteService;
	@Autowired
	private FileUploadUtil fileUtil;
	@Autowired
	private ImageResp image;
	
	private IAutToNode autToNode = new AutToNoteImp();

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
	 * 发布纸条
	 * 
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping(value = "/addNote", method = RequestMethod.POST)
	public void addNote(HttpServletRequest request, HttpServletResponse response, MultipartRequest re)
			throws IOException {
		List<byte[]> b3List = null;
		// 从前台接收数据
		int uid = Integer.parseInt(request.getParameter("uid"));
		int mood = Integer.parseInt(request.getParameter("mood"));
		String noteAdout = request.getParameter("noteAdout");
		String noteContent = request.getParameter("noteContent");
		SimpleDateFormat sdt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String time = sdt.format(new Date());
		Note note = new Note(mood, noteAdout, noteContent, time, uid);
		// 接收图片数据
		try {
			b3List = fileUtil.MultiPartFileUpLoad(re);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
<<<<<<< HEAD
		// 保存到数据库
=======

		//保存到数据库
>>>>>>> 4658790eef2b75a140b15b9dc542a0ede78ed0fb
		noteService.addNote(note);
		Map<String, Object> val = new HashMap<String, Object>();
		val.put("uid", uid);
		val.put("time", time);
		int noteId = noteService.schNote(val);// 获得刚存储的字条的id，以存储图片
		for (byte[] b3 : b3List) {
			image = new ImageResp(noteId, b3);
			noteService.addImage(image);
		}
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
		// 获得当前点赞数和当前纸条id
		int goodNum = Integer.parseInt(request.getParameter("goodNum")) + 1;
		int noteId = Integer.parseInt(request.getParameter("noteId"));
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
		// 获得当前点赞数和当前纸条id
		int egg = Integer.parseInt(request.getParameter("egg")) + 1;
		int noteId = Integer.parseInt(request.getParameter("noteId"));
		Map<String, Object> val = new HashMap<String, Object>();
		val.put("egg", egg);
		val.put("noteId", noteId);
		// 修改数据库
		noteService.updGoodNum(val);
		// 响应给前台
		Gson gson = new Gson();
		response.setContentType("text/json");
		PrintWriter out = response.getWriter();
		out.print(gson.toJson(egg));
		out.flush();
		out.close();

<<<<<<< HEAD
=======

>>>>>>> 4658790eef2b75a140b15b9dc542a0ede78ed0fb
	}

	/**
	 * 显示用户的所有字条
	 * @param request
	 * @param response
	 * @throws IOException 
	 */
	@RequestMapping(value="/showMyAllNote", method = RequestMethod.POST)
<<<<<<< HEAD
	public void showMyAllNote(HttpServletRequest request,HttpServletResponse response) throws IOException{
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
		List<Note> noteList = notePage.getDatas();
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
		//获取要看的亲友的Uid和当前用户的id
		String myuserNunber = request.getParameter("myuserNunber");
		int friUid = Integer.parseInt(request.getParameter("friUid"));
		//调用接口得到可看的noteList
		List<Note> autNoteList = autToNode.getAutNote(friUid, myuserNunber);
		//数据响应到前台
		Gson gson = new Gson();
		response.setContentType("text/json");
		PrintWriter out = response.getWriter();
		out.print(gson.toJson(autNoteList));
		out.flush();
		out.close();
	}
	
	/**
	 * 显示所有亲友的自己有权限看的字条
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping(value="/showAllfriNote", method = RequestMethod.POST)
	public void showAllfriNote(HttpServletRequest request,HttpServletResponse response) throws IOException{
		//得到当前用户的uid
		int myUid = Integer.parseInt(request.getParameter("uid"));
		String myuserNunber = request.getParameter("myuserNunber");
		//查出用户的所有好友uid
		List<Integer> friUidList = ;
		//查出所有好友的有权限的noteList再集合为一个总的allNoteList
		List<Note> allNoteList = new ArrayList<Note>();
		List<Note> noteList = new ArrayList<Note>();
		for(int friUid : friUidList){
			noteList = autToNode.getAutNote(friUid, myuserNunber);
			for(Note note :noteList){
				allNoteList.add(note);
			}
		}
		//数据响应到前台
		Gson gson = new Gson();
		response.setContentType("text/json");
		PrintWriter out = response.getWriter();
		out.print(gson.toJson(allNoteList));
		out.flush();
		out.close();
=======
	public void showMyAllNote(HttpServletRequest request,HttpServletResponse response){
		

>>>>>>> 4658790eef2b75a140b15b9dc542a0ede78ed0fb
	}
}
