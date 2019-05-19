package com.ap.ui.testcases;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.ap.ui.base.TestBase;
import com.ap.ui.pages.HomePage;
import com.ap.ui.pages.LoginPageOR;
import com.ap.ui.pages.ProductDetailsPage;
import com.ap.ui.pages.SearchPage;

public class AddWishlistTest extends TestBase{

	LoginPageOR loginPage;
	HomePage homepage;
	SearchPage searchPage;
	ProductDetailsPage productDetailsPage;
	

public AddWishlistTest() {
	super();
	
}
@BeforeMethod 
public void setUpdriver() {
	initialization();
	loginPage = new LoginPageOR();
	homepage= new HomePage();
	
}
	
@Test
public void testAddProductToWishList() {
	
	String product = "Printed Chiffon Dress";
	homepage.clickOnContactLink();
	homepage = loginPage.login(propt.getProperty("username"), propt.getProperty("password"));
	//search product 
	searchPage = homepage.searchProduct(product);
	String header = searchPage.getHeader();
	Assert.assertTrue(header.toLowerCase().contains(product.toLowerCase()));
	//add product to wish list 
	productDetailsPage.clickAddWishlist();
	productDetailsPage.verifyAddWishListMsg();
	homepage.logOut();
}
@AfterMethod 
public void tearDown(){
	driver.quit();
}


}
