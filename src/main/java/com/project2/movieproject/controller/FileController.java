package com.project2.movieproject.controller;

import java.io.File;
import java.nio.file.Files;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.FileCopyUtils;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.project2.movieproject.admin.AdminService;

@RestController
public class FileController {

		@Autowired
		AdminService adminService;
		
		@Value("${project.upload.path}")
		private String uploadPath;
	
		@RequestMapping(value="/download")
		@ResponseBody
		public ResponseEntity<byte[]>download(@RequestParam("FILEPATH")String FILEPATH,
												@RequestParam("uuid") String uuid,
												 @RequestParam("filename")String filename){
			File file = new File(uploadPath+ "\\" + FILEPATH +"\\"+ uuid +"_"+filename);
			
			ResponseEntity<byte[]> result = null;
			try {
				HttpHeaders header = new HttpHeaders();
				header.add("content-Disposition", "attachment; filename=" + new String(filename.getBytes("UTF-8"),("ISO-8859-1")));
				byte[] filecopy = FileCopyUtils.copyToByteArray(file);
				result = new ResponseEntity<>(filecopy, header,HttpStatus.OK);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return result;
		}
}
