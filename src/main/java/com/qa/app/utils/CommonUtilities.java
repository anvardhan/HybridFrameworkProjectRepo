package com.qa.app.utils;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.exc.StreamWriteException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;

import pojo.Users;

public class CommonUtilities {
	
	//Append filename with current data and timestamp
	public static String getFileName(String fileName) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");
        String dateTimeInfo = dateFormat.format(new Date());
        return fileName.concat(String.format("_%s", dateTimeInfo));
    }
	
	//convert POJO to JSON
	public static String createJsonPayload(Object obj) throws StreamWriteException, DatabindException, IOException {
		
		String jsonPayload = null;
		
		//convert java object (POJO) to JSON using Jackson API (search for Jackson-databind maven library and add it in pom)		
				
		ObjectMapper mapper = new ObjectMapper(); // import com.fasterxml.jackson.databind.ObjectMapper;
		
		try {
			jsonPayload = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(obj);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		
		//store JSON to somewhere if needed (not necessary step)-> src\test\resources\UserJSONPayload
		//mapper.writeValue(resultFile, value);
		//use date to append with name - Implement later
		//mapper.writeValue(new File("src\\test\\resources\\UserJSONPayload\\user.json"), user);
		mapper.writeValue(new File("src\\test\\resources\\payloads\\"+obj.toString()+".json"), obj);
		
		return jsonPayload;
	}
	
	
	
	
	//Get Latest file from Local dir path
	public File getLatestFileFromDir(String dirPath) {
		
		File dir = new File(dirPath);
		File[] files = dir.listFiles();
		if (files == null || files.length == 0) {
			
			return null;
			
		}
		
		File lastModifiedFile = files[0];
		for(int i = 1; i < files.length; i++) {
			if(lastModifiedFile.lastModified() < files[i].lastModified()) {
				lastModifiedFile = files[i];
			}
		}
		
		return lastModifiedFile;
		
	}

}
