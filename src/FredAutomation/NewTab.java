package FredAutomation;

import java.time.Duration;
import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.manager.SeleniumManager;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class NewTab {
	WebDriver driver;
	
    	@BeforeClass
 
    		static void setupAll() {
//        	WebDriverManager.chromedriver().setup();
    		SeleniumManager.getInstance();

   	}
    	@BeforeMethod
    		void setup() {
        	driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.manage().window().maximize();
   	}
	
	    @AfterMethod
  	  void teardown() {
       
	    	driver.quit();
   	}
	
	@Test
	public void newTab()
	{

		
		driver.get("https://rahulshettyacademy.com/angularpractice/");
		driver.switchTo().newWindow(WindowType.TAB);
		Set<String> handles = driver.getWindowHandles();
		Iterator<String> it = handles.iterator();
		String parentID = it.next();
		String childID = it.next();
		
		driver.switchTo().window(childID);
		driver.get("http://qaclickacademy.com");
		
		String course = driver.findElement(By.cssSelector("div.item h3 a")).getText();
		driver.switchTo().window(parentID);
		driver.findElement(By.xpath("//input[@name='name'][1]")).sendKeys(course);
		
		

	}
	
	@Test
	public void qaClick()
	{
		driver.get("http://qaclickacademy.com/");
		System.out.println(driver.getTitle());
	
	}

}
