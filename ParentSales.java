package code.marathon3;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class ParentSales {
	
	public static EdgeDriver driver;
	
	@BeforeMethod
	public void preCondition() {
		EdgeOptions option = new EdgeOptions();

		option.addArguments("--disable-notifications");

		driver = new EdgeDriver(option);

//		ChromeOptions options = new ChromeOptions();
//		
//		options.addArguments("--disable notification");
//		
//		ChromeDriver driver = new ChromeDriver(options);

		// 1. Login to https://login.salesforce.com

		driver.get("https://login.salesforce.com");

		driver.manage().window().maximize();

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));

		// 2. Login to Salesforce with your username and password
		driver.findElement(By.id("username")).sendKeys("hari.radhakrishnan@qeagle.com");

		driver.findElement(By.id("password")).sendKeys("Leaf@1234");

		driver.findElement(By.id("Login")).click();
	}
	
	@AfterMethod
	public void postCondition() {
		
		driver.close();

	}


}
