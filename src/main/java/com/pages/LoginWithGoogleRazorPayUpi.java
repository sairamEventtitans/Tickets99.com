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

	@FindBy(xpath = "//h3[text()='Your order is completed']")
	WebElement Phonepemsg;

	@FindBy(xpath = "//span[@class='pl-2 font-weight-bold text-primary']")
	WebElement order_id;
	
	@FindBy (xpath = "//span[@id='spansummarytotalamount']")
	WebElement TicketAmount;

	public LoginWithGoogleRazorPayUpi(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	public boolean verifyPaymentPage() {
		
		String ticket_cost=TicketAmount.getText();
		
		//double ticketCostNumeric = Double.parseDouble(ticket_cost);
		
		Utils.WriteInExistingExcel(ticket_cost, "OrderRazorPay", 7);
		
		Utils.javaScriptClick(proceedbtn_topayment);
		Paymentdetails.isDisplayed();
		return true;

	}

	public void verifyRazorCheckbox(String contactNumber) throws Throwable {

		razorUpi.click();
		proceedbtn_razor.click();
		Thread.sleep(2000);

		driver.switchTo().frame(iframeswitch);

		MobileValue.click();
		Thread.sleep(2000);
		MobileValue.sendKeys(contactNumber);
		proceedRazor.click();

	}

	public void verifyRazorUpiPaymentMethods(String UpiId) throws Throwable {

		qr_msg.isDisplayed();
		Thread.sleep(2000);
		Upibutton.click();
		Upi_mobile.click();
		Mobile_upiValue.clear();
		Mobile_upiValue.sendKeys(UpiId);
		Thread.sleep(3000);
		
	
		Paybtn.click();
			
		driver.switchTo().defaultContent();
		
		Thread.sleep(2000);
	
		String orderID = order_id.getText();
		
		Utils.WriteInExistingExcel(orderID, "OrderRazorPay", 4); // 6
		
		boolean OrderSuccess = Phonepemsg.isDisplayed();
		
		Assert.assertTrue(OrderSuccess);
		
		
		
	}

}
