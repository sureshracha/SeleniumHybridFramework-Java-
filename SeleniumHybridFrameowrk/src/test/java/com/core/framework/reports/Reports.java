package com.core.framework.reports;

import java.net.UnknownHostException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import com.core.framework.utils.FileUtil; 
import com.core.framework.utils.Timer;
import com.core.framework.utils.iniConfigFileLoader;

import org.testng.ITestContext;  
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;


public class Reports{
	
	private static String TestStartTime;
	private Timer TestTimer = new Timer();
	public String TestClass ;	 
	private static String TestResultFolder;	 
	private  static String TestsFolder;
	private static String IndexFilePath = new String();	 
	private Date Sdate;
	private String CurrentFileName;	 
	private ProjectConstants ProjectParams = new ProjectConstants();	
	private static HtmlWriter htmlwriter = new HtmlWriter(); 
	private String TestSuiteName;
	 
	
	@SuppressWarnings("static-access")
	@BeforeTest
	public void beforeTest(final ITestContext testContext) {
		
			this.getConfigDetails();
		
		   DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		   Sdate = new Date();
		   TestStartTime = dateFormat.format(this.Sdate); //this.TestStartTime;
		   System.out.println("Test STarted : " + TestStartTime );
		   TestSuiteName = testContext.getCurrentXmlTest().getSuite().getName();
		   htmlwriter.strTestCaseLogs.setLength(0);		   
		   this.SetTestResultsFolders(testContext.getName());
		   FileUtil.CreateFile(IndexFilePath); 	
			 
	}
	
	@BeforeClass
	public void beforeClass()
	{
		 this.TestClass = this.getClass().getSimpleName() + "(" + this.getClass().getName()+")" ;
	}
	
	@BeforeMethod
	public void beforeMethod(Method method)
	{
		 this.CurrentFileName =   TestsFolder + System.getProperty("file.separator") +method.getName() + ".html";
		 FileUtil.CreateFile( this.CurrentFileName );
		 Logger.setFilePaths( this.CurrentFileName, TestResultFolder );
		 String msg = "<font color=\"black\"><br><b>" +   "###############  Test Case ::" + method.getName() + ":: Started ###############" + "<\b> </font>";
		 FileUtil.AppendTextToFile( this.CurrentFileName ,msg + "\n");		 
		 this.TestTimer.start();
		 
		 
	}
	 
	@AfterMethod
	public void afterMethod(final Method method ,final ITestResult result )
	{		
		System.out.println("Test Name :  " + method.getName());
		String msg = "<font color=\"black\"><br><b>" +   "###############  Test Case ::" + method.getName() + ":: Ended ###############" + "<\b> </font>";
		FileUtil.AppendTextToFile( this.CurrentFileName ,msg + "\n");		 
		String LogFilepath =  "LogFiles" + System.getProperty("file.separator") + method.getName() + ".html";
		this.TestTimer.stop();
		htmlwriter.TestCaseLog(result, this.TestClass,method.getName(),LogFilepath,TestTimer.getElapsedTime());
		
		 
	}
		
	@AfterTest
	public void afterTest()
	{
		 try {
			
			  
			 
			 htmlwriter.initializeReport(TestSuiteName, this.ProjectParams.Browser,TestStartTime);			
			 htmlwriter.concludeReport();
			 
			 try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			 this.CopyCurrentResultFolderToArchive();
			 
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
		 
	   
	}
	
	private void SetTestResultsFolders(String TestName)  
	{		
		if(this.ProjectParams.CustomResultFolder.equals(""))
		   {
			 TestResultFolder = "c:" + System.getProperty("file.separator") +"AutomationTestRunResults"; //+ System.getProperty("file.separator") + TestSuiteName + "_" + this.DateFolder.format(this.Sdate);
		   }
		else
		{
			TestResultFolder = this.ProjectParams.CustomResultFolder; // + System.getProperty("file.separator") + TestSuiteName + "_" + this.DateFolder.format(this.Sdate);
		}
		   TestsFolder =  TestResultFolder + System.getProperty("file.separator") + "LogFiles";	
		  
		    
		    IndexFilePath = TestResultFolder + System.getProperty("file.separator") +TestName +".html";
		    htmlwriter.indexFilePath =    IndexFilePath;
		    
		    File source3 = new File( TestResultFolder );
			 try {						
				 	FileUtil.DeleteFilesInFolder(source3);
				 	//FileUtil.DeleteSizeZeroFilesInFolder(source3) ;
				 
				 } catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}			
		    
		    
		   
	}
	private void CopyCurrentResultFolderToArchive()
	{
		 try {
			   
			   	if(FileUtil.FileExists(IndexFilePath))
				{ 
			   		 String ArchiveFolder = getArchiveFolderName(IndexFilePath) ;
			   		 if(ArchiveFolder !="")
			   			 {
			   			 	 File source = new File(TestResultFolder);			   			 
							 File destination = new File(TestResultFolder + "_Archive" +System.getProperty("file.separator") + ArchiveFolder);
						   	 FileUtil.CreateDirectory(TestResultFolder + "_Archive");						   	
							 FileUtil.copyFolder(source,destination );
							 
							
			   			 }
					 
			   	}
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
		 
		
	}
	private String getArchiveFolderName (String FilePath)  
	{
	  
	  

		String str ="";
	 
	    	String filecontent = FileUtil.ReadData(FilePath);
	    	if(!filecontent.isEmpty())
	    	{
	    		int pos = filecontent.indexOf("Test Test Started at") +32;	    	 
	    	
		    	if(pos != -1)
		    	{
		    		str = filecontent.substring(pos ,(pos+19));
		    		 
		    		str = str.replace("/", "_").replace(".", "").replace(":","").replace(" ","_");
		    	}
			  //  System.out.println("Creation date: " + str);
	    	}
		    return str;	    
	    
	}
	private void getConfigDetails()
	{
		String filPath=null;
	 	String commonpath =System.getProperty("user.dir")+System.getProperty("file.separator") +"src" + System.getProperty("file.separator")
	 			 
	 			+"test" + System.getProperty("file.separator")
				+"resources";
				
	 	File fileContent;
	 	
	 	filPath = commonpath + System.getProperty("file.separator") + "config" +System.getProperty("file.separator") + "Config.ini";
	 	
	 	  fileContent = new File(filPath);
		
	 	if (fileContent.exists()){
	 		//System.out.println(filPath);
	 		
	 		iniConfigFileLoader configfile = new iniConfigFileLoader();
			Properties  PrjConfg = configfile.loadProperties(filPath);		
			this.ProjectParams.Browser = PrjConfg.getProperty("Browser");
			this.ProjectParams.CustomResultFolder = PrjConfg.getProperty("CustomResultPath");	
	 		
	 	}
		
	}
}


 
 