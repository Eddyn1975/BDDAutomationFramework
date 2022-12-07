package com.qa.stepdefinitions;

import org.openqa.selenium.support.PageFactory;

import com.qa.factory.DriverManager;
import com.qa.pages.DashboardPage;
import com.qa.pages.LoginPage;
import com.qa.pages.SideNavigationPage;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class LoginTestSteps {
	LoginPage loginPage = PageFactory.initElements(DriverManager.getDriver(), LoginPage.class);
	SideNavigationPage sideNavigation = PageFactory.initElements(DriverManager.getDriver(), SideNavigationPage.class);
	DashboardPage dashboard = PageFactory.initElements(DriverManager.getDriver(), DashboardPage.class);

	@Given("user logs with valid username {string} and password {string}")
	public void user_logs_with_valid_username_and_password(String userName, String password) {
		try {
			loginPage.enterUserName(userName);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		try {
			loginPage.enterPassword(password);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@When("user clicks on Signin button")
	public void user_clicks_on_signin_button() {
		try {
			loginPage.clickSignInButton();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

	@Then("The Dashboard page should be displayed")
	public void the_dashboard_page_should_be_displayed() {
		try {
			dashboard.verifyDashBoardElement();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@Given("user logs with invalid {string} or {string}")
	public void user_logs_with_invalid_or(String username, String password) {
		try {
			loginPage.enterUserName(username);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		try {
			loginPage.enterPassword(password);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@Then("the Dashboard should not be displayed")
	public void the_dashboard_should_not_be_displayed() {
		try {
			loginPage.verifyErrorMessageForInvalidLogin();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
