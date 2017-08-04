package com.TB.TBox.user.servlet;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Pattern;

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

import com.TB.TBox.user.bean.User;
import com.TB.TBox.user.service.UserService;

/**
 * Servlet implementation class UserServlet
 */

@Controller
@RequestMapping("/user")
@Scope("prototype")
public class UserServlet {
	private static final long serialVersionUID = 1L;

	@Autowired
	private UserService userService;

	@Autowired
	private User user;

	/**
	 * 用户注册
	 * 
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping(value = "/addUser", method = RequestMethod.POST)
	public void addUser(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String number = request.getParameter("number");
		String password = request.getParameter("password");
		String repassword = request.getParameter("repassword");
		String phone = request.getParameter("phone");
		String place = request.getParameter("place");
		System.out.println(number + " " + password + " " + phone + " " + phone);
		if ((number.isEmpty()) || (password.isEmpty()) || (phone.isEmpty()) || (phone.isEmpty())) {
			response.setContentType("text/json");
			PrintWriter out = response.getWriter();
			out.print("用户注册信息填写不完整,请填写完整！");
			out.flush();
			out.close();
		} else {
			// 判断注册账号是不是是数字串
			Pattern pattern = Pattern.compile("[0-9]*");
			if (pattern.matcher(number).matches()) {
				// 判断用户的注册账号是不是是6-11位
				if ((number.length() >= 6) && (number.length() <= 11)) {
					// 判断密码的长度
					if (password.length() < 6) {
						response.setContentType("text/json");
						PrintWriter out = response.getWriter();
						out.print("密码位数太少，最少为6位！");
						out.flush();
						out.close();
						// 重复密码和密码是否一致
					} else if (number.equals(repassword)) {
						user.setNumber(number);
						user.setPassword(password);
						user.setPhone(phone);
						user.setPlace(place);
						userService.addUser(user);
						user = userService.selectUserByNumber(number);
						System.out.println("注册成功");
						response.setContentType("text/json");
						PrintWriter out = response.getWriter();
						out.print(user.toJson());
						out.flush();
						out.close();
					} else {
						response.setContentType("text/json");
						PrintWriter out = response.getWriter();
						out.print("密码和重复密码不一致！");
						out.flush();
						out.close();
					}
				} else {
					response.setContentType("text/json");
					PrintWriter out = response.getWriter();
					out.print("注册账号应为6-11位的数字！");
					out.flush();
					out.close();
				}
			} else {
				response.setContentType("text/json");
				PrintWriter out = response.getWriter();
				out.print("注册账号应为6-11位的数字,不能含有其他字符！");
				out.flush();
				out.close();
			}

		}

	}

	/**
	 * 创建角色（根据以前注册的信息补全信息）
	 * 
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping(value = "/createRole", method = RequestMethod.POST)
	public void createRole(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String uuid = request.getParameter("uid");
		int uid = Integer.parseInt(uuid);
		user = userService.selectUserByID(uid);
		String username = request.getParameter("username");
		user.setUsername(username);
		String constellation = request.getParameter("constellation");
		user.setConstellation(constellation);
		String blood = request.getParameter("blood");
		user.setBlood(blood);
		String signature = request.getParameter("signature");
		user.setSignature(signature);
		String birthday = request.getParameter("birthday");
		user.setBirthday(birthday);
		String hobby = request.getParameter("hobby");
		user.setHobby(hobby);
		String job = request.getParameter("job");
		user.setJob(job);
		String gender = request.getParameter("gender");
		user.setGender(gender);
		String personalPassword = request.getParameter("personalPassword");
		user.setPersonalPassword(personalPassword);
		String uage = request.getParameter("age");
		int age = Integer.parseInt(uage);
		user.setAge(age);
		// 判断上传表单是否为multipart/form-data类型
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
						System.out.println(b);
						user.setUfacing(b);
						// 6. 调用FileItem的delete()方法，删除临时文件
						fileItem.delete();
					}

				}
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		System.out.println(user.toJson());
		userService.createRole(user);
		response.setContentType("text/json");
		PrintWriter out = response.getWriter();
		out.print(user.toJson());
		out.flush();
		out.close();
	}

	/**
	 * 修改密码
	 * 
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping(value = "/updatePassword", method = RequestMethod.POST)
	public void updatePassword(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String uuid = request.getParameter("uid");
		int uid = Integer.parseInt(uuid);
		user = userService.selectUserByID(uid);
		String personalPassword = request.getParameter("personalPassword");
		user.setPassword(personalPassword);
		userService.updateRole(user);
		System.out.println(user.toJson());
		response.setContentType("text/json");
		PrintWriter out = response.getWriter();
		out.print(user.toJson());
		out.flush();
		out.close();

	}

	/**
	 * 修改私人密码
	 * 
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping(value = "/updatepersionPasswprd", method = RequestMethod.POST)
	public void updatepersionPasswprd(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String uuid = request.getParameter("uid");
		int uid = Integer.parseInt(uuid);
		user = userService.selectUserByID(uid);
		String password = request.getParameter("password");
		user.setPassword(password);
		userService.updateRole(user);
		System.out.println(user.toJson());
		response.setContentType("text/json");
		PrintWriter out = response.getWriter();
		out.print(user.toJson());
		out.flush();
		out.close();

	}

	/**
	 * 查看用户信息
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping(value = "/selectUser", method = RequestMethod.POST)
	public void selectUser(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String uuid = request.getParameter("uid");
		int uid = Integer.parseInt(uuid);
		user = userService.selectUserByID(uid);
		User formuser = new User();
		formuser.setUid(user.getUid());
		formuser.setPhone(user.getPhone());
		formuser.setPlace(user.getPlace());
		formuser.setNumber(user.getNumber());
		System.out.println(formuser.toJson());
		response.setContentType("text/json");
		PrintWriter out = response.getWriter();
		out.print(formuser.toJson());
		out.flush();
		out.close();
	}
	
	/**
	 * 查看角色信息
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping(value = "/selectRole", method = RequestMethod.POST)
	public void selectRole(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String uuid = request.getParameter("uid");
		int uid = Integer.parseInt(uuid);
		user = userService.selectUserByID(uid);
		User formRole = new User();
		formRole.setUsername(user.getUsername());
		formRole.setConstellation(user.getConstellation());
		formRole.setBlood(user.getBlood());
		formRole.setSignature(user.getSignature());
		formRole.setBirthday(user.getBirthday());
		formRole.setUfacing(user.getUfacing());
		formRole.setHobby(user.getHobby());
		formRole.setJob(user.getJob());
		formRole.setGender(user.getGender());
		formRole.setAge(user.getAge());
		System.out.println(formRole.toJson());
		response.setContentType("text/json");
		PrintWriter out = response.getWriter();
		out.print(formRole.toJson());
		out.flush();
		out.close();
	}
}
