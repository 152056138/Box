package com.TB.TBox.user.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.TB.TBox.user.bean.Friend_category;
import com.TB.TBox.user.service.Friend_categoryService;
import com.google.gson.Gson;


@Controller
@RequestMapping("/category")
@Scope("prototype")
public class Friend_categoryServlet {
@Autowired
private Friend_categoryService friend_categoryService;
	
	/**
	 * 查看亲友分类
	 * @param request
	 * @param response
	 * @throws IOException 
	 */
@RequestMapping(value="/selectAllCategory",method = RequestMethod.POST)
public void selectAllFriend_category(HttpServletRequest request, HttpServletResponse response) throws IOException{
	List<Friend_category> categoryList = friend_categoryService.selectAllFriend_category();
	Gson gson = new Gson();
	response.setContentType("text/json");
	PrintWriter out = response.getWriter();
	out.print(gson.toJson(categoryList));
	out.flush();
	out.close();
}


@Test
public void test(){
	Friend_categoryService friend_categoryService = new Friend_categoryService();
	List<Friend_category> categoryList = friend_categoryService.selectAllFriend_category();
	Gson json = new Gson();
	System.out.println(json.toJson(categoryList));
	
	//JSONArray jsonArray = JSONArray.fromObject(categoryList);
	
	//System.out.println(jsonArray.toString());


}
}
