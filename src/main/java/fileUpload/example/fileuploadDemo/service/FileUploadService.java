package fileUpload.example.fileuploadDemo.service;

import org.springframework.web.multipart.MultipartFile;

public interface FileUploadService {

	public String uploadFile(MultipartFile file)throws Exception;
	
	public String downloadFile(String fileName)throws Exception;
	
}
