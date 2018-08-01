package com.online5.tests.login;


import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import org.testng.annotations.Test;

import com.online5.pages.Homepage;
import com.online5.tests.TestBase;
import com.online5.utilities.ConfigurationReader;

public class LoginTests extends TestBase{
	
	

	@Test
	public void login() {
		extentLogger=report.createTest("Login Positive");
		driver.get(ConfigurationReader.getProperty("url"));
		Homepage homePage=new Homepage();
		homePage.LogIn.click();
		homePage.email.sendKeys(ConfigurationReader.getProperty("username"));
		homePage.pass.sendKeys(ConfigurationReader.getProperty("password"));
		homePage.loginButton.click();
		assertTrue(homePage.welcomeMessage.isDisplayed());	
		extentLogger.pass("passed");
		
	}
	
	@Test
	public void loginNegative() {
		extentLogger=report.createTest("Login Negative");
		driver.get(ConfigurationReader.getProperty("url"));
		Homepage homePage=new Homepage();
		homePage.LogIn.click();
		homePage.email.sendKeys(ConfigurationReader.getProperty("username"));
		homePage.pass.sendKeys("something");
		homePage.loginButton.click();
		String expectedMessage="Incorrect username or password. Please try again.";
		String actualMessage=homePage.errorMessage.getText();
		assertEquals(expectedMessage, actualMessage);	
		extentLogger.pass("passed");
	}
	
	

}
