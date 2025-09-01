package com.spicejet;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.Locale;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class SpicejetCalendarAutomation {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ChromeOptions chromeOptions = new ChromeOptions();
		chromeOptions.addArguments("--start-maximized");
		WebDriver driver = new ChromeDriver(chromeOptions);
		driver.get("https://www.spicejet.com/");
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("document.body.style.zoom='70%'");

		By fromCityCheckBoxLocator = By.xpath("//div[contains(text(),\"From\")]/following-sibling::div//input");
		WebElement fromCityTextbox = wait.until(ExpectedConditions.visibilityOfElementLocated(fromCityCheckBoxLocator));
		fromCityTextbox.sendKeys("Mum");

		By toCityCheckBoxLocator = By.xpath("//div[contains(text(),\"To\")]/following-sibling::div//input");
		WebElement toCityTextbox = wait.until(ExpectedConditions.visibilityOfElementLocated(toCityCheckBoxLocator));
		toCityTextbox.sendKeys("Pun");

		By calendarPickerLocator = By.xpath("//div[@data-testid=\"undefined-calendar-picker\"]");
		WebElement calendarPicker = wait.until(ExpectedConditions.visibilityOfElementLocated(calendarPickerLocator));

		By nextButtonLocator = By.xpath(".//*[local-name() = 'svg' and @data-testid=\"svg-img\"]");
		WebElement nextButton = calendarPicker.findElement(nextButtonLocator);
		nextButton.click();

		// To find SVG element //*[local-name()='svg'] //local-name() is a method

		// To get the current date

		LocalDate localDate = LocalDate.now();
		localDate = localDate.plusDays(7);

		localDate = localDate.plusMonths(2);
		System.out.println(localDate);
		String month = localDate.getMonth().getDisplayName(TextStyle.FULL, Locale.ENGLISH);
		System.out.println(month);
		int year = localDate.getYear();
		System.out.println(year);

		sleepFor(4);

		By monthCalendarLocator = By.xpath("//div[@data-testid=\"undefined-month-"+month+"-"+year+"\"]");
		WebElement monthCalendar = wait.until(ExpectedConditions.visibilityOfElementLocated(monthCalendarLocator));

		By dateLocator = By.xpath(".//div[contains(text(),'9')]");
//		WebElement date = wait.until(ExpectedConditions.visibilityOfElementLocated(dateLocator));
		// First try regular click
		wait.until(ExpectedConditions.elementToBeClickable(monthCalendar.findElement(dateLocator))).click();
//		monthCalendar.findElement(dateLocator).click();
		System.out.println("Date clicked successfully with regular click");

	}

	public static void sleepFor(int timeInSec) {
		try {
			Thread.sleep(timeInSec * 1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
