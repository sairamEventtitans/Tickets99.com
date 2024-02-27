package com.pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.base.TestBase;
import com.utils.Utils;

public class LoginWithExistingEmailUpiPayment extends TestBase {

	@FindBy(xpath = "//div[@onclick='redirecttoticket(this)']") // Address of the Webelements
	WebElement buyButton;

	@FindBy(xpath = "(//span[text()='+'])[1]")
	WebElement AddticketFree;

	@FindBy(xpath = "(//span[text()='+'])[2]")
	WebElement AddticketVip;

	@FindBy(xpath = "//div[@onclick='proceedwithtickets()']")
	WebElement proceedticketbtn;

	@FindBy(xpath = "//span[@class='d-sm-inline text-decoration-underline text-primary cursor-pointer']")
	WebElement loginLink;

	@FindBy(id = "txtusername")
	WebElement Username;

	@FindBy(id = "txtpassword")
	WebElement Password;

	@FindBy(xpath = "(//button[@class='btn py-2 btn-block btn-danger bg-red-col w-100'])[1]")
	WebElement loginButton;

	@FindBy(xpath = "//small[text()='Remove Coupon ']")
	WebElement removeCoupon;

	@FindBy(id = "btnproceedcheck")
	WebElement proceedPaymentPageBtn;

	@FindBy(xpath = "//span[text()=' Payment Details ']")
	WebElement paymentPageMessage;

	@FindBy(xpath = "//button[text()='Proceed']")
	WebElement proceedBtn;

	@FindBy(id = "radioupiqr-1")
	WebElement phonepeCheckbox;

	@FindBy(xpath = "(//span[text()='PhonePe'])[2]")
	WebElement phonepeMsg;

	@FindBy(xpath = "//span[text()=' Back to change UPI option']")
	WebElement backToPayment;

	@FindBy(id = "radioupiqr-2")
	WebElement amazonCheckbox;

	@FindBy(xpath = "(//span[text()='AmazonPay'])[2]")
	WebElement amazonMsg;

	@FindBy(xpath = "//span[text()='I am done making UPI Payment']")
	WebElement doneBtn;

	@FindBy(id = "textpaymenttransactionid")
	WebElement enterTransIdValue;

	@FindBy(id = "acompletepayment")
	WebElement completeOrderPhonepe;
	
	@FindBy (xpath="//h3[text()='Your order is pending']")
	WebElement orderPendingMsg;
	
	@FindBy (xpath="//button[text()='Complete Registration']")
	WebElement completeRegBtn;

	public LoginWithExistingEmailUpiPayment(WebDriver driver) {

		PageFactory.initElements(driver, this);

	}

	public void verifyLoginWithExistingEmail(String Email, String password, String Url) throws Throwable {

		buyButton.click();
		AddticketFree.click();
		AddticketVip.click();
		proceedticketbtn.click();

		Utils.javaScriptClick(loginLink);

		// loginLink.click();
		Username.sendKeys(Email);
		Password.sendKeys(password);
		loginButton.click();

		String VerificationUrl = Url;
		String priorPaymentPageUrl = driver.getCurrentUrl();
		Assert.assertEquals(VerificationUrl, priorPaymentPageUrl);

	}

	public void verifyCouponRemoveFunctionality() {

		removeCoupon.click();

	}

	public void verifyProceedPaymentPage() {

		Utils.javaScriptClick(proceedPaymentPageBtn);
		boolean paymentMessage = paymentPageMessage.isDisplayed();

		Assert.assertTrue(paymentMessage);
		proceedBtn.click();

	}

	public void verifyPhonepe() {

		phonepeCheckbox.click();
		boolean phonepeDisplay = phonepeMsg.isDisplayed();

		Assert.assertTrue(phonepeDisplay);
		backToPayment.click();

	}

	public void verifyAmazon() {

		amazonCheckbox.click();
		boolean amazonDisplay = amazonMsg.isDisplayed();
		Assert.assertTrue(amazonDisplay);
		backToPayment.click();

	}

	public void verifyPhonepeOrder(String phnepeTransID) {
		phonepeCheckbox.click();
		Utils.javaScriptClick(doneBtn);
		enterTransIdValue.sendKeys(phnepeTransID);
		completeOrderPhonepe.click();
		boolean OrderpendingMsg=orderPendingMsg.isDisplayed();
		
		Assert.assertTrue(OrderpendingMsg);
	}

	public void verifyCompleteRegisterbutton() {
		
		boolean RegBtn=completeRegBtn.isDisplayed();
		Assert.assertFalse(RegBtn);
		
	}
	
	public void verifyOrderIdVip() {
		
		
		
	}
	
	
	
	
}
