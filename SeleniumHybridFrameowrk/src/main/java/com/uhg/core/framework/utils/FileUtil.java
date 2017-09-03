package com.uhg.core.framework.utils;

import java.io.File;
import java.io.IOException;

 import org.apache.commons.io.FileUtils;

public class FileUtil{
	
	
	public static boolean FileExists( String filepath){
		File f = new File(filepath);

		if(f.exists()) {
			return true;
		}
		return false;
	}
	 

	public static boolean  Deletefile(String filepath){
		if ( FileExists(filepath)){
			File file = new File(filepath);
			if (file.delete()){
				return true;
			}
			return false;

		}
		return true;

	}
	
	public static boolean CopyFile(String SourcefilePath, String destinationfilepath) {

		if ( FileExists(SourcefilePath)){
			if ( Deletefile(destinationfilepath)){
				File Source =new File(SourcefilePath);
				File Destination =new File(destinationfilepath);

				try {
					FileUtils.copyFile(Source, Destination);
					
					return true;
				} catch (IOException e) {

					return false;
				}
			}
			return false;
		}
		return false;
	}
	 
	
	
}


