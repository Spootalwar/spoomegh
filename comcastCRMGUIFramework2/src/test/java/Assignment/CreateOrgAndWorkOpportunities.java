package Assignment;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import java.util.Random;
import java.util.Set;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.comcast.crm.generic.fileutility.ExcelUtility;
import com.comcast.crm.generic.fileutility.FileUtility;

public class CreateOrgAndWorkOpportunities {

	public static void main(String[] args) throws InterruptedException, EncryptedDocumentException, IOException {
		
		FileUtility flib = new FileUtility();
		ExcelUtility elib = new ExcelUtility();

		String url = flib.getDataFromPrpertiesFile("url");
		String browser =flib.getDataFromPrpertiesFile("browser");
		String userName = flib.getDataFromPrpertiesFile("username");
		String passWord = flib.getDataFromPrpertiesFile("password");

		Random random = new Random();
		int randomnum = random.nextInt(1000);

		FileInputStream fis1 = new FileInputStream("C:/Users/Nithin/Desktop/Data1/TestScript.xlsx");
		Workbook wb = WorkbookFactory.create(fis1);
		Sheet sh = wb.getSheet("Sheet1");
		Row row = sh.getRow(7);
		String orgname = row.getCell(2).toString() + randomnum;
		String orgname1 = elib.getDataFromExcel("Sheet1", 7, 2);
		
		String opp = row.getCell(5).toString();
	
		wb.close();

		WebDriver driver = null;

		if (browser.equals("chrome")) {
			driver = new ChromeDriver();
		} else if (browser.equals("firefox")) {
			driver = new FirefoxDriver();
		} else if (browser.equals("edge")) {
			driver = new EdgeDriver();
		} else {
			driver = new ChromeDriver();
		}

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
		driver.get(url);
		String parentwindow = driver.getWindowHandle();
		Thread.sleep(2000);
		driver.findElement(By.name("user_name")).sendKeys(userName);
		driver.findElement(By.name("user_password")).sendKeys(passWord);
		driver.findElement(By.id("submitButton")).click();
		
		driver.findElement(By.linkText("Organizations")).click();
		driver.findElement(By.xpath("//img[@src='themes/softed/images/btnL3Add.gif']")).click();
		driver.findElement(By.name("accountname")).sendKeys(orgname);	
		driver.findElement(By.name("ship_street")).sendKeys("wer");
		
	
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
		String head = driver.findElement(By.className("dvHeaderText")).getText();
			if(head.contains(orgname)) {
		System.out.println(orgname + " orgname is created");
	}
		driver.findElement(By.linkText("Opportunities")).click();
		driver.findElement(By.xpath("//img[@src='themes/softed/images/btnL3Add.gif']")).click();
		driver.findElement(By.name("potentialname")).sendKeys(opp);
		driver.findElement(By.xpath("//input[@id='related_to_display']/following-sibling::img")).click();
		
		Set<String> childwindow = driver.getWindowHandles();
		String expected = "module=Accounts&action";
		for(String window:childwindow) {
			driver.switchTo().window(window);
			if(driver.getTitle().contains(expected)) {
				break;
			}
			
		}
		
		driver.findElement(By.name("search_text")).sendKeys(orgname);
		driver.findElement(By.name("search")).click();
		driver.findElement(By.xpath("//a[text()='"+orgname+"']")).click();
		driver.switchTo().window(parentwindow);
		driver.findElement(By.xpath("//input[@name='button']")).click();
		String head1 = driver.findElement(By.className("dvHeaderText")).getText();
		if(head1.contains(opp)) {
			System.out.println(head1 +" opp is created");
		}
		

	}

}
