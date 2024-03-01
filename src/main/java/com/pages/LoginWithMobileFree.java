package com.pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.base.TestBase;
import com.utils.Utils;

public class LoginWithMobileFree extends TestBase {

	@FindBy(xpath = "//div[@onclick='redirecttoticket(this)']")
	WebElement buyTicket;

	@FindBy(xpath = "(//span[text()='+'])[1]")
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

	@FindBy(xpath = "//input[@id='textbuyerphone']")
	WebElement mobileDisable;

	@FindBy(xpath = "//input[@id='textbuyerfirstname']")
	WebElement buyerfirstname;

	@FindBy(xpath = "//input[@id='textbuyerlastname']")
	WebElement buyerLastname;

	@FindBy(xpath = "//input[@id='textbuyeremail']")
	WebElement buyerEmail;

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
	WebElement orderSuccessMsg;

	@FindBy(xpath = "//div[@id='OrderDiv']//span")
	WebElement orderid;

	@FindBy(xpath = "(//button[@onclick='return ViewMyTicket(this);'])[1]")
	WebElement viewTickets;

	@FindBy(xpath = "//td//span[@id='orderid']")
	WebElement ticketverify;

	@FindBy(xpath = "//button[@onclick='return OpenCompleteProfile(this);']")
	WebElement Registrationbtn;

	@FindBy(xpath = "//div[@class='userdetibdg pt-3']//h4")
	WebElement fnamereg;

	@FindBy(xpath = "//div[@class='userdetibdg pt-3']//h5")
	WebElement reglname;

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
	
	@FindBy (id="textcompanyname")
	WebElement CompanyName;
	
	@FindBy (id="textdesignationname")
	WebElement Job;
	
	@FindBy (xpath="//span[@class='py-0 px-2 text-red-col']")
	WebElement editButton;
	
	public LoginWithMobileFree(WebDriver driver) {

		PageFactory.initElements(driver, this);
	}

	public void verifyMobileLogin(String mobile_number) throws Throwable {

		buyTicket.click();
		Addticket.click();
		proceedticketbtn.click();
		mobileValue.sendKeys(mobile_number);
		continueBtn.click();
		Thread.sleep(30000);
	//	verifyOtp.click();

		boolean orderSummaryMeassage = orderSummary.isDisplayed();
		Assert.assertTrue(orderSummaryMeassage);

	}

	public void verifydisableEmail(String b_fname, String b_lname, String b_email) throws Throwable {

		//editButton.click();
		
		buyerfirstname.clear();
		buyerfirstname.sendKeys(b_fname);

		buyerLastname.clear();
		buyerLastname.sendKeys(b_lname);
		
		buyerEmail.clear();
		buyerEmail.sendKeys(b_email);
		
		Thread.sleep(10000);

		boolean verifyDisable = mobileDisable.isEnabled();
		Assert.assertFalse(verifyDisable);
	}

	public void verifyAttendee_orderConfirmantion(String forAttendee, String fname, String lname, String email,
			String mobile) {

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
		Utils.javaScriptClick(whatsappcheckbox);

	}

	public void verifyOrderSuccessMsg() {

		Utils.javaScriptClick(proceedlast);
		boolean orderMsgVerify = orderSuccessMsg.isDisplayed();
		Assert.assertTrue(orderMsgVerify);

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

		Utils.WriteInExistingExcel(ORDERID, "MobileNumberFree", 9);

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

	public void verifyCompleteRegistration(String companyName,String JobTitle,String intrestin, String helpwith, String about, String Picth) {

		String windowregcomplete = driver.getCurrentUrl();
		Registrationbtn.click();
		Utils.Windowhandless(windowregcomplete);
		
		CompanyName.clear();
		CompanyName.sendKeys(companyName);
		
		Job.clear();
		Job.sendKeys(JobTitle);

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

		Utils.javaScriptClick(completeregbtn);
 
		// completeregbtn.click();

		boolean checkcompleteionmsg = completemsg.isDisplayed();

		System.out.println("printing msg " + completemsg.getText());
		System.out.println("printing msg " + completemsg.isDisplayed());

		boolean buttonevent = eventpagebtn.isDisplayed();

		// Assert.assertTrue(buttonevent);
		// Assert.assertTrue(checkcompleteionmsg);

	}

}
