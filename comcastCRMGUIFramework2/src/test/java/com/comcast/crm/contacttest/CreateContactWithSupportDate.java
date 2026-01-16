package com.comcast.crm.contacttest;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Calendar;
import java.util.Date;
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
import com.comcast.crm.objectrepositoryutility.CreateNewContact;
import com.comcast.crm.objectrepositoryutility.HomePage;
import com.comcast.crm.objectrepositoryutility.LoginPage;

public class CreateContactWithSupportDate {

	public static void main(String[] args) throws InterruptedException, EncryptedDocumentException, IOException {
		

		  FileUtility flib = new FileUtility();	
	        ExcelUtility elib = new ExcelUtility();
	        JavaUtility jlib = new JavaUtility();
	        
			String url = flib.getDataFromPrpertiesFile("url");
			String browser = flib.getDataFromPrpertiesFile("browser");
			String userName = flib.getDataFromPrpertiesFile("username");
			String passWord = flib.getDataFromPrpertiesFile("password");

	
		String lastname = elib.getDataFromExcel("Sheet3", 4, 2)+jlib.getRandomNumber();
	

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
		
		LoginPage lp = new LoginPage(driver);
		lp.loginToapp(url, userName, passWord);
		
		HomePage hp = new HomePage(driver);
		hp.getConlink().click();
		
		CreateNewContact cnc = new CreateNewContact(driver);
		cnc.createNewContact1(lastname);
		
		cnc.startandend(jlib.getSystemDateYYYYDDMM(), jlib.getRequiredDateYYYYDDMM(5));
		
//		Thread.sleep(2000);
//		driver.findElement(By.name("user_name")).sendKeys(userName);
//		driver.findElement(By.name("user_password")).sendKeys(passWord);
//		driver.findElement(By.id("submitButton")).click();
//
//		driver.findElement(By.linkText("Contacts")).click();
//		driver.findElement(By.xpath("//img[@src='themes/softed/images/btnL3Add.gif']")).click();
//		
//		String startdate = jlib.getSystemDateYYYYDDMM();
//		String enddate = jlib.getRequiredDateYYYYDDMM(30);
//		
//		driver.findElement(By.name("lastname")).sendKeys(lastname);
//		
//		driver.findElement(By.name("support_start_date")).clear();
//		driver.findElement(By.name("support_start_date")).sendKeys(startdate);	
//		driver.findElement(By.name("support_end_date")).clear();
//		driver.findElement(By.name("support_end_date")).sendKeys(enddate);
//		
//		
//
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
//		String actstartdate = driver.findElement(By.id("dtlview_Support Start Date")).getText();
//		if(actstartdate.contains(startdate)) {
//			System.out.println(startdate  + " date is verified");		
//			}else {
//				System.out.println(startdate  + " date is not verified");
//				
//			}
//		String actenddate = driver.findElement(By.id("dtlview_Support Start Date")).getText();
//		if(actenddate.contains(enddate)) {
//			System.out.println(enddate  + " date is verified");		
//			}else {
//				System.out.println(enddate  + " date is not verified");
//				
//			}
		
	}

}
