/**
 * 权限控制类控制层
 */
package com.TB.TBox.note.servlet;

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
	@Autowired
	private Authority authority;
	
	@RequestMapping(value="/setAuthority", method = RequestMethod.POST)
	public void setAuthority(HttpServletRequest request,HttpServletResponse response){
		//接收数据
		int noteId = Integer.parseInt(request.getParameter("noteId"));
		//将json字串转换为list
		String fidListStr = request.getParameter("fidList");
		Gson gson = new Gson();
		List<Integer> fidList = gson.fromJson(fidListStr, new TypeToken<List<Integer>>() {}.getType());
		int obvious = Integer.parseInt(request.getParameter("obvious"));
		//定义和fidList等长的List存储权限类
		List<Authority> autorityList = new ArrayList<Authority>();
		for(int i = 0;i<=fidList.size();i++){
			if(i==0){
				
			}
		}
	}
}
