package applicationtests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.appPages.VerifyPayerWillPay_razor;
import com.appPages.verifyIWillPay_razor;
import com.base.TestBaseOrganizer;
import com.utils.Utils;

public class PayerWillPayRazorTest extends TestBaseOrganizer {

	verifyIWillPay_razor appLogin;
	VerifyPayerWillPay_razor payer;
	public static String sheetname = "ApplicationLogin";
	public static String sheetname2 = "OrderRazorPayValidation(payer)";

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
		payer = new VerifyPayerWillPay_razor(driver);
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

	@Test(priority = 3)
	public void validateSearch() throws Throwable {

		payer.searchFunctionality();
	}

	@Test(priority = 4, dataProvider = "dataFetch2", dataProviderClass = PayerWillPayRazorTest.class)
	public void validateCartTest(String row[]) throws Throwable {

		payer.cartValue(row[10]);

	}

	@Test(priority = 5, dataProvider = "dataFetch2", dataProviderClass = PayerWillPayRazorTest.class)
	public void validateDiscount(String row[]) {

		payer.verifyDiscount(row[8]);

	}

	@Test(priority = 6, dataProvider = "dataFetch2", dataProviderClass = PayerWillPayRazorTest.class)
	public void validatePlatformFee(String[] row) {

		payer.verifyPlatformFee(row[11]);

	}

	@Test(priority = 7, dataProvider = "dataFetch2", dataProviderClass = PayerWillPayRazorTest.class)
	public void validatePayment_Collected(String[] row) {

		payer.verifyPaymentCollected(row[15]);

	}

	@Test(priority = 8, dataProvider = "dataFetch2", dataProviderClass = PayerWillPayRazorTest.class)
	public void validateRazorpay_charges(String[] row) {

		payer.verifyRazorPay_charges(row[14]);

	}

	@Test(priority = 9, dataProvider = "dataFetch2", dataProviderClass = PayerWillPayRazorTest.class)
	public void validateAmount_owe(String row[]) {

		payer.verifyamountOwe(row[16]);

	}

}
