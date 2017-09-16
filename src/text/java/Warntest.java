import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.TB.TBox.note.bean.Warn;
import com.TB.TBox.note.service.WarnService;

public class Warntest {
	@Test
	public void addwarn(){
		ApplicationContext application = new ClassPathXmlApplicationContext("applicationContext.xml");
		WarnService warnService = (WarnService) application.getBean("warnService");
//		Warn warn = new Warn("sb", "xx-xx", "me", "it");
//		//调用方法
//		warnService.setWarn(warn);
//	}
}
}