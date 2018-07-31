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
	public void foodPageDisplayTest()  {
		//Navigate to Food Page
		//Assert that FoodMage title matches
		String actualTitle = driver.getTitle();
		String expectedTitle ="Calorie Chart, Nutrition Facts, Calories in Food | MyFitnessPal.com";
		assertEquals(actualTitle, expectedTitle);
		
	}
	
	@Test
	public void foodSearchTest() {
		foodPage = new FoodMainPage();
		foodPage.FoodSearchInput.sendKeys("Apple");
		foodPage.FoodSearchButton.click();
		String firstFoodResult = foodPage.FoodSearchResults.get(0).getText();
		System.out.println(firstFoodResult);
		
	}
}
