package FredAutomation;

import java.time.Duration;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class AutomationPractice {

	public static void main(String[] args) {
		System.setProperty("webdriver.chrome.driver","C:\\Users\\iruborf\\Eclipse\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.manage().window().maximize();
		
		driver.get("https://www.rahulshettyacademy.com/AutomationPractice/");
		
		List<WebElement> links = driver.findElements(By.tagName("a"));
		System.out.println("count of links in webpage:"+links.size());
//		for(WebElement w : links) {
//			System.out.println(w.getText());}
		List<WebElement> linksFooter = driver.findElements(By.xpath("//div[contains(@class, 'footer')] //a"));
		System.out.println("count of links in footer:"+linksFooter.size());
		
		WebElement footerDriver = driver.findElement(By.xpath("//div[@id='gf-BIG']"));
		WebElement columnDriver = footerDriver.findElement(By.cssSelector("table tbody tr td:nth-child(1) ul"));
		
		List<WebElement> linksColumn = columnDriver.findElements(By.tagName("a"));
		System.out.println("count of links in column:"+linksColumn.size());
		
		Actions a = new Actions(driver);
				
		for(WebElement w: linksColumn) {
			a.keyDown(Keys.CONTROL).click(w).build().perform();
		}
		
		Set<String> handles = driver.getWindowHandles();
		Iterator<String> it = handles.iterator();
		
		while(it.hasNext()) {
			driver.switchTo().window(it.next());
			System.out.println(driver.getTitle());
		}
		
	}

}