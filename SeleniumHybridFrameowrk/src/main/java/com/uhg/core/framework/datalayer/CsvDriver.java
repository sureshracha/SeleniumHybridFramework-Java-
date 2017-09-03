package com.uhg.core.framework.datalayer;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.uhg.core.framework.utils.CsvReader;

/**
 * Driver class that extracts the data from "csv" file
 * 
 * @author kvarma1
 * 
 */
public class CsvDriver {

	CsvReader csvReader = null;

	public CsvDriver(String filePath) {
		this(new File(filePath));
	}

	public CsvDriver(File file) {
		try {
			csvReader = new CsvReader(file);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public CsvDriver(String filePath, char separator, char quotecharacter) {
		this(new File(filePath), separator, quotecharacter);
	}

	public CsvDriver(File file, char separator, char quotecharacter) {
		try {
			csvReader = new CsvReader(file, separator, quotecharacter);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public Map<String, List<String>> getData() {
		return this.getData(false);
	}

	public Map<String, List<String>> getData(Boolean executeFlag) {

		HashMap<String, List<String>> data = new HashMap<>();
		int rowCount = csvReader.getNoOfRows();
		int zeroRowColumns = csvReader.getNoOfColumns();
		List<Integer> rowsToSkip = new LinkedList<>();

		for (int column = 0; column < zeroRowColumns; column++) {
			String key = csvReader.getData(0, column);
			List<String> valueList = new ArrayList<>();

			for (int row = 1; row < rowCount; row++) {
				if (rowsToSkip.isEmpty() || !rowsToSkip.contains(row)) {
					String value = csvReader.getData(row, column);
					if (executeFlag && column == 0
							&& value.trim().equalsIgnoreCase("n")) {
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
		int rowNum  = csvReader.getRowNo(ColumnName, ColumnValue); 
		int columns = csvReader.getNoOfColumns();
		
		for (int column = 0; column < columns; column++) {
			String key = csvReader.getData(0, column);

			List<String> valueList = new ArrayList<>();

				String value = csvReader.getData(rowNum, column);
				 
						valueList.add(value);
					 
				 
			 
			data.put(key, valueList);
		}
		 
		// TODO Auto-generated method stub
		return data;
	}


}
