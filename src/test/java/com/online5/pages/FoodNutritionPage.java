package com.online5.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.online5.utilities.Driver;

public class FoodNutritionPage {
	public FoodNutritionPage() {
		PageFactory.initElements(Driver.getDriver(), this);
	}
	
	@FindBy(id="food_entry_quantity")
	public WebElement FoodEntryQty;
	
	@FindBy(xpath="//div[@id='nutrition_info']//tr[1]//td[2]")
	public WebElement FoodCalorie;
}
