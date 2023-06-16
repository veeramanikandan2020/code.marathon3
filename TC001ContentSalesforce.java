package code.marathon3;

import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class TC001ContentSalesforce extends ParentContent {
	
	@Test(dataProvider = "sendData")
	public void runContentTestCase(String question, String certificates) throws IOException {
				// To switch to lighting app 
				//driver.findElement(By.xpath("//a[@class='switch-to-lightning']")).click();
				
				//3. Click on the App Launcher (dots)
				
				driver.findElement(By.xpath("//button[contains(@class,'slds-button slds-icon-waffle')]")).click();
				
				//4. Click View All
				
				driver.findElement(By.xpath("//button[text()='View All']")).click();
				
				//5. Type Content on the Search box
				
				driver.findElement(By.xpath("//input[@class='slds-input']")).sendKeys("content");
				
				//6. Click the Content Link
				
				driver.findElement(By.xpath("//mark[text()='Content']")).click();
				
				WebElement chatter = driver.findElement(By.xpath("(//span[@class='slds-truncate'])[3]"));
				
				//7. Click on Chatter Tab

				driver.executeScript("arguments[0].click()", chatter);
				
				//9. Click the Question tab
				
				driver.findElement(By.xpath("//span[text()='Question']")).click();
				
				//10. Type Question with data (coming from Excel)
				
				driver.findElement(By.xpath("//textarea[@class='cuf-questionTitleField textarea']")).sendKeys(question);
				
				//11. Type Details with data (coming from Excel)
				
				driver.findElement(By.xpath("//div[contains(@class,'ql-editor ql-blank slds')]")).sendKeys(certificates);
				
				//12. Click Ask
				
				driver.findElement(By.xpath("//button[@title='Click, or press Ctrl+Enter']")).click();
				
				//13. Confirm the question appears
				
				String questionAsk = driver.findElement(By.xpath("(//span[@data-aura-class='uiOutputText'])[4]")).getText();
				
				System.out.println("Question asked is : "+questionAsk);
	}
	
	@DataProvider
	public String[][] sendData() throws IOException {
		
		String[][] data = new String [1][2];
		
		XSSFWorkbook wb = new XSSFWorkbook("./data/KeyDeals.xlsx");
		// Go to specific sheet using index/sheet name
		XSSFSheet sheet = wb.getSheetAt(0);
		//Find the total number of rows
		int rowCount = sheet.getLastRowNum();
		//System.out.println("Total number of rows :"+rowCount);
		//Find  the total number of columns
		int columnCount = sheet.getRow(0).getLastCellNum();
		//System.out.println("Total number of columns :"+columnCount);
		// read specific data
		XSSFCell cell = sheet.getRow(1).getCell(0);
		String firstrowfirstcolumnData = cell.getStringCellValue();
		XSSFCell cell1 = sheet.getRow(1).getCell(1);
		String firstrowsecondcolumnData = cell1.getStringCellValue();
		
		data[0][0] = firstrowfirstcolumnData;
		data[0][1] = firstrowsecondcolumnData;
		
		return data;
		
	}

}
