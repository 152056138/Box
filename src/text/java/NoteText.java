
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.multipart.MultipartRequest;

import com.TB.TBox.dataBean.ImageResp;
import com.TB.TBox.dataUtils.FileUploadUtil;

import com.TB.TBox.note.bean.Evaluate;
import com.TB.TBox.note.bean.Note;
import com.TB.TBox.note.service.NoteService;
import com.google.gson.Gson;
import com.mysql.jdbc.Blob;

/**
 * note 测试类
 * @author 20586
 *
 */

public class NoteText {
	@Autowired
	private FileUploadUtil fileUtil;
	@Autowired
	private NoteService noteService;
	Logger log = Logger.getLogger(NoteText.class);
	private ApplicationContext appContext = new ClassPathXmlApplicationContext("applicationContext.xml");
	//测试添加
	
	public void addnote(){
		 ApplicationContext appContext = new ClassPathXmlApplicationContext("applicationContext.xml");
		 noteService = (NoteService) appContext.getBean(NoteService.class);
//		FileInputStream b1=null;
//		FileOutputStream b3 = null;
//		try {
//			b1 = new FileInputStream("C:/Users/20586/Desktop/face.jpg");
//		} catch (FileNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		byte[] b = new byte[1024];
//		try {
//			b1.read(b);
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		Note note=new Note(1, "me", "fjskdg", "10:09", 2) ;
//		noteService.addNote(note);
	}
	
	//测试删除
	
	public void delNotebyId(){
		int noteId = 3;
		noteService = (NoteService) appContext.getBean(NoteService.class);
		noteService.delNote(noteId);
	}
	
	//查询所有测试
	//查询所有测试
	public void schNoteall(){
		noteService=(NoteService) appContext.getBean(NoteService.class);
//		noteService.schNoteall();
	}
	//按id查询测试
	
	public void schNotebyId(){
		int noteId = 5;
		noteService=(NoteService) appContext.getBean(NoteService.class);
		noteService.schNotebyId(noteId);
	}
	
	//gson转list为json
	@Test
	public void ListtoJson(){

		String houzhui="a.jpg";
		System.out.println(houzhui.lastIndexOf("."));
		 houzhui = houzhui.substring(houzhui.lastIndexOf("."),3);
		 System.out.println(houzhui);

	}
	
	//测试上传note方法
	
	public void uplNote() throws IOException{
		List<byte[]> b3List = new ArrayList<byte[]>();
		fileUtil = appContext.getBean(FileUploadUtil.class);
		noteService=(NoteService) appContext.getBean(NoteService.class);
		ImageResp image ;
		//从前台接收数据
//		int uid =Integer.parseInt( request.getParameter("uid"));
//		int mood =Integer.parseInt( request.getParameter("mood"));
//		String noteAdout = request.getParameter("noteAdout");
//		String noteContent = request.getParameter("noteContent");
//		SimpleDateFormat sdt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//		String time = sdt.format(new Date());
//		Note note = new Note(1, "xx", "xx", "xx:xx", 2);
		//接收图片数据
//		try {
//			b3List = fileUtil.MultiPartFileUpLoad(re);
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		InputStream in = new FileInputStream("C:/Users/20586/Desktop/HuaBeiM/face1.jpg");
		BufferedInputStream buf = new BufferedInputStream(in);
		byte[] b3 = IOUtils.toByteArray(buf);
		for(int i = 0;i< 2;i++){
			b3List.add(b3);
		}
		//保存到数据库
//		noteService.addNote(note);
//		Map<String, Object> val = new HashMap<String, Object>();
//		val.put("uid", note.getUid());
//		val.put("time", note.getTime());
//		System.out.println("uid"+note.getUid());
//		System.out.println("time"+note.getUid());
//		int noteId = noteService.schNote(val);//获得刚存储的字条的id，以存储图片
//		for(byte[] b : b3List){
//			 image = new ImageResp(noteId, b);
//			noteService.addImage(image);
//		}
	}
	
	public void good(){
		int goodNum = 2+1;
		int noteId = 7;
		noteService=(NoteService) appContext.getBean(NoteService.class);
		Map<String, Object> val = new HashMap<String, Object>();
		val.put("goodNum", goodNum);
		val.put("noteId", noteId);
		//修改数据库
		noteService.updGoodNum(val);
	}
	
}
class T {
	private String a;
	private List<String> b;
	public String getA() {
		return a;
	}
	public void setA(String a) {
		this.a = a;
	}
	public List<String> getB() {
		return b;
	}
	public void setB(List<String> b) {
		this.b = b;
	}
	
}
