package com.online5.tests.community;

import java.util.Random;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.online5.pages.CommunityMainPage;
import com.online5.pages.Homepage;
import com.online5.tests.TestBase;
import com.online5.utilities.BrowserUtils;
import com.online5.utilities.ConfigurationReader;

public class CommunityTests extends TestBase {
	@Test
	public void communityPageDisplayTest() {
		
		driver.get(ConfigurationReader.getProperty("url"));
		BrowserUtils.waitForPageToLoad(2);
		
		// assert that homepage titles matches
		Assert.assertEquals(driver.getTitle(), "Free Calorie Counter, Diet & Exercise Journal | MyFitnessPal.com");
		
		// click on Sign Up button
		Homepage homePage = new Homepage();
		homePage.communityLink.click();
		BrowserUtils.waitForPageToLoad(2);
		
		// assert that title matches
		String expectedTitle = "Free Diet and Fitness Forums and Chat, Free Diet Tips | MyFitnessPal.com";
		Assert.assertEquals(driver.getTitle(), expectedTitle, "Title on Community page does not match");
		
		// assert that Message Boards header is displayed
		CommunityMainPage comMainPage = new CommunityMainPage();
		Assert.assertTrue(comMainPage.pageHeader.isDisplayed(), "Community page header is not displayed");
	}
	
	
	@Test
	public void randomMainTopicTest() {
		// navigate to homepage
		driver.get(ConfigurationReader.getProperty("url"));
		BrowserUtils.waitForPageToLoad(2);
		
		// navigate to Community page
		Homepage homePage = new Homepage();
		homePage.communityLink.click();
		BrowserUtils.waitForPageToLoad(2);
		
		// get main topic count
		CommunityMainPage comMainPage = new CommunityMainPage();
		int count = comMainPage.mainTopics.size();
		
		// click on random topic link
		int random = (new Random().nextInt(count));
		String randomTopic = comMainPage.mainTopics.get(random).getText();
		System.out.println("Chosen random topic: " + randomTopic);
		comMainPage.findTopic(randomTopic).click();
		BrowserUtils.waitForPageToLoad(2);
		
		// verify title contains selected random topic name
		Assert.assertTrue(driver.getTitle().contains(randomTopic), randomTopic + " topic does not match the title on topic page");
	}
	
	
}
