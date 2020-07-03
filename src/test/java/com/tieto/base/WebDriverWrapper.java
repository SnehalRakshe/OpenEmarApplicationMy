package com.tieto.base;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import com.tieto.utilties.PropUtils;

public class WebDriverWrapper {
	protected WebDriver driver;
	
	@Parameters({"browser"})
	@BeforeMethod
	public void init(@Optional("ch")String browserName) throws IOException
	{
		System.out.println(browserName);
		System.setProperty("webdriver.chrome.driver", "driver/chromedriver.exe");
		System.setProperty("webdriver.gecko.driver", "driver/geckodriver.exe");
		//System.setProperty("webdriver.ie.driver", "driver/IEDriverServer.exe");
		
		//WebDriver driver=new FirefoxDriver();//open the firefoxdriver	
		
		if(browserName.toLowerCase().equals("ch"))
		{
			driver=new ChromeDriver();//open the chromedriver	
		}
		else if(browserName.toLowerCase().equals("ff"))
		{
			driver= new FirefoxDriver();
		}
		else if(browserName.toLowerCase()=="ie")
		{
			
		}
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		String baseUrl = PropUtils.getValueFromKey("testData/data.properties", "url");
		driver.get(baseUrl);
	}
	
	@AfterMethod
	public void end()
	{
		driver.quit();
	}
}
