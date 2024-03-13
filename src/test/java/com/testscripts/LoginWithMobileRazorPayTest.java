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
import com.pages.LoginWithMobileFree;
import com.pages.LoginWithMobileUpiPay;
import com.pages.LoginWithNewEmail;
import com.utils.Utils;

public class LoginWithMobileRazorPayTest extends TestBase {

	
	String sheetname2 = "ExistingEmaillogin";
	LoginWithNewEmail newEmail;
	LoginWithGoogle login;
	LoginWithExistingEmailUpiPayment Upi;
	LoginWithGooglePaidTicket googlePaid;
	LoginWithGoogleRazorPayUpi razorUpi;
	LoginWithMobileFree mobileFree;
	LoginWithMobileUpiPay mobileUpiPay;

	@BeforeClass
	public void setup() { // prerequisites for testmethods are placed here

		Initialization();
		newEmail = new LoginWithNewEmail(driver);
		login = new LoginWithGoogle(driver);
		Upi = new LoginWithExistingEmailUpiPayment(driver);
		googlePaid = new LoginWithGooglePaidTicket(driver);
		razorUpi = new LoginWithGoogleRazorPayUpi(driver);
		mobileFree = new LoginWithMobileFree(driver);
		mobileUpiPay = new LoginWithMobileUpiPay(driver);
	}
	
	@Test(priority = 1, dataProvider = "dataFetch", dataProviderClass = LoginWithMobileUpiPayTest.class)

	public void validateMobileLogin_MbUpiRazor(String[] row) throws Throwable {

		mobileUpiPay.verifyMobileLogin(row[0]);

	}

	@Test(priority = 2, dataProvider = "dataFetch", dataProviderClass = LoginWithMobileUpiPayTest.class)

	public void validateDisableEmail_MbUpiRzr(String row[]) throws Throwable {
		mobileFree.verifydisableEmail(row[2], row[3], row[4]);
	}

	@Test(priority = 3, dataProvider = "dataFetch", dataProviderClass = LoginWithMobileUpiPayTest.class)

	public void validateOrderConfirmation_MbUpiRzr(String[] row) {
		mobileFree.verifyAttendee_orderConfirmantion(row[1], row[5], row[6], row[7], row[8]);
	}

	@Test(priority = 4, dataProvider = "dataFetch", dataProviderClass = LoginWithMobileUpiPayTest.class)
	public void validateCoupon_MobileRzr(String[] row) {

		newEmail.verifyCoupon(row[9]);
		// check coupon applied and remove coupon

	}

	@Test(priority = 5)
	public void validateCouponRemove_mobileRzr() {

		Upi.verifyCouponRemoveFunctionality();

	}

	@Test(priority = 6, dataProvider = "dataFetch", dataProviderClass = LoginWithMobileUpiPayTest.class)
	public void validateWhatsappIsPresent_mobileRzr(String[] row) {
		newEmail.verifyWhatsAppCheckbox(row[2], row[3], row[4]);
		// after validating click on proceed
	}
	
	
	@Test(priority = 7)
	public void validatePaymentUrl_mobileRzr() { 
		razorUpi.verifyPaymentPage();
		// verify payment url
	}

	@Test(priority = 8,dataProvider = "dataFetch", dataProviderClass = LoginWithGoogleRazorPayUpiTest.class)
	public void validateRazorUpiPaymentMsg_mobileRzr(String row[]) throws Throwable {
		razorUpi.verifyRazorCheckbox(row[0]);
	}

	@Test(priority = 9,dataProvider = "dataFetch", dataProviderClass = LoginWithGoogleRazorPayUpiTest.class)
	public void validateUpiMobile_mobileRzr(String row[]) throws Throwable {
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
