package edu.spring.ex05.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

import javax.annotation.Resource;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

import edu.spring.ex05.domain.TestVO;
import edu.spring.ex05.util.FileUploadUtil;
import edu.spring.ex05.util.MediaUtil;

@Controller
public class FileUploadController {
	private static final Logger logger = 
			LoggerFactory.getLogger(FileUploadController.class);
	
	// servlet-context.xml ���Ͽ� ������ ���ڿ� ���ҽ� ����
	@Resource(name = "uploadPath")
	private String uploadPath;
	
	@GetMapping("/upload")
	public void uploadGET() {
		logger.info("uploadGET() ȣ�� : " + uploadPath);
	}
	
	@PostMapping("/upload")
	public void uploadPOST(TestVO vo, MultipartFile file) {
		logger.info("uploadPost() ȣ��");
		logger.info("���� �̸� : " + file.getOriginalFilename());
		logger.info("���� ũ�� : " + file.getSize());
	
		
		String savedFileName = saveUploadFile(file);
		
	}
	
	@PostMapping("/upload2")
	public String uploadPost2(MultipartFile[] files) {
		String result = "";
		for(MultipartFile f : files) {
			result += saveUploadFile(f) + " ";
		}
		logger.info("result = " + result);
		return "upload";
	}
	
	@GetMapping("/upload-ajax")
	public void uploadAjaxGET() {
		logger.info("uploadAjaxGET() ȣ��");
	}
	
	@PostMapping("/upload-ajax")
	public ResponseEntity<String> uploadAjaxPOST(MultipartFile[] files) {
		logger.info("uploadAjaxPOST() ȣ��");
		
		// ���� �ϳ��� ����
		String result = null; // result : ���� ��� �� ����� �̹��� �̸�
		try {
			result = FileUploadUtil.saveUploadedFile(uploadPath,
					files[0].getOriginalFilename(),
					files[0].getBytes());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return new ResponseEntity<String>(result, HttpStatus.OK);
		
	}

	private String saveUploadFile(MultipartFile file) {
		// UUID : ���ε��ϴ� ���� �̸��� �ߺ����� �ʵ��� �� ����
		UUID uuid = UUID.randomUUID();
		String savedName = uuid + "_" + file.getOriginalFilename();
		File target = new File(uploadPath, savedName);
		
		try {
			FileCopyUtils.copy(file.getBytes(), target);
			logger.info("���� ���� ����");
			return savedName;
		} catch (IOException e) {
			logger.error("���� ���� ����");
			return null;
		}
		
	}
	
	// display �Լ��� ȣ���ϸ� �������� �̹����� Ȯ���� �� ����
	// - ���� ��θ� �����ؾ� ��
	@GetMapping("/display")
	public ResponseEntity<byte[]> display(String fileName) {
		logger.info("display() ȣ��");
		
		ResponseEntity<byte[]> entity = null;
		InputStream in = null;
		
		String filePath = uploadPath + fileName;
		try {
			in = new FileInputStream(filePath);
			
			// ���� Ȯ����
			String extension = 
					filePath.substring(filePath.lastIndexOf(".") + 1);
			logger.info(extension);
			
			// ���� ���(response header)�� Content-Type ����
			HttpHeaders httpHeaders = new HttpHeaders();
			httpHeaders.setContentType(MediaUtil.getMediaType(extension));
			// ������ ����
			entity = new ResponseEntity<byte[]>(
						IOUtils.toByteArray(in), // ���Ͽ��� ���� ������
						httpHeaders, // ���� �ش�
						HttpStatus.OK
					);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return entity;
		
	}
}
