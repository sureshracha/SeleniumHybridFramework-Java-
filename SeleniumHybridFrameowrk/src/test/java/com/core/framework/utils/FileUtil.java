package com.core.framework.utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Collection;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
 /**
  * 
  * @author sracha
  * 
  */
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
			if (file.exists()){
				if(file.delete())
				{ return true;}
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
	
	public static void CreateDirectory(String DirPath)
	{
		
		String[] Dirs = StringUtils.split(DirPath,System.getProperty("file.separator"));
		String  Ardirs =Dirs[0] + System.getProperty("file.separator")  ; 
		
		for(int i=1; i<Dirs.length; i++)
		{
			if(Dirs[i] !=System.getProperty("file.separator"))
				Ardirs =  Ardirs + Dirs[i] + System.getProperty("file.separator");
			 
			File theDir = new File(Ardirs);
			
			if (!theDir.exists()) {
			    System.out.println("creating directory: " + theDir.getName());
			    boolean result = false;
	
			    try{
			        theDir.mkdir();
			        result = true;
			    } 
			    catch(SecurityException se){
			        //handle it
			    }        
			    if(result) {    
			        System.out.println("DIR created");  
			    }
			}
			
			
		}
	}
	
	public static void CreateFile(String FilePath)
	{
		String[] arStr = StringUtils.split(FilePath,System.getProperty("file.separator"));
		String FileFolders = arStr[0] + System.getProperty("file.separator")  ;
		
		for(int i=1; i<arStr.length-1; i++)
		{
			FileFolders = FileFolders + arStr[i] + System.getProperty("file.separator");
		}
		
		CreateDirectory(FileFolders);
		File file = new File(FilePath);
		
		if (!file.exists()) {
		    System.out.println("creating file: " + file.getName());
		    boolean result = false;

		    try{
		    	
		    	file.createNewFile();
		        result = true;
		    } 
		    catch(IOException se){
		        //handle it
		    }        
		    if(result) {    
		        System.out.println("DIR created");  
		    }
		}
		else
		{
			PrintWriter writer;
			try {
				writer = new PrintWriter(file);
				writer.print("");
				writer.close();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}
	
	public static void WriteToFile(String FilePath,String textToWrite)
	{
		 BufferedWriter output = null;
		File f = new File(FilePath);
		if(f.exists())
		{
			try {
				output = new BufferedWriter(new FileWriter(f));
				  output.write(textToWrite);
				  
				  output.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 

		}
	}
	
	public static String ReadData(String FilePath)
	{
		
		
		String targetFileStr =  "";
		File f = new File(FilePath);
		if(f.length() == 0)
			{
				return targetFileStr;
			}
		
		FileInputStream fisTargetFile = null;
		try {
			fisTargetFile = new FileInputStream(new File(FilePath));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		

		try {
			 targetFileStr = IOUtils.toString(fisTargetFile, "UTF-8");
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		return targetFileStr;
	}
	public static void AppendTextToFile(String FilePath,String textToAppend)
	{
		 
			if(FileExists(FilePath))
			{
				try {
					FileWriter fw = new FileWriter(FilePath,true); 
					fw.write(textToAppend);
					  fw.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 

			}
	}
	
	public static void copyFolder(File src, File dest) throws IOException {
		
		if(!src.exists())
			return;
		
		  if (src.isDirectory()) {
		    //if directory not exists, create it
		    if (!dest.exists()) {
		    	dest.mkdir();
		    }
		    //list all the directory contents
		    String files[] = src.list();
		    for (String file : files) {
		      //construct the src and dest file structure
		      File srcFile = new File(src, file);
		      File destFile = new File(dest, file);
		      //recursive copy
		      copyFolder(srcFile,destFile);
		    }
		  } else {
			  
			  if(src.exists())
			  {
				  				  //if file, then copy it
				    //Use bytes stream to support all file types
				    InputStream in = new FileInputStream(src);		    
				    OutputStream out = new FileOutputStream(dest); 
				    byte[] buffer = new byte[1024];
				    int length;
				    //copy the file content in bytes 
				    while ((length = in.read(buffer)) > 0){
				      out.write(buffer, 0, length);
				    }
				    in.close();
				    out.close();
				    
			  }
		  }
		}
	
	public static void DeleteSizeZeroFilesInFolder(File src) throws IOException {
		
		
		  if (src.isDirectory()) {
		    //if directory not exists, create it
		    
		    //list all the directory contents
		    String files[] = src.list();
		    for (String file : files) {
		      //construct the src and dest file structure
		      File srcFile = new File(src, file);
		      if(srcFile.isFile())
		    	  if(srcFile.length()==0)
		    	  {  			
		    		    srcFile.delete();
		    	  }	    	   
		       
		      //recursive copy
		      DeleteFilesInFolder(srcFile );
		      
		    }		    
		  }  
		}
	
	public static void DeleteFilesInFolder(File src) throws IOException {
		
		
		  if (src.isDirectory()) {
		    //if directory not exists, create it
		    
		    //list all the directory contents
		    String files[] = src.list();
		    for (String file : files) {
		      //construct the src and dest file structure
		      File srcFile = new File(src, file);
		      if(srcFile.isFile())
		    	  if(!srcFile.delete())
		    	  {
		    		  PrintWriter writer;
		  			try {
		  				writer = new PrintWriter(srcFile);
		  				writer.print("");
		  				writer.close();
		  				 
		  			} catch (FileNotFoundException e) {
		  				// TODO Auto-generated catch block
		  				e.printStackTrace();
		  			}
		  			
		    		    srcFile.delete();
		    	  }
		      //recursive copy
		      DeleteFilesInFolder(srcFile );
		      
		    }		    
		  }  
		}
}

