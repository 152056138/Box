/**
 * 好友列表分类类
 */
package com.TB.TBox.user.bean;



public class Friend_category {
	private int cid; //主键
	private String category; //分类
	
	//set-get
	public int getCid() {
		return cid;
	}
	public void setCid(int cid) {
		this.cid = cid;
	}
	
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	
	
}
