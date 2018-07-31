package com.online5.tests.exercise;

import static org.testng.Assert.*;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.online5.pages.ExerciseMainPage;
import com.online5.tests.TestBase;
import com.online5.utilities.BrowserUtils;
import com.online5.utilities.ConfigurationReader;

public class ExerciseTests extends TestBase {

	ExerciseMainPage exercisePage;

	@BeforeMethod
	public void navigateToExercisePage() {
		extentLogger.info("Navigate to the website");
		driver.get(ConfigurationReader.getProperty("url"));
		BrowserUtils.waitForPageToLoad(2);
		exercisePage = new ExerciseMainPage();
		exercisePage.exerciseButton.click();
	}
	
	@Test
	public void ExercisePageVerify() {
		extentLogger=report.createTest("EXERCISE Page Verification");
		String expectedTitle = "Calories Burned From Exercise | MyFitnessPal.com";
		String actualTitle = driver.getTitle();
		Assert.assertEquals(actualTitle, expectedTitle);
		extentLogger.info("Assert if actual Title matching with Expected Title");
		extentLogger.pass("Verified Expected Page Title is matching with Actual Title");
	
	}
	
	@Test
	public void ExerciseSearchBoxTest() {
		extentLogger=report.createTest("SEARCH Box Verification in Exercise Page");
		exercisePage.exerciseSearchInput.clear();
		exercisePage.exerciseSearchInput.sendKeys("tennis");
		exercisePage.exerciseSearchButton.click();
		assertTrue(exercisePage.dataInTheBox.getText().contains("tennis"));
		extentLogger.info("Testing Search Box Functionality in the Exercise Page with valid input");
		extentLogger.pass("Verified when user type <<tennis>> , it is displayed in matching exercises box");

	}
	
	@Test
	public void ExerciseSearchBoxNegative() {
		extentLogger=report.createTest("SEARCH Box in Exercise Page Verification with Invalid Input");
		exercisePage.exerciseSearchInput.clear();
		exercisePage.exerciseSearchInput.sendKeys("abcdefg");
		exercisePage.exerciseSearchButton.click();
		assertTrue(exercisePage.errorMsgInTheBox.isDisplayed());
		extentLogger.info("Verifying the Error Message in the Matching Exercise Box ");
		extentLogger.pass("Verified when user type <<abcdefg>> Error message is displayed in the Matching Exercise Box");

	}

	@Test
	public void ExerciseCaloriesBurnBox() {
		extentLogger=report.createTest("Matching Exercise Box and Calories Burned Box Verification");
		exercisePage.exerciseSearchInput.clear();
		exercisePage.exerciseSearchInput.sendKeys("tennis");
		extentLogger.info("Verifying SEARCH box functionality");
		exercisePage.exerciseSearchButton.click();
		extentLogger.info("Clicking on Serach Button");
		exercisePage.FirstInputInTheBox.click();
		extentLogger.info("Clicking on <<Tennis, double>> link");
		extentLogger.info("Verifying Matching Exercise data with Calories Burned Box data");
		Assert.assertTrue(exercisePage.caloriesBurnedBox.getText().contains("Tennis, double"));
		extentLogger.pass("Verified when user type <<tennis>> , it is displayed in matching exercises box");
		extentLogger.pass("Verified the user click on <<Tennis, double>>,  <<Tennis, double>> should also displayed in Calories Burned Box");

	}

	@AfterMethod
	public void tearDown() {
		driver.close();
	}
}
