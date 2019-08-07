package fileUpload.example.fileuploadDemo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;



import fileUpload.example.fileuploadDemo.service.FileUploadService;

@RestController
@CrossOrigin
@RequestMapping(value = "api/v1")
public class fileUploadController {

	@Autowired
	private FileUploadService fileUploadService;
	

	
	@PostMapping(value = "/uploadFile")
	public ResponseEntity<Object> uploadFile(@RequestParam("file") MultipartFile file){
		try {
			System.out.println("work");
			System.out.println(file);
			return new ResponseEntity<Object>(fileUploadService.uploadFile(file),HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Object>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("downloadFile/{fileName}")
	public ResponseEntity<Object> downloadFile(@PathVariable String fileName){
		try {
			return new ResponseEntity<Object>(fileUploadService.downloadFile(fileName),HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Object>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
}
