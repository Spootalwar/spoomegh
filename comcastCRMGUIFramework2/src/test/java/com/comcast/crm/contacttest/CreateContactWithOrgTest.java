package com.comcast.crm.contacttest;

import java.io.IOException;
import java.time.Duration;
import java.util.Random;
import java.util.Set;

import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.comcast.crm.generic.fileutility.ExcelUtility;
import com.comcast.crm.generic.fileutility.FileUtility;
import com.comcast.crm.generic.webdriverutility.JavaUtility;
import com.comcast.crm.generic.webdriverutility.WebDriverUtility;
import com.comcast.crm.objectrepositoryutility.CreateNewContact;
import com.comcast.crm.objectrepositoryutility.CreatingNewOrganizationPage;
import com.comcast.crm.objectrepositoryutility.HomePage;
import com.comcast.crm.objectrepositoryutility.LoginPage;
import com.comcast.crm.objectrepositoryutility.WindowHandles;

public class CreateContactWithOrgTest {

	public static void main(String[] args) throws InterruptedException, EncryptedDocumentException, IOException {
		
		  FileUtility flib = new FileUtility();	
	        ExcelUtility elib = new ExcelUtility();
	        JavaUtility jlib = new JavaUtility();
	        WebDriverUtility wlib = new WebDriverUtility();
	        
			String url = flib.getDataFromPrpertiesFile("url");
			String browser = flib.getDataFromPrpertiesFile("browser");
			String userName = flib.getDataFromPrpertiesFile("username");
			String passWord = flib.getDataFromPrpertiesFile("password");

		String orgname =elib.getDataFromExcel("Sheet3", 7, 2) + jlib.getRandomNumber();
		String contactlastname1 = elib.getDataFromExcel("Sheet3", 7, 3);

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

//		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
		wlib.waitForPageToLoad(driver);
		
		
		LoginPage lp = new LoginPage(driver);
		lp.loginToapp(url, userName, passWord);
		
		HomePage hp = new HomePage(driver);
		hp.getOrglink().click();
		
		CreatingNewOrganizationPage cnp = new CreatingNewOrganizationPage(driver);
		cnp.createOrgaName(orgname);
		Thread.sleep(3000);
		
		CreateNewContact cnc = new CreateNewContact(driver);
		cnc.getContactlink().click();
		cnc.getCreatecon().click();
		cnc.createNewContact1(contactlastname1);
		
		Thread.sleep(3000);
		
		WindowHandles wh = new WindowHandles(driver);
		wh.window(orgname, "Organization Name");
		
		driver.findElement(By.xpath("//a[text()='"+orgname+"']")).click();
		wlib.switchToTabOnTitle(driver, "module=Contacts&action");
		cnc.getSaveBtn().click();
		System.out.println("executed successfully");
		
		
		
		
		
//	//	String parentwindow = driver.getWindowHandle();
//		Thread.sleep(2000);
//		driver.findElement(By.name("user_name")).sendKeys(userName);
//		driver.findElement(By.name("user_password")).sendKeys(passWord);
//		driver.findElement(By.id("submitButton")).click();
//
//		driver.findElement(By.linkText("Organizations")).click();
//		driver.findElement(By.xpath("//img[@src='themes/softed/images/btnL3Add.gif']")).click();
//		driver.findElement(By.name("accountname")).sendKeys(orgname);
//		driver.findElement(By.id("phone")).sendKeys(contactlastname);
//		
//		driver.findElement(By.name("ship_street")).sendKeys("wer");
//		
//		
//		
//		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
//		
////		Actions action = new Actions(driver);
////		action.moveToElement(driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"))).perform();
////		driver.findElement(By.linkText("Sign Out")).click();
//		
//		
//	
//		String headerInfo = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
//		if(headerInfo.contains(orgname)) {
//			System.out.println(orgname  + " header verified");		
//			}else {
//				System.out.println(orgname  + " header not verified");
//				
//			}
//		
//		driver.findElement(By.linkText("Contacts")).click();
//		driver.findElement(By.xpath("//img[@src='themes/softed/images/btnL3Add.gif']")).click();
//		driver.findElement(By.name("lastname")).sendKeys(contactlastname);
//		
//	//	driver.findElement(By.name("ship_street")).sendKeys("wer");
//		driver.findElement(By.xpath("//input[@name='account_name']/following-sibling::img")).click();
//		
//		//switch to child window
////		Set<String> childwindow = driver.getWindowHandles();
////		String expected ="module=Accounts&action";
////		for(String window : childwindow) {
////		 driver.switchTo().window(window);
////		 if(driver.getTitle().contains(expected)) {
////			 break;
////		 }	
////		}
//		 wlib.switchToTabOnTitle(driver, "module=Accounts&action");
//		
//		
//		driver.findElement(By.id("search_txt")).sendKeys(orgname);
//		driver.findElement(By.name("search")).click();
//		driver.findElement(By.xpath("//a[text()='"+orgname+"']")).click();
//		
//		//switch to parent window
//	//	driver.switchTo().window(parentwindow);
//		wlib.switchToTabOnTitle(driver, "action=DetailView&module");
//		
//		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
//		
//		
//		String headerInfo1 = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
//		if(headerInfo1.contains(contactlastname)) {
//			System.out.println(contactlastname  + " header verified");		
//			}else {
//				System.out.println(contactlastname  + " header not verified");
//				
//			}
//		
//		//verify header orgname info expected result
//		String actOrgname = driver.findElement(By.id("mouseArea_Organization Name")).getText();
//		if(actOrgname.contains(orgname)) {
//			System.out.println(orgname  + " information is created");		
//			}else {
//				System.out.println(orgname  + " information is not created");
//				
//			}
		
		

	}

}
