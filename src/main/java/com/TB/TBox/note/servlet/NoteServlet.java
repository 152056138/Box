/**
 * 纸条类控制层
 */

package com.TB.TBox.note.servlet;

import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartRequest;

import com.TB.TBox.note.service.NoteService;

@Controller
@RequestMapping("/noteServlet")
@Scope("prototype")
public class NoteServlet {
	@Autowired
	private NoteService noteService;
	
	/**
	 * 发布纸条
	 * @param request
	 * @param response
	 */
	@RequestMapping(value="/addNote", method = RequestMethod.POST)
	public void addNote(HttpServletRequest request,HttpServletResponse response,MultipartRequest re){
		//从前台接收数据
		int mood =Integer.parseInt( request.getParameter("mood"));
		String noteAdout = request.getParameter("noteAdout");
		String noteContent = request.getParameter("noteContent");
		//接收图片数据
		if (ServletFileUpload.isMultipartContent(request)) {
			try {
				// 1.创建DiskFileItemFactory对象，设置缓存区大小和临时文件目录
				DiskFileItemFactory factory = new DiskFileItemFactory();
				System.out.println(System.getProperty("java.io.tmpdir"));// 默认临时文件夹
				// 2.创建ServletFileUpload对象，并设置上传文件的大小限制。
				ServletFileUpload sfu = new ServletFileUpload(factory);
				sfu.setSizeMax(10 * 1024 * 1024);// 以byte为单位 不能超过10M 1024byte =
				// 1kb 1024kb=1M 1024M = 1G
				sfu.setHeaderEncoding("utf-8");
				// 3.调用ServletFileUpload.parseRequest方法解析request对象，得到一个保存了所有上传内容的List对象。
				@SuppressWarnings("unchecked")
				// J2SE 提供的最后一个批注是 @SuppressWarnings。该批注的作用是给编译器一条指令，
				// 告诉它对被批注的代码元素内部的某些警告保持静默
				List<FileItem> fileItemList = sfu.parseRequest(request);
				Iterator<FileItem> fileItems = fileItemList.iterator();
				// 4. 遍历list，每迭代一个FileItem对象，调用其isFormField方法判断是否是上传文件
				while (fileItems.hasNext()) {
					FileItem fileItem = fileItems.next();
					// <input type="file">的上传文件的元素
					if (!fileItem.isFormField()) {
						byte[] b = null;
						InputStream in = fileItem.getInputStream();
						b = IOUtils.toByteArray(in);
						if(b!=null){
							
						}
						// 6. 调用FileItem的delete()方法，删除临时文件
						fileItem.delete();
					}

				}
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
//		System.out.println(user.toJson());
//		userService.createRole(user);
//		response.setContentType("text/json");
//		PrintWriter out = response.getWriter();
//		out.print(user.toJson());
//		out.flush();
//		out.close();
	}
}
