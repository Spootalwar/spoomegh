package Assignment;

import java.io.FileInputStream;
import java.io.IOException;

import java.time.Duration;

import java.util.Properties;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class DropdownofPoduct {

	public static void main(String[] args) throws InterruptedException, IOException {
		FileInputStream fis = new FileInputStream("./Data/common1data.properties");
   		Properties pobj = new Properties();
   		pobj.load(fis);
		
   		String url = pobj.getProperty("url");
   		String browser = pobj.getProperty("browser");
   		String userName = pobj.getProperty("username");
   		String passWord = pobj.getProperty("password");

   		FileInputStream fis1 = new FileInputStream("C:/Users/Nithin/Desktop/Data1/TestScript.xlsx");
   		Workbook wb = WorkbookFactory.create(fis1);
   		Sheet sh = wb.getSheet("Sheet1");
   		Row row = sh.getRow(1);
   		String prodname = row.getCell(3).toString() ;
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
   		Thread.sleep(2000);
   		driver.findElement(By.name("user_name")).sendKeys(userName);
   		driver.findElement(By.name("user_password")).sendKeys(passWord);
   		driver.findElement(By.id("submitButton")).click();
   		
   		driver.findElement(By.linkText("Products")).click();
   		driver.findElement(By.xpath("//img[@src='themes/softed/images/btnL3Add.gif']")).click();
   		driver.findElement(By.name("productname")).sendKeys(prodname);
   		
   		
   		WebElement drops = driver.findElement(By.name("productcategory"));
   		Select sel = new Select(drops);
   		sel.selectByVisibleText("Software");
   		String expdrop = sel.getFirstSelectedOption().getText();
   		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
   		
   		String actprod = driver.findElement(By.id("dtlview_Product Name")).getText();
   		if(actprod.contains(prodname)) {
   			System.out.println(prodname +" is correct");
   		}
   		
   		String actdrop = driver.findElement(By.id("dtlview_Product Category")).getText();
   		if(actdrop.contains(expdrop)) {
   			System.out.println(expdrop +" is verified");
   		}
   		
   		

	}

}
