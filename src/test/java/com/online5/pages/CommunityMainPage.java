package com.online5.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.online5.utilities.Driver;

public class CommunityMainPage {
	public CommunityMainPage() {
		PageFactory.initElements(Driver.getDriver(), this);
	}
	
	@FindBy(xpath="//a/span[.='Message Boards']")
	public WebElement pageHeader;
	
	@FindBy(xpath="//table[@class='DataTable CategoryTable']/tbody//td[@class='CategoryName']//h3/a")
	public List<WebElement> mainTopics;
	
	public WebElement findTopic(String name) {
		return Driver.getDriver().findElement(By.xpath("//table[@class='DataTable CategoryTable']/tbody//td[@class='CategoryName']//h3/a[.='" + name + "']"));
	}
}
