package com.testscripts;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.base.TestBase;
import com.pages.LoginWithExistingEmailAmazonPay;
import com.pages.LoginWithExistingEmailUpiPayment;
import com.pages.LoginWithGoogle;
import com.pages.LoginWithNewEmail;
import com.utils.Utils;

public class LoginWithNewMailUpiPayTest extends TestBase {

	String sheetname5 = "emaildata";
	LoginWithNewEmail newEmail;
	LoginWithGoogle login;
	LoginWithExistingEmailUpiPayment Upi;
	LoginWithExistingEmailAmazonPay amazonPay;

	@DataProvider
	public String[][] emailtestdata() throws Throwable {
		return Utils.FetchData(sheetname5);
	}

	@BeforeClass
	public void setup() { // prerequisites for testmethods are placed here

		Initialization();
		newEmail = new LoginWithNewEmail(driver);
		login = new LoginWithGoogle(driver);
		Upi = new LoginWithExistingEmailUpiPayment(driver);
		amazonPay = new LoginWithExistingEmailAmazonPay(driver);

	}

	@Test(priority = 1, dataProvider = "emailtestdata", dataProviderClass = LoginWithNewMailUpiPayTest.class)
	public void ValidateSigninWithNewEmail_upiPay(String[] row) throws Throwable {

		newEmail.verifyNewEmailLogin(row[0]);

	}

	@Test(priority = 2, dataProvider = "emailtestdata", dataProviderClass = LoginWithNewMailUpiPayTest.class)

	public void ValidateCoupon_Email(String[] row) {

		newEmail.verifyCoupon(row[1]);

	}

	@Test(priority = 3)
	public void validateCouponRemove() {

		Upi.verifyCouponRemoveFunctionality();

	}

	@Test(priority = 4)
	public void ValidateEmailDisable_Email() {
		newEmail.verifyEmailDisable();
	}

	@Test(priority = 5, dataProvider = "emailtestdata", dataProviderClass = LoginWithNewMailUpiPayTest.class)
	public void validateWhatsappCheckbox(String[] row) {
		newEmail.verifyWhatsAppCheckbox(row[3], row[4], row[5]);

	}

	@Test(priority = 6, dataProvider = "emailtestdata", dataProviderClass = LoginWithNewMailUpiPayTest.class)
	public void validateAttendeeEmail(String[] row) {

		login.verifyAttendee_orderConfirmantion(row[6], row[7], row[8], row[9], row[2]);

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
		// phone pe is displayed verify when clicks on honepe
	}

	@Test(priority = 10, dataProvider = "emailtestdata", dataProviderClass = LoginWithNewMailUpiPayTest.class)
	public void validatephoenpeOrderMessage(String[] row) {

		Upi.verifyPhonepeOrder(row[20]);

		// eneter transaction id and verify message
	}

	@Test(priority = 11)
	public void validateRegisterBtn() {

		Upi.verifyCompleteRegisterbutton(); // should not be displayed in both upi and cash cases

	}

	@Test(priority = 12)
	public void validateOrderIdVip() {

		Upi.verifyOderVip_upi();
		// vip order id verify
	}

	@Test(priority = 13)
	public void validateOrderIdFree() {

		Upi.verifyOrderIdFree_upi();
		// free order id verify
	}

	@Test(priority = 14, dataProvider = "emailtestdata", dataProviderClass = LoginWithNewEmailTest.class)

	public void validateAttendeeDetailsViewticket_NewEmail(String[] rowindex) {

		newEmail.verifyAttendeeDetailsInViewTickets(rowindex[2], rowindex[6], rowindex[7]);
	}

}
