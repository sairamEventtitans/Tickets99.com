package com.appPages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.base.TestBaseOrganizer;

public class VerifyPayerWillPay_razor extends TestBaseOrganizer {

	@FindBy(xpath = "//div[@id='addnewdeptdiv']//text[contains(text(),'Advanced Search')]")
	WebElement search_Function;
	
	@FindBy(xpath = "//input[@name='OrderId']")
	WebElement search_value;
	
	@FindBy(xpath = "//*[@id=\"row5805\"]/td[9]")   
	WebElement cart_value;

	@FindBy(xpath = "//*[@id=\"row5805\"]/td[10]")
	WebElement discount_element;

	@FindBy(xpath = "//*[@id=\"row5813\"]/td[12]")
	WebElement platform_Fee;

	@FindBy(xpath = "//*[@id=\"row5805\"]/td[15]")
	WebElement paymentCollected_element;
	
	@FindBy (xpath = "//*[@id=\"row5813\"]/td[14]")
	WebElement RazorPay_element;
	
	@FindBy (xpath = "//*[@id=\"row5805\"]/td[18]")   ////*[@id="row5813"]/td[18]
	WebElement amountOwe_element;

	public VerifyPayerWillPay_razor(WebDriver driver) {

		PageFactory.initElements(driver, this);
	}
	
	public void searchFunctionality() throws Throwable {

		// search_Function.click();
		// Utils.javaScriptClick(search_Function);
		Thread.sleep(2000);

		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("document.body.style.zoom='25%'");

		Thread.sleep(8000);

		// Actions actions = new Actions(driver);
		// actions.keyDown(Keys.CONTROL).sendKeys("-").keyUp(Keys.CONTROL).perform();

		// JavascriptExecutor js=(JavascriptExecutor)driver;
		js.executeScript("arguments[0].click();", search_Function);

		Thread.sleep(8000);

		// search_value.click();
		// Thread.sleep(7000);

		// js.executeScript("elements[0].value='20240320060614136412';", search);
		// search_value.sendKeys(search);

		search_value.sendKeys("20240321092358136412");

		search_value.sendKeys(Keys.ENTER);

	}
	public void cartValue(String cartvalue) throws Throwable {

		JavascriptExecutor js = (JavascriptExecutor) driver;
	//	js.executeScript("document.body.style.zoom='33%'");

		String cartAmount = cartvalue;
		System.out.println("The cart value from excel " + cartAmount);
		String cart_value_table = cart_value.getText();

		System.out.println("the cart value from order table " + cart_value_table);

		String actualCart = cart_value_table.replace("₹", "").trim();

		// String cartFinal=actualCart.trim();

		System.out.println("the cart value from order table " + actualCart);

		Assert.assertEquals(cartAmount, actualCart);

	}

	//Else conditions and execute when only discount applied

	public void verifyDiscount(String discount) {

		String discount_offered = discount;
		System.out.println("discount offered "+ discount_offered);

		String discount_table = discount_element.getText().replace("₹", "").trim();

		Assert.assertEquals(discount_offered, discount_table);

	}

	public void verifyPlatformFee(String Pfee) {

		String Platform_Fee = Pfee;
		System.out.println("platform fee in excel "+Platform_Fee);

		String platForm_table = platform_Fee.getText().replace("₹", "").trim();

		Assert.assertEquals(Platform_Fee, platForm_table);

	}

	public void verifyPaymentCollected(String PayCollected) {

		String payment_Collected = PayCollected;
		System.out.println("payment collected in excel "+payment_Collected);

		String paymentCollected_table = paymentCollected_element.getText().replace("₹", "").trim();
		Assert.assertEquals(payment_Collected, paymentCollected_table);

	}
	
	public void verifyRazorPay_charges(String razor_pay) {
		
		String razorPay_fee=razor_pay;
		System.out.println("razor pay charges as perexcel "+razorPay_fee);
		String razorPay_table=RazorPay_element.getText().replace("₹","").trim();
		Assert.assertEquals(razorPay_fee, razorPay_table);
		
	}
	
	public void verifyamountOwe(String amountOwe) {
	
		String amount_owe=amountOwe;
		System.out.println("amount owe asper excel "+amount_owe);
		String amountOwe_table=amountOwe_element.getText();
		Assert.assertEquals(amountOwe_table, amount_owe);
		
	}
	

}
