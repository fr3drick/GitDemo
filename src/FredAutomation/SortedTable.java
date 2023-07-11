package FredAutomation;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.manager.SeleniumManager;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class SortedTable {

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
	public void sortedTables() {

		driver.get("https://rahulshettyacademy.com/seleniumPractise/#/offers");
		driver.findElement(By.xpath("//tr/th[1]")).click();
//		driver.findElement(By.xpath("//tr/th[1]")).click(); //uncomment to make test fail

		// checking if table is sorted
		List<WebElement> items = driver.findElements(By.xpath("//tr/td[1]"));
		List<String> originalList = items.stream().map(s -> s.getText()).collect(Collectors.toList());
		List<String> sortedList = originalList.stream().sorted().collect(Collectors.toList());

		Assert.assertTrue(originalList.equals(sortedList), "web table does not sort");

		// find beans on table and print its price
		List<String> priceOfItem;// = new List<String>();
		do {

			List<WebElement> items1 = driver.findElements(By.xpath("//tr/td[1]"));
			priceOfItem = items1.stream().filter(s -> s.getText().contains("Rice")).map(s -> getPrice(s))
					.collect(Collectors.toList());

			if (priceOfItem.size() < 1) {
				driver.findElement(By.xpath("//a[@aria-label='Next']")).click();
			} else {
				priceOfItem.forEach(a -> System.out.println(a));
			}

		} while (priceOfItem.size() < 1);

	}

	private static String getPrice(WebElement s) {
		return s.findElement(By.xpath("following-sibling::td[1]")).getText();
	}

}
