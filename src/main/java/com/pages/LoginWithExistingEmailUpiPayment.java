package com.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginWithExistingEmailUpiPayment {

	@FindBy(xpath = "//div[@onclick='redirecttoticket(this)']") // Address of the Webelements
	WebElement buyButton;

	@FindBy(xpath = "(//span[text()='+'])[1]")
	WebElement AddticketFree;

	@FindBy(xpath = "(//span[text()='+'])[2]")
	WebElement AddticketVip;
	
	@FindBy(xpath = "//div[@onclick='proceedwithtickets()']")
	WebElement proceedticketbtn;

	
	public LoginWithExistingEmailUpiPayment(WebDriver driver) {
		
		PageFactory.initElements(driver, this);
		
	}
	
	
	
	
	
	
}
