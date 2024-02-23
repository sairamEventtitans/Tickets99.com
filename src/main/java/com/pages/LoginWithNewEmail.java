package com.pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import com.base.TestBase;
import com.utils.Utils;

public class LoginWithNewEmail extends TestBase {

	JavascriptExecutor js = (JavascriptExecutor) driver;

	@FindBy(xpath = "//div[@onclick='redirecttoticket(this)']") // Address of the Webelements
	WebElement buyButton;

	@FindBy(xpath = "(//span[text()='+'])[1]")
	WebElement AddticketFree;

	@FindBy(xpath = "(//span[text()='+'])[2]")
	WebElement AddticketVip;

	@FindBy(xpath = "//div[@onclick='proceedwithtickets()']")
	WebElement proceedticketbtn;

	@FindBy(id = "txtguestcheckoutemail")
	WebElement Emailvalue;

	@FindBy(xpath = "//button[@onclick='proceedtoregister()']")
	WebElement ContinueMail;

	@FindBy(id = "txtVerifyCode")
	WebElement Verifyotpvalue;

	@FindBy(id = "VerifyMail")
	WebElement verifymailbtn;

	@FindBy(id = "textcouponcode")
	WebElement couponValue;

	@FindBy(id = "applycoupon")
	WebElement couponApplybtn;

	@FindBy(xpath = "//small[text()='Coupon code VISION2024 is applied successfully. Discounted Amount is : ₹ 5.00']")
	WebElement Couponmessage;

	@FindBy(xpath = "//i[@class='icon-edit-3 fs-6']")
	WebElement editbtn;

	@FindBy(id = "textbuyerfirstname")
	WebElement buyerFname;

	@FindBy(id = "textbuyerlastname")
	WebElement buyerLname;

	@FindBy(id = "textbuyerphone")
	WebElement buyerMobile;

	@FindBy(id = "textbuyeremail")
	WebElement emailDisable;

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

	@FindBy(xpath = "//h5[text()='WhatsApp Configuration']")
	WebElement whatsappConfig;

	@FindBy(id = "isWhatsappYes")
	WebElement whatsappcheckbox;

	@FindBy(id = "btnproceed")
	WebElement proceedlast;

	@FindBy(xpath = "(//button[@onclick='return ViewMyTicket(this);'])[1]")
	WebElement Ticketurlbtn;

	@FindBy(xpath = "(//button[@onclick='return ViewMyTicket(this);'])[2]")
	WebElement viewTickets;

	@FindBy(xpath = "//div[@id='OrderDiv']//span")
	WebElement orderid;

	@FindBy(xpath = "//td//span[@id='orderid']")
	WebElement ticketverify;

	@FindBy(xpath = "(//button[text()='Complete Registration'])[1]")
	WebElement Registrationbtnvip;

	@FindBy(xpath = "(//button[text()='Complete Registration'])[2]")
	WebElement Registrationbtnfree;

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

	@FindBy(xpath = "//button[text()='  Complete Registration']")
	WebElement completeregbtn;

	@FindBy(xpath = "//h5[text()='THANKS FOR COMPLETING YOUR REGISTRATION']")
	WebElement completemsg;

	@FindBy(id = "textcompanyname")
	WebElement companyNameAttendee;

	@FindBy(id = "textdesignationname")
	WebElement JobtitleAttendee;

	@FindBy(xpath = "(//div[@class='selected-flag'])[1]")
	WebElement countrydropdown1;

	@FindBy(xpath = "(//div[@class='selected-flag'])[2]")
	WebElement countrydopdown2;

	@FindBy(xpath = "//span[text()='+91 India (भारत)']")
	WebElement countryphonenumber;

	@FindBy(xpath = "//input[@onblur='return ValidateOnBlurEmailId(this)']")
	WebElement Attendee2emailvalue;
	
	@FindBy(xpath = "//h3[text()='Your order is completed']")
	WebElement ordersuccessFul;
	
	@FindBy (id="btnproceed")
	WebElement proceedPayment;
	
	@FindBy (xpath="(//button[@id='btnproceed'])[1]")
	WebElement proceed2;

