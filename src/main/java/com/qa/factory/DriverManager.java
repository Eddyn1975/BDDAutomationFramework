package com.qa.factory;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.qa.utils.ReadConfig;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DriverManager {
	public WebDriver driver;
	ReadConfig readConfig;
	public static ThreadLocal<WebDriver> tdriver = new ThreadLocal<WebDriver>();
	protected static ThreadLocal<String> dateTime = new ThreadLocal<String>();

	public String getDateTime() {
		return dateTime.get();
	}

	public void setDateTime(String dateTime2) {
		dateTime.set(dateTime2);
	}

	public WebDriver initialize_driver(String browser) {

		System.out.println("The browsername is : " + browser);
		switch (browser.toLowerCase()) {
		case "chrome":
			WebDriverManager.chromedriver().cachePath("drivers").setup();
			tdriver.set(new ChromeDriver());
			break;
		case "firefox":
			WebDriverManager.firefoxdriver().cachePath("drivers").setup();
			tdriver.set(new FirefoxDriver());
			break;
		case "edge":
			WebDriverManager.edgedriver().cachePath("drivers").setup();
			tdriver.set(new EdgeDriver());
			break;
		default:
			throw new IllegalStateException("invalid browsername : " + browser);
		}
		getDriver().manage().deleteAllCookies();
		getDriver().manage().window().maximize();
		getDriver().get("https://techfios.com/billing/?ng=login/");
		getDriver().manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		return getDriver();
	}

	public static synchronized WebDriver getDriver() {
		return tdriver.get();
	}

}
