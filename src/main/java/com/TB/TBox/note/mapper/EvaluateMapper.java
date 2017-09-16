/**
 * 评回类映射器
 */
package com.TB.TBox.note.mapper;

import java.util.List;

import com.TB.TBox.note.bean.Evaluate;

public interface EvaluateMapper {
	/*
	 * 添加评回
	 */
	public void addEva(Evaluate evaluate);
	/*
	 * 删除评回
	 */
	public void delEva(int eid);
	/*
	 * 查看评回
	 */
	public List<Evaluate> selEva(int noteId);
}
