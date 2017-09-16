/*
 * 提醒类映射器
 */
package com.TB.TBox.note.mapper;

import java.util.List;
import java.util.Map;

import com.TB.TBox.note.bean.Warn;

public interface WarnMapper {
	/**
	 * 添加提醒字条
	 * @param warn
	 */
	public void addWarn(Warn warn);
	/**
	 * 根据提醒时间模糊查询
	 * @param date
	 */
	public List<Warn> selWarn(String date);
	
	/**
	 * 根据wid删除
	 * @param wid
	 */
	public void delWarn(int wid);
	
	
	/**
	 * 修改提醒表状态
	 */
	public void updateWarn(int wid);
	
	
	/**
	 * 前台查找用
	 */
	
	public List<Warn> selWarnByPre(Map<String,Object> map);
}
