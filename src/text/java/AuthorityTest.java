import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.TB.TBox.dataUtils.FileUploadUtil;
import com.TB.TBox.note.bean.Authority;
import com.TB.TBox.note.service.AuthorityService;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

import net.sf.json.JSONArray;

/**
 * 权限设置测试
 * @author 20586
 *
 */
public class AuthorityTest {
	@Autowired
	private FileUploadUtil fileUtil;
	Logger log = Logger.getLogger(NoteText.class);
	private ApplicationContext appContext = new ClassPathXmlApplicationContext("applicationContext.xml");
	AuthorityService authorityService;
	/*
	 * 设置权限
	 */
	@Test
	public void setAuthority(){
		authorityService = appContext.getBean(AuthorityService.class);
		
		//接收数据
				int noteId = 7;
				//将json字串转换为list
				String fidListStr =  "[{\"fid\":2},{\"fid\":3},{\"fid\":4},{\"fid\":8}]";
				
				
				
				Gson gson = new Gson();
				List<Authority> fidList = gson.fromJson(fidListStr, new TypeToken<List<Authority>>() {}.getType());
				
				
//				JSONArray jsonarray = JSONArray.fromObject(fidListStr);  
//		        System.out.println(jsonarray);  
//		        List<Integer> fidList = (List)JSONArray.toCollection(jsonarray); 
//		        System.out.println(fidList.get(2).getClass());
				
				//Json的解析类对象
//			    JsonParser parser = new JsonParser();
//			    //将JSON的String 转成一个JsonArray对象
//			    JsonArray jsonArray = parser.parse(fidListStr).getAsJsonArray();
//			    Gson gson = new Gson();
//			    ArrayList<Integer> fidList = new ArrayList<Integer>();
//			    //加强for循环遍历JsonArray
//			    for (JsonElement user : jsonArray) {
//			        //使用GSON，直接转成Bean对象
//			    	Authority fid = gson.fromJson(user, Authority.class);
//			        fidList.add(fid.getFid());
//			    }
				
			    
			    
//			    
//				int obvious = 0;
//				//定义和fidList等长的List存储权限类,来批量插入
//				List<Authority> authorityList = new ArrayList<Authority>();
//				for(int i = 0;i<=fidList.size();i++){
//					if(i==0){
//						//每对应一个字条的权限关系，开头都会有一个fid为0的记录，此记录用来查找确认此字条是以什么方式（obvious）设置权限
//						Authority authority = new Authority();
//						authority.setFid(0);
//						authority.setNoteId(noteId);
//						authority.setObvious(obvious);
//						authorityList.add(authority);
//						continue;
//					}
//					Authority authority = new Authority();
//					authority.setFid(fidList.get(i-1).getFid());
//					authority.setNoteId(noteId);
//					authority.setObvious(obvious);
//					authorityList.add(authority);
//				System.out.println(authorityList.get(i).getFid());
//				System.out.println(fidList);
//				}
//				//存储数据库
//				
//				authorityService.setAut(authorityList);
	}
}
