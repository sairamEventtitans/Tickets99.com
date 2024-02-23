package com.testscripts;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.base.TestBase;
import com.pages.LoginWithGoogle;
import com.pages.LoginWithNewEmail;
import com.utils.Utils;

public class LoginWithNewEmailTest extends TestBase {

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

//	@BeforeMethod
//	public void testsetup() {                               //For quick checking 
//
//		Initialization();
//		newEmail = new LoginWithNewEmail(driver);
//		driver.get("https://admin.tickets99.com/event/orderdetails/acahGJEo0LARB%20brV55pyQ==/vision-2024");
//
//	}
//	

	@Test(priority = 1, dataProvider = "emailtestdata", dataProviderClass = LoginWithNewEmailTest.class)
	public void ValidateSigninWithNewEmail(String[] row) throws Throwable {

		newEmail.verifyNewEmailLogin(row[0]);

	}

	@Test(priority = 2, dataProvider = "emailtestdata", dataProviderClass = LoginWithNewEmailTest.class)

	public void ValidateCoupon(String[] row) {

		newEmail.verifyCoupon(row[1]);

	}

	@Test(priority = 3)
	public void ValidateEmailDisable() {
		newEmail.verifyEmailDisable();
	}

	@Test(priority = 4, dataProvider = "emailtestdata", dataProviderClass = LoginWithNewEmailTest.class)
	public void validateWhatsappCheckbox(String[] row) {
		newEmail.verifyWhatsAppCheckbox(row[3], row[4], row[5]);

	}

	@Test(priority = 5, dataProvider = "emailtestdata", dataProviderClass = LoginWithNewEmailTest.class)
	public void ValidateAttendee(String[] row) {

		login.verifyAttendee_orderConfirmantion(row[6], row[7], row[8], row[9]);

	}

	@Test(priority = 6)
	public void validateOrderifVip() {

		newEmail.verifyOderVip();

	}

//	@Test(priority = 6)
//	
//	public void ValidateVipTicketID() throws Throwable {
//		
//		login.verifyViewticketUrl();
//		newEmail.verifyTicketUrl();
//		
//		
//		
//	}
	
	@Test(priority = 7)
	public void ValidateOrderIdFree() {

		newEmail.verifyOrderIdFree();

	}
	
	@Test(priority = 8,dataProvider = "emailtestdata", dataProviderClass = LoginWithNewEmailTest.class)
	public void validateAttendeeRegistration_Vip(String[] row) {
		
		newEmail.VerifyAttendeeRegistration_Vip(row[10], row[11], row[12], row[13], row[14], row[15],row[16]);
		
	}
	
	
	

}
