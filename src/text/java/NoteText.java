
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.TB.TBox.note.bean.Note;
import com.TB.TBox.note.service.NoteService;
import com.mysql.jdbc.Blob;

/**
 * note 测试类
 * @author 20586
 *
 */

public class NoteText {
	@Autowired
	private NoteService noteService;
	
	private ApplicationContext appContext = new ClassPathXmlApplicationContext("/applicationContext.xml");
	//测试添加
//	@Test
	public void addnote(){
		 ApplicationContext appContext = new ClassPathXmlApplicationContext("/applicationContext.xml");
		 noteService = (NoteService) appContext.getBean(NoteService.class);
		FileInputStream b1=null;
		FileOutputStream b3 = null;
		try {
			b1 = new FileInputStream("C:/Users/20586/Desktop/face.jpg");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		byte[] b = new byte[1024];
		try {
			b1.read(b);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		Note note ;
//		noteService.addNote(note);
	}
	
	//测试删除
	
	public void delNotebyId(){
		int noteId = 3;
		noteService = (NoteService) appContext.getBean(NoteService.class);
		noteService.delNote(noteId);
	}
	
	//查询所有测试
	
	public void schNoteall(){
		noteService=(NoteService) appContext.getBean(NoteService.class);
		noteService.schNoteall();
	}
	//按id查询测试
	@Test
	public void schNotebyId(){
		int noteId = 5;
		noteService=(NoteService) appContext.getBean(NoteService.class);
		noteService.schNotebyId(noteId);
	}
}
