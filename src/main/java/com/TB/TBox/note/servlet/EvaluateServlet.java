package com.TB.TBox.note.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.TB.TBox.note.bean.Evaluate;
import com.TB.TBox.note.service.EvaluateService;
import com.TB.TBox.user.interfaceTo.ToNodeInterface;
import com.google.gson.Gson;

@Controller
@RequestMapping("/evaluate")
@Scope("prototype")
public class EvaluateServlet {
	@Autowired
	private EvaluateService evaluateService;
	@Autowired
	ToNodeInterface toNode;
	Gson gson = new Gson();
	/**
	 * 发表评论
	 * @param request
	 * @param response
	 * @throws IOException 
	 */
	@RequestMapping(value="/pushcomment", method = RequestMethod.POST)
	public void pushcomment(HttpServletRequest request,HttpServletResponse response) throws IOException{
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		//获取参数
		int noteId = Integer.parseInt(request.getParameter("noteId"));
		int commentId = Integer.parseInt(request.getParameter("commentId"));
		int ifObv = Integer.parseInt(request.getParameter("ifObv"));
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String commentTime = sdf.format(new Date());
		String econtent = request.getParameter("econtent");
		//封装类型
		//此为评论所以replyId回复人id=0，标志位eflag=1
		String commentNum = null;
		if(ifObv!=0){
			commentNum = toNode.selectUserNumber(commentId);
		}
		Evaluate evaluate = new Evaluate(noteId, 0, commentId,commentNum,null, ifObv, commentTime, econtent, 1,0);
		evaluateService.addEva(evaluate);
		//发布完成后，将本纸条的所有评论打包返回
		List<Evaluate> evaluateLst = new ArrayList<>();
		evaluateLst = evaluateService.showEva(noteId);
		//数据响应到前台
		response.setContentType("text/json");
		PrintWriter out = response.getWriter();
		out.print(gson.toJson(evaluateLst));
		out.flush();
		out.close();
	}
	
	/**
	 * 发表回复
	 * 回复可针对评论（标志位为2）
	 * 可针对回复（标志位为3）
	 * eflag==2：comment为评论人id，replyId为回复人id
	 * eflag==3：comment为“回复1”的人id，replyId为回复“回复1”的人的id
	 * @param request
	 * @param response
	 * @throws IOException 
	 */
	@RequestMapping(value="/pushReply", method = RequestMethod.POST)
	public void pushReply(HttpServletRequest request,HttpServletResponse response) throws IOException{
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		//获取参数
				int noteId = Integer.parseInt(request.getParameter("noteId"));
				int commentId = Integer.parseInt(request.getParameter("commentId"));//被回复评回的发布者
				int replyId = Integer.parseInt(request.getParameter("replyId"));//本次评回的发布者
				int ifObv = Integer.parseInt(request.getParameter("ifObv"));
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				String commentTime = sdf.format(new Date());
				String econtent = request.getParameter("econtent");
				int eflag = Integer.parseInt(request.getParameter("eflag"));//回复的评回的标志位
				int replyEid = Integer.parseInt(request.getParameter("replyEid"));
//				int eflag = 0;
				//根据被回复的评回标志位判断本次评回的标志位
//				if(eflagBefor == 1){
//					eflag = 2;
//				}
//				else {
//					eflag = 3;
//				}
				//封装类型
				String replyNum=null;
				if(ifObv!=0){
					replyNum = toNode.selectUserNumber(replyId);
				}	
				String commentNum = toNode.selectUserNumber(commentId);
				Evaluate evaluate = new Evaluate(noteId, replyId, commentId,commentNum,replyNum, ifObv, commentTime, econtent, eflag,replyEid);
				evaluateService.addEva(evaluate);
				//发布完成后，将本纸条的所有评论打包返回
				List<Evaluate> evaluateLst = new ArrayList<>();
				evaluateLst = evaluateService.showEva(noteId);
				//数据响应到前台
				response.setContentType("text/json");
				PrintWriter out = response.getWriter();
				out.print(gson.toJson(evaluateLst));
				out.flush();
				out.close();
	}
}
