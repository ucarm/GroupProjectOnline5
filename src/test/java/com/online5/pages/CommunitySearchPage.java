package com.online5.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.online5.utilities.Driver;

public class CommunitySearchPage {
	public CommunitySearchPage() {
		PageFactory.initElements(Driver.getDriver(), this);
	}
	
	@FindBy(id="Form_search")
	public WebElement searchBox;
	
	@FindBy(xpath="//button[@title='Search']")
	public WebElement searchButton;
	
	
}
