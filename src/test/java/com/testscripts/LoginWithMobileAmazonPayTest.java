package com.testscripts;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.base.TestBase;
import com.pages.LoginWithExistingEmailUpiPayment;
import com.pages.LoginWithGoogle;
import com.pages.LoginWithGooglePaidTicket;
import com.pages.LoginWithMobileAmazonPay;
import com.pages.LoginWithMobileFree;
import com.pages.LoginWithMobileUpiPay;
import com.pages.LoginWithNewEmail;
import com.utils.Utils;

public class LoginWithMobileAmazonPayTest extends TestBase {

	String sheetname4 = "MobileNumberUpi";
	LoginWithNewEmail newEmail;
	LoginWithGoogle login;
	LoginWithExistingEmailUpiPayment Upi;
	LoginWithGooglePaidTicket googlePaid;
	LoginWithMobileFree mobileFree;
	LoginWithMobileUpiPay mobileUpiPay;
	LoginWithMobileAmazonPay mbamazonpay;

	@DataProvider
	public String[][] dataFetch() throws Throwable {
		return Utils.FetchData(sheetname4);
	}

	@BeforeClass
	public void setup() { // prerequisites for testmethods are placed here

		Initialization();
		newEmail = new LoginWithNewEmail(driver);
		login = new LoginWithGoogle(driver);
		Upi = new LoginWithExistingEmailUpiPayment(driver);
		googlePaid = new LoginWithGooglePaidTicket(driver);
		mobileFree = new LoginWithMobileFree(driver);
		mobileUpiPay = new LoginWithMobileUpiPay(driver);

		mbamazonpay = new LoginWithMobileAmazonPay(driver);
	}

	@Test(priority = 1, dataProvider = "dataFetch", dataProviderClass = LoginWithMobileAmazonPayTest.class)

	public void validateMobileLogin_MbUpi(String[] row) throws Throwable {

		mobileUpiPay.verifyMobileLogin(row[0]);

	}

	@Test(priority = 2, dataProvider = "dataFetch", dataProviderClass = LoginWithMobileAmazonPayTest.class)

	public void validateDisableEmail_MbUpi(String row[]) throws Throwable {
		mobileFree.verifydisableEmail(row[2], row[3], row[4]);
	}

	@Test(priority = 3, dataProvider = "dataFetch", dataProviderClass = LoginWithMobileAmazonPayTest.class)

	public void validateOrderConfirmation_MbUpi(String[] row) {
		mobileFree.verifyAttendee_orderConfirmantion(row[1], row[5], row[6], row[7], row[8]);
	}

	@Test(priority = 4, dataProvider = "dataFetch", dataProviderClass = LoginWithMobileAmazonPayTest.class)
	public void validateCoupon(String[] row) {

		newEmail.verifyCoupon(row[9]);
		// check coupon applied and remove coupon

	}

	@Test(priority = 5)
	public void validateCouponRemove() {

		Upi.verifyCouponRemoveFunctionality();

	}

	@Test(priority = 6, dataProvider = "dataFetch", dataProviderClass = LoginWithMobileAmazonPayTest.class)
	public void validateWhatsappIsPresent(String[] row) {
		mbamazonpay.verifyWhatsAppCheckbox(row[2], row[3], row[4]);
		// after validating click on proceed
	}

	@Test(priority = 7)
	public void validatePaymentUrl() {

		Upi.verifyProceedPaymentPage();

		// verify payment url
	}

	@Test(priority = 8)
	public void validatePhonepe() throws Throwable {

		Upi.verifyPhonepe();
		// phone pe is displayed verify when clicks on honepe
		// clcik on back again

	}

	@Test(priority = 9)
	public void validateAmazonPe() {

		Upi.verifyAmazon();
		// phone pe is displayed verify when clicks on phonepe
	}

	@Test(priority = 10, dataProvider = "dataFetch", dataProviderClass = LoginWithMobileAmazonPayTest.class)
	public void validateAmazonOrderMessage(String[] row) {

		mbamazonpay.verifyAmazonPay(row[13]);

		// eneter transaction id and verify message
	}

//	@Test(priority = 11)
//	public void validateRegisterBtn() {
//
//		Upi.verifyCompleteRegisterbutton(); // should not be displayed in both upi and cash cases
//
//	}

	@Test(priority = 12)
	public void validateOrderIdVip_amazonpay() {

		mbamazonpay.verifyOderVip_amazon();
		// vip order id verify
	}
	
	
	@Test(priority = 13, dataProvider = "dataFetch", dataProviderClass = LoginWithMobileFreeTest.class)
	public void validateAttendeeDetails_MobileloginViewTicketsFreeAmazonP(String rowindex[]) {
		mobileFree.verifyAttendeeDetailsInViewTickets(rowindex[1], rowindex[5], rowindex[6]);
	}

//	@Test(priority = 10, dataProvider = "dataFetch", dataProviderClass = LoginWithMobileFreeTest.class)
//	public void validateAttendeeDetails_MobileLoginCompleteRegAmazonP(String rowindex[]) throws Throwable {
//		mobileFree.verifyAttendeeDetailsInRegistration(rowindex[1], rowindex[5], rowindex[6]);
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

}
