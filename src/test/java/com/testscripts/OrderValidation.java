package com.testscripts;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.appPages.ApplicationLogin;
import com.base.TestBaseOrganizer;
import com.utils.Utils;

public class OrderValidation extends TestBaseOrganizer {

	ApplicationLogin appLogin;
	public static String sheetname = "ApplicationLogin";
	public static String sheetname2 = "OrderRazorPay";

	@DataProvider
	public static String[][] dataFetch() throws Throwable {
		return Utils.FetchData(sheetname);
	}

	@DataProvider
	public static String[][] dataFetch2() throws Throwable {
		return Utils.FetchData(sheetname2);
	}

	@BeforeClass

	public void setup() {
		Initialization();
		appLogin = new ApplicationLogin(driver);
	}

	@Test(priority = 1, dataProvider = "dataFetch", dataProviderClass = OrderValidation.class)

	public void validateAppLogin(String[] rowindex) throws Throwable {

		appLogin.verifyApplicationLogin(rowindex[0], rowindex[1]);
		String ExpectedUrl = "https://admin.tickets99.com/companyarea/company/index";
		String Actualurl = driver.getCurrentUrl();
		System.out.println(Actualurl);
		Assert.assertEquals(Actualurl, ExpectedUrl);

	}

	@Test(priority = 2)
	public void validateCheckIn() {
		appLogin.verifyCheckin();
	}

//	@Test(priority = 3, dataProvider = "dataFetch2", dataProviderClass = OrganizerTest.class)
//	public void validateSearch(String row[]) throws Throwable {
//		
//		appLogin.searchFunctionality(row[4]);
//	}

	@Test(priority = 4, dataProvider = "dataFetch2", dataProviderClass = OrderValidation.class)
	public void validateCartTest(String row[]) throws Throwable {
		appLogin.cartValue(row[10]);

	}
	
	@Test()
	public void validateDiscount() {
		
	}
	
	
	@Test()
	public void validatePlatformFee() {
		
	}
	
	@Test()
	public void validatePayment_Collected() {
		
	}
	
	
	@Test()
	public void validateAmount_owe() {
		
	}
	

	

}
