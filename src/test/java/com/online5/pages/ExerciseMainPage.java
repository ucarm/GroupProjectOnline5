package com.online5.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.online5.utilities.Driver;

public class ExerciseMainPage {
	
	public ExerciseMainPage() {
		PageFactory.initElements(Driver.getDriver(), this);
	}
	
		@FindBy(xpath="//a[.='Exercise']")
		public WebElement exerciseButton;
		
		@FindBy(id="search")
		public WebElement exerciseSearchInput;
		
		@FindBy(xpath="//input[@type='submit']")
		public WebElement exerciseSearchButton;
				
		@FindBy(xpath="//p[contains(text(),'No results found')]")
		public WebElement errorMsgInTheBox;
		
		@FindBy(xpath="//ul[@id='matching']")
		public WebElement dataInTheBox;
		
		@FindBy(xpath="//h4[@id='calories_burned_description']")
		public WebElement caloriesBurnedBox; 
				
		@FindBy(xpath="(//a[@class='search'])[1]")
		public WebElement FirstInputInTheBox;
		
}


