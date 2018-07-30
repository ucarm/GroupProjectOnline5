package com.online5.pages;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.online5.utilities.Driver;

public class CommunitySearchResultPage {
	public CommunitySearchResultPage() {
		PageFactory.initElements(Driver.getDriver(), this);
	}
	
	@FindBy(xpath="//ol[@id='search-results']//h3/a")
	public List<WebElement> resultTitles;
}
