package com.core.framework.datalayer;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.core.framework.utils.XmlReader;

/**
 * Driver class that extracts the data from "xml" file
 * 
 * @author sracha
 * 
 */
public class XmlDriver {

	XmlReader xmlReader = null;

	public XmlDriver(String filePath) {
		this(new File(filePath));
	}

	public XmlDriver(File file) {
		try {
			xmlReader = new XmlReader(file);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public Map<String, List<String>> getData() {
		return this.getData(false);
	}

	public Map<String, List<String>> getData(Boolean executeFlag) {

		HashMap<String, List<String>> data = new HashMap<>();
		int rowCount = xmlReader.getNoOfRows();
		int zeroRowColumns = xmlReader.getNoOfColumns();
		List<Integer> rowsToSkip = new LinkedList<>();

		for (int column = 0; column < zeroRowColumns; column++) {
			String key = xmlReader.getColumnName(0, column);
			List<String> valueList = new ArrayList<>();

			for (int row = 0; row < rowCount; row++) {
				if (rowsToSkip.isEmpty() || !rowsToSkip.contains(row)) {
					String value = xmlReader.getColumnValue(row, column);
					if (executeFlag && column == 0
							&& value.trim().equalsIgnoreCase("false")) {
						rowsToSkip.add(row);
					} else {
						valueList.add(value);
					}
				}
			}
			data.put(key, valueList);
		}
		return data;
	}
	
	
	/**
	 * Returns the the Row Data
	 * @param ColumnName
	 * @param ColumnValue
	 * @return Hash map
	 */
	
	public Map<String, List<String>> getRowData(String ColumnName,String ColumnValue) {
		HashMap<String, List<String>> data = new HashMap<>();
		int rowNum  = xmlReader.getRowNo(ColumnName, ColumnValue); 
		int columns = xmlReader.getNoOfColumns();
		
		for (int column = 0; column < columns; column++) {
			String key = xmlReader.getColumnValue(0, column);

			List<String> valueList = new ArrayList<>();

				String value = xmlReader.getColumnValue(rowNum, column);
				 
						valueList.add(value);
					 
				 
			 
			data.put(key, valueList);
		}
		 
		// TODO Auto-generated method stub
		return data;
	}

}
