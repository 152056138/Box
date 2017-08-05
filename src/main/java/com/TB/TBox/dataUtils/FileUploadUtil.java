package com.TB.TBox.dataUtils;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;


import org.apache.commons.fileupload.disk.DiskFileItem;
import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
@Repository
public class FileUploadUtil {
	
	/**
	 * 封装传入MultipartFile时将其转化为二进制数组
	 * @param re
	 * @return
	 * @throws IOException
	 */
	public List<byte[]> MultiPartFileUpLoad(MultipartRequest re) throws IOException{
				InputStream in=null;
				List<byte[]> b3List = new ArrayList<byte[]>();
				int cont = 0;//b3List脚标
				// 对图片的获取
				List<MultipartFile> fileList = re.getFiles("head");
				//遍历MultipartFile集合
				for(MultipartFile file:fileList){
				//multipartfile 转 file 上传文件大小若小于2048，则不会生成临时文件
				CommonsMultipartFile cf = (CommonsMultipartFile) file;
				DiskFileItem fi = (DiskFileItem) cf.getFileItem();
				File f = fi.getStoreLocation();
				File tmpFile=null;
				//手动创建临时文件  
		        if(f.length() < 2048){  
		             tmpFile = new File(System.getProperty("java.io.tmpdir") + System.getProperty("file.separator") +   
		                    f.getName());  
		            try {
						file.transferTo(tmpFile);
					} catch (IllegalStateException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}  
		        }  
				try {
					//创建临时文件输入流
					in = new FileInputStream(tmpFile);
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				// 创建缓冲区
				BufferedInputStream bis = new BufferedInputStream(in);
				//将输入流转为二进制数组
				b3List.add((IOUtils.toByteArray(bis)));
				
	}
				return b3List;
	}
}
