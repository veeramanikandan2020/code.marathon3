package code.marathon3;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class ParentContent {
	
	public ChromeDriver driver;
	
	@BeforeMethod
	public void preCondition() {
		ChromeOptions options = new ChromeOptions();
		
		options.addArguments("--disable notification");
		
		driver = new ChromeDriver(options);
		
		//1. Launch https://login.salesforce.com/ 
		
		driver.get("https://login.salesforce.com");
		
		driver.manage().window().maximize();
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		
		//2. Login to Salesforce with your username and password
		driver.findElement(By.id("username")).sendKeys("veera_2020@testleaf.com");
		
		driver.findElement(By.id("password")).sendKeys("M@noharan2020");
		
		driver.findElement(By.id("Login")).click();
	}
	
	@AfterMethod
	public void postCondition() {

		driver.close();

	}

}
