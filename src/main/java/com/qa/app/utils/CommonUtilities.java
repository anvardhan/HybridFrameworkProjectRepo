package com.qa.app.utils;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CommonUtilities {
	
	//Append filename with current data and timestamp
	public static String getFileName(String fileName) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");
        String dateTimeInfo = dateFormat.format(new Date());
        return fileName.concat(String.format("_%s", dateTimeInfo));
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
