package com.core.framework.reports;
 

import java.net.UnknownHostException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.testng.ITestResult; 

import com.core.framework.utils.FileUtil;
public class HtmlWriter
{
 public static int passCount = 0;
 public static int totalCount =0; 

// private static FileStream reportFile;
 public static String startTime;
 public static String stopTime;
 public String indexFilePath;
 public static StringBuilder strTestCaseLogs = new StringBuilder();

 public void initializeReport(String pkgName, String Browser,String time) throws UnknownHostException
 {
	  StringBuilder htmlReportString = new StringBuilder();  
	  startTime = time;
	   
	  java.net.InetAddress localMachine = java.net.InetAddress.getLocalHost(); 
			   
	  String machineName = localMachine.getHostName();
	  String userName = System.getProperty("user.name"); 
	   
	  
	  htmlReportString.append(getHeader());
	  htmlReportString.append("<hr>");
	  htmlReportString.append("<h2>Automation Test Run Report</h2>");
	  htmlReportString.append("<table style=\"width:80%\">");
	  htmlReportString.append("<tr>");
	  htmlReportString.append("<td>Test Suite Name</td>");
	  htmlReportString.append("<td><i>" + pkgName + "</i></td>");
	  htmlReportString.append("</tr>");
	 // htmlReportString.append("<tr>");
	  //htmlReportString.append("<td>Test Suite Started at</td>");
	 // htmlReportString.append("<td><i>" +  startTime + "</i></td>");
	 // htmlReportString.append("</tr>");  
	  htmlReportString.append("<tr>");
	  htmlReportString.append("<td>UserName/Machine Name</td>");
	  htmlReportString.append("<td><i>" + userName + "/" + machineName + "</i></td>");
	  htmlReportString.append("</tr>");
	  htmlReportString.append("<tr>");
	  htmlReportString.append("<td>Results Location</td>");
	  htmlReportString.append("<td><i>" +  indexFilePath + "</i></td>");
	  htmlReportString.append("</tr>");
	  htmlReportString.append("<tr>");
	  htmlReportString.append("<td>Browser</td>");
	  htmlReportString.append("<td><i>" + Browser + "</i></td>");
	  htmlReportString.append("</tr>");
	  htmlReportString.append("</table>");
	  htmlReportString.append("<br>");
	  htmlReportString.append("<br>");
	  htmlReportString.append("<table id=\"t01\">");
	  htmlReportString.append("<tr>");
	  htmlReportString.append("<th style=\"width:35%\"><b>Test Scenario (Class)<b></th>");
	  htmlReportString.append("<th style=\"width:25%\"><b>Test Case Name <b></th>");
	  htmlReportString.append("<th style=\"width:25%\"><b>Execution Time (hh:mm:ss)<b></th>");
	  htmlReportString.append("<th style=\"width:15%\"><b>Status<b></th>");
	  htmlReportString.append("</tr>");
	   FileUtil.AppendTextToFile( indexFilePath,htmlReportString.toString());
   
 }

 public  void TestCaseLog(ITestResult  result, String TestClass,String TestMethod, String RefFile ,String timeElapsed)
 {
	 String Status;
	 
	   switch (result.getStatus()) {
	    case ITestResult.SUCCESS:
	    	Status = "Passed";
	        break;

	    case ITestResult.FAILURE:	        
	    	Status = "Failed";	         
	        break;

	    case ITestResult.SKIP:	         
	        Status = "Skipped";	       
	        break;

	    default:
	        throw new RuntimeException("Invalid status");
	    }
	 
		   String str2 = "bgcolor = \"red\"";
		   if (Status.equalsIgnoreCase("Passed"))
		   {
		     str2 = "bgcolor = \"green\"";
		      passCount ++;
		   }
		   	   
		   
		    strTestCaseLogs.append("<tr>");
		    strTestCaseLogs.append("<td>" + TestClass + "</td>");
		    strTestCaseLogs.append("<td>" + TestMethod + "</td>");
		    strTestCaseLogs.append("<td>" + timeElapsed + "</td>");
		    strTestCaseLogs.append("<td " + str2 + "><a href=" + RefFile + ">" + Status + "</a></td>");
		    strTestCaseLogs.append("</tr>");
		    totalCount ++;		  
 }
 

 public void concludeReport()
 {   
	 StringBuilder htmlReportString = new StringBuilder();  
	  float num = (float) ((double) passCount / (double)   totalCount  * 100.0);
	  
	  DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		 Date date = new Date();
		 //this.TestEndTime = dateFormat.format(date);		  
		 stopTime= dateFormat.format(date); //this.TestEndTime;
	  
	  htmlReportString.append("</table>");
	  htmlReportString.append("<br>");
	  htmlReportString.append("<br>");
	  htmlReportString.append("<table style=\"width:30%\">");
	  htmlReportString.append("<tr>");
	  htmlReportString.append("<td>Test Test Started at</td>");
	  htmlReportString.append("<td><i>" + startTime + "</i></td>");
	  htmlReportString.append("</tr>");
	  htmlReportString.append("<tr>");
	  htmlReportString.append("<td>Test Test Ended at</td>");
	  htmlReportString.append("<td><i>" +  stopTime + "</i></td>");
	  htmlReportString.append("</tr>");
	  htmlReportString.append("<tr>");
	  htmlReportString.append("<td>Total # of Test Cases</td>");
	  htmlReportString.append("<td>" +   totalCount + "</td>");
	  htmlReportString.append("</tr>");
	  htmlReportString.append("<tr>");
	  htmlReportString.append("<td># of Tests Passed</td>");
	  htmlReportString.append("<td>" +   passCount + "</td>");
	  htmlReportString.append("</tr>");
	  htmlReportString.append("<tr>");
	  htmlReportString.append("<td>Pass Percentage</td>");
	  htmlReportString.append("<td>" +   num + "%</td>");
	  htmlReportString.append("</tr>");
	  htmlReportString.append("</table>");
	  htmlReportString.append("</body>");
	  htmlReportString.append("<br>");
	  htmlReportString.append("<br>");
	  htmlReportString.append("<hr>"); 
	  htmlReportString.append("</html>");
	  FileUtil.AppendTextToFile( indexFilePath,  strTestCaseLogs.toString());
	  FileUtil.AppendTextToFile( indexFilePath, htmlReportString.toString());
	  passCount = 0;
	  totalCount =0; 
	  indexFilePath = "";
	  startTime= "";
	  stopTime= "";	  
	  
 }

 private String getHeader()
 {
   return "<!DOCTYPE html><html><head><style>table {width:80%;}table, th, td {border: 1px solid black;border-collapse: collapse;}td {padding: 5px;text-align: left;}th {padding: 5px;text-align: center;}table#t01 tr:nth-child(even) {background-color: #BDBDBD;}table#t01 tr:nth-child(odd) {background-color:#fff;}table#t01 th\t{background-color: #0404B4;color: white;}.topright {position: absolute;font-size: 18px;top: 8px;right: 16px;}</style></head><body>";
 }

  
}
 
