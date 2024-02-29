package com.testscripts;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.base.TestBase;
import com.pages.LoginWithGoogle;
import com.pages.LoginWithNewEmail;
import com.utils.Utils;

public class LoginWithNewEmailTest extends TestBase {

	String sheetname2 = "emaildata";
	LoginWithNewEmail newEmail;
	LoginWithGoogle login;

	@DataProvider
	public String[][] emailtestdata() throws Throwable {
		return Utils.FetchData(sheetname2);
	}

	@BeforeClass
	public void setup() { // prerequisites for testmethods are placed here

		Initialization();
		newEmail = new LoginWithNewEmail(driver);
		login = new LoginWithGoogle(driver);

	}

//	@BeforeMethod
//	public void testsetup() {                               //For quick checking 
//
//		Initialization();
//		newEmail = new LoginWithNewEmail(driver);
//		driver.get("https://admin.tickets99.com/event/orderdetails/acahGJEo0LARB%20brV55pyQ==/vision-2024");
//
//	}
//	

	@Test(priority = 1, dataProvider = "emailtestdata", dataProviderClass = LoginWithNewEmailTest.class)
	public void ValidateSigninWithNewEmail(String[] row) throws Throwable {

		newEmail.verifyNewEmailLogin(row[0]);

	}

	@Test(priority = 2, dataProvider = "emailtestdata", dataProviderClass = LoginWithNewEmailTest.class)

	public void ValidateCoupon_Email(String[] row) {

		newEmail.verifyCoupon(row[1]);

	}

	@Test(priority = 3)
	public void ValidateEmailDisable_Email() {
		newEmail.verifyEmailDisable();
	}

	@Test(priority = 4, dataProvider = "emailtestdata", dataProviderClass = LoginWithNewEmailTest.class)
	public void validateWhatsappCheckbox(String[] row) {
		newEmail.verifyWhatsAppCheckbox(row[3], row[4], row[5]);

	}

	@Test(priority = 5, dataProvider = "emailtestdata", dataProviderClass = LoginWithNewEmailTest.class)
	public void validateAttendeeEmail(String[] row) {

		login.verifyAttendee_orderConfirmantion(row[6], row[7], row[8], row[9],row[19]);

	}
	
	@Test(priority = 6)
	public void validateOrderSuccessMessage() {
		
		login.verifyOrderSuccessMessage();
	}
	
	
	

	@Test(priority = 7)
	public void validateOrderifVip_email() {

		newEmail.verifyOderVip();

	}

//	@Test(priority = 6)
//	
//	public void ValidateVipTicketID() throws Throwable {
//		
//		login.verifyViewticketUrl();
//		newEmail.verifyTicketUrl();
//		
//		
//		
//	}

	@Test(priority = 8)
	public void ValidateOrderIdFree_email() {

		newEmail.verifyOrderIdFree();

	}

	@Test(priority = 9, dataProvider = "emailtestdata", dataProviderClass = LoginWithNewEmailTest.class)
	public void validateAttendeeRegistration_Vip(String[] row) {

		newEmail.VerifyAttendeeRegistration_Vip(row[10], row[11], row[12], row[13], row[14], row[15], row[16]);

	}

	@AfterMethod

	public void teardown(ITestResult result) throws Throwable {

		if (result.getStatus() == ITestResult.FAILURE) {

			capture(result.getMethod().getMethodName());

		}

	}

	public void capture(String methodname) throws Throwable {

		Utils.CapturescreenShot(methodname);

	}

}
