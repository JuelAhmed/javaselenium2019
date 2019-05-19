package com.ap.ui.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.ap.ui.base.TestBase;

public class OrderSummaryPage extends TestBase{

	
	@FindBy(css="[class='button btn btn-default standard-checkout button-medium']")
	WebElement proceedCheckOutButton;
	
	@FindBy(css="[name='processAddress']")
	WebElement processdAddressButton;
	
	@FindBy (css="[name='processCarrier']")
	WebElement processCarrierButton;
	
	@FindBy(id="cgv")
	WebElement TermsandConditionCheckbox;
	
	@FindBy(css ="[class='payment_module'] [title='Pay by bank wire']")
	WebElement PaybybankWire;
	
	@FindBy(css="['//span[contains(text(),'I confirm my order')]") 
	WebElement orderConfirm;
	
	public OrderSummaryPage proceedCheckOut() {
		PageFactory.initElements(driver, this);
	return this;
	}
	
	public OrderSummaryPage proceedAddressCheckOut() {
		processdAddressButton.click();
		return this;
	}
	public OrderSummaryPage proceedShipping() {
		TermsandConditionCheckbox.click();
		processCarrierButton.click();
		return this;
	}
	public OrderSummaryPage confirmOrder() {
		PaybybankWire.click();
		orderConfirm.click();
		return this;
	}

	public String getConfirmationMessage() {
		return orderConfirm.getText();
	}




}
