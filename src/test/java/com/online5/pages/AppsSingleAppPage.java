package com.online5.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.online5.utilities.Driver;

public class AppsSingleAppPage {
	public AppsSingleAppPage() {
		PageFactory.initElements(Driver.getDriver(), this);
	}
	
	@FindBy(xpath="//ul[@class='app-breadcrumbs']")
	public WebElement breadcrumbs;
	
	@FindBy(xpath="//h2[@class='app-name']")
	public WebElement appName;
	
	@FindBy(xpath="//a[@class='connect-button']")
	public WebElement connectButton;
	
	@FindBy(xpath="//a[@class='get-app-button btn']")
	public WebElement getButton;
}
