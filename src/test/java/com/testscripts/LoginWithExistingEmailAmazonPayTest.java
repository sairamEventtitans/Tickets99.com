package com.testscripts;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.base.TestBase;
import com.pages.LoginWithExistingEmailAmazonPay;
import com.pages.LoginWithExistingEmailUpiPayment;
import com.pages.LoginWithGoogle;
import com.pages.LoginWithNewEmail;
import com.utils.Utils;

public class LoginWithExistingEmailAmazonPayTest extends TestBase {

	String sheetname2 = "ExistingEmaillogin";
	LoginWithNewEmail newEmail;
	LoginWithGoogle login;
	LoginWithExistingEmailUpiPayment Upi;
	LoginWithExistingEmailAmazonPay amazonPay;

	@DataProvider
	public String[][] emailtestdata() throws Throwable {
		return Utils.FetchData(sheetname2);
	}

	@BeforeClass
	public void setup() { // prerequisites for testmethods are placed here

		Initialization();
		newEmail = new LoginWithNewEmail(driver);
		login = new LoginWithGoogle(driver);
		Upi = new LoginWithExistingEmailUpiPayment(driver);
		amazonPay = new LoginWithExistingEmailAmazonPay(driver);

	}

	@Test(priority = 1, dataProvider = "emailtestdata", dataProviderClass = LoginWithExistingEmailUpiPayTest.class)
	public void validateLogin(String[] row) throws Throwable {
		Upi.verifyLoginWithExistingEmail(row[0], row[1], row[2]); // login as exsisting user
	}

	@Test(priority = 2, dataProvider = "emailtestdata", dataProviderClass = LoginWithExistingEmailUpiPayTest.class)
	public void validateCoupon(String[] row) {

		newEmail.verifyCoupon(row[3]);
		// check coupon applied and remove coupon

	}

	@Test(priority = 3)
	public void validateEmailDisable() {

		newEmail.verifyEmailDisable();

		// email should be disabled by default

	}

	@Test(priority = 4)
	public void validateCouponRemove() {

		Upi.verifyCouponRemoveFunctionality();

	}

	@Test(priority = 5, dataProvider = "emailtestdata", dataProviderClass = LoginWithExistingEmailUpiPayTest.class)
	public void validateBuyForSomeone(String[] row) {

		login.verifyAttendee_orderConfirmantion(row[4], row[5], row[6], row[7],row[17]);
	}

	@Test(priority = 6, dataProvider = "emailtestdata", dataProviderClass = LoginWithExistingEmailUpiPayTest.class)
	public void validateWhatsappIsPresent(String[] row) {
		newEmail.verifyWhatsAppCheckbox(row[8], row[9], row[10]);
		// after validating clcick on proceed
	}

	@Test(priority = 7)
	public void validatePaymentUrl() {

		Upi.verifyProceedPaymentPage();

		// verify payment url
	}

//
	@Test(priority = 8)
	public void validatePhonepe() throws Throwable {

		Upi.verifyPhonepe();
		// phone pe is displayed verify when clicks on honepe
		// clcik on back again

	}

	@Test(priority = 9)
	public void validateAmazonPe() {

		Upi.verifyAmazon();
		// phone pe is displayed verify when clicks on honepe
	}

	@Test(priority = 10, dataProvider = "emailtestdata", dataProviderClass = LoginWithExistingEmailUpiPayTest.class)
	public void validateAmazonOrderMessage(String[] row) {

		amazonPay.verifyAmazonPay(row[12]);

		// eneter transaction id and verify message
	}

	@Test(priority = 11)
	public void validateRegisterBtn() {

		Upi.verifyCompleteRegisterbutton(); // should not be displayed in both upi and cash cases

	}

	@Test(priority = 12)
	public void validateOrderIdVip_amazonpay() {

		amazonPay.verifyOderVip_amazon();
		// vip order id verify
	}

	@Test(priority = 13)
	public void validateOrderIdFree_amazonpay() {

		amazonPay.verifyOrderIdFree_amazon();
		// free order id verify
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
