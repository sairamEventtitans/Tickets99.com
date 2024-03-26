package com.testscripts;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.base.TestBase;
import com.pages.LoginWithExistingEmailUpiPayment;
import com.pages.LoginWithGoogle;
import com.pages.LoginWithGooglePaidTicket;
import com.pages.LoginWithGoogleRazorPayUpi;
import com.pages.LoginWithNewEmail;
import com.utils.Utils;

public class LoginWithNewMailRazorPayTest extends TestBase {

	String sheetname2 = "ExistingEmaillogin";
	LoginWithNewEmail newEmail;
	LoginWithGoogle login;
	LoginWithExistingEmailUpiPayment Upi;
	LoginWithGooglePaidTicket googlePaid;
	LoginWithGoogleRazorPayUpi razorUpi;

	@BeforeClass
	public void setup() { // prerequisites for testmethods are placed here

		Initialization();
		newEmail = new LoginWithNewEmail(driver);
		login = new LoginWithGoogle(driver);
		Upi = new LoginWithExistingEmailUpiPayment(driver);
		googlePaid = new LoginWithGooglePaidTicket(driver);
		razorUpi = new LoginWithGoogleRazorPayUpi(driver);
	}

	@Test(priority = 1, dataProvider = "emailtestdata", dataProviderClass = LoginWithNewEmailTest.class)
	public void ValidateSigninWithNewEmail_Newmailrazor(String[] row) throws Throwable {

		newEmail.verifyNewEmailLogin(row[0]);

	}

	@Test(priority = 2, dataProvider = "emailtestdata", dataProviderClass = LoginWithNewEmailTest.class)

	public void ValidateCoupon_Email_NewmailRzr(String[] row) {

		newEmail.verifyCoupon(row[1]);

	}
	
	
	@Test(priority = 3)
	
	public void validateRemoveCoupon_NewmailRzr() {
		
		Upi.verifyCouponRemoveFunctionality();
	}
	

	@Test(priority = 4)
	public void ValidateEmailDisable_NewmailRzr() {
		newEmail.verifyEmailDisable();
	}

	@Test(priority = 5, dataProvider = "emailtestdata", dataProviderClass = LoginWithNewEmailTest.class)
	public void validateWhatsappCheckbox_NewmailRzr(String[] row) {
		newEmail.verifyWhatsAppCheckbox(row[3], row[4], row[5]);

	}

	@Test(priority = 6, dataProvider = "emailtestdata", dataProviderClass = LoginWithNewEmailTest.class)
	public void validateAttendeeEmail_NewmailRzr(String[] row) {

		login.verifyAttendee_orderConfirmantion(row[6], row[7], row[8], row[9], row[2]);

	}
	
	@Test(priority = 7)
	public void validatePaymentUrl_NewmailRzr() { 
		razorUpi.verifyPaymentPage();
		// verify payment url
	}

	@Test(priority = 8,dataProvider = "dataFetch", dataProviderClass = LoginWithGoogleRazorPayUpiTest.class)
	public void validateRazorUpiPaymentMsg_NewmailRzr(String row[]) throws Throwable {
		razorUpi.verifyRazorCheckbox(row[0]);
	}

	@Test(priority = 9,dataProvider = "dataFetch", dataProviderClass = LoginWithGoogleRazorPayUpiTest.class)
	public void validateUpiMobile_NewmailRzr(String row[]) throws Throwable {
		razorUpi.verifyRazorUpiPaymentMethods(row[1]);
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
