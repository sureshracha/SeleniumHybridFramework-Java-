package com.uhg.core.framework.utils;

import java.io.File;

public class DriversFilePath {
	
	public static String getDriverFilepath(String fileName)
	 {
		 	 
		 	String filPath =System.getProperty("user.dir")+System.getProperty("file.separator") +"src" + System.getProperty("file.separator")
		 			 
		 			+"test" + System.getProperty("file.separator") +"resources" + System.getProperty("file.separator")  + "browserdrivers" + System.getProperty("file.separator") + fileName; 
		 	
		 	  File fileContent = new File(filPath);
			
		 	if (fileContent.exists()){
		 		
		 		//System.out.println(filPath);
		 		return  filPath;	
		 		
		 	} 
		 	
		 	System.out.println("file not found : " + filPath);
		 	
			return null;
		 	
	 }

}
