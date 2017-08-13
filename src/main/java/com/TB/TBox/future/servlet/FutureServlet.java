package com.TB.TBox.future.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.TB.TBox.future.bean.Future;
import com.TB.TBox.future.bean.Message;
import com.TB.TBox.future.service.FutureService;
import com.TB.TBox.jobClass.FutureNote;
import com.TB.TBox.push.bean.PushMsg;
import com.TB.TBox.push.service.PushMsgService;
import com.TB.TBox.user.servlet.FriendServlet;
import com.TB.base.quartz.QuartzThreadPool;
import com.TB.push.PushMsgToSingleDevice;
import com.baidu.yun.push.exception.PushClientException;
import com.baidu.yun.push.exception.PushServerException;
import com.google.gson.Gson;

@Controller
@RequestMapping("/future")
@Scope("prototype")
public class FutureServlet {
	Gson gson = new Gson();
	Logger log = Logger.getLogger(FriendServlet.class);
	DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	@Autowired
	private Future future;
	@Autowired
	private FutureService futureService;
	@Autowired
	private FutureNote futureNote;
	
	/**
	 * 添加未来纸条
	 * @param request
	 * @param response
	 * @throws IOException
	 */
@RequestMapping(value = "/addFuture", method = RequestMethod.POST)
public void addFuture(HttpServletRequest request, HttpServletResponse response) throws IOException{
	String formafrom = request.getParameter("afrom");
	int afrom = Integer.parseInt(formafrom);
	String afterAcontent = request.getParameter("afterAcontent");
	String aend = request.getParameter("aend");
	Date date = new Date();
	String abegin = format.format(date);
	System.out.println(abegin);
	future.setAbegin(abegin);
	future.setAend(aend);
	future.setAfrom(afrom);
	future.setAfterAcontent(afterAcontent);
	future.setAstatus(0);
	futureService.addFuture(future);
	QuartzThreadPool q = new QuartzThreadPool();
	q.setText("FutureNote", aend+" 09:00:00");
	response.setContentType("text/json");
	PrintWriter out = response.getWriter();
	out.print("添加成功！");
	out.flush();
	out.close();
	
}


@Test
public void test(){
	Future future = new Future();
	FutureService futureService = new FutureService();
	String formafrom = ("1");
	int afrom = Integer.parseInt(formafrom);
	String afterAcontent = ("希望自己长的高一点，帅一点！");
	String aend = ("2017-12-23 00：00：00");
	Date date = new Date();
	String abegin = format.format(date);
	System.out.println(abegin);
	future.setAbegin(abegin);
	future.setAend(aend);
	future.setAfrom(afrom);
	future.setAfterAcontent(afterAcontent);
	future.setAstatus(0);
	futureService.addFuture(future);
	log.info(gson.toJson(future));
}



}
