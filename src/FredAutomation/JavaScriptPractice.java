package FredAutomation;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class JavaScriptPractice {
	
	WebDriver driver;
	
	@BeforeClass

		static void setupAll() {
    	WebDriverManager.chromedriver().setup();
	}
	@BeforeMethod
		void setup() {
		ChromeOptions options = new ChromeOptions();
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
	public void jsPractice() throws InterruptedException {

		driver.get("https://www.rahulshettyacademy.com/AutomationPractice/");
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,300)");
		Thread.sleep(2000);
		js.executeScript("document.querySelector(\".tableFixHead\").scrollTop=5000");
		
		List<WebElement> values = driver.findElements(By.cssSelector("div.tableFixHead td:nth-child(4)"));
		int sum=0;
		
		for(WebElement w : values) {
			sum += Integer.parseInt(w.getText());
		}
		
		System.out.println("amount calculated: "+sum);
		System.out.println("amount shown: "+driver.findElement(By.cssSelector(".totalAmount")).getText().split(":")[1].trim());
		int total = Integer.parseInt(driver.findElement(By.cssSelector(".totalAmount")).getText().split(":")[1].trim());
		
		Assert.assertEquals(total, sum);
	}

}
