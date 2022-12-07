package com.qa.hooks;

import java.util.Properties;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import com.qa.factory.DriverManager;
import com.qa.utils.ReadConfig;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

public class Hooks {
	private DriverManager driverManager;
	private WebDriver driver;
	private ReadConfig readConfig;
	Properties property;

	@Before(order = 0)
	public void getProperty() {
		readConfig = new ReadConfig();
		property = readConfig.init_prop();
	}

	@Before(order = 1)
	public void launchBrowser() {
		String browserName = property.getProperty("Browser");
		driverManager = new DriverManager();
		driver = driverManager.initialize_driver(browserName);

	}

	@After(order = 0)
	public void quitBrowser() {
		driver.quit();
	}

	@After(order = 1)
	public void tearDown(Scenario scenario) {
		if (scenario.isFailed()) {
			// take screenshot:
			String screenshotName = scenario.getName().replaceAll(" ", "_");
			byte[] sourcePath = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
			scenario.attach(sourcePath, "image/png", screenshotName);

		}
	}

}
