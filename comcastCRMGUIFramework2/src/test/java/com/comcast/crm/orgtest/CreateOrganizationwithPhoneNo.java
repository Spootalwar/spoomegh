package com.comcast.crm.orgtest;


import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import java.util.Random;

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
import com.comcast.crm.generic.webdriverutility.JavaUtility;
import com.comcast.crm.objectrepositoryutility.CreatingNewOrganizationPage;
import com.comcast.crm.objectrepositoryutility.HomePage;
import com.comcast.crm.objectrepositoryutility.LoginPage;
import com.comcast.crm.objectrepositoryutility.OrganizationInfoPage;
import com.comcast.crm.objectrepositoryutility.OrganizationsPage;

public class CreateOrganizationwithPhoneNo {

	public static void main(String[] args) throws IOException, InterruptedException {
		
		  FileUtility flib = new FileUtility();	
	        ExcelUtility elib = new ExcelUtility();
	        JavaUtility jlib = new JavaUtility();
	        
			String url = flib.getDataFromPrpertiesFile("url");
			String browser = flib.getDataFromPrpertiesFile("browser");
			String userName = flib.getDataFromPrpertiesFile("username");
			String passWord = flib.getDataFromPrpertiesFile("password");

		
		String orgname =elib.getDataFromExcel("org", 7, 2)+ jlib.getRandomNumber() ;
		String phonenum = elib.getDataFromExcel("org", 7, 3);
	
		
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
		
		LoginPage lp = new LoginPage(driver);
		lp.loginToapp(url, userName, passWord);
		
		HomePage hp = new HomePage(driver);
		hp.getOrglink();
		
		OrganizationsPage op = new OrganizationsPage(driver);
		op.getCreateorg().click();
		
		CreatingNewOrganizationPage cnp= new CreatingNewOrganizationPage(driver);
		cnp.createOrgaNamewithphone(orgname, phonenum);
		
		OrganizationInfoPage onp = new OrganizationInfoPage(driver);
		String actphone = onp.getPhonemsg().getText();
		if(actphone.contains(phonenum)) {
			System.out.println(phonenum + " is verified");
		}else {
			System.out.println(phonenum +" is not verified");
		}
		
		
		
//		driver.findElement(By.name("user_name")).sendKeys(userName);
//		driver.findElement(By.name("user_password")).sendKeys(passWord);
//		driver.findElement(By.id("submitButton")).click();
//
//		driver.findElement(By.linkText("Organizations")).click();
//		driver.findElement(By.xpath("//img[@src='themes/softed/images/btnL3Add.gif']")).click();
//		driver.findElement(By.name("accountname")).sendKeys(orgname);
//		driver.findElement(By.id("phone")).sendKeys(phonenum);
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
//		String actphonenumber = driver.findElement(By.id("dtlview_Phone")).getText();
//		if(actphonenumber.contains(phonenum)) {
//			System.out.println(phonenum  + " information verified");		
//			}else {
//				System.out.println(phonenum  + " information not verified");
//				
//			}
		
		
		
		
	}


	}


