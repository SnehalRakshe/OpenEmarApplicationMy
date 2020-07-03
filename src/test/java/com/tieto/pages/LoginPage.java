package com.tieto.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {

	private static By usernameLoc = By.id("authUser");
	private static By passwordLoc = By.id("Pass");

	public static void enterUserName(WebDriver driver, String username) {
		driver.findElement(usernameLoc).sendKeys(username);
	}

	public static void enterPassword(WebDriver driver, String password) {
		driver.findElement(passwordLoc).sendKeys(password);
	}

}
