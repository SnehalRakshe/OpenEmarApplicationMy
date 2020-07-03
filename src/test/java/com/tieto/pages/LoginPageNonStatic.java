package com.tieto.pages;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

public class LoginPageNonStatic {
	private By usernameLoc = By.id("authUser");
	private By passwordLoc = By.id("clearPass");
	private By languageLoc = By.name("languageChoice");
	private By submitLoc = By.xpath("//button[@type='submit']");
	private By alertLoc = By.xpath("//div[@class=\"alert alert-danger login-failure m-1\"]");

	WebDriver driver;
	public LoginPageNonStatic(WebDriver driver)
	{
		this.driver = driver;
	}

	public void enterUserName(String username) {
		driver.findElement(usernameLoc).sendKeys(username);
	}

	public void enterPassword(String password) {
		driver.findElement(passwordLoc).sendKeys(password);
	}
	
	public void selectLanguage(String language)
	{
		Select selectLanguage = new Select(driver.findElement(languageLoc));	
		selectLanguage.selectByVisibleText(language);			
	}
	
	public void clickOnLogin()
	{
		driver.findElement(submitLoc).click();
	}
	
	public String getErrorMessage()
	{
		Alert alert = driver.switchTo().alert();
		String alertText = alert.getText();
		driver.switchTo().defaultContent();
		return alertText;
	}
	
	public String getErrorText()
	{
		return driver.findElement(alertLoc).getText();
	}
}
