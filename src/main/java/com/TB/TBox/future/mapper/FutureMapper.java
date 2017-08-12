package com.TB.TBox.future.mapper;

import java.util.List;

import com.TB.TBox.future.bean.Future;

public interface FutureMapper {
public void addFuture(Future future);

public void updateFutureStatus(Future future);

public Future selectUserFutureNoteById(int aid);

public List<Future> selectUserFutureNote (String aend);

}
