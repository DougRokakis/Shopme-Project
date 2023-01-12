package com.shopme.admin;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

public class FileUploadUtil {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(FileUploadUtil.class);
	
	//SAVE PHOTO FILE
	public static void saveFile(String uploadDir, String fileName, MultipartFile multipartFile) throws IOException {
		Path uploadPath = Paths.get(uploadDir);
		//IF PHOTO FILE DOES NOT EXIST FOR THAT ID CREATE NEW FOLDER
		if(!Files.exists(uploadPath)) {
			Files.createDirectories(uploadPath);
		}
		//IF FILE WITH SAME NAME EXISTS IN FOLDER ALREADY, THAT FILE WILL BE OVERWRITTEN. 
		//IF THE FILE TYPE IS INAPPROPRIATE AND CANNOT BE SAVED AN EXCEPTION WILL BE THROWN STATING "FILE COULD NOT BE SAVED".
		try(InputStream inputStream = multipartFile.getInputStream()){
			Path filePath = uploadPath.resolve(fileName);
			Files.copy(inputStream,  filePath,StandardCopyOption.REPLACE_EXISTING);
		}catch(IOException ex) {
			throw new IOException("Could not save file: " + fileName, ex);
		}
	}
	
	//METHOD FOR DELETING FILE FROM DIRECTORY. THIS METHOD WAS ESTABLISHED TO ENSURE THAT WHEN 
	//A FILE IS EDITED/CHANGED IN ID DIRECTORY THAT THE PREVIOUS FILE THERE WILL BE ERASED UNLESS 
	//FILE CANNOT BE ERASED IN WHICH CASE AN EXCEPTION WILL BE THROWN.
	public static void cleanDir(String dir) {
		Path dirPath = Paths.get(dir);
		
		try {
			Files.list(dirPath).forEach(file-> {
				if(!Files.isDirectory(file)) {
					try {
					Files.delete(file);
					}catch(IOException ex) {
						LOGGER.error("Could not delete file: " + file);
					}
				}
			});
		}catch(IOException ex) {
			LOGGER.error("Could not list directory: " + dirPath);
		}
	}

	public static void removeDir(String dir) {
		cleanDir(dir);

		try {
			Files.delete(Paths.get(dir));
		} catch (IOException e) {
			LOGGER.error("Could not remove directory: " + dir);
		}

	}
}
