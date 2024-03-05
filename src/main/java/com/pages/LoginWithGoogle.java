package com.pages;

import org.apache.xmlbeans.impl.xb.xsdschema.All;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import com.base.TestBase;
import com.utils.Utils;

public class LoginWithGoogle extends TestBase {

	int ticketcount = 1;

	JavascriptExecutor js = (JavascriptExecutor) driver;

	SoftAssert soft = new SoftAssert();

	@FindBy(xpath = "//div[@onclick='redirecttoticket(this)']") // Address of the Webelements
	WebElement buyButton;

	@FindBy(xpath = "(//span[text()='+'])[1]")
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

	@FindBy(id = "btnproceedcheck")
	WebElement proceedlast;

	@FindBy(xpath = "//h3[text()='Your order is completed']")
	WebElement ordersuccessFul;

	@FindBy(xpath = "//div[@id='OrderDiv']//span")
	WebElement orderid;

	@FindBy(xpath = "(//button[@onclick='return ViewMyTicket(this);'])[1]")
	WebElement viewTickets;

	@FindBy(xpath = "//td//span[@id='orderid']")
	WebElement ticketverify;

	@FindBy(xpath = "//button[text()='Complete Registration']")
	WebElement Registrationbtn;

	@FindBy(xpath = "//div[@class='userdetibdg pt-3']//h4")
	WebElement fnamereg;

	@FindBy(xpath = "//div[@class='userdetibdg pt-3']//h5")
	WebElement reglname;

	@FindBy(id = "primarytextcontactnumber")
	WebElement AttendeeMobilereg; // value

	@FindBy(id = "textdesignationname")
	WebElement attendeejobreg; // value

	@FindBy(xpath = "//button[text()='  Complete Registration']")
	WebElement completeregbtn;

	@FindBy(xpath = "//h5[text()='THANKS FOR COMPLETING YOUR REGISTRATION']")
	WebElement completemsg;

	@FindBy(xpath = "//input[@id='autointerestedin']")
	WebElement InterstedIn;

	@FindBy(id = "autocanhelpyou")
	WebElement canYouhelp;

	@FindBy(xpath = "(//div[@class='note-editable'])[1]")
	WebElement aboutme;

	@FindBy(xpath = "(//div[@class='note-editable'])[2]")
	WebElement pitch;

	@FindBy(id = "btneventpage")
	WebElement eventpagebtn;

	@FindBy(id = "btnproceedcheck")
	WebElement proceedPayment;

	@FindBy(id = "textbuyeremail")
	WebElement buyerEmailvalue;

	public LoginWithGoogle(WebDriver driver) { // initializing the webelements address using constructor

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

	} // checked

	public void verifyWhatsappCongig(String bfname, String blname, String bmobile, String bEmail) throws Throwable { // verifying
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
	//	buyerEmailvalue.sendKeys(bEmail);

		// if whatsapp config not displayed need to pass the data after clicking edit
		// button

		boolean whatsappconfirm = whatsappConfig.isDisplayed();
		Assert.assertTrue(whatsappconfirm);
		// logic needs to updated (with new email) //checked

	}

	public void verifyAttendee_orderConfirmantion(String fname, String lname, String email, String mobile,			String forAttendee) {

		String attendee = prop.getProperty("ticketforattendee"); // Verifying the Order confirmation by validating order
		String Attendee = forAttendee; // success message

		if (Attendee.equalsIgnoreCase("yes")) { // if ticket is for attendee then give yes in configprop
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

		js.executeScript("arguments[0].click();", whatsappcheckbox);
//		js.executeScript("arguments[0].scrollIntoView(true);", proceedlast);
//		js.executeScript("arguments[0].click();", proceedlast);
//		
		// proceedPayment.click();
		// js.executeScript("arguments[0].click();", proceedPayment);
	}

	public void verifyOrderSuccessMessage() {

		js.executeScript("arguments[0].click();", proceedPayment);
		boolean orderSuccessMsg = ordersuccessFul.isDisplayed();
		Assert.assertTrue(orderSuccessMsg); // checked

	}

	public void verifyViewticketUrl() throws Throwable { // verify when user clicks on view ticket url

//		viewTickets.click();
//		String viewTicketUrl = driver.getCurrentUrl();
//
//		Assert.assertEquals("https://admin.tickets99.com/ticket/ASuLvCVm9%204uT6W-PPjrBw==", viewTicketUrl);

		String window1 = driver.getWindowHandle();
		viewTickets.click();
		Thread.sleep(1000);
		String UrlActual = "https://admin.tickets99.com/ticket/ASuLvCVm9%204uT6W-PPjrBw==";
		Utils.Windowhandless(window1);
		String viewTicketUrl = driver.getCurrentUrl();

		SoftAssert soft = new SoftAssert();

		soft.assertEquals(viewTicketUrl, UrlActual);
		// Assert.assertEquals(viewTicketUrl, UrlActual);
		driver.switchTo().window(window1); // checked

	}

	public void VerifyRegistrationUrl() {

		String windowreg = driver.getWindowHandle();
		Registrationbtn.click();
		Utils.Windowhandless(windowreg);
		String regUrl = driver.getCurrentUrl();
		String actualregUrl = "https://admin.tickets99.com/attendee/8vcGE9MKLXRs7x9Yj0Yn1g==";

		soft.assertEquals(regUrl, actualregUrl);
		driver.switchTo().window(windowreg); // checked
	}

	public void verifyOrderId() {

		String ticketwindow = driver.getWindowHandle();
		String ORDERID = orderid.getText();

		System.out.println("Order id is " + ORDERID);

		viewTickets.click();
		Utils.Windowhandless(ticketwindow);
		String ticketID = ticketverify.getText();
		Assert.assertEquals(ORDERID, ticketID);

		driver.switchTo().window(ticketwindow);

		// Utils.writeInToExcel(ORDERID);

		Utils.WriteInExistingExcel(ORDERID, "logincred", 14);

	}

	public void verifyBuyerdetails(String regfirstname, String regLastname) {

		String windowreg = driver.getWindowHandle();
		Registrationbtn.click();
		Utils.Windowhandless(windowreg);

		String regFname = fnamereg.getText();

		String reg_lname = reglname.getText();

		String actualfname = regfirstname;

		String actuallname = regLastname;

		Assert.assertEquals(actualfname, regFname);
		Assert.assertEquals(actuallname, regLastname);

		driver.switchTo().window(windowreg);

	}

	public void verifyCompleteRegistration(String intrestin, String helpwith, String about, String Picth) {

		String windowregcomplete = driver.getCurrentUrl();
		Registrationbtn.click();
		Utils.Windowhandless(windowregcomplete);

		InterstedIn.clear();
		InterstedIn.sendKeys(intrestin);
		InterstedIn.sendKeys(Keys.ENTER);

		canYouhelp.clear();
		canYouhelp.sendKeys(helpwith);
		canYouhelp.sendKeys(Keys.ENTER);

		aboutme.clear();
		aboutme.sendKeys(about);

		pitch.clear();
		pitch.sendKeys(Picth);

		js.executeScript("arguments[0].click();", completeregbtn);

		// completeregbtn.click();

		boolean checkcompleteionmsg = completemsg.isDisplayed();

		System.out.println("printing msg " + completemsg.getText());
		System.out.println("printing msg " + completemsg.isDisplayed());

		boolean buttonevent = eventpagebtn.isDisplayed();

		// Assert.assertTrue(buttonevent);
		// Assert.assertTrue(checkcompleteionmsg);

	}

}
