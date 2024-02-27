package com.testscripts;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.base.TestBase;
import com.pages.LoginWithGoogle;
import com.utils.Utils;

public class LoginGoogleTests extends TestBase {

	LoginWithGoogle loginWithGoogle;

	String sheetname = "logincred";

	public LoginGoogleTests() {

		super();
	}

	@DataProvider
	public String[][] dataFetch() throws Throwable {
		return Utils.FetchData(sheetname);
	}

	@BeforeClass
	public void setup() { // prerequisites for test methods are placed here

		Initialization();
		loginWithGoogle = new LoginWithGoogle(driver);

	}

//	@BeforeMethod
//	public void testsetup() {                               //For quick checking 
//
//		Initialization();
//		loginWithGoogle = new LoginWithGoogle(driver);
//		driver.get("https://admin.tickets99.com/attendee/HvSTWX%20XvD0nnNCG1zQuqA==");
//
//	}

	@Test(priority = 1, dataProvider = "dataFetch", dataProviderClass = LoginGoogleTests.class)
	public void validateDisableEmail_google(String[] rowindex) throws Throwable {

		loginWithGoogle.VerifyGooglesignIn(rowindex[1], rowindex[2]);

	}

	@Test(priority = 2, dataProvider = "dataFetch", dataProviderClass = LoginGoogleTests.class)
	public void ValidateWhatsappConfigEnable(String[] rowindex) throws Throwable {

		loginWithGoogle.verifyWhatsappCongig(rowindex[7], rowindex[8], rowindex[9]);

	}

	@Test(priority = 3, dataProvider = "dataFetch", dataProviderClass = LoginGoogleTests.class)
	public void validateTicketForAttendee_google(String[] rowindex) throws Throwable {

		loginWithGoogle.verifyAttendee_orderConfirmantion(rowindex[3], rowindex[4], rowindex[5], rowindex[6]);

	}

	@Test(priority = 4)
	public void validateOrderSuccessMessage() {

		loginWithGoogle.verifyOrderSuccessMessage();

	}

	@Test(priority = 5)

	public void validateViewTicketUrl_google() throws Throwable {

		loginWithGoogle.verifyViewticketUrl();

	}

	@Test(priority = 6)
	public void ValidateRegUrl_google() {

		loginWithGoogle.VerifyRegistrationUrl();

	}

	@Test(priority = 7)
	public void ValidateOrderId_google() {
		loginWithGoogle.verifyOrderId();
	}

	@Test(priority = 8, dataProvider = "dataFetch", dataProviderClass = LoginGoogleTests.class)
	public void validateBuyerDetails_google(String[] rowindex) throws Throwable {

		loginWithGoogle.verifyBuyerdetails(rowindex[7], rowindex[8]);

	}

	@Test(priority = 9, dataProvider = "dataFetch", dataProviderClass = LoginGoogleTests.class)
	public void validateRegistration_google(String[] rowindex) throws Throwable {

		loginWithGoogle.verifyCompleteRegistration(rowindex[10], rowindex[11], rowindex[12], rowindex[13]);

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
