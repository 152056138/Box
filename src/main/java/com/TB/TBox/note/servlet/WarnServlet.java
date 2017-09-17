/*
 * 提醒类控制层
 */
package com.TB.TBox.note.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.TB.TBox.note.bean.Warn;
import com.TB.TBox.note.service.WarnService;
import com.TB.TBox.user.service.FriendService;
import com.TB.TBox.user.service.UserService;
import com.TB.base.mybatisUtils.SessionFactory;
import com.google.gson.Gson;

@Controller
@RequestMapping("/warn")
@Scope("prototype")
public class WarnServlet {
	@Autowired
	public WarnService warnService;
	@Autowired
	public UserService userService;

	Gson gson = new Gson();

	/**
	 * 设置提醒字条
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping(value = "/addWarn", method = RequestMethod.POST)
	public void setWarn(HttpServletRequest request, HttpServletResponse response) throws IOException {
		request.setCharacterEncoding("utf-8");
		// 接收参数
		String wcontent = request.getParameter("wcintent");
		String wtime = request.getParameter("wtime");
		String wto = request.getParameter("wto");//备注
		String wphone;
//<<<<<<< HEAD
		int wfrom= Integer.parseInt(request.getParameter("wfrom"));	
//=======
//		int wfrom = Integer.parseInt(request.getParameter("wfrom"));
//		if (wto != null) {
//			wphone = userService.selectUserByNumber(wto).getPhone();
//		} else {
//>>>>>>> dev
		wphone = request.getParameter("wphone");
		int status = 0;
		Warn warn = new Warn(wcontent, wtime, wto, wfrom, wphone, status);
		// 调用方法
		warnService.setWarn(warn);
		response.setContentType("text/json;charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.print("提醒设置成功");
		out.flush();
		out.close();
	}

	/**
	 * 按时间查找提醒字条
	 * 
	 * @param request
	 * @param response
	 * @throws IOException
	 */

	public void sehWarn(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// 接收参数
		String wtime = request.getParameter("wtime");
		List<Warn> warnList = new ArrayList<Warn>();
		// 调用方法
		warnList = warnService.sehWarn(wtime);
		Gson gson = new Gson();
		response.setContentType("text/json");
		PrintWriter out = response.getWriter();
		out.print(gson.toJson(warnList));
		out.flush();
		out.close();
	}

	/**
	 * 删除提醒字条
	 * 
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	public void delWarn(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// 接收参数
		int wid = Integer.parseInt(request.getParameter("wid"));
		// 调用方法
		warnService.delWarn(wid);
		response.setContentType("text/json");
		PrintWriter out = response.getWriter();
		out.print("删除提醒成功");
		out.flush();
		out.close();
	}

	/**
	 * 前台查找已触发后修改过状态的提醒记录
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping(value = "/selWarnByPre", method = RequestMethod.POST)
	public void selWarnByPre(HttpServletRequest request, HttpServletResponse response) throws IOException {
		request.setCharacterEncoding("utf-8");
		int wfrom = Integer.parseInt(request.getParameter("uid"));
		int status = 1;
		List<Warn> warnList = new ArrayList<>();
		Map<String, Object> map = new HashMap<>();
		map.put("wfrom", wfrom);
		map.put("status", status);
		warnList = warnService.selWarnByPre(map);
		response.setContentType("text/json;charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.print(gson.toJson(warnList));
		out.flush();
		out.close();
		// 前台返回去以后进行修改状态
		for (Warn warn : warnList) {
			warn.setStatus(2);
			warnService.updateWarn(warn.getWid());
		}

	}

	@Test
	public void test() {
		ApplicationContext ap = new ClassPathXmlApplicationContext("applicationContext.xml");

		UserService userService;
		userService = ap.getBean(UserService.class);
		WarnService warnService = new WarnService();
		String wcontent = "今天是你的生日";
		String wtime = "2017-9-17";
		String wto = "6813990908";
		String wphone;
		int wfrom = 20;
		if (wto != null) {
			wphone = userService.selectUserByNumber(wto).getPhone();
		} else {
			wphone = "wphone";
		}
		int status = 0;
		Warn warn = new Warn(wcontent, wtime, wto, wfrom, wphone, status);
		// 调用方法
		warnService.setWarn(warn);
		System.out.println("添加成功！");
	}
}