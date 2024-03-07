package com.testscripts;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.base.TestBase;
import com.pages.LoginWithExistingEmailUpiPayment;
import com.pages.LoginWithGoogle;
import com.pages.LoginWithGooglePaidTicket;
import com.pages.LoginWithNewEmail;
import com.utils.Utils;

public class LoginGoogleUPIPaidTicketTest extends TestBase {

	String sheetname2 = "ExistingEmaillogin";
	LoginWithNewEmail newEmail;
	LoginWithGoogle login;
	LoginWithExistingEmailUpiPayment Upi;
	LoginWithGooglePaidTicket googlePaid;

	@BeforeClass
	public void setup() { // prerequisites for testmethods are placed here

		Initialization();
		newEmail = new LoginWithNewEmail(driver);
		login = new LoginWithGoogle(driver);
		Upi = new LoginWithExistingEmailUpiPayment(driver);
		googlePaid = new LoginWithGooglePaidTicket(driver);
	}

	@Test(priority = 1, dataProvider = "dataFetch", dataProviderClass = LoginGoogleTests.class)
	public void validateDisableEmail_google_Upi(String[] rowindex) throws Throwable {

		googlePaid.VerifyGooglesignIn(rowindex[1], rowindex[2]);

	}

	@Test(priority = 2, dataProvider = "dataFetch", dataProviderClass = LoginGoogleTests.class)
	public void ValidateWhatsappConfigEnable_UPi(String[] rowindex) throws Throwable {

		login.verifyWhatsappCongig(rowindex[7], rowindex[8], rowindex[9], rowindex[1]);

	}

	@Test(priority = 3, dataProvider = "dataFetch", dataProviderClass = LoginGoogleTests.class)
	public void validateTicketForAttendee_google_upi(String[] rowindex) throws Throwable {

		login.verifyAttendee_orderConfirmantion(rowindex[3], rowindex[4], rowindex[5], rowindex[6], rowindex[15]);

	}

	@Test(priority = 4)
	public void validatePaymentUrl() {

		Upi.verifyProceedPaymentPage();

		// verify payment url
	}

	@Test(priority = 5)
	public void validatePhonepe() throws Throwable {

		Upi.verifyPhonepe();
		// phone pe is displayed verify when clicks on honepe
		// clcik on back again

	}

	@Test(priority = 6)
	public void validateAmazonPe() {

		Upi.verifyAmazon();
		// phone pe is displayed verify when clicks on honepe
	}

	@Test(priority = 7, dataProvider = "emailtestdata", dataProviderClass = LoginWithExistingEmailUpiPayTest.class)
	public void validatephoenpeOrderMessage(String[] row) {

		Upi.verifyPhonepeOrder(row[11]);

		// eneter transaction id and verify message
	}

	@Test(priority = 8)
	public void validateRegisterBtn() {

		Upi.verifyCompleteRegisterbutton(); // should not be displayed in both upi and cash cases

	}

	@Test(priority = 9)
	public void validateOrderIdVip() {

		googlePaid.verifyOderVip_upi();
		// vip order id verify
	}
	
	@Test(priority = 10, dataProvider = "dataFetch", dataProviderClass = LoginGoogleTests.class)

	public void validateAttendeeDetails_GoogleAmazon(String[] rowindex) {

		login.verifyAttendeeDetailsInViewTickets(rowindex[15], rowindex[3], rowindex[4]);
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
