package com.testscripts;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.base.TestBase;
import com.pages.LoginWithGoogle;
import com.pages.LoginWithNewEmail;
import com.utils.Utils;

public class LoginWithExistingEmailUpiPayTest extends TestBase {

	String sheetname2 = "emaildata";
	LoginWithNewEmail newEmail;
	LoginWithGoogle login;

	@DataProvider
	public String[][] emailtestdata() throws Throwable {
		return Utils.FetchData(sheetname2);
	}

	@BeforeClass
	public void setup() { // prerequisites for testmethods are placed here

		Initialization();
		newEmail = new LoginWithNewEmail(driver);
		login = new LoginWithGoogle(driver);

	}

	@Test(priority = 1, dataProvider = "emailtestdata", dataProviderClass = LoginWithExistingEmailUpiPayTest.class)
	public void validateLogin(String[] row) {
		// login as exsisting user
	}

	@Test(priority = 2, dataProvider = "emailtestdata", dataProviderClass = LoginWithExistingEmailUpiPayTest.class)
	public void validateCoupon(String[] row) {

		// check coupon applied and remove coupon

	}

	@Test(priority = 3)
	public void validateEmailDisable() {

		// email should be disabled by default

	}

	@Test(priority = 4)
	public void validateBuyForSomeone() {

	}

	@Test(priority = 5)
	public void validateWhatsappIsPresent() {
		// after validating clcick on proceed
	}

	@Test(priority = 6)
	public void validatePaymentUrl() {
								//verify payment url
	}
	
	
	@Test(priority = 7)
	public void validatePhonepe() {
		
		//phone pe is displayed verify when clicks on honepe
		//clcik on back again
		
	}
	@Test(priority = 8)
	public void validateAmazonPe() {
		
		//phone pe is displayed verify when clicks on honepe
	}
	@Test(priority = 9)
	public void validatephoenpeOrderMessage() {
		
		//eneter transaction id and verify message
	}
	
	@Test(priority = 10)
	public void validateOrderIdFree() {
		
		//free order id verify
	}
	
	@Test(priority = 11)
	public void validateOrderIdVip() {
		
		//vip order id verify
	}
	
	@Test(priority = 12)
	public void validateCompleteRegistrationBtn() {
		
		//should not be displayed in both upi and cash cases 
	}

}
