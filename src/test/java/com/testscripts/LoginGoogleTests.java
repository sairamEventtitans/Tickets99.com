package com.testscripts;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.base.TestBase;
import com.pages.LoginWithGoogle;
import com.utils.Utils;

public class LoginGoogleTests extends TestBase {

	LoginWithGoogle loginWithGoogle;
	ExtentReports extent;
	ExtentTest logger;
	String sheetname = "logincred";

	private Logger logger2; // Declare the logger field

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
//	public void loggingSetup() {
//		logger.info("Starting test method execution");
//	}

//	@BeforeMethod
//	public void testsetup() {                               //For quick checking 
//
//		Initialization();
//		loginWithGoogle = new LoginWithGoogle(driver);
//		driver.get("https://admin.tickets99.com/attendee/HvSTWX%20XvD0nnNCG1zQuqA==");
//
//	}

//	@BeforeMethod
//	public void setup2() {
//		// Initialize the logger
//		logger2 = LogManager.getLogger(LoginGoogleTests.class);
//		logger2.info("Starting test method execution");
//	}

	@Test(priority = 1, dataProvider = "dataFetch", dataProviderClass = LoginGoogleTests.class)
	public void validateDisablemail_google(String[] rowindex) throws Throwable {
		// logger2.info("Starting test method execution");
		loginWithGoogle.VerifyGooglesignIn(rowindex[1], rowindex[2]);

	}

	@Test(priority = 2, dataProvider = "dataFetch", dataProviderClass = LoginGoogleTests.class)
	public void ValidateWhatsappConfigEnable(String[] rowindex) throws Throwable {

		loginWithGoogle.verifyWhatsappCongig(rowindex[7], rowindex[8], rowindex[9], rowindex[1]);

	}

	@Test(priority = 3, dataProvider = "dataFetch", dataProviderClass = LoginGoogleTests.class)
	public void validateTicketForAttendee_google(String[] rowindex) throws Throwable {

		loginWithGoogle.verifyAttendee_orderConfirmantion(rowindex[3], rowindex[4], rowindex[5], rowindex[6],
				rowindex[15]);

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
	public void validateAttendeeDetails_googleloginViewTickets(String rowindex[]) {
		loginWithGoogle.verifyAttendeeDetailsInViewTickets(rowindex[15], rowindex[3], rowindex[4]);
	}

	@Test(priority = 10, dataProvider = "dataFetch", dataProviderClass = LoginGoogleTests.class)
	public void validateAttendeeDetails_googleCompleteReg(String rowindex[]) throws Throwable {
		loginWithGoogle.verifyAttendeeDetailsInRegistration(rowindex[15], rowindex[3], rowindex[4]);
	}

//	@Test(priority = 9, dataProvider = "dataFetch", dataProviderClass = LoginGoogleTests.class)
//	public void validateRegistration_google(String[] rowindex) throws Throwable {
//
//		loginWithGoogle.verifyCompleteRegistration(rowindex[10], rowindex[11], rowindex[12], rowindex[13]);
//
//	} 

	@AfterMethod

	public void teardown(ITestResult result) throws Throwable {

		if (result.getStatus() == ITestResult.FAILURE) {
			capture(result.getMethod().getMethodName());
		}

	}

	public void capture(String methodname) throws Throwable {
		Utils.CapturescreenShot(methodname);
	}

//	public void LoggingInfo(ITestResult result) {
//		if (result.getStatus() == ITestResult.SUCCESS) {
//			logger2.info("Test method passed: " + result.getMethod().getMethodName());
//		} else if (result.getStatus() == ITestResult.FAILURE) {
//			logger2.error("Test method failed: " + result.getMethod().getMethodName());
//		} else if (result.getStatus() == ITestResult.SKIP) {
//			logger2.warn("Test method skipped: " + result.getMethod().getMethodName());
//		}
//	}

}
