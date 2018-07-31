package com.online5.tests.apps;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import com.online5.pages.AppsPage;
import com.online5.pages.AppsSearchPage;
import com.online5.pages.AppsSingleAppPage;
import com.online5.pages.Homepage;
import com.online5.tests.TestBase;
import com.online5.utilities.BrowserUtils;
import com.online5.utilities.ConfigurationReader;

public class AppTests extends TestBase {
	AppsPage appsPage;
	Homepage homePage;
	AppsSearchPage appsSearchPage;
	AppsSingleAppPage singleApp;

	@Test
	public void SPA_1072_searchAnAppName() {
		extentLogger=report.createTest("APPS - Search an application name - Positive");
		homePage = new Homepage();
		
		extentLogger.info("Navigate to the website");
		driver.get(ConfigurationReader.getProperty("url"));
		BrowserUtils.waitForPageToLoad(2);

		// assert that homepage titles matches
		extentLogger.info("Verify the website title");
		assertEquals(driver.getTitle(), "Free Calorie Counter, Diet & Exercise Journal | MyFitnessPal.com");
		extentLogger.pass("Verified the website title: 'Free Calorie Counter, Diet & Exercise Journal | MyFitnessPal.com'");
		
		// click on Apps menu
		extentLogger.info("Click on the \"Apps\" menu item");
		homePage.AppsLink.click();
		appsPage = new AppsPage();

		// assert if we see App Marketplace on the page
		extentLogger.info("Verify \"App Marketplace\" text is visible on the page");
		assertEquals(appsPage.appMarketPlaceTitle.getText(), "App Marketplace",
				"H2 text does not match on search page");
		extentLogger.pass("Verified the page header title");
		
		// select a random featured app
		WebElement randomApp = appsPage.getRandomFeaturedApp();

		// search on the searchbox on appspage
		String appName = randomApp.getText();
		extentLogger.info("Select one the a random app under \"App MarketPlace\" AppName: "+appName);
		appsPage.appSearch.sendKeys(appName + Keys.ENTER);
		appsSearchPage = new AppsSearchPage();

		// Assert if the page header is "Search Result"
		extentLogger.info("Assert if we see 'Search Results' in search results page")
		assertTrue(appsSearchPage.searchResultHeader.getText().contains("Search Results"));
		extentLogger.pass("Verified Search Results' in search results page");

		// Assert if more than one result is found
		extentLogger.info("Assert if we get more than 0 result count");
		assertTrue(appsSearchPage.searchResultCount() > 0);
		extentLogger.pass("Verified that we have more than 0 search results");
		// Assert if the correct app name is displayed in 1st place search results
		String actualAppName = appsSearchPage.searchResultsAppNames.get(0).getText();
		extentLogger.info("Assert if 1st result has the name of the app");
		assertEquals(actualAppName, appName);
		extentLogger.pass("Verified 1st result has the name of the app");
		
		// Assert if BUY or GET button is displayed
		String actualButtonText = appsSearchPage.buttonsGetBuy.get(0).getText();
		extentLogger.info("Assert if Buy/Get button is visible");
		assertTrue(actualButtonText.contains("BUY") || actualButtonText.contains("GET"));
		extentLogger.pass("Verified Buy/Get button is visible");


	}

	@Test
	public void SPA_1073_searchAnAppNameNegative() {
		homePage = new Homepage();
		driver.get(ConfigurationReader.getProperty("url"));
		BrowserUtils.waitForPageToLoad(2);

		// assert that homepage titles matches
		assertEquals(driver.getTitle(), "Free Calorie Counter, Diet & Exercise Journal | MyFitnessPal.com");

		// click on Apps menu
		homePage.AppsLink.click();

		appsPage = new AppsPage();
		// assert if we see App Marketplace on the page
		assertEquals(appsPage.appMarketPlaceTitle.getText(), "App Marketplace",
				"H2 text does not match on search page");

		// choose a wrong search item name
		String appName = "aaazzz";

		// search on the searchbox on appspage
		appsPage.appSearch.sendKeys(appName + Keys.ENTER);
		appsSearchPage = new AppsSearchPage();

		// Assert if the page header is "Search Result"
		assertTrue(appsSearchPage.searchResultHeader.getText().contains("Search Results"));

		// Assert if "0" items are found
		assertTrue(appsSearchPage.searchResultCount() == 0);


	}

	@Test
	public void SPA_1086_selectAnApp() {
		homePage = new Homepage();
		driver.get(ConfigurationReader.getProperty("url"));
		BrowserUtils.waitForPageToLoad(2);

		// assert that homepage titles matches
		assertEquals(driver.getTitle(), "Free Calorie Counter, Diet & Exercise Journal | MyFitnessPal.com");

		// click on Apps menu
		homePage.AppsLink.click();

		appsPage = new AppsPage();
		// assert if we see App Marketplace on the page
		assertEquals(appsPage.appMarketPlaceTitle.getText(), "App Marketplace",
				"H2 text does not match on search page");

		// Select one the a random Featured app under "Featured Apps"
		WebElement randomApp = appsPage.getRandomFeaturedApp();
		String randomAppName= randomApp.getText();
		// Click on the selected app link and Assert if we see "App Gallery" and "<app
		// name>"
		randomApp.click();

		singleApp = new AppsSingleAppPage();
		assertTrue(singleApp.breadcrumbs.getText().contains("App Gallery"));
		assertTrue(singleApp.breadcrumbs.getText().contains(randomAppName));

		// assert if the randomApp name matches on the page
		String ActualAppName=  singleApp.appName.getText();
		assertEquals(ActualAppName,randomAppName);
		
		//Check if CONNECT button is displayed
		assertTrue(singleApp.connectButton.isDisplayed());
		
		// Assert if GET button is displayed
		assertTrue(singleApp.getButton.isDisplayed());	

	}

}
