package com.ap.ui.testcases;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.ap.ui.base.TestBase;
import com.ap.ui.pages.HomePage;
import com.ap.ui.pages.LoginPageOR;
import com.ap.ui.pages.SearchPage;

public class SearchItemTest extends TestBase {
	

	LoginPageOR loginPage;
	HomePage homepage;
	SearchPage searchPage;
	

public SearchItemTest() {
	super();
}
@BeforeMethod 
public void setUpdriver() {
	initialization();
	loginPage = new LoginPageOR();
	homepage= new HomePage();
	
}
@Test(priority=3)
public void testSearchItem() {
	String product = "Evening";
	homepage.clickOnContactLink();
	homepage = loginPage.login(propt.getProperty("username"), propt.getProperty("password"));

	searchPage = homepage.searchProduct("Evening");
	String header = searchPage.getHeader();
	System.out.println(header);
	Assert.assertTrue(header.toLowerCase().contains(product.toLowerCase()));
	homepage.logOut();
}

@AfterMethod 
public void tearDown() {
	driver.quit();
}
}
















