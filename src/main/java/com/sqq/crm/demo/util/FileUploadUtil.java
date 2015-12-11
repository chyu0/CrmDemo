package com.sqq.crm.demo.util;

import java.io.File;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;

public class FileUploadUtil {

	public static final String FILE_PATH = "/template/static/upload/";

	public static String fileUpload(MultipartFile file, HttpServletRequest request) {
		String path = request.getSession().getServletContext().getRealPath(FILE_PATH);
		if (file.getSize() <= 0)
 return null;
		String fileName = System.currentTimeMillis()
				+ file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
		File targetFile = new File(path, fileName);
		if (!targetFile.exists()) {
			targetFile.mkdirs();
		}
		try {
			file.transferTo(targetFile);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return fileName;
	}

	public static boolean deleteFile(HttpServletRequest request,String fileName) {
		String path = request.getSession().getServletContext().getRealPath(FILE_PATH);
		if(fileName==null)return false;
		File file = new File(path,fileName);
		if (file.isFile() && file.exists()) {
			file.delete();
			return true;
		} 
		return false;
	}
}