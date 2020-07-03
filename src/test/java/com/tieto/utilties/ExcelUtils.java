package com.tieto.utilties;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtils {
	public static Object[][] getFileIntoObject(String fileDetail, String sheetName) throws IOException {
		FileInputStream file = new FileInputStream(fileDetail);
		XSSFWorkbook book = new XSSFWorkbook(file);
		XSSFSheet sheet = book.getSheet(sheetName);

		int totalrows = sheet.getPhysicalNumberOfRows();
		int totalcols = sheet.getRow(0).getPhysicalNumberOfCells();
		Object[][] main = new Object[totalrows - 1][totalcols];

		for (int rowIndex = 0; rowIndex < totalrows; rowIndex++) {
			XSSFRow row = sheet.getRow(rowIndex);
			
			for (int colIndex = 0; colIndex < totalcols; colIndex++) {
				XSSFCell cell = row.getCell(colIndex);
				DataFormatter format = new DataFormatter();
				String cellValue = format.formatCellValue(cell);
				System.out.println(cellValue);
			}

		}

		book.close();
		file.close();
		return main;
	}

}
