package com.qa.pages;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import com.qa.utils.Utilities;

public class DashboardPage {

	private WebDriver driver;

	public DashboardPage(WebDriver driver) {
		this.driver = driver;
	}
	// Element Library

	@FindBy(how = How.XPATH, using = "//h2[normalize-space()='Dashboard']")
	private WebElement DASHBOARD_ELEMENT_FIELD;

	// Methods to interact with the elements
	public DashboardPage validateURL() {
		String expectedURL = "https://techfios.com/billing/?ng=dashboard/";
		String CurrentURL = driver.getCurrentUrl();
		Assert.assertEquals(CurrentURL, expectedURL, "Wrong URL!!!");
		return this;

	}

	public DashboardPage verifyDashBoardElement() throws InterruptedException {
		Utilities.threadsleep(2000);
		String actualPageTextMessage = DASHBOARD_ELEMENT_FIELD.getText();
		String expectedPageTextMessage = "Dashboard";
		Utilities.threadsleep(2000);
		Assert.assertTrue(actualPageTextMessage .contains(expectedPageTextMessage));

		return this;
	}

	public DashboardPage verifyPageTitle() throws InterruptedException {
		String exp_title = "Dashboard- iBilling";
	    String act_title = driver.getTitle();
	    System.out.println(act_title);
		Assert.assertTrue(act_title.contains(exp_title));
		return this;

	}
}
