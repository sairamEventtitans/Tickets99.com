package applicationtests;

import org.apache.poi.ss.usermodel.Row;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.appPages.verifyIWillPay_razor;
import com.base.TestBaseOrganizer;
import com.utils.Utils;
import com.utils.UtilsApp;

public class OrderValidation extends TestBaseOrganizer {

	verifyIWillPay_razor appLogin;
	public static String sheetname = "ApplicationLogin";
	public static String sheetname2 = "OrderRazorPayValidation";

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
		appLogin = new verifyIWillPay_razor(driver);
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
	public void validateCheckIn() throws Throwable {
		appLogin.verifyCheckin();
	}

	@Test(priority = 3, dataProvider = "dataFetch2", dataProviderClass = OrderValidation.class)
	public void validateSearch(String row[]) throws Throwable {

		appLogin.searchFunctionality(row[4]);
	}

	@Test(priority = 4, dataProvider = "dataFetch2", dataProviderClass = OrderValidation.class)
	public void validateCartTest(String row[]) throws Throwable {

		appLogin.cartValue(row[10]);

	}

	@Test(priority = 5, dataProvider = "dataFetch2", dataProviderClass = OrderValidation.class)
	public void validateDiscount(String row[]) {

		appLogin.verifyDiscount(row[8]);

	}

	@Test(priority = 6, dataProvider = "dataFetch2", dataProviderClass = OrderValidation.class)
	public void validatePlatformFee(String[] row) {

		appLogin.verifyPlatformFee(row[11]);

	}

	@Test(priority = 7, dataProvider = "dataFetch2", dataProviderClass = OrderValidation.class)
	public void validatePayment_Collected(String[] row) {

		appLogin.verifyPaymentCollected(row[15]);

	}

	@Test(priority = 8, dataProvider = "dataFetch2", dataProviderClass = OrderValidation.class)
	public void validateRazorpay_charges(String[] row) {

		appLogin.verifyRazorPay_charges(row[14]);

	}   

	@Test(priority = 9, dataProvider = "dataFetch2", dataProviderClass = OrderValidation.class)
	public void validateAmount_owe(String row[]) {

		appLogin.verifyamountOwe(row[16]);

	}

	@AfterMethod

	public void teardown(ITestResult result) throws Throwable {

		if (result.getStatus() == ITestResult.FAILURE) {
			capture(result.getMethod().getMethodName());
		}

	}

	public void capture(String methodname) throws Throwable {
		UtilsApp.CapturescreenShot(methodname);
	}

}
