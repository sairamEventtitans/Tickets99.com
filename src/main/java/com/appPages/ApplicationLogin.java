package com.appPages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.SendKeysAction;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.base.TestBaseOrganizer;
import com.utils.Utils;

public class ApplicationLogin extends TestBaseOrganizer {

	@FindBy(id = "txtusername")
	WebElement App_Username;

	@FindBy(id = "txtpassword")
	WebElement App_Password;

	@FindBy(xpath = "//iframe[@title='reCAPTCHA']")
	WebElement i_Frame1;

	@FindBy(xpath = "//label[@id='recaptcha-anchor-label']")
	WebElement Captcha;

	@FindBy(xpath = "//a[@id='aloginbutton']")
	WebElement Login_button;

	@FindBy(xpath = "//li[@id='oldlogin']")
	WebElement organizer_event;

	@FindBy(xpath = "//a[@href='/companyarea/Company/Events']/span")
	WebElement events;

	@FindBy(xpath = "(//div[@id='divactionsquestionnaire'])[2]")
	WebElement EventCheckin;

	@FindBy(xpath = "(//a[@onclick='CheckinView(this)'])[2]")
	WebElement checkInbtn;
	
	@FindBy (id="textcheckinfirstname")
	WebElement fnamecheck;
	
	@FindBy (xpath = "//div[@class='description']//a[@id='NJPPRuc53vFCbsl0iAeskQ==']")
	WebElement EventsInfo;
	
	@FindBy (xpath = "//a[@href='/companyarea/appevent/eventorders/NJPPRuc53vFCbsl0iAeskQ==/']")
	WebElement orders_link;
	
	@FindBy (xpath = "//a[@onclick='ShowAdSerach4()']")
	WebElement search_Function;
	
	@FindBy (xpath = "//input[@name='OrderId']")
	WebElement search_value;
	
	@FindBy (xpath = "(//td[text()='₹ 0.05'])[1]")
	WebElement cartValue_text;
	
	@FindBy (xpath = "//th[text()='Identity File']")
	WebElement sample;
	
	
	public ApplicationLogin(WebDriver driver) {

		PageFactory.initElements(driver, this);

	}

	public void verifyApplicationLogin(String uname, String pword) throws Throwable {

		App_Username.sendKeys(uname);
		App_Password.sendKeys(pword);

		Login_button.click();

		Thread.sleep(2000);

	}

	public void verifyCheckin() {

		organizer_event.click();
		events.click();
		EventsInfo.click();
		orders_link.click();
	}
	
	
	public  void searchFunctionality(String search) throws Throwable {
		
		//search_Function.click();
		//Utils.javaScriptClick(search_Function);
		
		JavascriptExecutor js=(JavascriptExecutor)driver;
		js.executeScript("arguments[0].click();", search_Function);
		
		search_value.click();
		Thread.sleep(3000);
		
		//js.executeScript("elements[0].value='20240320060614136412';", search);
		search_value.sendKeys(search);
		search_value.sendKeys(Keys.ENTER);
		
		
	}
	
	public void cartValue(String cartvalue) throws Throwable {
		
		String cartAmount=cartvalue;	
		System.out.println(cartAmount);		
		JavascriptExecutor js=(JavascriptExecutor)driver;
		
	
		
		js.executeScript("arguments[0].scrollIntoView(true);", sample);
		
		js.executeScript("document.documentElement.scrollLeft += 1000;");
		
//		 js.executeScript("window.scrollBy(0,300)");
//		 js.executeScript("window.scrollBy(1000,0)");
		
//		js.executeScript("windows.scrollBy(100,0)");
		Thread.sleep(5000);
		String cart=cartValue_text.getText();	
		System.out.println(cart);		
		
		Utils.WriteInExistingExcel(cart, "OrderRazorPay", 8);
		boolean text=cartValue_text.isDisplayed();		
		Assert.assertTrue(text);
		
	}
	
	public void verifyDiscount(String discount) {
		
		String discount_offered=discount;
		System.out.println(discount_offered);
		
	}
	
	public void verifyPlatformFee(String Pfee) {
		
		String Platform_Fee=Pfee;
		System.out.println(Platform_Fee);
		
	}
	
	public void verifyPaymentCollected(String PayCollected) {
		
		String payment_Collected=PayCollected;
		System.out.println(payment_Collected);
	}
	

}
