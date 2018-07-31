package com.online5.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.online5.utilities.Driver;

public class DiscussionPage {
	public DiscussionPage() {
		PageFactory.initElements(Driver.getDriver(), this);
	}
	
	@FindBy(xpath="//div[@class='MessageList Discussion']//h1")
	public WebElement header;
	
	@FindBy(xpath="//div[@class='MessageList Discussion']//span[@class='Author']/a[@class='Username']")
	public WebElement discussionCreator;
	
}
