package com.online5.pages;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.online5.utilities.Driver;

public class AppsSearchPage {
	public AppsSearchPage() {
		PageFactory.initElements(Driver.getDriver(), this);
	}
	
	@FindBy(xpath="//h2[.='Search Results']")
	public WebElement searchResultHeader;

	@FindBy(xpath="//div[@class='app-name']/a")
	public List<WebElement> searchResultsAppNames;
	
	@FindBy(xpath="//div[@class='search-text']")
	public WebElement searchResultText;
	
	@FindBy(xpath="//a[@class='btn app-cta']")
	public List<WebElement> buttonsGetBuy;

	public int searchResultCount() {
		String[] words= searchResultText.getText().trim().split(" ");
		return Integer.parseInt(words[0]);
	}
	
	
	
	
	
}
