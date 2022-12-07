package com.qa.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.github.javafaker.Faker;

public class Utilities {
	static WebDriver driver;
	Map<String, String> params = new HashMap<String, String>();

	public static Logger log() {
		return LogManager.getLogger(Thread.currentThread().getStackTrace()[2].getClassName());
	}

	public static String getdateTime() {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
		Date date = new Date();
		System.out.println(dateFormat.format(date));
		return dateFormat.format(date);
	}

	public static int randomNumGenerator() {
		Random rnd = new Random();
		int randomNum = rnd.nextInt(999);
		return randomNum;
	}

	public static void waitForImplicitlyWait() throws InterruptedException {

		Thread.sleep(3000);
	}

	public static void windowScrollDown() {

		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("scroll(0,8000)");
	}

	public void dataFaker() {
		Faker faker = new Faker();
		// To generate valid random first name
		String FirstName = faker.name().firstName();
		// To generate valid random last name
		String LastName = faker.name().lastName();
		// To generate valid Street number
		String StreetNumber = faker.address().streetAddressNumber();
		// To generate valid Street name
		String StreetName = faker.address().streetName();
		// To generate valid random city name
		String City = faker.address().cityName();
		// To generate valid random state name
		String Address = faker.address().state();
		// To generate valid Zip code
		String ZipCode = faker.address().zipCode();
		// To generate valid random country name
		String Country = faker.address().country();
		// To generate valid random cell number
		String PhoneNumber = faker.phoneNumber().cellPhone();
		// To generate valid Company name
		String Company = faker.company().name();
		// To generate valid email address
		String Email = faker.internet().emailAddress();
		// To generate valid Company name
		String JobTitle = faker.job().title();
		// To generate valid password
		String Password = faker.internet().password();
		// To generate valid random number
		int RandomNumber = (faker.number().numberBetween(1, 999));

	}

	public static void waitForElement(WebElement element, WebDriver driver, int time) {
		WebDriverWait wait = new WebDriverWait(driver, time);
		wait.until(ExpectedConditions.elementToBeClickable(element));
	}

	public static void waitForElementToBeClickable(WebElement element, WebDriver driver, int time) {
		WebDriverWait wait = new WebDriverWait(driver, time);
		wait.until(ExpectedConditions.elementToBeClickable(element));
	}

	public static void isDataPresent(WebDriver driver, String beforexpath, String afterxpath, String countRowsxpath,
			String lookupData) {
		String before_xpath = beforexpath;
		String after_xpath = afterxpath;
		int countRows = driver.findElements(By.xpath(countRowsxpath)).size();
		System.out.println("The number of rows in a table is :" + countRows);

		for (int i = 1; i <= countRows; i++) {
			String name = driver.findElement(By.xpath(before_xpath + i + after_xpath)).getText();
			if (name.contains(lookupData)) {
				driver.findElement(By.xpath(before_xpath + i + after_xpath)).getText();
				System.out.println("The look up data is : " + name);
				break;
			} else {
				System.out.println("The look up data is missing in the table");
				break;
			}
		}

	}

