package FredAutomation;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.manager.SeleniumManager;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class CalendarPractice {

	WebDriver driver;

	@BeforeClass

	static void setupAll() {
//    	WebDriverManager.chromedriver().setup();
		SeleniumManager.getInstance();

	}

	@BeforeMethod
	void setup() {
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--remote-allow-origins=*");

		driver = new ChromeDriver(options);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.manage().window().maximize();
	}

	@AfterMethod
	void teardown() {

		driver.quit();
	}

	@Test
	public void calendarPractice() {

		driver.get("https://www.globalsqa.com/demo-site/datepicker/");

		String desiredDay = "30";
		String desiredMonth = "January";
		String desiredYear = "2026";

		driver.switchTo().frame(driver.findElement(By.cssSelector("iframe.demo-frame.lazyloaded")));
		driver.findElement(By.id("datepicker")).click();

		WebDriverWait wt = new WebDriverWait(driver, Duration.ofSeconds(5));
		wt.until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath("//div[@id='ui-datepicker-div'] //a[@title='Next']")));

		while (!(driver.findElement(By.xpath("//span[@class='ui-datepicker-month']")).getText()
				.equalsIgnoreCase(desiredMonth)
				&& driver.findElement(By.xpath("//span[@class='ui-datepicker-year']")).getText()
						.equalsIgnoreCase(desiredYear))) {
			driver.findElement(By.xpath("//div[@id='ui-datepicker-div'] //a[@title='Next']")).click();
			wt.until(ExpectedConditions
					.visibilityOf(driver.findElement(By.xpath("//span[@class='ui-datepicker-month']"))));
		}

		List<WebElement> days = driver.findElements(By.cssSelector("a[class*='ui-state-default']"));

		for (WebElement w : days) {
			if (w.getText().equalsIgnoreCase(desiredDay)) {
				w.click();
				break;
			}
		}

	}

}
