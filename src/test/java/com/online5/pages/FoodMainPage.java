package com.online5.pages;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.online5.utilities.Driver;

public class FoodMainPage {
	public FoodMainPage() {
		PageFactory.initElements(Driver.getDriver(), this);
	}
	
	@FindBy(xpath="//a[@href='/food/calorie-chart-nutrition-facts']")
	public WebElement FoodNavigation;
	
	@FindBy(className="text long")
	public WebElement FoodSearchInput;
	
	@FindBy(xpath="//input[@value='Search']")
	public WebElement FoodSearchButton;
	
	@FindBy(xpath="//ul[@class='food_search_results']//a[1]")
	public List<WebElement> FoodSearchResults;
	
}
