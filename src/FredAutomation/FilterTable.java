package FredAutomation;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.Assertion;

import io.github.bonigarcia.wdm.WebDriverManager;

public class FilterTable {
	
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
	public void filterTable()
	{

		
		driver.get("https://rahulshettyacademy.com/seleniumPractise/#/offers");
		driver.findElement(By.id("search-field")).sendKeys("Orange");
		List<WebElement> filterResult = driver.findElements(By.xpath("//tr/td[1]"));
		List<WebElement> checkedResult = filterResult.stream().filter(s->s.getText().contains("Orange")).collect(Collectors.toList());
		System.out.println("filterResult: "+filterResult.size()+"\ncheckedResult: "+checkedResult.size());
		Assertion a = new Assertion();
		a.assertEquals(filterResult.size(), checkedResult.size(),"some filtered results do not contain keyword");
		
		
		

	}

}
