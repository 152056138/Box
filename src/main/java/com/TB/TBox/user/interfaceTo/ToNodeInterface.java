package com.TB.TBox.user.interfaceTo;

import org.springframework.stereotype.Component;

@Component
public interface ToNodeInterface {
	public int selectFriendUid(String friendNumber);
}
