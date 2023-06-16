package code.marathon3;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class TC002Sales extends ParentSales {

	@Test(dataProvider = "sendData")

	public void runSales(String name, String amount) throws InterruptedException {

		// To switch to lighting app - When needed
		// driver.findElement(By.xpath("//a[@class='switch-to-lightning']")).click();

		// 2. Click on the toggle menu button from the left corner

		driver.findElement(By.xpath("//button[contains(@class,'slds-button slds-icon-waffle')]")).click();

		// 3. Click View All and click Sales from App Launcher

		Thread.sleep(2000);

		driver.findElement(By.xpath("//button[text()='View All']")).click();

		Set<String> windowHandles = driver.getWindowHandles();

		List<String> handles = new ArrayList<String>(windowHandles);

		// driver.switchTo().window(handles.get(1)); // chromebrowser

		driver.switchTo().window(handles.get(0));

		driver.findElement(By.xpath("//p[text()='Sales']")).click();

		// 4. Click View All Key Deals in Key Deals

		WebElement scrollToKeyDeals = driver.findElement(By.xpath("//span[text()='View All Key Deals']"));

		Actions builder = new Actions(driver);

		builder.scrollToElement(scrollToKeyDeals).perform();

		Thread.sleep(2000);

		WebElement js = driver.findElement(By.xpath("(//a[@class='viewAllLink'])[4]"));

		driver.executeScript("arguments[0].click()", js);

		// 6. Click on New

		driver.findElement(By.xpath("//div[@title='New']")).click();

		// 7. Give Opportunity Name (From Excel)

		Set<String> windowHandles2 = driver.getWindowHandles();

		List<String> handles2 = new ArrayList<String>(windowHandles2);

		// driver.switchTo().window(handles2.get(1)); // Chrome Browser

		driver.switchTo().window(handles2.get(0));

		driver.findElement(By.xpath("//input[@name='Name']")).sendKeys(name);

		// 8. Select Type as New Customer and Lead Source as Partner Referral

		// driver.findElement(By.xpath("(//button[contains(@class,'slds-combobox__input')])[2]")).click();

		WebElement type = driver
				.findElement(By.xpath("(//button[contains(@class,'slds-combobox__input slds-input_faux')])[2]"));

		driver.executeScript("arguments[0].click()", type);

		driver.findElement(By.xpath("//span[text()='New Customer']")).click();

		driver.findElement(By.xpath("//button[@aria-label='Lead Source, --None--']")).click();

		driver.findElement(By.xpath("//span[text()='Partner Referral']")).click();

		// 9. Give the Amount as 75000 (from Excel)

		driver.findElement(By.xpath("//input[@name='Amount']")).sendKeys(amount);

		// 10. Select Close Date as tomorrow

		driver.findElement(By.xpath("//input[@name='CloseDate']")).sendKeys("6/17/2023");

		// 11. Select Stage as Needs Analysis

		driver.findElement(By.xpath("//button[@aria-label='Stage, --None--']")).click();

		driver.findElement(By.xpath("//span[@title='Needs Analysis']")).click();

		// 12. Click on Primary Campaign Source and Select the first option

		driver.findElement(By.xpath("//input[@placeholder= 'Search Campaigns...']")).click();

		driver.findElement(By.xpath("(//span[contains(@class,'slds-listbox__option-text slds')])[1]")).click();

		// 13. Click Save and Verify the opportunity is created"

		driver.findElement(By.xpath("//button[text()='Save']")).click();

		String opportunityName = driver.findElement(By.xpath("//lightning-formatted-text[@slot='primaryField']"))
				.getText();

		if (opportunityName.contains("Veera"))
			System.out.println("Verified and opportunity is created");
		else
			System.out.println("Not Verified and opportunity is not created\");");

	}

	@DataProvider
	public String[][] sendData() throws IOException {
		String[][] data = new String[1][2];

		XSSFWorkbook wb = new XSSFWorkbook("./data/Sales.xlsx");
		// Go to specific sheet using index/sheet name
		XSSFSheet sheet = wb.getSheetAt(0);
		// read specific data
		XSSFCell cell = sheet.getRow(1).getCell(0);
		String firstrowfirstcolumnData = cell.getStringCellValue();
		XSSFCell cell1 = sheet.getRow(1).getCell(1);
		String firstrowsecondcolumnData = cell1.getStringCellValue();
		System.out.println(firstrowsecondcolumnData);

		data[0][0] = firstrowfirstcolumnData;
		data[0][1] = firstrowsecondcolumnData;

		return data;
	}

}
