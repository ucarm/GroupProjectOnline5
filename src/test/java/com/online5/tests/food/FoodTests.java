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

  FoodMainPage foodPage;
  FoodNutritionPage nutritionTable;

  @BeforeMethod
  public void zNavigateToFoodPage() {
    driver.get(ConfigurationReader.getProperty("url"));
    driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    driver.manage().window().fullscreen();
    foodPage = new FoodMainPage();
    foodPage.FoodNavigation.click();
  }

  @Test
  public void foodSearchTestPositive() {
    extentLogger = report.createTest("Food Search Test: Positve Scenario.");
    extentLogger.info("Navigating to Food page");
    // Navigate to Food Page
    // Assert that FoodMage title matches
    String actualTitle = driver.getTitle();
    String expectedTitle = "Calorie Chart, Nutrition Facts, Calories in Food | MyFitnessPal.com";
    assertEquals(actualTitle, expectedTitle);
    extentLogger.pass("Verified Food page title matches the expected title");

    // Navigate to Food Search Page and get Result after searching for an apple
    // foodPage = new FoodMainPage();
    foodPage.FoodSearchInput.sendKeys(ConfigurationReader.getProperty("foodToSearch"));
    foodPage.FoodSearchButton.click();
    String firstFoodResult = foodPage.FoodSearchResults.get(0).getText();
    System.out.println("First food from search result: " + firstFoodResult);

    extentLogger.info(
        "Searching for certain food and verifying if search results contain the food name.");
    // Check if all results contain the word searched (in this case "Apple")
    System.out.println(
        "Food result list contains searched food: " + foodPage.checkIfResultsContainFood());
    // assertEquals( foodPage.checkIfResultsContainFood(), true);
    assertTrue(foodPage.checkIfResultsContainFood());
    extentLogger.pass("Verified that all search results contain the searched food name.");
  }

  @Test
  public void foodSearchTestNegative() {
    extentLogger = report.createTest("Food Search Test: Negative Scenario.");
    extentLogger.info("Navigating to Food page.");

    // foodPage = new FoodMainPage();
    extentLogger.pass("Searching an invalid food name.");
    foodPage.FoodSearchInput.sendKeys("fdsa");
    foodPage.FoodSearchButton.click();
    String actualSearchResult = foodPage.invalidFoodInput.getText();
    assertEquals(actualSearchResult, "No foods found.");
    System.out.println("Invalid food name entry search result: " + actualSearchResult);
    extentLogger.pass("Verified that invalid food name search gives [No foods found.] result");
  }

  @Test
  public void nutritionTablePositve() throws InterruptedException {
    extentLogger = report.createTest("Nutrition Table: Positive Scenario.");
    extentLogger.info("Navigating to Food page.");

    // foodPage = new FoodMainPage();
    foodPage.FoodSearchInput.sendKeys(ConfigurationReader.getProperty("foodToSearch"));
    foodPage.FoodSearchButton.click();
    // Navigate to first food's nutrition table
    foodPage.FoodSearchResults.get(0).click();

    extentLogger.info("Searching for food and getting it's Nutrition Table.");
    nutritionTable = new FoodNutritionPage();
    nutritionTable.FoodEntryQty.clear();
    nutritionTable.FoodEntryQty.sendKeys("1");
    Thread.sleep(300);
    int initialFoodCalorie = Integer.parseInt(nutritionTable.FoodCalorie.getText());
    // change food serving qty to "2"
    nutritionTable.FoodEntryQty.clear();
    Thread.sleep(300);
    extentLogger.info("Changing food serving quantity to see Calories change.");
    nutritionTable.FoodEntryQty.sendKeys("2");
    Thread.sleep(300);
    int updatedFoodCalorie = Integer.parseInt(nutritionTable.FoodCalorie.getText());
    assertEquals(updatedFoodCalorie, initialFoodCalorie * 2);
    // assertEquals(actual, expected);
    System.out.println("Initial food Calories: " + initialFoodCalorie);
    System.out.println("Updated food Calories:" + updatedFoodCalorie);
    extentLogger.pass("Verified that doubling food serving qty doubles it's Calories.");
  }

  @Test
  public void nutritionTableNegative() throws InterruptedException {
    extentLogger = report.createTest("Nutrition Table: Negative Scenario.");
    extentLogger.info("Navigating to Food page.");

    // foodPage = new FoodMainPage();
    foodPage.FoodSearchInput.sendKeys(ConfigurationReader.getProperty("foodToSearch"));
    foodPage.FoodSearchButton.click();

    extentLogger.info("Searching for food and getting it's Nutrition Table.");
    // Navigate to first food's nutrition table
    foodPage.FoodSearchResults.get(0).click();
    nutritionTable = new FoodNutritionPage();
    nutritionTable.FoodEntryQty.clear();

    extentLogger.info("Entering invalid Quantity (stirng letters)");
    // Enter invalid qty
    nutritionTable.FoodEntryQty.sendKeys("abc");
    Thread.sleep(300);
    int zeroCalories = Integer.parseInt(nutritionTable.FoodCalorie.getText());

    assertEquals(zeroCalories, 0);
    extentLogger.pass("Verified that entering an invalid quantity results in zero Calories.");
  }
}
