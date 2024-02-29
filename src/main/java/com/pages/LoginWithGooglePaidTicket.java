package com.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.base.TestBase;
import com.utils.Utils;

public class LoginWithGooglePaidTicket extends TestBase{

	@FindBy(xpath = "//div[@onclick='redirecttoticket(this)']") // Address of the Webelements
	WebElement buyButton;

	@FindBy(xpath = "(//span[text()='+'])[2]")
	WebElement Addticket;

	@FindBy(xpath = "//div[@onclick='proceedwithtickets()']")
	WebElement proceedticketbtn;

	@FindBy(xpath = "//div[@class='gsi-material-button-content-wrapper']")
	WebElement signInGoogle;

	@FindBy(id = "identifierId")
	WebElement emailvalue;

	@FindBy(xpath = "//span[text()='Next']")
	WebElement nextbtn;

	@FindBy(name = "Passwd")
	WebElement passwordvalue;

	@FindBy(xpath = "//span[text()='Next']")
	WebElement continuebtn;

	@FindBy(xpath = "//i[@class='icon-edit-3 fs-6']")
	WebElement editbtn;

	@FindBy(id = "textbuyerfirstname")
	WebElement buyerFname;

	@FindBy(id = "textbuyerlastname")
	WebElement buyerLname;

	@FindBy(id = "textbuyerphone")
	WebElement buyerMobile;

	@FindBy(id = "textbuyeremail")
	WebElement disablemail;

	@FindBy (id="textbuyeremail")
	WebElement buyerEmailvalue;

	@FindBy(xpath = "//h5[text()='WhatsApp Configuration']")
	WebElement whatsappConfig;

	@FindBy(xpath = "//div[@id='OrderDiv']//span")
	WebElement orderid;

	@FindBy(xpath = "//td//span[@id='orderid']")
	WebElement ticketverify;

	@FindBy(xpath = "(//button[@onclick='return ViewMyTicket(this);'])[2]")
	WebElement viewTickets;

	@FindBy(xpath = "(//button[@onclick='return ViewMyTicket(this);'])[1]")
	WebElement Ticketurlbtn;
	
	
	public LoginWithGooglePaidTicket(WebDriver driver) {
		
		PageFactory.initElements(driver, this);
	}
	
	
	public void VerifyGooglesignIn(String uname, String pword) throws Throwable {

		buyButton.click(); // verifying the sign in functionality for booking the eventtickets

//		for (int i = 1; i <= ticketcount; i++) {
			Addticket.click(); // adding tickets based on quantity
//		}

		proceedticketbtn.click();
		signInGoogle.click();
		emailvalue.sendKeys(uname);
		nextbtn.click();

		passwordvalue.sendKeys(pword);
		Thread.sleep(2000);
		continuebtn.click();
		Thread.sleep(2000);
		editbtn.click();
		boolean checkemail = disablemail.isEnabled();
		Assert.assertFalse(checkemail);
		System.out.println("checking email is disabled expected false " + checkemail);

	} // checked

	public void verifyWhatsappCongig(String bfname, String blname, String bmobile,String bEmail) throws Throwable { // verifying
																										// whatsapp
																										// configuration
		// link is dispalyed in the
		// webpage
//
//		buyerFname.clear();
//		buyerFname.sendKeys(bfname);
//		buyerLname.clear();
//		buyerLname.sendKeys(blname);
//		buyerMobile.clear();
//		buyerMobile.sendKeys(bmobile);
        buyerEmailvalue.sendKeys(bEmail);   

		// if whatsapp config not displayed need to pass the data after clicking edit
		// button

		boolean whatsappconfirm = whatsappConfig.isDisplayed();
		Assert.assertTrue(whatsappconfirm);
		// logic needs to updated (with new email) //checked

	}
	
	public void verifyOderVip_upi() {

		String ticketwindow = driver.getWindowHandle();
		String orderID = orderid.getText();
		Ticketurlbtn.click();
		Utils.Windowhandless(ticketwindow);
		String ticketID = ticketverify.getText();
		Assert.assertEquals(orderID, ticketID);

		Utils.WriteInExistingExcel(orderID, "logincred", 16);
		driver.switchTo().window(ticketwindow);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
