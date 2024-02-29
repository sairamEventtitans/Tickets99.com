package com.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.base.TestBase;
import com.utils.Utils;

public class LoginWithExistingEmailAmazonPay extends TestBase {

	@FindBy(id = "radioupiqr-2")
	WebElement amazonCheckbox;

	@FindBy(xpath = "//span[text()='I am done making UPI Payment']")
	WebElement doneBtn;

	@FindBy(id = "textpaymenttransactionid")
	WebElement enterTransIdValue;

	@FindBy(id = "acompletepayment")
	WebElement completeOrderPhonepe;

	@FindBy(xpath = "//span[@id='acompletepayment']")
	WebElement CompleteOrderBtn;

	@FindBy(xpath = "//h3[text()='Your order is pending']")
	WebElement orderPendingMsg;
	
	@FindBy(xpath = "//div[@id='OrderDiv']//span")
	WebElement orderid;
	
	@FindBy(xpath = "//td//span[@id='orderid']")
	WebElement ticketverify;
	
	@FindBy(xpath = "(//button[@onclick='return ViewMyTicket(this);'])[2]")
	WebElement viewTickets;
	
	@FindBy(xpath = "(//button[@onclick='return ViewMyTicket(this);'])[1]")
	WebElement Ticketurlbtn;
	
	

	public LoginWithExistingEmailAmazonPay(WebDriver driver) {

		PageFactory.initElements(driver, this);
	}

	public void verifyAmazonPay(String amazonPayTransId) {

		amazonCheckbox.click();
		Utils.javaScriptClick(doneBtn);
		enterTransIdValue.sendKeys(amazonPayTransId);

		CompleteOrderBtn.click();
		boolean OrderpendingMsg = orderPendingMsg.isDisplayed();

		Assert.assertTrue(OrderpendingMsg);

	}
	
	public void verifyOderVip_amazon() {

		String ticketwindow = driver.getWindowHandle();
		String orderID = orderid.getText();
		Ticketurlbtn.click();
		Utils.Windowhandless(ticketwindow);
		String ticketID = ticketverify.getText();
		Assert.assertEquals(orderID, ticketID);
		
		Utils.WriteInExistingExcel(orderID, "ExistingEmaillogin", 16);
		driver.switchTo().window(ticketwindow);
	}

	public void verifyOrderIdFree_amazon() {

		String ticketwindow = driver.getWindowHandle();
		String orderID = orderid.getText();
		viewTickets.click();
		Utils.Windowhandless(ticketwindow);
		String ticketID = ticketverify.getText();
		Assert.assertEquals(orderID, ticketID);
		Utils.WriteInExistingExcel(orderID, "ExistingEmaillogin", 15);
		driver.switchTo().window(ticketwindow);

	}

}
