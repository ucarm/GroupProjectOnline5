package com.online5.tests.food;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.util.concurrent.TimeUnit;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.online5.pages.FoodMainPage;
import com.online5.pages.FoodNutritionPage;
import com.online5.tests.TestBase;
import com.online5.utilities.ConfigurationReader;

public class FoodTests extends TestBase {
	
	FoodMainPage foodPage ;
	FoodNutritionPage nutritionTable;
	
	@BeforeMethod
	public void zNavigateToFoodPage() {
		driver.get(ConfigurationReader.getProperty("url"));
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.manage().window().fullscreen();
		foodPage = new FoodMainPage();
		foodPage.FoodNavigation.click();
		
	}

	@Test (priority=1)
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
		//assertEquals( foodPage.checkIfResultsContainFood(), true);
		assertTrue( foodPage.checkIfResultsContainFood());
		}
	
	@Test(priority=2)
	public void foodSearchTestNegative() {
		foodPage = new FoodMainPage();
		foodPage.FoodSearchInput.sendKeys("fdsa");
		foodPage.FoodSearchButton.click();
		String actualSearchResult =foodPage.invalidFoodInput.getText();
		assertEquals(actualSearchResult, "No foods found.");
		System.out.println("Invalid food name entry search result: "+actualSearchResult);
	}
	
	@Test(priority=3)
	public void nutritionTablePositve() throws InterruptedException {
		foodPage = new FoodMainPage();
		foodPage.FoodSearchInput.sendKeys(ConfigurationReader.getProperty("foodToSearch"));
		foodPage.FoodSearchButton.click();
		//Navigate to first food's nutrition table
		foodPage.FoodSearchResults.get(0).click();
		
		nutritionTable = new FoodNutritionPage();
		nutritionTable.FoodEntryQty.clear();
		nutritionTable.FoodEntryQty.sendKeys("1");
		Thread.sleep(300);
		int initialFoodCalorie = Integer.parseInt(nutritionTable.FoodCalorie.getText());
		//change food serving qty to "2"
		nutritionTable.FoodEntryQty.clear();
		Thread.sleep(300);
		nutritionTable.FoodEntryQty.sendKeys("2");
		Thread.sleep(300);
		int updatedFoodCalorie = Integer.parseInt(nutritionTable.FoodCalorie.getText());
		assertEquals(updatedFoodCalorie, initialFoodCalorie*2);
		//assertEquals(actual, expected);
		System.out.println("Initial food Calories: "+initialFoodCalorie);
		System.out.println("Updated food Calories:"+updatedFoodCalorie);
				
	}
	
	@Test(priority=4)
	public void nutritionTableNegative() throws InterruptedException {
		foodPage = new FoodMainPage();
		foodPage.FoodSearchInput.sendKeys(ConfigurationReader.getProperty("foodToSearch"));
		foodPage.FoodSearchButton.click();
		//Navigate to first food's nutrition table
		foodPage.FoodSearchResults.get(0).click();
		nutritionTable = new FoodNutritionPage();
		nutritionTable.FoodEntryQty.clear();
		//Enter invalid qty
		nutritionTable.FoodEntryQty.sendKeys("abc");
		Thread.sleep(300);
		int zeroCalories = Integer.parseInt(nutritionTable.FoodCalorie.getText());
		
		assertEquals(zeroCalories, 0);
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
