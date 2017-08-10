/**
 * 权限控制类控制层
 */
package com.TB.TBox.note.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.TB.TBox.note.bean.Authority;
import com.TB.TBox.note.service.AuthorityService;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

@Controller
@RequestMapping("/authorityServlet")
@Scope("prototype")
public class AuthorityServlet {
	@Autowired
	private AuthorityService authorityService;
	
	/**
	 * 为字条设置权限
	 * @param request
	 * @param response
	 * @throws IOException 
	 */
//	@RequestMapping(value="/setAuthority", method = RequestMethod.POST)
//	public void setAuthority(HttpServletRequest request,HttpServletResponse response) throws IOException{
//	/*
//	 * 接收数据
//	 */
//		int noteId = Integer.parseInt(request.getParameter("noteId"));
//		//将json字串转换为list
//		String friendNumberList = request.getParameter("friendNumberList");
//		Gson gson = new Gson();
//		//将json中的fid数组取出以其类对象的形式保存
//		List<Authority> autList = gson.fromJson(friendNumberList, new TypeToken<List<Authority>>() {}.getType());
//		int obvious = Integer.parseInt(request.getParameter("obvious"));
//		//定义和fidList等长的List存储权限类,来批量插入
//		List<Authority> authorityList = new ArrayList<Authority>();
//		for(int i = 0;i<autList.size();i++){
////			if(i==0){
////				Authority authority = new Authority();//需每次循环都新建一个对象，不然从头到尾都只是改变一个对象的值
////				//每对应一个字条的权限关系，开头都会有一个FriendNumber为0-0的记录，此记录用来查找确认此字条是以什么方式（obvious）设置权限
////				authority.setFriendNumber("0-0");;
////				authority.setNoteId(noteId);
////				authority.setObvious(obvious);
////				authorityList.add(authority);
////				continue;
////			}
//			Authority authority = new Authority();
//			authority.setFriendNumber(autList.get(i).getFriendNumber());
//			authority.setNoteId(noteId);
//			authority.setObvious(obvious);
//			authorityList.add(authority);
//		}
//		/*
//		 * 存储数据库
//		 */
//		authorityService.setAut(authorityList);
//		/*
//		 * 数据响应到前台
//		 */
//			response.setContentType("text/json");
//			PrintWriter out = response.getWriter();
//			out.print("权限设置成功");
//			out.flush();
//			out.close();
//	}
	
	
}
