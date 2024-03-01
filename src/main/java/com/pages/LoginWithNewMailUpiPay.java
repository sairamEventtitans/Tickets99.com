package com.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.base.TestBase;
import com.utils.Utils;

public class LoginWithNewMailUpiPay extends TestBase {

	@FindBy(xpath = "//div[@id='OrderDiv']//span")
	WebElement orderid;

	@FindBy(xpath = "//td//span[@id='orderid']")
	WebElement ticketverify;

	@FindBy(xpath = "(//button[@onclick='return ViewMyTicket(this);'])[2]")
	WebElement viewTickets;

	@FindBy(xpath = "(//button[@onclick='return ViewMyTicket(this);'])[1]")
	WebElement Ticketurlbtn;

	public LoginWithNewMailUpiPay(WebDriver driver) {

		PageFactory.initElements(driver, this);

	}

	public void verifyOderVip_upi() {

		String ticketwindow = driver.getWindowHandle();
		String orderID = orderid.getText();

		Ticketurlbtn.click();
		Utils.Windowhandless(ticketwindow);
		String ticketID = ticketverify.getText();
		Assert.assertEquals(orderID, ticketID);

		Utils.WriteInExistingExcel(orderID, "emaildata", 20);
		driver.switchTo().window(ticketwindow);
	}

}
