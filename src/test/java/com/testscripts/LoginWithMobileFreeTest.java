package com.testscripts;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.base.TestBase;
import com.pages.LoginWithExistingEmailUpiPayment;
import com.pages.LoginWithGoogle;
import com.pages.LoginWithGooglePaidTicket;
import com.pages.LoginWithMobileFree;
import com.pages.LoginWithNewEmail;
import com.utils.Utils;

public class LoginWithMobileFreeTest extends TestBase {

	String sheetname3 = "MobileNumberFree";
	LoginWithNewEmail newEmail;
	LoginWithGoogle login;
	LoginWithExistingEmailUpiPayment Upi;
	LoginWithGooglePaidTicket googlePaid;
	LoginWithMobileFree mobileFree;

	@DataProvider
	public String[][] dataFetch() throws Throwable {
		return Utils.FetchData(sheetname3);
	}

	@BeforeClass
	public void setup() { // prerequisites for testmethods are placed here

		Initialization();
		newEmail = new LoginWithNewEmail(driver);
		login = new LoginWithGoogle(driver);
		Upi = new LoginWithExistingEmailUpiPayment(driver);
		googlePaid = new LoginWithGooglePaidTicket(driver);
		mobileFree = new LoginWithMobileFree(driver);
	}

	@Test(priority = 1, dataProvider = "dataFetch", dataProviderClass = LoginWithMobileFreeTest.class)

	public void validateMobileLogin_MbFree(String[] row) throws Throwable {

		mobileFree.verifyMobileLogin(row[0]);

	}

	@Test(priority = 2, dataProvider = "dataFetch", dataProviderClass = LoginWithMobileFreeTest.class)

	public void validateDisableEmail_Mbfree(String row[]) throws Throwable {
		mobileFree.verifydisableEmail(row[2], row[3], row[4]);
	}

	@Test(priority = 3, dataProvider = "dataFetch", dataProviderClass = LoginWithMobileFreeTest.class)

	public void validateOrderConfirmation_Mbfree(String[] row) {
		mobileFree.verifyAttendee_orderConfirmantion(row[1], row[5], row[6], row[7], row[8]);
	}

	@Test(priority = 4)
	public void verifyOrderMessage_Mbfree() {

		mobileFree.verifyOrderSuccessMsg();
	}

	@Test(priority = 5)
	public void verifyOrderId_Mbfree() {

		mobileFree.verifyOrderId();
	}

	@Test(priority = 6, dataProvider = "dataFetch", dataProviderClass = LoginWithMobileFreeTest.class)
	public void verifyBuyerdetails_Mbfree(String row[]) {
		mobileFree.verifyBuyerdetails(row[2], row[3]);
	}

	@Test(priority = 7, dataProvider = "dataFetch", dataProviderClass = LoginWithMobileFreeTest.class)

	public void verifyRegistration_Mbfree(String[] row) {

		mobileFree.verifyCompleteRegistration(row[10], row[11], row[12], row[13], row[14], row[15]);
	}

}
