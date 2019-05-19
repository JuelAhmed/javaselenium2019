package com.ap.ui.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import com.ap.ui.base.TestBase;

public class ContactsPage extends TestBase{

	
	
	@FindBy(id="id_contact")
	WebElement headingDropdown;
	
	@FindBy(id="email")
	WebElement emailInput;
	
	@FindBy(id="id_order")
	WebElement OrderReference;
	
	@FindBy(id="message")
	WebElement messageTextBox;
	
	@FindBy(id="submitMessage")
	WebElement submitMessageButton;
	
	@FindBy(css="[class='alert alert-sucess']")
	WebElement sucessMsg;
	
	public ContactsPage() { 
		PageFactory.initElements(driver, this);
	}

	//creating a method , multiple dropdown options for email, reference, message. all we going to do is
	//call the object and pass info into the method we created.
	
	public ContactsPage fillContactForm(String heading, String email, String reference, String message) {
	Select select = new Select(headingDropdown);
	select.selectByVisibleText(heading);
	emailInput.sendKeys(email);
	OrderReference.sendKeys(reference);
	messageTextBox.sendKeys(message);
	return this; 
}
	//created a submit button method

	public void submitMessage() {
		submitMessageButton.click();
	}
	
	//will tell us the message we get after sending out the message
	public String getMessage() {
		return sucessMsg.getText();
	}
	
	
}	