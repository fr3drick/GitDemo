package FredAutomation;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.asserts.Assertion;

public class FilterTable {

	public static void main(String[] args) {
		System.setProperty("webdriver.chrome.driver","C:\\Users\\iruborf\\Eclipse\\chromedriver.exe");
		ChromeOptions options = new ChromeOptions();
		options.setAcceptInsecureCerts(true);
		
		WebDriver driver = new ChromeDriver(options);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.manage().window().maximize();
		
		driver.get("https://rahulshettyacademy.com/seleniumPractise/#/offers");
		driver.findElement(By.id("search-field")).sendKeys("Orange");
		List<WebElement> filterResult = driver.findElements(By.xpath("//tr/td[1]"));
		List<WebElement> checkedResult = filterResult.stream().filter(s->s.getText().contains("Orange")).collect(Collectors.toList());
		System.out.println("filterResult: "+filterResult.size()+"\ncheckedResult: "+checkedResult.size());
		Assertion a = new Assertion();
		a.assertEquals(filterResult.size(), checkedResult.size(),"some filtered results do not contain keyword");
		
		
		

	}

}
