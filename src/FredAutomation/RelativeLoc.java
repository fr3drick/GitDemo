package FredAutomation;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.manager.SeleniumManager;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

import static org.openqa.selenium.support.locators.RelativeLocator.*;

public class RelativeLoc {
	
	WebDriver driver;
	
	@BeforeClass

		static void setupAll() {
//    	WebDriverManager.chromedriver().setup();
		SeleniumManager.getInstance();

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
	public void relativeLoc() 
	{
		
		driver.get("https://rahulshettyacademy.com/angularpractice/");
		
		//driver.findElement(with(By.tagName("input")).below(By.xpath("//label[text()='Name']"))).click();//sendKeys("Hello!");
		driver.findElement(with(By.tagName("input")).above(By.xpath("//label[text()='Email']"))).sendKeys("Hello!");

		//label#exampleCheck1
		driver.findElement(with(By.tagName("input")).toLeftOf(By.cssSelector("label[for='exampleCheck1']"))).click();
		driver.findElement(with(By.tagName("input")).below(By.cssSelector("label[for='dateofBirth']"))).click();
		
	}

}
