package fileUpload.example.fileuploadDemo.config;

import java.io.FileInputStream;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.storage.StorageOptions;

@Component
public class CloudConfig {
	
	@Value("${cloudProjectId}")
	private String projectId;
	
	@Value("${cloudJsonFile}")
	private String jsonFile;
	
	public StorageOptions configStorage() {
		
		
		try {
			StorageOptions options = StorageOptions.newBuilder()
		            .setProjectId(projectId)
		            .setCredentials(GoogleCredentials.fromStream(
		                    new FileInputStream(jsonFile))).build();
			
			return options;
		
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
	}
	
	
}
