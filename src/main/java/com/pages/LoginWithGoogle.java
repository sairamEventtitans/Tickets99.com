package com.pages;

import org.apache.xmlbeans.impl.xb.xsdschema.All;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import com.base.TestBase;
import com.utils.Utils;

public class LoginWithGoogle extends TestBase {

	int ticketcount = 1;

	@FindBy(xpath = "//span[@onclick='redirecttoticket(this)']")
	WebElement buyButton;

	@FindBy(xpath = "(//span[text()='+'])[1]") // Address of the Webelements
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

	@FindBy(xpath = "(//div[@class='VfPpkd-RLmnJb'])[2]")
	WebElement continuebtn;

	@FindBy(xpath = "//i[@class='icon-edit-3 fs-6']")
	WebElement editbtn;

	@FindBy(id = "textbuyeremail")
	WebElement disablemail;

	@FindBy(xpath = "//h5[text()='WhatsApp Configuration']")
	WebElement whatsappConfig;

	@FindBy(id = "checkingforsomeone")
	WebElement attendeebox;

	@FindBy(id = "textattendeefirstname")
	WebElement fnamevalue;

	@FindBy(id = "textattendeelastname")
	WebElement lnamevalue;

	@FindBy(id = "textattendeeemail")
	WebElement attendeeemailvalue;

	@FindBy(id = "textattendeephone")
	WebElement mobilenumvalue;

	@FindBy(id = "isWhatsappYes")
	WebElement whatsappcheckbox;

	@FindBy(xpath = "//button[text()='Proceed']")
	WebElement proceedlast;

	@FindBy(xpath = "//h3[text()='Your order is completed']")
	WebElement ordersuccessFul;

	@FindBy(xpath = "//div[@id='OrderDiv']//span")
	WebElement orderid;

	@FindBy(xpath = "//button[@onclick='return ViewMyTicket(this);']")
	WebElement viewTickets;

	@FindBy(xpath = "//td//span[@id='orderid']")
	WebElement ticketverify;

	@FindBy(xpath = "//button[text()='Complete Registration']")
	WebElement Registrationbtn;

	@FindBy(id = "primarytextcontactnumber")
	WebElement AttendeeMobilereg; // value

	@FindBy(id = "textdesignationname")
	WebElement attendeejobreg; // value

	@FindBy(xpath = "//button[text()='  Complete Registration']")
	WebElement completeregbtn;

	@FindBy(xpath = "//h5[text()='THANKS FOR COMPLETING YOUR REGISTRATION']")
	WebElement completemsg;

	public LoginWithGoogle(WebDriver driver) { // initializing the webelements address

		PageFactory.initElements(driver, this);

	}

	public void VerifyGooglesignIn(String uname, String pword) throws Throwable {

		buyButton.click(); // verifying the sign in functionality for booking the eventtickets

		for (int i = 1; i <= ticketcount; i++) {
			Addticket.click(); // adding tickets based on quantity
		}

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
		
	}

	public void verifyWhatsappCongig() { // verifying whatsapp configuration link is dispalyed in the webpage

		boolean whatsappconfirm = whatsappConfig.isDisplayed();
		Assert.assertTrue(whatsappconfirm);          //logic needs to updated (with new email)

	}

	public void verifyAttendee_orderConfirmantion(String fname, String lname, String email, String mobile) {

		String attendee = prop.getProperty("ticketforattendee");

		if (attendee.equalsIgnoreCase("yes")) { // Verifying the Order confirmation
			attendeebox.click();
			fnamevalue.clear();
			fnamevalue.sendKeys(fname);
			lnamevalue.clear();
			lnamevalue.sendKeys(lname);
			attendeeemailvalue.clear();
			attendeeemailvalue.sendKeys(email);
			mobilenumvalue.clear();
			mobilenumvalue.sendKeys(mobile);

		}
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", whatsappcheckbox);
		
		//whatsappcheckbox.click();
		
		js.executeScript("arguments[0].scrollIntoView(true);", proceedlast);
		js.executeScript("arguments[0].click();", proceedlast);

		boolean orderSuccessMsg = ordersuccessFul.isDisplayed();

		Assert.assertTrue(orderSuccessMsg);

	}

	public void verifyViewticketUrl() throws Throwable {

//		viewTickets.click();
//		String viewTicketUrl = driver.getCurrentUrl();
//
//		Assert.assertEquals("https://admin.tickets99.com/ticket/ASuLvCVm9%204uT6W-PPjrBw==", viewTicketUrl);

		String window1 = driver.getWindowHandle();

		viewTickets.click();
		Thread.sleep(1000);

		String viewTicketUrl = driver.getCurrentUrl();
		
		System.out.println(viewTicketUrl);   //check test
		String UrlActual = "https://admin.tickets99.com/ticket/ASuLvCVm9%204uT6W-PPjrBw==";
		Utils.Windowhandless(window1);
		
		SoftAssert sf=new SoftAssert();
		
		sf.assertEquals(viewTicketUrl, UrlActual);
		//Assert.assertEquals(viewTicketUrl, UrlActual);
		driver.switchTo().window(window1);

	}

	public void VerifyRegistrationUrl() {

		String windowreg = driver.getWindowHandle();
		Registrationbtn.click();
		String regUrl = driver.getCurrentUrl();
		String actualregUrl = "https://admin.tickets99.com/attendee/ASuLvCVm9%204uT6W-PPjrBw==";
		Utils.Windowhandless(windowreg);

		driver.switchTo().window(windowreg);
	}

	public void verifyOrderId() {

		String ticketwindow = driver.getWindowHandle();
		String orderID = orderid.getText();
		viewTickets.click();
		Utils.Windowhandless(ticketwindow);
		String ticketID = ticketverify.getText();
		Assert.assertEquals(orderID, ticketID);

	}

//	public void VerifyGoogleLoginAttendee(String uname, String pword, String afanme, String alname, String amail,
//			String amobile) throws Throwable {
//
//		String attendee = prop.getProperty("ticketforattendee");
//
//		buyButton.click();
//		Addticket.click();
//		proceedticketbtn.click();
//		signInGoogle.click();
//		emailvalue.sendKeys(uname);
//		nextbtn.click();
//		passwordvalue.sendKeys(pword);
//		Thread.sleep(2000);
//
//		continuebtn.click();
//		Thread.sleep(2000);
//		Thread.sleep(2000);
//
//		if (attendee.equalsIgnoreCase("yes")) {
//			attendeebox.click();
//			fnamevalue.sendKeys(afanme);
//			lnamevalue.sendKeys(alname);
//			attendeeemailvalue.sendKeys(amail);
//			mobilenumvalue.sendKeys(amobile);
//
//		}
//		JavascriptExecutor js = (JavascriptExecutor) driver;
//		js.executeScript("arguments[0].scrollIntoView(true);", proceedlast);
//		js.executeScript("arguments[0].click();", proceedlast);
//
//	}

}
