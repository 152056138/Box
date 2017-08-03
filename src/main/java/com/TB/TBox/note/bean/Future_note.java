/**
 * 写给未来的信
 */
package com.TB.TBox.note.bean;

import java.sql.Blob;
import java.util.Date;

public class Future_note {
	private int aid; //纸条id
	private String afterAcontent; //纸条内容
	private int afrom; //写纸条人id
	private int astatus; //纸条状态 0：未读  1：到时已读
	private Date abegin; //写纸条时间
	private Date aend; //推送纸条的时间
	private Blob afterImg; //图片
}
