package com.online5.tests.community;

import java.util.Random;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.online5.pages.CommunityMainPage;
import com.online5.pages.CommunitySearchPage;
import com.online5.pages.CommunitySearchResultPage;
import com.online5.pages.DiscussionPage;
import com.online5.pages.Homepage;
import com.online5.tests.TestBase;
import com.online5.utilities.BrowserUtils;
import com.online5.utilities.ConfigurationReader;

public class CommunityTests extends TestBase {
	@Test
	public void communityPageDisplayTest() {

		/*
		 * As a user when I click on Community page link on Homepage, I should land on
		 * the Community forums page.
		 */
		
		extentLogger = report.createTest("Community Page Display Test");
		
		extentLogger.info("navigating to homepage");
		driver.get(ConfigurationReader.getProperty("url"));
		BrowserUtils.waitForPageToLoad(2);

		// assert that homepage titles matches
		Assert.assertEquals(driver.getTitle(), "Free Calorie Counter, Diet & Exercise Journal | MyFitnessPal.com");
		extentLogger.pass("Verified homepage titles matches the expected title");
		
		// click on Sign Up button
		Homepage homePage = new Homepage();
		extentLogger.info("clicking on Community link");
		homePage.communityLink.click();
		BrowserUtils.waitForPageToLoad(2);

		// assert that title matches
		String expectedTitle = "Free Diet and Fitness Forums and Chat, Free Diet Tips | MyFitnessPal.com";
		Assert.assertEquals(driver.getTitle(), expectedTitle, "Title on Community page does not match");
		extentLogger.pass("Verified Community page titles matches the expected title");

		// assert that Message Boards header is displayed
		CommunityMainPage comMainPage = new CommunityMainPage();
		Assert.assertTrue(comMainPage.pageHeader.isDisplayed(), "Community page header is not displayed");
		extentLogger.pass("Verified that Community page header is displayed");
	}

	@Test
	public void randomTopicDisplayTest() {

		/*
		 * As a user, when I click on any one of listed main topics on Community page, I
		 * should land on the page of that topic.
		 */

		extentLogger = report.createTest("Random Topic Display Test");
		extentLogger.info("navigating to homepage");
		// navigate to homepage
		driver.get(ConfigurationReader.getProperty("url"));
		BrowserUtils.waitForPageToLoad(2);
		
		// navigate to Community page
		Homepage homePage = new Homepage();
		extentLogger.info("Clicking on Community link");
		homePage.communityLink.click();
		BrowserUtils.waitForPageToLoad(2);

		// get main topic count
		CommunityMainPage comMainPage = new CommunityMainPage();
		int count = comMainPage.mainTopics.size();

		// click on random topic link
		int random = (new Random().nextInt(count));
		String randomTopic = comMainPage.mainTopics.get(random).getText();
		System.out.println("Chosen random topic: " + randomTopic);
		extentLogger.info("Clicking on random topic");
		comMainPage.findTopic(randomTopic).click();
		BrowserUtils.waitForPageToLoad(2);

		// verify title contains selected random topic name
		Assert.assertTrue(driver.getTitle().contains(randomTopic),
				randomTopic + " topic does not match the title on topic page");
		extentLogger.pass("Verified topic page title contains topic name");
	}

	@Test
	public void randomDiscussionSearchTest() {

		/*
		 * As a user, when I perform search with valid discussion topic in Community
		 * page, first displayed result should be the link for the topic I searched for.
		 */
		
		extentLogger = report.createTest("Random Discussion Search Test");
		extentLogger.info("navigating to homepage");
		// navigate to homepage
		driver.get(ConfigurationReader.getProperty("url"));
		BrowserUtils.waitForPageToLoad(2);

		// navigate to Community page
		Homepage homePage = new Homepage();
		extentLogger.info("Clicking to Community link");
		homePage.communityLink.click();
		BrowserUtils.waitForPageToLoad(2);

		// get listed latest discussion count
		extentLogger.info("Getting listed latest discussion count");
		CommunityMainPage comMainPage = new CommunityMainPage();
		int count = comMainPage.latestDiscussionTitles.size();

		// get title of random discussion
		extentLogger.info("Getting title of a random discussion");
		int random = (new Random().nextInt(count));
		String randomDiscussionTitle = comMainPage.latestDiscussionTitles.get(random).getText();
		System.out.println("Chosen random discussion: " + randomDiscussionTitle);

		// click on search link
		extentLogger.info("Clicking on search link");
		comMainPage.searchLink.click();
		BrowserUtils.waitForPageToLoad(2);

		// verify that title has Search
		Assert.assertTrue(driver.getTitle().contains("Search"));
		extentLogger.pass("Verified page title contains Search");
		
		// search for chosen random discussion with title box
		extentLogger.info("Searching for chosen random topic title");
		CommunitySearchPage searchPage = new CommunitySearchPage();
		searchPage.advancedSearchArrow.click();
		BrowserUtils.waitForVisibility(searchPage.titleSearchBox, 2);
		searchPage.titleSearchBox.sendKeys(randomDiscussionTitle);
		searchPage.searchButton.click();
		BrowserUtils.waitForPageToLoad(2);

		// confirm that first listed result matches the chosen discussion title
		CommunitySearchResultPage resultPage = new CommunitySearchResultPage();
		String actual = resultPage.resultTitles.get(0).getText();
		Assert.assertEquals(actual, randomDiscussionTitle);
		extentLogger.pass("Verified first displayed result header matches searched discussion title");
	}

	@Test
	public void randomDiscussionOpenTest() {

		/*
		 * As a user when I click on a random discussion from latest discussion titles
		 * posted on Community home page, the discussion page for that title should be
		 * displayed. And header of the page should match the discussion title.
		 */
		
		extentLogger = report.createTest("Random Discussion Open Test");
		extentLogger.info("navigating to homepage");
		// navigate to homepage
		driver.get(ConfigurationReader.getProperty("url"));
		BrowserUtils.waitForPageToLoad(2);

		extentLogger.info("Clicking on Community link");
		// navigate to Community page
		Homepage homePage = new Homepage();
		homePage.communityLink.click();
		BrowserUtils.waitForPageToLoad(2);

		// get listed latest discussion count
		CommunityMainPage comMainPage = new CommunityMainPage();
		int count = comMainPage.latestDiscussionTitles.size();

		extentLogger.info("Getting title of random discussion from latest discussions");
		// get title of random discussion
		int random = (new Random().nextInt(count));
		String randomDiscussionTitle = comMainPage.latestDiscussionTitles.get(random).getText();
		String randomDiscussionCreator = comMainPage.findDiscussionCreator(randomDiscussionTitle).getText();
		System.out.println("Chosen random discussion: " + randomDiscussionTitle);

		// click on that discussion
		extentLogger.info("Clicking on chosen random discussion");
		comMainPage.findDiscussion(randomDiscussionTitle).click();
		BrowserUtils.waitForPageToLoad(2);

		// verify that page title contains discussion title
		Assert.assertTrue(driver.getTitle().contains(randomDiscussionTitle));
		extentLogger.pass("Verified discussion page title contains discussion title");
		
		// verify that page header matches the discussion title
		DiscussionPage discussionPage = new DiscussionPage();
		Assert.assertEquals(discussionPage.header.getText(), randomDiscussionTitle);
		extentLogger.pass("Verified discussion page header matches discussion title");
	}
}
