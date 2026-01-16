package Assignment;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.List;
import java.util.Properties;
import java.util.Random;

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
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

public class DleteOrg {

	public static void main(String[] args) throws IOException, InterruptedException {
		
		FileInputStream fis = new FileInputStream("./Data/common1data.properties");
		Properties pobj = new Properties();
		pobj.load(fis);

		
		
		String url = pobj.getProperty("url");
		String browser = pobj.getProperty("browser");
		String userName = pobj.getProperty("username");
		String passWord = pobj.getProperty("password");

		Random random = new Random();
		int randomnum = random.nextInt(2000);

		FileInputStream fis1 = new FileInputStream("C:/Users/Nithin/Desktop/Data1/TestScript.xlsx");
		Workbook wb = WorkbookFactory.create(fis1);
		Sheet sh = wb.getSheet("Sheet3");
		Row row = sh.getRow(7);
		String orgname = row.getCell(2).toString()+randomnum;
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

		driver.findElement(By.linkText("Organizations")).click();
		driver.findElement(By.xpath("//img[@src='themes/softed/images/btnL3Add.gif']")).click();
		driver.findElement(By.name("accountname")).sendKeys(orgname);
        driver.findElement(By.name("ship_street")).sendKeys("wer");
		
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
		Thread.sleep(2000);
		driver.findElement(By.linkText("Organizations")).click();
		
		driver.findElement(By.name("search_text")).sendKeys(orgname);
		WebElement drop = driver.findElement(By.id("bas_searchfield"));
		Select sel = new Select(drop);
		sel.selectByIndex(1);
		driver.findElement(By.xpath("//input[@name='submit']")).click();
		Thread.sleep(3000);
	    driver.findElement(By.xpath("//input[@name='selected_id']")).click();
	    driver.findElement(By.xpath("(//input[@value='Delete'])[2]")).click();
		driver.switchTo().alert().accept();
	
		}
	    
		
	//td[text()='ACC43244 ']/preceding-sibling::td/input[@name='selected_id']
		
	}


