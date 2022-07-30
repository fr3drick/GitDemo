package FredAutomation;

import java.io.File;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.List;
import java.util.Random;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import io.github.bonigarcia.wdm.WebDriverManager;

public class MiscFunc {
	
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
	public void miscFns() throws IOException, MalformedURLException 
	{

	
//		driver.get("https://google.com");
//		
		//broken URL
		
		//random number
		Random rand = new Random();
		
		driver.get("https://rahulshettyacademy.com/AutomationPractice");
		File src = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(src,new File(System.getProperty("user.dir")+"\\screenshots\\screenshot"+rand.nextInt(20)+".png"));
		//System.out.println(System.getProperty("user.dir"));

		
		//takes screenshot of image at site intro
		File src1 = ((TakesScreenshot)driver.findElement(By.cssSelector("img"))).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(src1,new File(System.getProperty("user.dir")+"\\screenshots\\screenshot"+rand.nextInt(20)+".png"));

		List<WebElement> links = driver.findElements(By.cssSelector("div#gf-BIG a"));
//		String url = links.get(2).getAttribute("href");
		SoftAssert sa = new SoftAssert();
		
		for(WebElement w : links) {
			HttpURLConnection conn = (HttpURLConnection)new URL(w.getAttribute("href")).openConnection();
			conn.setRequestMethod("HEAD");
			conn.connect();
			int respCode = conn.getResponseCode();
			System.out.println(w.getAttribute("href") +" " +respCode);
			
			sa.assertTrue(respCode<400, "The link with Text: "+ w.getText()+" failed with code "+respCode);
		}

		sa.assertAll();
	}

}
