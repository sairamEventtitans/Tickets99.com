package com.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.base.TestBase;
import com.utils.Utils;

public class LoginWithExistingEmailFree extends TestBase {

	@FindBy(xpath = "//div[@onclick='redirecttoticket(this)']") // Address of the Webelements
	WebElement buyButton;

	@FindBy(xpath = "(//span[text()='+'])[1]")
	WebElement AddticketFree;

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

	@FindBy(xpath = "//h5[text()='WhatsApp Configuration']")
	WebElement whatsappConfig;

	@FindBy(xpath = "//span[@id='orderby']")
	WebElement nameValidate;

	@FindBy(xpath = "//h4[@id='fname']")
	WebElement FnameAttendee;

	@FindBy(xpath = "//h5[@id='lname']")
	WebElement lnameAttendee;

	@FindBy(xpath = "(//button[@onclick='return ViewMyTicket(this);'])[1]")
	WebElement viewTickets;
	
	@FindBy(xpath = "//button[text()='Complete Registration']")
	WebElement completeregbtn;

	public LoginWithExistingEmailFree(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	public void verifyLoginWithExistingEmail(String Email, String password, String Url) throws Throwable {

		buyButton.click();
		AddticketFree.click();

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

	public void verifyWhatsAppCheckbox() {

		boolean whatsAppCheckbox = whatsappConfig.isDisplayed();

		Assert.assertTrue(whatsAppCheckbox);

	}

	public void verifyAttendeeDetailsInViewTickets(String attendeeStatus, String firstName, String lastName) {

		String attendee = attendeeStatus;

		String windowmain = driver.getWindowHandle();

		if (attendee.equalsIgnoreCase("yes")) {

			viewTickets.click();
			Utils.Windowhandless(windowmain);

			String NameOfAttendee_given = firstName + " " + lastName;
			String attendeeName_inApp = nameValidate.getText();

			Assert.assertEquals(NameOfAttendee_given, attendeeName_inApp);

			driver.switchTo().window(windowmain);

			System.out.println("verified");

		} else {

			System.out.println("Ticket bought for self not for Someone");
		}
	}

	public void verifyAttendeeDetailsInRegistration(String attendeeStatus, String firstName, String lastName)
			throws Throwable {

		String attendee = attendeeStatus;

		String windowmain = driver.getWindowHandle();

		if (attendee.equalsIgnoreCase("yes")) {

			completeregbtn.click();

			Utils.Windowhandless(windowmain);

			String FnameInReg = FnameAttendee.getText();

			String lnameInReg = lnameAttendee.getText();

			String NameInReg = FnameInReg + " " + lnameInReg;

			String NameGivenToAttendee = firstName + " " + lastName;

			System.out.println(NameInReg);

			System.out.println(NameGivenToAttendee);

			Assert.assertEquals(NameGivenToAttendee, NameInReg);

			driver.switchTo().window(windowmain);
		}

		System.out.println("Ticket bought for self not for Someone");

	}
	
	
	
	
	

}
