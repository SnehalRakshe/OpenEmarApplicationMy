package com.tieto.test;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.tieto.base.WebDriverWrapper;
import com.tieto.pages.LoginPage;
import com.tieto.pages.LoginPageNonStatic;
import com.tieto.utilties.ExcelUtils;

public class LoginTest extends WebDriverWrapper {
	
	@Test
	public void checkLinkCount()
	{
		int actualValue = driver.findElements(By.tagName("a")).size();
		Assert.assertEquals(actualValue, 1);
	}
	
	@Test(priority=2, invocationCount=2)
	
	public void invalidCrendetailTest()
	{		
		driver.findElement(By.id("authUser")).sendKeys("admin123");	
		driver.findElement(By.id("clearPass")).sendKeys("pass");
		Select selectLanguage = new Select(driver.findElement(By.name("languageChoice")));	
		selectLanguage.selectByVisibleText("English (Indian)");	
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		
		String actualTitle=driver.findElement(By.xpath("/div[contains(text(),'Invalid')]")).getText();
		
		//Assert.assertEquals(actualTitle.trim(),"Invalid");
		//equal methoud-- we have given dot.. invalid.
		
		Assert.assertTrue(actualTitle.contains("Invalid username or password"));
	}

	@Test(priority=1)
	
	public void ValidCrentialTest()
	{
		//C:\\SeleniumTraining\\TestMavenProject\\driver\\chromedriver.exe -- absulate path  and used one is relative path
		driver.findElement(By.id("authUser")).sendKeys("admin");	
		driver.findElement(By.id("clearPass")).sendKeys("pass");
		
		LoginPage.enterUserName(driver, "admin");
		LoginPage.enterPassword(driver, "pass");
		
		LoginPageNonStatic objLoginPage = new LoginPageNonStatic(driver);
		objLoginPage.enterUserName("admin");
		objLoginPage.enterPassword("pass");
		objLoginPage.selectLanguage("English (Indian)");
		
		/*
		 * Select selectLanguage = new
		 * Select(driver.findElement(By.name("languageChoice")));
		 * selectLanguage.selectByVisibleText("English (Indian)");
		 * driver.findElement(By.xpath("//button[@type='submit']")).click();
		 */
		
		WebDriverWait wait=new WebDriverWait(driver,40);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[text()='Message Center']")));
		
		String actualTitle=driver.getTitle();
		System.out.println(actualTitle);
		Reporter.log(actualTitle);
		Assert.assertEquals(actualTitle,"OpenEMR");	    
	}
	
	@DataProvider
	public Object[][] fillInvalidData()
	{
		Object[][] main = new Object[2][4];
		
		main[0][0]="john";
		main[0][1]="john123";
		main[0][2]="French (Standard)";
		main[0][3]="Invalid username or password";
		
		main[1][0]="peter";
		main[1][1]="peter123";
		main[1][2]="English (Indian)";
		main[1][3]="Invalid username or password";
		
		return main;
		
	}
	
	@Test(dataProvider="fillInvalidData")
	public void invalidCrendetailTestDataProvider(String username, String password, String language, String expectedvalue)
	{
		LoginPageNonStatic objLoginPage = new LoginPageNonStatic(driver);
		objLoginPage.enterUserName(username);
		objLoginPage.enterPassword(password);
		objLoginPage.selectLanguage(language);
		objLoginPage.clickOnLogin();
		String alertText = objLoginPage.getErrorText();
		System.out.println(alertText);
		Assert.assertEquals(alertText, expectedvalue);		
	}	
	
	@DataProvider
	public Object[][] invalidCredentialData() throws IOException
	{
		Object[][] main = ExcelUtils.getFileIntoObject("testData/OpenEmrData.xlsx","ValidCredentialData") ;
		return main;
	}
	
	@Test(dataProvider="invalidCredentialData")
	public void invalidCrendetailTestDataProviderExcel(String username, String password, String language, String expectedvalue)
	{
		LoginPageNonStatic objLoginPage = new LoginPageNonStatic(driver);
		objLoginPage.enterUserName(username);
		objLoginPage.enterPassword(password);
		objLoginPage.selectLanguage(language);
		objLoginPage.clickOnLogin();
		String alertText = objLoginPage.getErrorText();
		System.out.println(alertText);
		Assert.assertEquals(alertText, expectedvalue);		
	}	
	
}
