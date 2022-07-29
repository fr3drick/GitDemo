package FredAutomation;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import static org.openqa.selenium.support.locators.RelativeLocator.*;

public class RelativeLoc {

	public static void main(String[] args) {
		System.setProperty("webdriver.chrome.driver","C:\\Users\\iruborf\\Eclipse\\chromedriver.exe");
		ChromeOptions options = new ChromeOptions();
		options.setAcceptInsecureCerts(true);
		
		WebDriver driver = new ChromeDriver(options);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.manage().window().maximize();
		
		driver.get("https://rahulshettyacademy.com/angularpractice/");
		
		//driver.findElement(with(By.tagName("input")).below(By.xpath("//label[text()='Name']"))).click();//sendKeys("Hello!");
		driver.findElement(with(By.tagName("input")).above(By.xpath("//label[text()='Email']"))).sendKeys("Hello!");

		//label#exampleCheck1
		driver.findElement(with(By.tagName("input")).toLeftOf(By.cssSelector("label[for='exampleCheck1']"))).click();
		driver.findElement(with(By.tagName("input")).below(By.cssSelector("label[for='dateofBirth']"))).click();
		
	}

}
