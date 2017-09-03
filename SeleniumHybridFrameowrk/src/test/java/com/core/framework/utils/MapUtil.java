package com.core.framework.utils;

 
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

 







import com.core.framework.datalayer.CsvDriver;
import com.core.framework.datalayer.ExcelDriver;
import com.core.framework.datalayer.XmlDriver;
import com.core.framework.datalayer.dbDriver;
/**
 * Read the data from excel or csv or Xml or DB
 * @author sracha
 *
 */

public class MapUtil {
	
	
	private Map <String,List<String>> MapObj =null;
	private ExcelDriver ExlData = null;
	private CsvDriver CsvData = null;
	private XmlDriver XmlData=null;
	private String FileType;
	private dbDriver DBdata = null;
	
	
	/**
	 * Excel Data File Data Driver
	 * @param filePath
	 * @param sheetNo
	 * @throws IOException 
	 */
	
	public  MapUtil(String filePath, int sheetNo) throws IOException {
		
		
		if(filePath.contains(".xls") || filePath.contains(".xlsx") )
		{
			this.ExlData = new ExcelDriver(filePath,sheetNo);
			FileType = "excel";
		}
		else{
			
			System.out.println("wrong construction called");
			throw new IOException();
		}
			
		
	}
	/**
	 * CSV DAta File Data Driver
	 * 
	 * @param filePath
	 * @param separator
	 * @param quotecharacter
	 * @throws IOException 
	 */
	public MapUtil(String filePath, char separator, char quotecharacter) throws IOException {
		
		
		
		if(filePath.contains(".csv"))
		{
			FileType = "csv";
			this.CsvData = new CsvDriver(filePath,separator,quotecharacter);
		}
		else{
			System.out.println("wrong construction called");
			throw new IOException();
		}
			
		
	}
	
	/**
	 * XML Data File Data driver
	 * 
	 * @param filePath
	 * @throws IOException 
	 */
	public MapUtil(String filePath) throws IOException {
		
		
		
		if(filePath.contains(".csv"))
		{
			FileType = "xml";
			this.XmlData = new XmlDriver(filePath);
		}
		else{
			System.out.println("wrong construction called");
			throw new IOException();
		}
		 
		
		 
	}

	 /**
	  * DB table data driver
	  * @param dbDriver
	  * @param dbURL
	  * @param user
	  * @param pwd
	  * @param sqlStatement
	  * @throws ClassNotFoundException
	  * @throws SQLException
	  */
	public MapUtil(String dbDriver,String dbURL,String user,String pwd,String sqlStatement) throws ClassNotFoundException, SQLException {
		
		this.DBdata = new dbDriver(  dbDriver,  dbURL,  user,  pwd,  sqlStatement);
		FileType = "db";
		
	}
	
	/**
	 * Loads the complete file/db table Data to Hash Map
	 * @throws SQLException
	 */
	
	public void LoadDatatoMap() throws SQLException { 
		 
		
		 MapObj = new HashMap<String,List<String>>();
		
		switch(FileType)
		{
		case "excel":
			MapObj = ExlData.getData();
			break;
		case "csv":
			MapObj = CsvData.getData();
			break;
		case "xml":
			MapObj = XmlData.getData();
			break;
		case "db" :
			MapObj = DBdata.getData();
				
		 
			
		}
			
	}
	
	/**
	 * Loads the complete file Data to Hash Map
	 * This method cannot retrive the data from DB
	 * @param executeFlag
	 *  
	 */
	
public void LoadDatatoMap(Boolean executeFlag)   { 
	 
		
		 MapObj = new HashMap<String,List<String>>();
		
		switch(FileType)
		{
		case "excel":
			MapObj = ExlData.getData(executeFlag);
			break;
		case "csv":
			MapObj = CsvData.getData(executeFlag);
			break;
		case "xml":
			MapObj = XmlData.getData(executeFlag);
			break; 
		 
			
		}
			
	}

	/**
	 * Loads the data of a Row Data to Hash Map
	 * @param ColumnName
	 * @param ColumnValue
	 * @throws SQLException
	 */

	public void LoadRowDatatoMap(String ColumnName,String ColumnValue) throws SQLException { 
		
		 
		
		MapObj = new HashMap<String,List<String>>();
		
		switch(FileType)
		{
		case "excel":
			MapObj = ExlData.getRowData( ColumnName, ColumnValue);
			break;
		case "csv":
			MapObj = CsvData.getRowData(ColumnName, ColumnValue);
			break; 
		case "db":
			MapObj = DBdata.getRowData(ColumnName, ColumnValue);
			break; 
		case "xml":
			MapObj = XmlData.getRowData(ColumnName, ColumnValue);
			break; 
			
		}
			
	}

	/**
	 * To get the Value of Key from Hash Map
	 * by default returns the first row data from list
	 * @param Key
	 * @return
	 */
	public  String  getValue(String Key)
	{
		
		for (Map.Entry<String, List<String>> entry : MapObj.entrySet()) { 
			
			String k = entry.getKey();
		             
			 if(  k.trim().equalsIgnoreCase(Key.trim()) ){
				
					 return entry.getValue().get(0).trim(); 
			} 
		}
		return "";
		
	}
	/**
	 * To get the Value of Key from Hash Map	 
	 * @param Key
	 * @param index of list
	 * @return String
	 */
	public  String  getValue( String Key,int index)
	{
		
		for (Map.Entry<String, List<String>> entry : MapObj.entrySet()) { 
			
			String k = entry.getKey();
		             
			 if(  k.trim().equalsIgnoreCase(Key.trim()) ){
				
					 return entry.getValue().get(index).trim(); 
			} 
		}
		return "";
		
	}
	
	/**
	 * To get the HashMap List row count 
	 * @return size of the LIst of Strings
	 */
	public  int getHashMapRowCount( )
	{
		
		return MapObj.values().iterator().next().size(); 
		
	}

}
