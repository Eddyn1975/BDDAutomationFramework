package com.qa.pages;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import com.qa.utils.Utilities;

public class LoginPage {
	private WebDriver driver;

	public LoginPage(WebDriver driver) {
		this.driver = driver;
	}

	@FindBy(how = How.XPATH, using = "//input[@id='username']")
	private @CacheLookup WebElement USERNAME_FIELD;
	@FindBy(how = How.XPATH, using = "//input[@id='password']")
	private @CacheLookup WebElement PASSWORD_FIELD;
	@FindBy(how = How.XPATH, using = "//button[contains(text(),'Sign in')]")
	private @CacheLookup WebElement SIGN_IN_BUTTON;
	@FindBy(how = How.XPATH, using = "//div[@class='alert alert-danger fade in']")
	private WebElement ERROR_MESSAGE_FIELD;
	@FindBy(how = How.XPATH, using = "//a[normalize-space()='Forgot password ?']")
	private @CacheLookup WebElement FORGOT_PASSWORD;
	@FindBy(how = How.XPATH, using = "//input[@id='username']")
	private @CacheLookup WebElement EMAIL_ADDRESS_FIELD;
	@FindBy(how = How.XPATH, using = "//button[normalize-space()='Reset Password']")
	private @CacheLookup WebElement RESET_PASSWORD_BUTTON;
	@FindBy(how = How.XPATH, using = "//a[normalize-space()='Back To Login']")
	private @CacheLookup WebElement BACK_TO_LOGIN_BUTTON;

// Methods to interact with the elements

	public LoginPage enterUserName(String userName) throws InterruptedException {
		Utilities.waitForElement(USERNAME_FIELD, driver, 30);
		USERNAME_FIELD.sendKeys(userName);
		return this;
	}

	public LoginPage enterPassword(String password) throws InterruptedException {
		Utilities.waitForElement(PASSWORD_FIELD, driver, 30);
		PASSWORD_FIELD.sendKeys(password);
		return this;
	}

	public LoginPage clickSignInButton() throws InterruptedException {
		Utilities.waitForElementToBeClickable(SIGN_IN_BUTTON, driver, 30);
		SIGN_IN_BUTTON.click();
		return this;
	}

	public LoginPage verifyErrorMessageForInvalidLogin() throws InterruptedException {
		Utilities.waitForElement(ERROR_MESSAGE_FIELD, driver, 30);
		String  actualErrorTextMessage= ERROR_MESSAGE_FIELD.getText();
		String expectedErrorTextMessage = "Invalid Username or Password";
		Assert.assertTrue(actualErrorTextMessage .contains(expectedErrorTextMessage));
		return this;
	}

	public LoginPage validateURL() {
		String CurrentURL = "https://techfios.com/billing/?ng=login/";
		String expectedURL = driver.getCurrentUrl();
		Assert.assertEquals(CurrentURL, expectedURL, "Wrong URL!!!");
		return this;

	}

}
