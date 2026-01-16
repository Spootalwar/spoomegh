package com.comcast.crm.contacttest;

import java.io.IOException;
import java.time.Duration;
import java.util.Random;

import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.comcast.crm.generic.fileutility.ExcelUtility;
import com.comcast.crm.generic.fileutility.FileUtility;
import com.comcast.crm.generic.webdriverutility.JavaUtility;
import com.comcast.crm.objectrepositoryutility.CreateContactInfo;
import com.comcast.crm.objectrepositoryutility.CreateNewContact;
import com.comcast.crm.objectrepositoryutility.HomePage;
import com.comcast.crm.objectrepositoryutility.LoginPage;

public class CreateContacttest {

	public static void main(String[] args) throws EncryptedDocumentException, IOException, InterruptedException {
		
        FileUtility flib = new FileUtility();	
        ExcelUtility elib = new ExcelUtility();
        JavaUtility jlib = new JavaUtility();
        
		String url = flib.getDataFromPrpertiesFile("url");
		String browser = flib.getDataFromPrpertiesFile("browser");
		String userName = flib.getDataFromPrpertiesFile("username");
		String passWord = flib.getDataFromPrpertiesFile("password");

		
		String lastname = elib.getDataFromExcel("Sheet3", 1, 2)+jlib.getRandomNumber();
		
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
		Thread.sleep(2000);
		
		LoginPage lp = new LoginPage(driver);
		lp.loginToapp(url, userName, passWord);
		
		HomePage hp = new HomePage(driver);
		hp.getConlink().click();
		
		CreateNewContact cnc= new CreateNewContact(driver);
		cnc.createNewContact(lastname);
		
		CreateContactInfo cci = new CreateContactInfo(driver);
		String actlastname = cci.getHeaderlastnamemsg().getText();
		System.out.println(actlastname);
				if(actlastname.contains(lastname)) {
					System.out.println(lastname +" is verified successfully");
				}
		
//		driver.findElement(By.name("user_name")).sendKeys(userName);
//		driver.findElement(By.name("user_password")).sendKeys(passWord);
//		driver.findElement(By.id("submitButton")).click();
//
//		driver.findElement(By.linkText("Contacts")).click();
//		driver.findElement(By.xpath("//img[@src='themes/softed/images/btnL3Add.gif']")).click();
//		driver.findElement(By.name("lastname")).sendKeys(lastname);
//		
//	//	driver.findElement(By.name("ship_street")).sendKeys("wer");
//		
//
//		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
//		
//		
//		//verify header msg expected result
//		String headerInfo = driver.findElement(By.className("dvHeaderText")).getText();
//		if(headerInfo.contains(lastname)) {
//			System.out.println(lastname  + " header verified");		
//			}else {
//				System.out.println(lastname  + " header not verified");
//				
//			}
//		
//		//verify header orgname info expected result
//		String actOrgname = driver.findElement(By.id("dtlview_Last Name")).getText();
//		if(actOrgname.contains(lastname)) {
//			System.out.println(lastname  + " information is created");		
//			}else {
//				System.out.println(lastname  + " information is not created");
//				
//			}
//		
	}

}
