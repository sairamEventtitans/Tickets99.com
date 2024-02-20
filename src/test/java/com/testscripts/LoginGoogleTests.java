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

	String sheetname = "log";

	@DataProvider
	public String[][] dataFetch() throws Throwable {
		return Utils.FetchData(sheetname);
	}

	@BeforeClass
	public void setup() {

		Initialization();
		loginWithGoogle = new LoginWithGoogle(driver);

	}

	@Test(priority = 1, dataProvider = "dataFetch", dataProviderClass = LoginGoogleTests.class)
	public void ValidateGoogleSigninDisablemail(String[] rowindex) throws Throwable {

		loginWithGoogle.VerifyGooglesignIn(rowindex[1], rowindex[2]);

	}

	@Test(priority = 2)
	public void ValidateWhatsappConfig() {

		loginWithGoogle.verifyWhatsappCongig();

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

//	@Test(dataProvider = "dataFetch", dataProviderClass = LoginGoogleTest.class)
//	public void VerifyLoginGoogle(String[] rowindex) throws Throwable {
//		loginWithGoogle.VerifyGoogleLoginAttendee(rowindex[1], rowindex[2], rowindex[3], rowindex[4], rowindex[5],
//				rowindex[6]);
//		boolean ordermsg = driver.findElement(By.xpath("//h3[text()='Your order is completed']")).isDisplayed();
//		Assert.assertTrue(ordermsg);
//
//	}

}
