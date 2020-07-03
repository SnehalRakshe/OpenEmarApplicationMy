package com.tieto.test;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.tieto.base.WebDriverWrapper;
import com.tieto.utilties.ExcelUtils;

public class AddPatientTest extends WebDriverWrapper {
	@DataProvider
	public Object[][] createPatientData() throws IOException
	{
		Object[][] main = ExcelUtils.getFileIntoObject("testData/OpenEmrData.xlsx","CreatePatientData") ;
		return main;
	}
	@Test
	public void createPatientTest()
	{
		driver.findElement(By.id("authUser")).sendKeys("admin");	
		driver.findElement(By.id("clearPass")).sendKeys("pass");
		Select selectLanguage = new Select(driver.findElement(By.name("languageChoice")));	
		selectLanguage.selectByVisibleText("English (Indian)");	
		driver.findElement(By.xpath("//button[@type='submit']")).click();
	}
}
