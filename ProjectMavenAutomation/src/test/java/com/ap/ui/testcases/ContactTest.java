package com.ap.ui.testcases;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.ap.ui.base.TestBase;
import com.ap.ui.pages.ContactsPage;
import com.ap.ui.pages.HomePage;

public class ContactTest extends TestBase {

	
	//created virtual objects 
	ContactsPage contactspage;
	HomePage homepage; //parent page
	
	
	//super class used to call parent class constructors
	public ContactTest() {
		super();
		
}

	
	//initializing the driver 
	
	@BeforeMethod
	public void setUpdriver() {
		initialization();
		contactspage = new ContactsPage();
		homepage = new HomePage(); //created new object for homepage 
		
	}

	@Test
	public void testContact() {
		homepage.clickOnContactLink();
		contactspage = contactspage.fillContactForm("Customer service", "Random@test.com",
				"Testing", "This is test purpose");
		
		contactspage.submitMessage();
		String sucessMsg = contactspage.getMessage();
		Assert.assertEquals(sucessMsg, "will check the message on the site later");
	}

	@AfterMethod 
	public void tearDown() {
	driver.quit();
}
}