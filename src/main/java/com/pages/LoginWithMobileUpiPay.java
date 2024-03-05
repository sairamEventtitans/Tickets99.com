package com.pages;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.base.TestBase;
import com.utils.Utils;

public class LoginWithMobileUpiPay extends TestBase {

	@FindBy(xpath = "//div[@onclick='redirecttoticket(this)']")
	WebElement buyTicket;

	@FindBy(xpath = "(//span[text()='+'])[2]")
	WebElement Addticket;

	@FindBy(xpath = "//div[@onclick='proceedwithtickets()']")
	WebElement proceedticketbtn;

	@FindBy(xpath = "//input[@id='txtguestcheckoutphonenum']")
	WebElement mobileValue;

	@FindBy(xpath = "//button[@onclick='proceedtoregister()']")
	WebElement continueBtn;

	@FindBy(id = "VerifyWhatsapp")
	WebElement verifyOtp;

	@FindBy(xpath = "//h5[text()='Order Summary']")
	WebElement orderSummary;

	@FindBy(xpath = "//div[@id='OrderDiv']//span")
	WebElement orderid;

	@FindBy(xpath = "//td//span[@id='orderid']")
	WebElement ticketverify;

	@FindBy(xpath = "(//button[@onclick='return ViewMyTicket(this);'])[2]")
	WebElement viewTickets;

	@FindBy(xpath = "(//button[@onclick='return ViewMyTicket(this);'])[1]")
	WebElement Ticketurlbtn;

	@FindBy(id = "radioupi")
	WebElement UpiCheckBox;

	@FindBy(id = "btnproceed")
	WebElement proceedBtn;

	public LoginWithMobileUpiPay(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	public void verifyMobileLogin(String mobile_number) throws Throwable {

		buyTicket.click();
		Addticket.click();
		proceedticketbtn.click();
		mobileValue.sendKeys(mobile_number);
		continueBtn.click();
		
	try {	
		Thread.sleep(30000);
		verifyOtp.click();
	}
	catch (NoSuchElementException nse) {
		
		System.out.println("No otp validation required for this Event");
	}

		boolean orderSummaryMeassage = orderSummary.isDisplayed();
		Assert.assertTrue(orderSummaryMeassage);

	}

	public void verifyOderVip_upi() {

		String ticketwindow = driver.getWindowHandle();
		String orderID = orderid.getText();
		Ticketurlbtn.click();
		Utils.Windowhandless(ticketwindow);
		String ticketID = ticketverify.getText();
		Assert.assertEquals(orderID, ticketID);

		Utils.WriteInExistingExcel(orderID, "MobileNumberUpi", 13);
		driver.switchTo().window(ticketwindow);
	}

	public void verifyPayMentOption() {
		UpiCheckBox.click();
		proceedBtn.click();

	}

}
