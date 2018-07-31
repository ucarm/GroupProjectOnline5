package com.online5.pages;


import static org.testng.Assert.assertTrue;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import com.online5.utilities.ConfigurationReader;
import com.online5.utilities.Driver;

public class Homepage {
	public Homepage() {
		PageFactory.initElements(Driver.getDriver(), this);
	}
	
	@FindBy(xpath="//a[.='Community']")
	public WebElement communityLink;
	
	@FindBy(xpath="//a[.='Apps']")
	public WebElement AppsLink;
	
	@FindBy(xpath="//a[.='Log In']")
	public WebElement LogIn;
	
	@FindBy(xpath="//input[@name='username']")
	public WebElement email;
	
	@FindBy(xpath="//input[@name='password']")
	public WebElement pass;
	
	

	@FindBy(xpath="//input[@value='Log In']")
	public WebElement loginButton;
	
	@FindBy(id="navTop")
	public WebElement welcomeMessage;

	@FindBy(xpath="	//p[@class='flash']")
	public WebElement errorMessage;
	
	

	
	public void login() {
		LogIn.click();
		email.sendKeys(ConfigurationReader.getProperty("username"));
		pass.sendKeys(ConfigurationReader.getProperty("password"));
		loginButton.click();	
	}
	
		
}
