package com.ap.ui.testcases;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.ap.ui.base.TestBase;
import com.ap.ui.pages.HomePage;
import com.ap.ui.pages.LoginPageOR;

public class LoginTest extends TestBase { //always extend the TestBase for everything to tie back to it. 

	
	LoginPageOR loginPage; //you have to create this object to interact directly with that page instead of calling it all the time
	HomePage homePage;
	
	public LoginTest() {
		super();
		
}

	//method to initialize browser
	@BeforeMethod 
	public void setUpdriver() {
		initialization();
		loginPage = new LoginPageOR();
		
	}

	
	//to test the title of the page. we created a string to call the title
	@Test(priority =1)
	public void loginPageTitleTest() {
		String title = loginPage.verifyPageTitle();
		Assert.assertEquals(title, "Later will check on the site"); //TestNG allows us to capture a value and makes sure if TRUE or NOT
		
	}
	
	@Test(priority=2)
	public void apLogoTest() {
		boolean value = loginPage.validateAPImage();
		Assert.assertTrue(value);
		
	}
	
	
	//making connection to home page and login page. "propt" is the property object we made in TestBase. using the property object 
	//we are able to get the info from the property file
	@Test(priority=3)
	public void loginTest() {
		homePage = loginPage.login(propt.getProperty("username"), propt.getProperty("password"));
	}
	
	
	//to close the browser 
	@AfterMethod 
	public void closeBrowser() {
		driver.quit();	
	}
	
		
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
