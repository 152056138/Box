/*
 * 提醒类控制层
 */
package com.TB.TBox.note.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;

import com.TB.TBox.note.bean.Warn;
import com.TB.TBox.note.service.WarnService;
import com.google.gson.Gson;

public class WarnServlet {
	@Autowired
	public WarnService warnService;
	
	/**
	 * 设置提醒字条
	 * @param request
	 * @param response
	 * @throws IOException 
	 */
	public void setWarn(HttpServletRequest request ,HttpServletResponse response) throws IOException{
		//接收参数
		String wcintent = request.getParameter("wcintent");
		String wtime = request.getParameter("wtime");
		String wto = request.getParameter("wto");
		String wfrom = request.getParameter("wfrom");
		//封装参数
		Warn warn = new Warn(wcintent, wtime, wto, wfrom);
		//调用方法
		warnService.setWarn(warn);
		response.setContentType("text/json");
		PrintWriter out = response.getWriter();
		out.print("提醒设置成功");
		out.flush();
		out.close();
	} 
	
	/**
	 * 按时间查找提醒字条
	 * @param request
	 * @param response
	 * @throws IOException 
	 */
	public void sehWarn(HttpServletRequest request ,HttpServletResponse response) throws IOException{
		//接收参数
		String wtime = request.getParameter("wtime");
		List<Warn> warnList = new ArrayList<Warn>();
		//调用方法
		warnList = warnService.sehWarn(wtime);
		Gson gson = new Gson();
		response.setContentType("text/json");
		PrintWriter out = response.getWriter();
		out.print(gson.toJson(warnList));
		out.flush();
		out.close();
	} 
	
	/**
	 * 设置提醒字条
	 * @param request
	 * @param response
	 * @throws IOException 
	 */
	public void delWarn(HttpServletRequest request ,HttpServletResponse response) throws IOException{
		//接收参数
		int wid = Integer.parseInt(request.getParameter("wid"));
		//调用方法
		warnService.delWarn(wid);
		response.setContentType("text/json");
		PrintWriter out = response.getWriter();
		out.print("删除提醒成功");
		out.flush();
		out.close();
	} 
}
