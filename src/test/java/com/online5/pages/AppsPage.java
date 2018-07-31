package com.online5.pages;

import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.online5.utilities.Driver;

public class AppsPage {
	public AppsPage() {
		PageFactory.initElements(Driver.getDriver(), this);
	}
	
	@FindBy(xpath="//div[@class='search module']//input")
	public WebElement appSearch;
	
	@FindBy(xpath="//h2[@class='sidebar-title']")
	public WebElement categoriesTitle;
	
	@FindBy(xpath="//h2[.='Featured Apps']")
	public WebElement featuredAppsTitle;
	
	@FindBy(xpath="//h2[.='App Marketplace']")
	public WebElement appMarketPlaceTitle;
	
	@FindBy(xpath="//div[@class='featured-apps']//div[@class='app-block']")
	public List<WebElement> featuredApps;
	
	public WebElement appsByIndex(List<WebElement> apps, int index) {
		return apps.get(index);
	}
		
	public WebElement getRandomFeaturedApp() {
		/*
		 * returns a random App from the featured apps list
		 * */
		Random rand= new Random();
		int randIndex= rand.nextInt(featuredApps.size());
		return featuredApps.get(randIndex);
	}
	
	public String getAppName(WebElement app) {
		return app.findElement(By.xpath("//a[2]")).getText();
	}
	
	
}
