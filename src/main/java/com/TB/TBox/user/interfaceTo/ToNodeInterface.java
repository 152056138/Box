package com.TB.TBox.user.interfaceTo;

<<<<<<< HEAD

=======
>>>>>>> a4b021d374c96762a20b2499a28d28734c83342e
import java.util.List;
import java.util.Map;

public interface ToNodeInterface {
	public int selectFriendUid(String friendNumber);
	
	public List<Integer> selectAllFriendUid(Map<String,Object> map);
	
	public String selectUserNumber(int uid);
}
