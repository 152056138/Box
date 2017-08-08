/**
 * 纸条类控制层
 */

package com.TB.TBox.note.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
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
import com.TB.TBox.note.service.NoteService;
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
	
	/**
	 * 发布纸条
	 * @param request
	 * @param response
	 * @throws IOException 
	 */
	@RequestMapping(value="/addNote", method = RequestMethod.POST)
	public void addNote(HttpServletRequest request,HttpServletResponse response,MultipartRequest re) throws IOException{
		List<byte[]> b3List = null;
		//从前台接收数据
		int uid =Integer.parseInt( request.getParameter("uid"));
		int mood =Integer.parseInt( request.getParameter("mood"));
		String noteAdout = request.getParameter("noteAdout");
		String noteContent = request.getParameter("noteContent");
		SimpleDateFormat sdt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String time = sdt.format(new Date());
		Note note = new Note(mood, noteAdout, noteContent, time, uid);
		//接收图片数据
		try {
			b3List = fileUtil.MultiPartFileUpLoad(re);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		//保存到数据库
		noteService.addNote(note);
		Map<String, Object> val = new HashMap<String, Object>();
		val.put("uid", uid);
		val.put("time", time);
		int noteId = noteService.schNote(val);//获得刚存储的字条的id，以存储图片
		for(byte[] b3 : b3List){
			 image = new ImageResp(noteId, b3);
			noteService.addImage(image);
		}
		//数据响应到前台
		note.setImageList(b3List);
		response.setContentType("text/json");
		PrintWriter out = response.getWriter();
		out.print(note.toJson());
		out.flush();
		out.close();
	}
	
	/**
	 * 点赞方法
	 * @throws IOException 
	 */
	@RequestMapping(value="/getGoodNum", method = RequestMethod.POST)
	public void getGoodNum(HttpServletRequest request,HttpServletResponse response) throws IOException{
		//获得当前点赞数和当前纸条id
		int goodNum = Integer.parseInt(request.getParameter("goodNum"))+1;
		int noteId = Integer.parseInt(request.getParameter("noteId"));
		Map<String, Object> val = new HashMap<String, Object>();
		val.put("goodNum", goodNum);
		val.put("noteId", noteId);
		//修改数据库
		noteService.updGoodNum(val);
		
		//响应给前台
		Gson gson = new Gson();
		response.setContentType("text/json");
		PrintWriter out = response.getWriter();
		out.print(gson.toJson(goodNum));
		out.flush();
		out.close();
	}
	
	/**
	 * 扔鸡蛋方法
	 * @throws IOException 
	 */
	@RequestMapping(value="/getEgg", method = RequestMethod.POST)
	public void getEgg(HttpServletRequest request,HttpServletResponse response) throws IOException{
		//获得当前点赞数和当前纸条id
		int egg = Integer.parseInt(request.getParameter("egg"))+1;
		int noteId = Integer.parseInt(request.getParameter("noteId"));
		Map<String, Object> val = new HashMap<String, Object>();
		val.put("egg", egg);
		val.put("noteId", noteId);
		//修改数据库
		noteService.updGoodNum(val);
		//响应给前台
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
	 */
	@RequestMapping(value="/showMyAllNote", method = RequestMethod.POST)
	public void showMyAllNote(HttpServletRequest request,HttpServletResponse response){
		

	}
}
