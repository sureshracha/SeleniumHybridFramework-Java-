package com.core.framework.reports; 

import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.imageio.ImageIO;

import org.testng.Assert;

import com.core.framework.utils.FileUtil;
 
 
public class Logger
{
  
	public static String filePath;
	public static String testResultFolder;
	
	public static void setFilePaths(String logerFilePath,String TestResultFolder)
	{
		 filePath = logerFilePath;
		 testResultFolder = TestResultFolder;
	}
  
	public static void info(String message)
	{
		String msg = "<font color=\"black\"> <br>" +   message + " </font>";	
		FileUtil.AppendTextToFile( filePath,msg + "\n");
		System.out.println("Info - " + message);
		
	}
	public static void infoWithScreenCapture(String message)
	{
		String msg = "<font color=\"black\"> <br>" +   message + " </font>";	
		FileUtil.AppendTextToFile( filePath,msg + "\n");
		System.out.println("Info - " + message);
		 try {
				captureScreen();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	
	public static void error(String message)
	{
		String msg = "<font color=\"red\"><b><br>" +   message + "</b></font>";		 
		FileUtil.AppendTextToFile(filePath,   msg + "\n");
		System.out.println( "Error - " + message);
		 
	}
	public static void errorWithScreenCapture(String message)
	{
		String msg = "<font color=\"red\"><b><br>" +   message + "</b></font>";		 
		FileUtil.AppendTextToFile(filePath,   msg + "\n" );
		System.out.println( "Error - " + message);
		 try {
			captureScreen();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void FailTheTestWithScreenCapture(String message)
	{
		String msg = "<font color=\"red\"><b><br>" +  message + "</b></font>";		 
		FileUtil.AppendTextToFile(filePath,   msg + "\n" );
		System.out.println( "Error - " + message);
		 try {
			captureScreen();
			Assert.fail();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void warn(String message)
	{
		String msg = "<font color=\"orange\"><b><br>" + message + "</b></font>";		 
		FileUtil.AppendTextToFile(filePath,  msg + "\n" );
		System.out.println( "Warn - " + message);
	}
	public static void warnWithScreenCapture(String message)
	{
		String msg = "<font color=\"orange\"><b><br>" +  message + "</b></font>";		 
		FileUtil.AppendTextToFile(filePath,   msg + "\n" );
		System.out.println( "Warn - " + message);
		 try {
				captureScreen();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	
	public static void captureScreen() throws Exception {
		
		   String ImgFileName = "screenshot_" + getRandomNumber() + ".jpg" ;
		   String ImageFilePath = testResultFolder + System.getProperty("file.separator") + "Images" + System.getProperty("file.separator") + ImgFileName;		  
		   Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		   FileUtil.CreateFile(ImageFilePath);
		   Rectangle screenRectangle = new Rectangle(screenSize);
		   Robot robot = new Robot();
		   BufferedImage image = robot.createScreenCapture(screenRectangle);
		   ImageIO.write(image, "png", new File(ImageFilePath));
		   String FileRef = "..\\Images\\" + ImgFileName;
		   String message = "Screen Captured and Saved. <a href=" + FileRef + "><i>Click Here for Screenshot</i></a>";		   
		   FileUtil.AppendTextToFile( filePath,  message + "\n");
			System.out.println( message);
		   
		}
	
	private static String getRandomNumber()
	{
		  Date date = new Date();
		 DateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
		 return dateFormat.format(date);
	}
	
}
 
