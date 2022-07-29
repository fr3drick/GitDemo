package FredAutomation;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CalendarPractice {

	public static void main(String[] args){
		System.setProperty("webdriver.chrome.driver","C:\\Users\\iruborf\\Eclipse\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.manage().window().maximize();
		
		driver.get("https://www.globalsqa.com/demo-site/datepicker/");
		
		String desiredDay = "30";
		String desiredMonth = "January";
		String desiredYear = "2026";
		
		driver.switchTo().frame(driver.findElement(By.cssSelector("iframe.demo-frame.lazyloaded")));
		driver.findElement(By.id("datepicker")).click();
		
		WebDriverWait wt = new WebDriverWait(driver, Duration.ofSeconds(5));
		wt.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='ui-datepicker-div'] //a[@title='Next']")));
		
		
		while(!(driver.findElement(By.xpath("//span[@class='ui-datepicker-month']")).getText().equalsIgnoreCase(desiredMonth) 
				&& driver.findElement(By.xpath("//span[@class='ui-datepicker-year']")).getText().equalsIgnoreCase(desiredYear) )) {
			driver.findElement(By.xpath("//div[@id='ui-datepicker-div'] //a[@title='Next']")).click();
			wt.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//span[@class='ui-datepicker-month']"))));
		}
		
		
		List<WebElement> days = driver.findElements(By.cssSelector("a[class*='ui-state-default']"));
		
		for(WebElement w: days) {
			if(w.getText().equalsIgnoreCase(desiredDay)) {
				w.click();
				break;
			}
		}
		
	}

}
