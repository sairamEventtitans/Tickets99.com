package com.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.base.TestBase;
import com.utils.Utils;

public class LoginWithExistingEmailFree extends TestBase {

	@FindBy(xpath = "//div[@onclick='redirecttoticket(this)']") // Address of the Webelements
	WebElement buyButton;

	@FindBy(xpath = "(//span[text()='+'])[1]")
	WebElement AddticketFree;

	@FindBy(xpath = "//div[@onclick='proceedwithtickets()']")
	WebElement proceedticketbtn;

	@FindBy(xpath = "//span[@class='d-sm-inline text-decoration-underline text-primary cursor-pointer']")
	WebElement loginLink;

	@FindBy(id = "txtusername")
	WebElement Username;

	@FindBy(id = "txtpassword")
	WebElement Password;

	@FindBy(xpath = "(//button[@class='btn py-2 btn-block btn-danger bg-red-col w-100'])[1]")
	WebElement loginButton;


	@FindBy(xpath = "//h5[text()='WhatsApp Configuration']")
	WebElement whatsappConfig;

	public LoginWithExistingEmailFree(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	public void verifyLoginWithExistingEmail(String Email, String password, String Url) throws Throwable {

		buyButton.click();
		AddticketFree.click();

		proceedticketbtn.click();

		Utils.javaScriptClick(loginLink);

		// loginLink.click();
		Username.sendKeys(Email);
		Password.sendKeys(password);
		loginButton.click();

		String VerificationUrl = Url;
		String priorPaymentPageUrl = driver.getCurrentUrl();
		Assert.assertEquals(VerificationUrl, priorPaymentPageUrl);

	}

	public void verifyWhatsAppCheckbox() {

		boolean whatsAppCheckbox = whatsappConfig.isDisplayed();

		Assert.assertTrue(whatsAppCheckbox);

	}

}
