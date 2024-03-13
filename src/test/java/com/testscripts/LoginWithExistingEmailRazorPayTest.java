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

public class LoginWithExistingEmailRazorPayTest extends TestBase {
	
				
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
	

	@Test(priority = 1, dataProvider = "emailtestdata", dataProviderClass = LoginWithExistingEmailUpiPayTest.class)
	public void validateLogin_ExistingEmailRzr(String[] row) throws Throwable {
		Upi.verifyLoginWithExistingEmail(row[0], row[1], row[2]); // login as exsisting user
	}

	@Test(priority = 2, dataProvider = "emailtestdata", dataProviderClass = LoginWithExistingEmailUpiPayTest.class)
	public void validateCoupon_ExistingEmailRzr(String[] row) {

		newEmail.verifyCoupon(row[3]);
		// check coupon applied and remove coupon

	}

	@Test(priority = 3)
	public void validateEmailDisable_ExistingEmailRzr() {

		newEmail.verifyEmailDisable();

		// email should be disabled by default

	}

	@Test(priority = 4)
	public void validateCouponRemove_ExistingEmailRzr() {

		Upi.verifyCouponRemoveFunctionality();

	}

	@Test(priority = 5, dataProvider = "emailtestdata", dataProviderClass = LoginWithExistingEmailUpiPayTest.class)
	public void validateBuyForSomeone_ExistingEmailRzr(String[] row) {

		login.verifyAttendee_orderConfirmantion(row[4], row[5], row[6], row[7], row[17]);
	}

	@Test(priority = 6, dataProvider = "emailtestdata", dataProviderClass = LoginWithExistingEmailUpiPayTest.class)
	public void validateWhatsappIsPresent_ExistingEmailRzr(String[] row) {
		newEmail.verifyWhatsAppCheckbox(row[8], row[9], row[10]);
		// after validating click on proceed
	}

	@Test(priority = 7)
	public void validatePaymentUrl_ExistingmailRzr() {
		razorUpi.verifyPaymentPage();
		// verify payment url
	}
	
	@Test(priority = 8,dataProvider = "dataFetch", dataProviderClass = LoginWithGoogleRazorPayUpiTest.class)
	public void validateRazorUpiPaymentMsg_ExistingEmailRzr(String row[]) throws Throwable {
		razorUpi.verifyRazorCheckbox(row[0]);
	}

	@Test(priority = 9,dataProvider = "dataFetch", dataProviderClass = LoginWithGoogleRazorPayUpiTest.class)
	 public void validateUpiMobile_ExistingEmailRzr(String row[]) throws Throwable {
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
