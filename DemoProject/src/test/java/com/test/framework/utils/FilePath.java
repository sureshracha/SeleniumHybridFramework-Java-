package com.test.framework.utils;

import java.io.File;

public class FilePath {
	
	public static String getFilepath(String fileName)
	 {
		 	String filPath=null;
		 	String commonpath =System.getProperty("user.dir")+System.getProperty("file.separator") +"src" + System.getProperty("file.separator")
		 			 
		 			+"test" + System.getProperty("file.separator")
					+"resources";
					
		 	File fileContent;
		 	
		 	filPath = commonpath + System.getProperty("file.separator") + "config" +System.getProperty("file.separator") +fileName;
		 	
		 	  fileContent = new File(filPath);
			
		 	if (fileContent.exists()){
		 		System.out.println(filPath);
		 		return  filPath;	
		 		
		 	}
		 	
		 	filPath = commonpath +System.getProperty("file.separator") + "objectrepository" +System.getProperty("file.separator") +fileName;
		 	
		 	  fileContent = new File(filPath);
			
		 	if (fileContent.exists()){
		 		System.out.println(filPath);
		 		return  filPath;	
		 		
		 	}
		 	
		 	filPath = commonpath + System.getProperty("file.separator") +"testdata" +System.getProperty("file.separator")+fileName;
		 	  fileContent = new File(filPath);
			
		 	if (fileContent.exists()){
		 		System.out.println(filPath);
		 		return  filPath;		 		 
		 	}
		 	
		 	System.out.println("file not found : " + filPath);
		 	
			return null;
		 	
	 }

}
