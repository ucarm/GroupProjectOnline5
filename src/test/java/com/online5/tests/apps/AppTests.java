package com.online5.tests.apps;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import com.online5.pages.AppsPage;
import com.online5.pages.AppsSearchPage;
import com.online5.pages.Homepage;
import com.online5.tests.TestBase;
import com.online5.utilities.BrowserUtils;
import com.online5.utilities.ConfigurationReader;

public class AppTests extends TestBase{
	AppsPage appsPage= new AppsPage();
	Homepage homePage= new Homepage();
	AppsSearchPage appsSearchPage= new AppsSearchPage();

	@Test
	public void searchAnAppName() { 
	driver.get(ConfigurationReader.getProperty("url"));
	BrowserUtils.waitForPageToLoad(2);

	// assert that homepage titles matches
	assertEquals(driver.getTitle(), "Free Calorie Counter, Diet & Exercise Journal | MyFitnessPal.com");
	
	// click on Apps menu
	homePage.AppsLink.click();
	
	//assert if we see appMarketplace on the page
	assertEquals(appsPage.featuredAppsTitle.getText(), "App Marketplace");
	
	//select a random featured app
	WebElement randomApp= appsPage.getRandomFeaturedApp();
	
	//search on the searchbox on appspage
	String appName=appsPage.getAppName(randomApp);
	appsPage.appSearch.sendKeys(appName+Keys.ENTER);
	
	// Assert if the page header is "Search Result"
	assertTrue(appsSearchPage.searchResultHeader.getText().contains("Search Results"));
	
	// Assert if more than one result is found
	assertTrue(appsSearchPage.searchResultCount()>0);
	
	//Assert if the correct app name is displayed in 1st place search results
	String actualAppName = appsSearchPage.searchResultsAppNames.get(0).getText();
	assertEquals(actualAppName , appName);
	
	//Assert if BUY or GET button is displayed
	String actualButtonText= appsSearchPage.buttonsGetBuy.get(0).getText();
	assertTrue(actualButtonText.contains("BUY") || 
				actualButtonText.contains("GET") );;
	
	
	}
	
	
}
