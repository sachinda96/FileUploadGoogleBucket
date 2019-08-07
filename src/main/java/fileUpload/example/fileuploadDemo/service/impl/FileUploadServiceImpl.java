package fileUpload.example.fileuploadDemo.service.impl;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.Base64;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


import com.google.cloud.storage.Blob;
import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.Bucket;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;

import fileUpload.example.fileuploadDemo.config.CloudConfig;
import fileUpload.example.fileuploadDemo.service.FileUploadService;

@Service
public class FileUploadServiceImpl implements FileUploadService{
	
	@Value("${bucket}")
	private String bucketName;

	@Autowired
	private CloudConfig cloudConfig;
	

	@Override
	public String uploadFile(MultipartFile file) throws Exception {
		
		
		StorageOptions options = cloudConfig.configStorage();
		Storage storage = options.getService();
		
        Bucket bucket = storage.get(bucketName);
        
        System.out.println(file.getOriginalFilename());
        
        Blob blobs = bucket.create(file.getOriginalFilename(), file.getBytes(), file.getContentType());
		
        if(blobs!=null) {
        	return "200";
        }

		return "204";
	}

	@Override
	public String downloadFile(String fileName) throws Exception {
		StorageOptions options = cloudConfig.configStorage();

		Storage storage = options.getService();
	
		byte[] read = storage.readAllBytes(BlobId.of(bucketName, fileName));
		
		BufferedOutputStream outputStream = new BufferedOutputStream(
	               new FileOutputStream(
	                     new File("/home/sachinda/Pictures/fileuploadDemo/images", fileName)));
	         outputStream.write(read);
	         outputStream.flush();
	         outputStream.close();
	
			if(read!=null) {
				return Base64.getEncoder().encodeToString(read);
			}
	
			return "204";
	}

	
	
}
