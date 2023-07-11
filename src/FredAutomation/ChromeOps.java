package FredAutomation;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.manager.SeleniumManager;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class ChromeOps {

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

		options.setAcceptInsecureCerts(true);
		driver = new ChromeDriver(options);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.manage().window().maximize();
	}

	@AfterMethod
	void teardown() {

		driver.quit();
	}

	@Test
	public void chromeOps() {

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().deleteCookieNamed("sessionKey");
		// if session cookie is deleted, user should be logged in

		driver.get("https:/expired.badssl.com");
		System.out.println(driver.getTitle());
	}

}