	public LoginWithNewEmail(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	public void verifyNewEmailLogin(String email) throws Throwable {

		String emailVerificationUrl = "https://www.tickets99.com/buy/vision-2024/ticket";

		buyButton.click();

		AddticketFree.click();
		AddticketVip.click();
		proceedticketbtn.click();
		Emailvalue.sendKeys(email);
		ContinueMail.click();
		Thread.sleep(30000);
		verifymailbtn.click();

		String actualUrl = driver.getCurrentUrl();
		Assert.assertEquals(emailVerificationUrl, actualUrl);
	}

	public void verifyCoupon(String Couponcode) {

		couponValue.sendKeys(Couponcode);
		couponApplybtn.click();

		boolean couponmessage = Couponmessage.isDisplayed();
		Assert.assertTrue(couponmessage);

	}

	public void verifyEmailDisable() {

		editbtn.click();
		boolean disabledEmail = emailDisable.isEnabled();
		Assert.assertFalse(disabledEmail);

	}

	public void verifyWhatsAppCheckbox(String mobile_b, String fname_b, String lname_b) {

		buyerMobile.clear();
		buyerMobile.sendKeys(mobile_b);

		buyerFname.clear();
		buyerFname.sendKeys(fname_b);

		buyerLname.clear();
		buyerLname.sendKeys(lname_b);

		boolean whatsAppCheckbox = whatsappConfig.isDisplayed();

		Assert.assertTrue(whatsAppCheckbox);

	}

	public void verifyTicketUrl() throws Throwable {

		String window1 = driver.getWindowHandle();
		viewTickets.click();
		Thread.sleep(1000);
		String UrlActual = "https://admin.tickets99.com/ticket/ASuLvCVm9%204uT6W-PPjrBw==";

		System.out.println(UrlActual);

		Utils.Windowhandless(window1);
		String viewTicketUrl = driver.getCurrentUrl();

		SoftAssert soft = new SoftAssert();
		System.out.println(viewTicketUrl);
		soft.assertEquals(viewTicketUrl, UrlActual);
		// Assert.assertEquals(viewTicketUrl, UrlActual);
		driver.switchTo().window(window1); // checked

	}

	public void verifyOderVip() {

		String ticketwindow = driver.getWindowHandle();
		String orderID = orderid.getText();
		Ticketurlbtn.click();
		Utils.Windowhandless(ticketwindow);
		String ticketID = ticketverify.getText();
		Assert.assertEquals(orderID, ticketID);

		driver.switchTo().window(ticketwindow);
	}

	public void verifyOrderIdFree() {

		String ticketwindow = driver.getWindowHandle();
		String orderID = orderid.getText();
		viewTickets.click();
		Utils.Windowhandless(ticketwindow);
		String ticketID = ticketverify.getText();
		Assert.assertEquals(orderID, ticketID);

		driver.switchTo().window(ticketwindow);

	}

	public void VerifyAttendeeRegistration_Vip(String company, String job, String intrestin, String helpwith,
			String about, String Picth, String attendee2mail) {

		String windowregcomplete = driver.getCurrentUrl();
		Registrationbtnvip.click();
		Utils.Windowhandless(windowregcomplete);

		countrydropdown1.click();
		countryphonenumber.click();

		companyNameAttendee.clear();
		companyNameAttendee.sendKeys(company);

		JobtitleAttendee.clear();
		JobtitleAttendee.sendKeys(job);

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

//		countrydopdown2.click();
//		countryphonenumber.click();

		Attendee2emailvalue.clear();
		Attendee2emailvalue.sendKeys(attendee2mail);

//		js.executeScript("arguments[0].value='sairam234@gmail.com';", Attendee2emailvalue);

		js.executeScript("arguments[0].click();", completeregbtn);

		// completeregbtn.click();

//		boolean checkcompleteionmsg = completemsg.isDisplayed();
//
//		System.out.println("printing msg " + completemsg.getText());
//		System.out.println("printing msg " + completemsg.isDisplayed());
//
//		boolean buttonevent = eventpagebtn.isDisplayed();		
//		Assert.assertTrue(buttonevent);

	}
	
	
	public void verifyAttendee_orderConfirmantion(String fname, String lname, String email, String mobile) {

		String attendee = prop.getProperty("ticketforattendee"); // Verifying the Order confirmation by validating order
																	// success message

		if (attendee.equalsIgnoreCase("yes")) { // if ticket is for attendee then give yes in configprop
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
		//proceedPayment.click();
		js.executeScript("arguments[0].click();", proceedPayment);
		
		js.executeScript("arguments[0].click();", proceed2);

		
		
		boolean orderSuccessMsg = ordersuccessFul.isDisplayed();
		Assert.assertTrue(orderSuccessMsg); // checked

	}

}
