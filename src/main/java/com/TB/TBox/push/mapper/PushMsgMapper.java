package com.TB.TBox.push.mapper;

import com.TB.TBox.push.bean.PushMsg;

public interface PushMsgMapper {
public void addPushMsg(PushMsg pushMsg);

public PushMsg selectPushMsg(int uid);

public void updatePushMsg(PushMsg pushMsg);
}
