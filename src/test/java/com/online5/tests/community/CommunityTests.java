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
		Assert.assertTrue(driver.getTitle().contains(randomTopic),
				randomTopic + " topic does not match the title on topic page");
	}

	@Test
	public void randomDiscussionSearchTest() {

		// navigate to homepage
		driver.get(ConfigurationReader.getProperty("url"));
		BrowserUtils.waitForPageToLoad(2);

		// navigate to Community page
		Homepage homePage = new Homepage();
		homePage.communityLink.click();
		BrowserUtils.waitForPageToLoad(2);

		// get listed latest discussion count
		CommunityMainPage comMainPage = new CommunityMainPage();
		int count = comMainPage.latestDiscussionTitles.size();

		// get title of random discussion
		int random = (new Random().nextInt(count));
		String randomDiscussionTitle = comMainPage.latestDiscussionTitles.get(random).getText();
		System.out.println("Chosen random discussion: " + randomDiscussionTitle);

		// click on search link
		comMainPage.searchLink.click();
		BrowserUtils.waitForPageToLoad(2);

		// verify that title has Search
		Assert.assertTrue(driver.getTitle().contains("Search"));

		// search for chosen random discussion
		CommunitySearchPage searchPage = new CommunitySearchPage();
		searchPage.searchBox.sendKeys(randomDiscussionTitle);
		searchPage.searchButton.click();
		BrowserUtils.waitForPageToLoad(2);

		// confirm that first listed result matches the chosen discussion title
		CommunitySearchResultPage resultPage = new CommunitySearchResultPage();
		String actual = resultPage.resultTitles.get(0).getText();
		Assert.assertEquals(actual, randomDiscussionTitle);
	}

	@Test
	public void randomDiscussionOpenTest() {

		/*
		 * As a user when I click on a random discussion from latest discussion titles
		 * posted on Community home page, the discussion page for that title should be
		 * displayed. And header of the page should match the discussion title.
		 */

		// navigate to homepage
		driver.get(ConfigurationReader.getProperty("url"));
		BrowserUtils.waitForPageToLoad(2);

		// navigate to Community page
		Homepage homePage = new Homepage();
		homePage.communityLink.click();
		BrowserUtils.waitForPageToLoad(2);

		// get listed latest discussion count
		CommunityMainPage comMainPage = new CommunityMainPage();
		int count = comMainPage.latestDiscussionTitles.size();

		// get title and creator username of random discussion
		int random = (new Random().nextInt(count));
		String randomDiscussionTitle = comMainPage.latestDiscussionTitles.get(random).getText();
		String randomDiscussionCreator = comMainPage.findDiscussionCreator(randomDiscussionTitle).getText();
		System.out.println("Chosen random discussion: " + randomDiscussionTitle);

		// click on that discussion
		comMainPage.findDiscussion(randomDiscussionTitle).click();
		BrowserUtils.waitForPageToLoad(2);

		// verify that page title contains discussion title
		Assert.assertTrue(driver.getTitle().contains(randomDiscussionTitle));

		// verify that page header matches the discussion title
		DiscussionPage discussionPage = new DiscussionPage();
		Assert.assertEquals(discussionPage.header.getText(), randomDiscussionTitle);
		
	}
}
