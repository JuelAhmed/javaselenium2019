package com.ap.ui.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.ap.ui.base.TestBase;

public class HomePage extends TestBase{

	
	//get all the info for the login button 
	
	@FindBy(css="[class='login']")
	WebElement signInButton;
	
	@FindBy(id="contact-link")
	WebElement contactLink; 
	
	@FindBy(id="search_query_top")
	WebElement searchInputbox;
	
	@FindBy(css="[name='submit_search']")
	WebElement submitSearch;
	
	@FindBy(css="[class='logout']")
	WebElement logOutButton; 
	
	public HomePage() {
		PageFactory.initElements(driver, this);
	}
	
	public SearchPage searchProduct(String productName) {
		searchInputbox.sendKeys(productName);
		submitSearch.click();
		return new SearchPage();
	}
	
	public void clickOnSignIn() {
		signInButton.click();
	}
	
	public void clickOnContactLink() {
		contactLink.click();
	}
	
	public void logOut() {
		logOutButton.click();
	}
}
