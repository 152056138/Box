/*
 * 提醒类映射器
 */
package com.TB.TBox.note.mapper;

import java.util.List;

import com.TB.TBox.note.bean.Warn;

public interface WarnMapper {
	/**
	 * 添加提醒字条
	 * @param warn
	 */
	public void addWarn(Warn warn);
	/**
	 * 根据提醒时间查询
	 * @param date
	 */
	public List<Warn> selWarn(String date);
}
