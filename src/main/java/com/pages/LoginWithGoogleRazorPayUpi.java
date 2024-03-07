package com.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.base.TestBase;
import com.utils.Utils;

public class LoginWithGoogleRazorPayUpi extends TestBase {

	@FindBy(id = "btnproceedcheck")
	WebElement proceedbtn_topayment;

	@FindBy(xpath = "//span[text()=' Payment Details ']")
	WebElement Paymentdetails;

	@FindBy(xpath = "//input[@id='radiorazorpay']")
	WebElement razorUpi;

	@FindBy(xpath = "//button[@id='btnproceed']")
	WebElement proceedbtn_razor;

	@FindBy(xpath = "//h3[text()='Pay With UPI QR']")
	WebElement qr_msg;

	@FindBy(xpath = "//button[@class='new-method has-tooltip false svelte-1d17g67']//div[text()='UPI / QR']")
	WebElement Upibutton;

	@FindBy(xpath = "//div[@class='vpa-title svelte-wbn63g']")
	WebElement Upi_mobile;

	@FindBy(xpath = "//input[@id='vpa-upi']")
	WebElement Mobile_upiValue;

	@FindBy(css = "#contact")
	WebElement MobileValue;

	@FindBy(xpath = "//button[@id='redesign-v15-cta']")
	WebElement proceedRazor;

	@FindBy(xpath = "//iframe[@src='https://api.razorpay.com/v1/checkout/public']")
	WebElement iframeswitch;

	@FindBy(xpath = "//button[text()='Pay Now']")
	WebElement Paybtn;

	@FindBy(xpath = "//div[text()=' UPI ID to complete payment']")
	WebElement Phonepemsg;

	public LoginWithGoogleRazorPayUpi(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	public boolean verifyPaymentPage() {

		Utils.javaScriptClick(proceedbtn_topayment);
		Paymentdetails.isDisplayed();
		return true;

	}

	public void verifyRazorCheckbox() throws Throwable {

		razorUpi.click();
		proceedbtn_razor.click();
		Thread.sleep(2000);

		driver.switchTo().frame(iframeswitch);

		MobileValue.click();
		Thread.sleep(2000);
		MobileValue.sendKeys("8106637137");
		proceedRazor.click();

	}

	public void verifyRazorUpiPaymentMethods() throws Throwable {

		qr_msg.isDisplayed();
		Upibutton.click();
		Upi_mobile.click();
		Mobile_upiValue.clear();
		Mobile_upiValue.sendKeys("jakkam.sairam@ybl");
		Thread.sleep(3000);
		Paybtn.click();

		boolean trans_msg = Phonepemsg.isDisplayed();

		Assert.assertTrue(trans_msg);

	}

}
