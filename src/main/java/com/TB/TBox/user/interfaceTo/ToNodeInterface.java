package com.TB.TBox.user.interfaceTo;

<<<<<<< HEAD
import org.springframework.stereotype.Component;

@Component
=======
import java.util.List;
import java.util.Map;

>>>>>>> 4658790eef2b75a140b15b9dc542a0ede78ed0fb
public interface ToNodeInterface {
	public int selectFriendUid(String friendNumber);
	
	public List<Integer> selectAllFriendUid(Map<String,Object> map);
}
