package com.online5.tests.community;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;
import com.online5.pages.Homepage;
import com.online5.pages.CommunityMainPage;
import com.online5.tests.TestBase;
import com.online5.utilities.BrowserUtils;
import com.online5.utilities.ConfigurationReader;

public class CommunityTests extends TestBase {
	@Test
	public void communityPageDisplayTest() {
		
		Faker fake = new Faker();
		
		driver.get(ConfigurationReader.getProperty("url"));
		BrowserUtils.waitForPageToLoad(5);
		
		// assert that homepage titles matches
		Assert.assertEquals(driver.getTitle(), "Free Calorie Counter, Diet & Exercise Journal | MyFitnessPal.com");
		
		// click on Sign Up button
		Homepage homePage = new Homepage();
		homePage.communityLink.click();
		BrowserUtils.waitForPageToLoad(5);
		
		// assert that title matches
		String expectedTitle = "Free Diet and Fitness Forums and Chat, Free Diet Tips | MyFitnessPal.com";
		Assert.assertEquals(driver.getTitle(), expectedTitle, "Title on Community page does not match");
		
		// assert that Message Boards header is displayed
		CommunityMainPage comMainPage = new CommunityMainPage();
		Assert.assertTrue(comMainPage.pageHeader.isDisplayed(), "Community page header is not displayed");
		
	}
	
	
}
