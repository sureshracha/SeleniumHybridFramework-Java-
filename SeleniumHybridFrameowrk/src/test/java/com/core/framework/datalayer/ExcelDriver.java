package com.core.framework.datalayer;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.core.framework.utils.ExcelReader;

/**
 * Driver class that extracts the data from "xls" or "xlsx" file
 * 
 * @author sracha
 * 
 */
public class ExcelDriver {

	ExcelReader excelReader = null;

	public ExcelDriver(String filePath) {
		this(new File(filePath));
	}

	public ExcelDriver(File file) {
		try {
			excelReader = new ExcelReader(file);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public ExcelDriver(String filePath, int sheetNo) {
		this(new File(filePath), sheetNo);
	}

	public ExcelDriver(File file, int sheetNo) {
		try {
			excelReader = new ExcelReader(file, sheetNo);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public Map<String, List<String>> getData() {
		return this.getData(false);
	}

	public Map<String, List<String>> getData(Boolean executeFlag) {
		HashMap<String, List<String>> data = new HashMap<>();
		int rowCount = excelReader.getNoOfRows();
		int zeroRowColumns = excelReader.getNoOfColumns();
		List<Integer> rowsToSkip = new LinkedList<>();

		for (int column = 0; column < zeroRowColumns; column++) {
			String key = excelReader.getData(0, column);

			List<String> valueList = new ArrayList<>();

			for (int row = 1; row < rowCount; row++) {
				if (rowsToSkip.isEmpty() || !rowsToSkip.contains(row)) {
					String value = excelReader.getData(row, column);
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
		// TODO Auto-generated method stub
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
		int rowNum  = excelReader.getRowNo(ColumnName, ColumnValue); 
		int columns = excelReader.getNoOfColumns();
		
		for (int column = 0; column < columns; column++) {
			String key = excelReader.getData(0, column);

			List<String> valueList = new ArrayList<>();

				String value = excelReader.getData(rowNum, column);
				 
						valueList.add(value);
					 
				 
			 
			data.put(key, valueList);
		}
		 
		// TODO Auto-generated method stub
		return data;
	}

}
