import org.apache.log4j.Logger;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.TB.TBox.dataUtils.FileUploadUtil;
import com.TB.TBox.note.bean.Evaluate;
import com.TB.TBox.note.service.EvaluateService;
import com.TB.TBox.note.service.NoteService;

/**
 * 评回类测试类
 * @author 20586
 *
 */
public class EvaluateTest {
	EvaluateService eservice;
	@Autowired
	private FileUploadUtil fileUtil;
	Logger log = Logger.getLogger(NoteText.class);
	private ApplicationContext appContext = new ClassPathXmlApplicationContext("applicationContext.xml");
	//添加评回
	@Test
	public void addEva(){
		eservice = appContext.getBean(EvaluateService.class);
//		Evaluate evaluate = new Evaluate(7, 1, 2, 1, "xx:xx", "xXxxx", 1);
//		eservice.addEva(evaluate);
	}
}
