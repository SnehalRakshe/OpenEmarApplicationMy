package com.tieto.test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class DemoTest {
	@Test
	public void readProperties() throws IOException {
		FileInputStream file = new FileInputStream("testData/data.properties");
		Properties prop = new Properties();
		prop.load(file);
		String baseUrl = prop.getProperty("url");
		System.out.println(baseUrl);
		System.out.println(prop.getProperty("browser"));
	}

	// @Test
	public void readExcel() throws IOException {
		FileInputStream file = new FileInputStream("testData/OpenEmrData.xlsx");

		XSSFWorkbook book = new XSSFWorkbook(file);

		XSSFSheet sheet = book.getSheet("ValidCredentialData");

		int totalrows = sheet.getPhysicalNumberOfRows();
		int totalcols = sheet.getRow(0).getPhysicalNumberOfCells();
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
	}

	@DataProvider
	public Object[][] fillFormData() {
		Object[][] main = new Object[3][2];

		main[0][0] = "John";
		main[0][1] = "John123";

		main[1][0] = "peter";
		main[1][1] = "peter123";

		main[2][0] = "paul";
		main[2][1] = "paul123";

		return main;
	}

	@Test(dataProvider = "fillFormData")
	public void fillFormTest(String username, String password) {
		System.out.println(username + password);
	}

	@DataProvider
	public Object[][] fillInvalidData() {
		Object[][] main = new Object[2][4];

		main[0][0] = "john";
		main[0][1] = "john123";
		main[0][2] = "French (Standard)";
		main[0][3] = "Invalid username or password";

		main[1][0] = "peter";
		main[1][1] = "peter123";
		main[1][2] = "English (Indian)";
		main[1][3] = "Invalid username or password";

		return main;

	}

	@Test(dataProvider = "fillInvalidData")
	public void invalidTest(String username, String password, String language, String expectedvalue) {
		System.out.println(username + password + language + expectedvalue);
	}
}
