package com.TB.TBox.dataUtils;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartRequest;

public class FileUploadThread extends Thread {
	@Autowired
	private FileUploadUtil fileupload;
	private MultipartRequest re;
	private String userNumber;
	private int noteId;
	public static List<String> b3List;
	public FileUploadThread(MultipartRequest re, String userNumber, int noteId) {
		super();
		this.re = re;
		this.userNumber = userNumber;
		this.noteId = noteId;
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		super.run();
		try {
			b3List = fileupload.MultiPartFileUpLoad( re,  userNumber,  noteId);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public List<String> getB3List() {
		return b3List;
	}
	public void setB3List(List<String> b3List) {
		this.b3List = b3List;
	}
}
