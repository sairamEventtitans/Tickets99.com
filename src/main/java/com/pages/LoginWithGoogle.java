package com.pages;

import org.apache.xmlbeans.impl.xb.xsdschema.All;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.base.TestBase;

public class LoginWithGoogle extends TestBase {

	@FindBy(xpath = "//span[@onclick='redirecttoticket(this)']")
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

	@FindBy(xpath = "(//div[@class='VfPpkd-RLmnJb'])[2]")
	WebElement continuebtn;

//	@FindBy(xpath = "(//div[@class='VfPpkd-RLmnJb'])[3]")
//	WebElement Allowbtn;

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

	@FindBy(xpath = "//button[text()='Proceed']")
	WebElement proceedlast;

	public LoginWithGoogle(WebDriver driver) {

		PageFactory.initElements(driver, this);

	}

	public void VerifyGoogleLoginAttendee(String uname, String pword, String afanme, String alname, String amail,
			String amobile) throws Throwable {

		String attendee = prop.getProperty("ticketbuymode");

		buyButton.click();
		Addticket.click();
		proceedticketbtn.click();
		signInGoogle.click();
		emailvalue.sendKeys(uname);
		nextbtn.click();
		passwordvalue.sendKeys(pword);
		Thread.sleep(2000);

//		WebDriverWait wait=new WebDriverWait(driver, 15);
//		wait.until(ExpectedConditions.visibilityOf(continuebtn));

		continuebtn.click();
		Thread.sleep(2000);
		Thread.sleep(2000);

		if (attendee.equals("attendee")) {
			attendeebox.click();
			fnamevalue.sendKeys(afanme);
			lnamevalue.sendKeys(alname);
			attendeeemailvalue.sendKeys(amail);
			mobilenumvalue.sendKeys(amobile);

			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].click();", proceedlast);

		}

		else {
			proceedlast.click();
		}
		// proceedlast.click();
	}

	public void VerifyGoogleLoginSelf() {

	}

}
