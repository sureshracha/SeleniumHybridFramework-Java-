package com.uhg.core.framework.datalayer;

 

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.uhg.core.framework.utils.DBReader;
 
/**
 * Data will be loaded from db table to Hash Map
 * 
 * @author sracha
 *
 */
public class dbDriver {
	
	DBReader dbReader = null;

 

	public dbDriver(String dbDriver,String dbURL,String user,String pwd,String sqlStatement) throws ClassNotFoundException, SQLException {
		dbReader = new DBReader(dbDriver,dbURL,user,pwd);
		dbReader.getConnection();
		dbReader.getResultSet(sqlStatement);
	}
 
	 /**
	  * Returns the result set as Map
	  * @return Hash map Map<String, List<String>>
	  * @throws SQLException
	  */
	public Map<String, List<String>> getData() throws SQLException {
		
		 
		
		HashMap<String, List<String>> data = new HashMap<>();
		long rowCount = dbReader.getNoOfRows();
		int zeroRowColumns = dbReader.getNoOfColumns();
		List<Integer> rowsToSkip = new LinkedList<>();

		for (int column = 0; column < zeroRowColumns; column++) {
			String key = dbReader.getData(0, column);

			List<String> valueList = new ArrayList<>();

			for (int row = 1; row < rowCount; row++) {
				if (rowsToSkip.isEmpty() || !rowsToSkip.contains(row)) {
					String value = dbReader.getData(row, column);
					 
						valueList.add(value);
					}
				}
			 
			data.put(key, valueList);
		}
		// TODO Auto-generated method stub
		return data;
	}
	
	/**
	 * Returns the the Row Data of result set
	 * @param ColumnName
	 * @param ColumnValue
	 * @return Hash map Map<String, List<String>>
	 * @throws SQLException 
	 */
	
	public Map<String, List<String>> getRowData(String ColumnName,String ColumnValue) throws SQLException {
		
		
		HashMap<String, List<String>> data = new HashMap<>();
		int rowNum  = dbReader.getRowNo(ColumnName, ColumnValue); 
		int columns = dbReader.getNoOfColumns();
		
		for (int column = 0; column < columns; column++) {
			String key = dbReader.getData(0, column);

			List<String> valueList = new ArrayList<>();

				String value = dbReader.getData(rowNum, column);
				 
						valueList.add(value);
					 
				 
			 
			data.put(key, valueList);
		}
		 
		// TODO Auto-generated method stub
		return data;
	}

}
