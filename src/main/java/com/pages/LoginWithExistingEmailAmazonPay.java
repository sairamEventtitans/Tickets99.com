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

}
