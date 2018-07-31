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
		driver.get(ConfigurationReader.getProperty("url"));
		BrowserUtils.waitForPageToLoad(2);
		exercisePage = new ExerciseMainPage();
		exercisePage.exerciseButton.click();
	}
	
	@Test
	public void ExercisePageVerify() {
		String expectedTitle = "Calories Burned From Exercise | MyFitnessPal.com";
		String actualTitle = driver.getTitle();
		Assert.assertEquals(actualTitle, expectedTitle);
	
	}
	
	@Test
	public void ExerciseSearchBoxTest() {
		exercisePage.exerciseSearchInput.clear();
		exercisePage.exerciseSearchInput.sendKeys("tennis");
		exercisePage.exerciseSearchButton.click();
		assertTrue(exercisePage.dataInTheBox.getText().contains("tennis"));

	}
	
	@Test
	public void ExerciseSearchBoxNegative() {
		exercisePage.exerciseSearchInput.clear();
		exercisePage.exerciseSearchInput.sendKeys("abcdefg");
		exercisePage.exerciseSearchButton.click();
		assertTrue(exercisePage.errorMsgInTheBox.isDisplayed());

	}

	@Test
	public void ExerciseCaloriesBurnBox() throws InterruptedException {
		exercisePage.exerciseSearchInput.clear();
		exercisePage.exerciseSearchInput.sendKeys("tennis");
		exercisePage.exerciseSearchButton.click();
		exercisePage.FirstInputInTheBox.click();
		Thread.sleep(5000);	
		Assert.assertTrue(exercisePage.caloriesBurnedBox.getText().contains("Tennis, double"));
	}

	@AfterMethod
	public void tearDown() {
		driver.close();
	}
}
