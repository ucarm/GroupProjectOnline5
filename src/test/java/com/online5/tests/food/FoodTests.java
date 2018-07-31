package com.online5.tests.food;

import static org.testng.Assert.assertEquals;

import java.util.concurrent.TimeUnit;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.online5.pages.FoodMainPage;
import com.online5.tests.TestBase;
import com.online5.utilities.ConfigurationReader;

public class FoodTests extends TestBase {
	
	FoodMainPage foodPage ;
	
	@BeforeMethod
	public void navigateToFoodPage() {
		driver.get(ConfigurationReader.getProperty("url"));
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.manage().window().fullscreen();
		foodPage = new FoodMainPage();
		foodPage.FoodNavigation.click();
		
	}

	@Test
	public void foodSearchTestPositive() {
		//Navigate to Food Page
		//Assert that FoodMage title matches
		String actualTitle = driver.getTitle();
		String expectedTitle ="Calorie Chart, Nutrition Facts, Calories in Food | MyFitnessPal.com";
		assertEquals(actualTitle, expectedTitle);
		//Navigate to Food Search Page and get Result after searching for an apple
		foodPage = new FoodMainPage();
		foodPage.FoodSearchInput.sendKeys(ConfigurationReader.getProperty("foodToSearch"));
		foodPage.FoodSearchButton.click();
		String firstFoodResult = foodPage.FoodSearchResults.get(0).getText();
		System.out.println("First food from search result: "+firstFoodResult);
		
		//Check if all results contain the word searched (in this case "Apple")
		System.out.println("Food result list contains searched food: "+foodPage.checkIfResultsContainFood());
		assertEquals( foodPage.checkIfResultsContainFood(), true);
		}
	
	@Test
	public void foodSearchTestNegative() {
		foodPage = new FoodMainPage();
		foodPage.FoodSearchInput.sendKeys("fdsa");
		foodPage.FoodSearchButton.click();
		String actualSearchResult =foodPage.invalidFoodInput.getText();
		assertEquals(actualSearchResult, "No foods found.");
		System.out.println("Invalid food name entry search result: "+actualSearchResult);
	}
	
	
}
