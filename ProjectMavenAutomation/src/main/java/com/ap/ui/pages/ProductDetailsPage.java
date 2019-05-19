package com.ap.ui.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import com.ap.ui.base.TestBase;

public class ProductDetailsPage extends TestBase{

	
	@FindBy(id="quantity_wanted")
	WebElement quantityInput;
	
	@FindBy(id="group_1")
	WebElement sizeDropDown;
	
	@FindBy(xpath="//button[@name='Submit']")
	WebElement addCartButton;
	
	@FindBy(css= "title='Add to my wishlist']")
	WebElement proceedCheckOut;
	
	
	@FindBy(id= "wishlist_button")
	WebElement addToWishListButton; 
	
	@FindBy(css="[class='fancybox-error']")
	WebElement addWishListMsg;

	@FindBy(css="fancybox-item fancybox-close")
	WebElement addWishListMsgCloseButton;
	
	public ProductDetailsPage() {
		PageFactory.initElements(driver, this);
	}
	
	public void verifyAddWishListMsg() { //method
		Assert.assertEquals(addWishListMsg.getText(), "Added to your wishlist.");
		addWishListMsgCloseButton.click();
	}

	public ProductDetailsPage selectProductColor(String color) { //created a method to interact with the color. 
		String locator = "[name='"+ color + "']"; //created a object called locator to concatenate the color because colors can change
		driver.findElement(By.cssSelector(locator)).click();
			return this;
	}
	public ProductDetailsPage inputQuantity(String quanity) { //inputQuanity is the constructor name
		quantityInput.clear();
		quantityInput.sendKeys(quanity);
		return this;
	}
	
	public ProductDetailsPage selectSize(String size) {
		Select select = new Select(sizeDropDown); //creating the method for dropdown 
		select.selectByVisibleText(size);
		return this;
	}

	public ProductDetailsPage clickAddCart() {
		addCartButton.click();
		return this;
		
	}
	public ProductDetailsPage clickAddWishlist() {
		addToWishListButton.click();
		return this;
	}
	public OrderSummaryPage proceedCheckOut() {
		proceedCheckOut.click();
		return new OrderSummaryPage();
	}

}