	public static void windowScrollUp() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("scroll(0,-8000)");
	}

	public static void dropDownListByVisibleText(WebElement locator, String visibleText) {
		Select select = new Select(locator);
		select.selectByVisibleText(visibleText);
	}

	public static void dropDownListByIndex(WebElement locator, int index) {
		Select select = new Select(locator);
		select.selectByIndex(index);
	}

	public static void dropDownListByValue(WebElement locator, String value) {
		Select select = new Select(locator);
		select.selectByValue(value);
	}

	public void navigateBack() {
		driver.navigate().back();
	}

	public static void threadsleep(int timeout) throws InterruptedException {
		Thread.sleep(timeout);
	}

	public static void selectDate(WebDriver driver, String targetDate, String dateFormat) throws Exception {
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat targetDateFormat = new SimpleDateFormat(dateFormat);
		Date formattedTargetDate;
		try {
			targetDateFormat.setLenient(false);
			formattedTargetDate = targetDateFormat.parse(targetDate);
			calendar.setTime(formattedTargetDate);
			int targetDay = calendar.get(Calendar.DAY_OF_MONTH);
			int targetMonth = calendar.get(Calendar.MONTH);
			int newtargetMonth = targetMonth + 1;
			int targetYear = calendar.get(Calendar.YEAR);
			// Click on Calendar
			threadsleep(2000);
			driver.findElement(By.xpath("//input[@id='date']")).click();
			threadsleep(2000);

			// 1. Print the current Month

			String actualDate = driver.findElement(By.xpath("//li[@data-type='month current']")).getText();
			calendar.setTime(new SimpleDateFormat("MMM yyyy").parse(actualDate));
			threadsleep(2000);
			int actualMonth = calendar.get(Calendar.MONTH);
			int newActualMonth = actualMonth + 1;
			int actualYear = calendar.get(Calendar.YEAR);

			// 2. Month Preview

			while (newtargetMonth < newActualMonth || targetYear < actualYear) {
				driver.findElement(By.xpath("//li[@data-type='month prev']")).click();
				actualDate = driver.findElement(By.xpath("//li[@data-type='month current']")).getText();
				calendar.setTime(new SimpleDateFormat("MMM yyyy").parse(actualDate));
				actualMonth = calendar.get(Calendar.MONTH);
				actualYear = calendar.get(Calendar.YEAR);
			}
			// 3. Next Month

			while (newtargetMonth > newActualMonth || targetYear > actualYear) {
				driver.findElement(By.xpath("//li[@data-type='month next']")).click();
				actualDate = driver.findElement(By.xpath("//li[@data-type='month current']")).getText();
				calendar.setTime(new SimpleDateFormat("MMM yyyy").parse(actualDate));
				actualMonth = calendar.get(Calendar.MONTH);
				actualYear = calendar.get(Calendar.YEAR);
			}

			driver.findElement(By.xpath(
					"//ul[@class='datepicker-days' and (@data-type='days')]//li[not(contains(@data-type,'day prev')) and (not(contains(@data-type,'day next')))][contains(text(),"
							+ targetDay + ")]"))
					.click();
		} catch (ParseException e) {
			throw new Exception("invalid date is provided, please check input date");
		}
	}

	public static void selectDate2(WebDriver driver, String targetDate, String dateFormat) throws Exception {
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat targetDateFormat = new SimpleDateFormat(dateFormat);
		Date formattedTargetDate;
		try {
			targetDateFormat.setLenient(false);
			formattedTargetDate = targetDateFormat.parse(targetDate);
			calendar.setTime(formattedTargetDate);
			int targetDay = calendar.get(Calendar.DAY_OF_MONTH);
			int targetMonth = calendar.get(Calendar.MONTH);
			int newtargetMonth = targetMonth + 1;
			int targetYear = calendar.get(Calendar.YEAR);
			// Click on Calendar
			threadsleep(2000);
			driver.findElement(By.xpath("//input[@id='idate']")).click();
			threadsleep(2000);

			// 1. Print the current Month

			String actualDate = driver.findElement(By.xpath("//li[@data-type='month current']")).getText();
			calendar.setTime(new SimpleDateFormat("MMM yyyy").parse(actualDate));
			threadsleep(2000);
			int actualMonth = calendar.get(Calendar.MONTH);
			int newActualMonth = actualMonth + 1;
			int actualYear = calendar.get(Calendar.YEAR);

			// 2. Month Preview

			while (newtargetMonth < newActualMonth || targetYear < actualYear) {
				driver.findElement(By.xpath("//li[@data-type='month prev']")).click();
				actualDate = driver.findElement(By.xpath("//li[@data-type='month current']")).getText();
				calendar.setTime(new SimpleDateFormat("MMM yyyy").parse(actualDate));
				actualMonth = calendar.get(Calendar.MONTH);
				actualYear = calendar.get(Calendar.YEAR);
			}
			// 3. Next Month

			while (newtargetMonth > newActualMonth || targetYear > actualYear) {
				driver.findElement(By.xpath("//li[@data-type='month next']")).click();
				actualDate = driver.findElement(By.xpath("//li[@data-type='month current']")).getText();
				calendar.setTime(new SimpleDateFormat("MMM yyyy").parse(actualDate));
				actualMonth = calendar.get(Calendar.MONTH);
				actualYear = calendar.get(Calendar.YEAR);
			}

			driver.findElement(By.xpath(
					"//ul[@class='datepicker-days' and (@data-type='days')]//li[not(contains(@data-type,'day prev')) and (not(contains(@data-type,'day next')))][contains(text(),"
							+ targetDay + ")]"))
					.click();
		} catch (ParseException e) {
			throw new Exception("invalid date is provided, please check input date");
		}
	}

	public static void selectaDate() throws Exception {
		String expectedmonthyear = "Dec 2022";
		String expectedday = "12";
		Thread.sleep(2000);
		driver.findElement(By.xpath("//input[@id='idate']")).click();
		Thread.sleep(3000);
		while (true) {
			String currentmonthyear = driver.findElement(By.xpath("//li[@data-type='month current']")).getText();
			if (currentmonthyear.equalsIgnoreCase(expectedmonthyear)) {
				break;
			} else {
				driver.findElement(By.xpath("//li[@data-type='month next']")).click();
			}
		}
		driver.findElement(By.xpath("//ul[@class='datepicker-days']//li[contains(text()," + expectedday + ")]"))
				.click();
	}
}
