/**
 * 纸条类控制层
 */

package com.TB.TBox.note.servlet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/noteServlet")
@Scope("prototype")
public class NoteServlet {
	/**
	 * 添加纸条
	 * @param request
	 * @param response
	 */
	@RequestMapping(value="/addNote")
	public void addNote(HttpServletRequest request,HttpServletResponse response){
		
	}
}
