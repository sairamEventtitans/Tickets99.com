package com.testscripts;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.base.TestBase;
import com.pages.LoginWithExistingEmailFree;
import com.pages.LoginWithExistingEmailUpiPayment;
import com.pages.LoginWithGoogle;
import com.pages.LoginWithGooglePaidTicket;
import com.pages.LoginWithMobileFree;
import com.pages.LoginWithNewEmail;
import com.utils.Utils;

public class LoginWithExistingEmailFreeTest extends TestBase {
	String sheetname3 = "ExistingEmaillogin";
	LoginWithNewEmail newEmail;
	LoginWithGoogle login;
	LoginWithExistingEmailUpiPayment Upi;
	LoginWithGooglePaidTicket googlePaid;
	LoginWithMobileFree mobileFree;
	LoginWithExistingEmailFree ExistingEmail_free;

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
		ExistingEmail_free = new LoginWithExistingEmailFree(driver);
	}

	@Test(priority = 1, dataProvider = "dataFetch", dataProviderClass = LoginWithExistingEmailFreeTest.class)

	public void validateMobileWithExistingEmail_free(String[] row) throws Throwable {

		ExistingEmail_free.verifyLoginWithExistingEmail(row[0], row[1], row[2]);
	}

	@Test(priority = 2, dataProvider = "dataFetch", dataProviderClass = LoginWithExistingEmailFreeTest.class)

	public void validateDisableEmail__freeExistingEmail(String row[]) {
		// mobileFree.verifydisableEmail(row[2], row[3], row[4]);
		newEmail.verifyEmailDisable();
	}

	@Test(priority = 3, dataProvider = "dataFetch", dataProviderClass = LoginWithExistingEmailFreeTest.class)

	public void validateOrderConfirmation__freeExistingEmail(String[] row) {
		login.verifyAttendee_orderConfirmantion(row[4], row[5], row[6], row[7], row[17]);
	}

	@Test(priority = 4)

	public void validateWhatsappIsPresent_freeExistingEmail() {

		ExistingEmail_free.verifyWhatsAppCheckbox();
	}

	@Test(priority = 5)

	public void validateOrderSuccessPage_freeExistingEmail() {
		mobileFree.verifyOrderSuccessMsg();
	}

	@Test(priority = 6)

	public void validateOrderID_freeExistingEmail() {
		mobileFree.verifyOrderId();
	}
	
//	@Test(priority = 7,dataProvider = "dataFetch", dataProviderClass = LoginWithMobileFreeTest.class)
//	public void verifyBuyerdetails_Mbfree(String row[]) {
//		mobileFree.verifyBuyerdetails(row[2], row[3]);
//	}
	
	@Test(priority = 8, dataProvider = "dataFetch", dataProviderClass = LoginWithExistingEmailFreeTest.class)

	public void validateAttendeeDetails_ExistingEmail(String[] rowindex) {

		ExistingEmail_free.verifyAttendeeDetailsInViewTickets(rowindex[17], rowindex[4], rowindex[5]);
	}
	
	@Test(priority = 10, dataProvider = "dataFetch", dataProviderClass = LoginWithExistingEmailFreeTest.class)
	public void validateAttendeeDetails_googleCompleteReg(String rowindex[]) throws Throwable {
		ExistingEmail_free.verifyAttendeeDetailsInRegistration(rowindex[17], rowindex[4], rowindex[5]);
	}
	
	
	
}
