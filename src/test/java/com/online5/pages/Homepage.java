package com.online5.pages;


import org.openqa.selenium.support.PageFactory;

import com.online5.utilities.Driver;

public class Homepage {
	public Homepage() {
		PageFactory.initElements(Driver.getDriver(), this);
	}
	
	
}
