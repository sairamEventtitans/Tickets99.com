package com.testscripts;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.base.TestBase;
import com.pages.LoginWithGoogle;
import com.utils.Utils;

public class LoginGoogleTests extends TestBase {

	LoginWithGoogle loginWithGoogle;

	String sheetname = "logincred";

	public LoginGoogleTests() {

		super();
	}

	@DataProvider
	public String[][] dataFetch() throws Throwable {
		return Utils.FetchData(sheetname);
	}

	@BeforeClass
	public void setup() { // prerequisites for testmethods are placed here

		Initialization();
		loginWithGoogle = new LoginWithGoogle(driver);

	}

//	@BeforeMethod
//	public void testsetup() {                               //For quick checking 
//
//		Initialization();
//		loginWithGoogle = new LoginWithGoogle(driver);
//		driver.get("https://admin.tickets99.com/attendee/HvSTWX%20XvD0nnNCG1zQuqA==");
//
//	}

	@Test(priority = 1, dataProvider = "dataFetch", dataProviderClass = LoginGoogleTests.class)
	public void ValidateGoogleSigninDisablemail(String[] rowindex) throws Throwable {

		loginWithGoogle.VerifyGooglesignIn(rowindex[1], rowindex[2]);

	}

	@Test(priority = 2, dataProvider = "dataFetch", dataProviderClass = LoginGoogleTests.class)
	public void ValidateWhatsappConfig(String[] rowindex) throws Throwable {

		loginWithGoogle.verifyWhatsappCongig(rowindex[7], rowindex[8], rowindex[9]);

	}

	@Test(priority = 3, dataProvider = "dataFetch", dataProviderClass = LoginGoogleTests.class)
	public void ValidateAttendee_orderconfrm(String[] rowindex) throws Throwable {

		loginWithGoogle.verifyAttendee_orderConfirmantion(rowindex[3], rowindex[4], rowindex[5], rowindex[6]);

	}

	@Test(priority = 4)

	public void validateViewTicketUrl() throws Throwable {

		loginWithGoogle.verifyViewticketUrl();

	}

	@Test(priority = 5)
	public void ValidateRegUrl() {

		loginWithGoogle.VerifyRegistrationUrl();

	}

	@Test(priority = 6)
	public void ValidateOrderId() {
		loginWithGoogle.verifyOrderId();
	}

	@Test(priority = 7, dataProvider = "dataFetch", dataProviderClass = LoginGoogleTests.class)
	public void ValidateBuyerDetails(String[] rowindex) throws Throwable {

		loginWithGoogle.verifyBuyerdetails(rowindex[7], rowindex[8]);

	}

	@Test(priority = 8, dataProvider = "dataFetch", dataProviderClass = LoginGoogleTests.class)
	public void ValidatecompleteReg(String[] rowindex) throws Throwable {

		loginWithGoogle.verifyCompleteRegistration(rowindex[10], rowindex[11], rowindex[12], rowindex[13]);

	}

//	@Test(dataProvider = "dataFetch", dataProviderClass = LoginGoogleTest.class)
//	public void VerifyLoginGoogle(String[] rowindex) throws Throwable {
//		loginWithGoogle.VerifyGoogleLoginAttendee(rowindex[1], rowindex[2], rowindex[3], rowindex[4], rowindex[5],
//				rowindex[6]);
//		boolean ordermsg = driver.findElement(By.xpath("//h3[text()='Your order is completed']")).isDisplayed();
//		Assert.assertTrue(ordermsg);
//
//	}

}
