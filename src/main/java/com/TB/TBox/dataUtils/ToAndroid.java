/**
 * 规范向前端发送的数据格式
 */
package com.TB.TBox.dataUtils;

import org.springframework.stereotype.Component;

@Component
public class ToAndroid {
	private String title; //键
	private Object value; //值
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Object getValue() {
		return value;
	}
	public void setValue(Object value) {
		this.value = value;
	}
	
	
}
