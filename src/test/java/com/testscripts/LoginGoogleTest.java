package com.testscripts;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.base.TestBase;
import com.pages.LoginWithGoogle;
import com.utils.Utils;

public class LoginGoogleTest extends TestBase {

	LoginWithGoogle loginWithGoogle;

	String sheetname = "log";

	@DataProvider
	public String[][] dataFetch() throws Throwable {
		return Utils.FetchData(sheetname);
	}

	@BeforeMethod
	public void setup() {

		Initialization();
		loginWithGoogle = new LoginWithGoogle(driver);

	}

	@Test(dataProvider = "dataFetch", dataProviderClass = LoginGoogleTest.class)
	public void VerifyLoginGoogle(String[] rowindex) throws Throwable {
		loginWithGoogle.VerifyGoogleLoginAttendee(rowindex[0], rowindex[1], rowindex[2], rowindex[3], rowindex[4],
				rowindex[5]);
		boolean ordermsg = driver.findElement(By.xpath("//h3[text()='Your order is completed']")).isDisplayed();
		Assert.assertTrue(ordermsg);

	}

}
