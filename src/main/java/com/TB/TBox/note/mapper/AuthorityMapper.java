/**
 * 权限控制类映射器
 */
package com.TB.TBox.note.mapper;

import java.util.List;

import com.TB.TBox.note.bean.Authority;

public interface AuthorityMapper {
	/*
	 * 添加用户与字条的权限关系
	 */
	public void addAut(Authority authorityList);
	
	/*
	 * 查询用户与字条的权限关系
	 */
	public List<Authority> schAut(int noteId);
}
