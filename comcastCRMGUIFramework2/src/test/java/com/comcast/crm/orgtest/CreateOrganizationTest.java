package com.comcast.crm.orgtest;


import java.io.IOException;
import java.time.Duration;

import java.util.Random;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.PageFactory;

import com.comcast.crm.generic.fileutility.ExcelUtility;
import com.comcast.crm.generic.fileutility.FileUtility;
import com.comcast.crm.generic.webdriverutility.JavaUtility;
import com.comcast.crm.objectrepositoryutility.CreatingNewOrganizationPage;
import com.comcast.crm.objectrepositoryutility.HomePage;
import com.comcast.crm.objectrepositoryutility.LoginPage;
import com.comcast.crm.objectrepositoryutility.OrganizationInfoPage;
import com.comcast.crm.objectrepositoryutility.OrganizationsPage;

public class CreateOrganizationTest {

	public static void main(String[] args) throws IOException, InterruptedException {
		
		  FileUtility flib = new FileUtility();	
	        ExcelUtility elib = new ExcelUtility();
	       JavaUtility jlib = new JavaUtility();
	       String browser = flib.getDataFromPrpertiesFile("browser");
			String url = flib.getDataFromPrpertiesFile("url");
			
			String userName = flib.getDataFromPrpertiesFile("username");
			String passWord = flib.getDataFromPrpertiesFile("password");

	
		String orgname =elib.getDataFromExcel("org", 1, 2) + jlib.getRandomNumber();
		

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
		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
		driver.get(url);
		
		LoginPage lp = new LoginPage(driver); 
		lp.loginToapp(url, userName, passWord);
		
		HomePage op = new HomePage(driver);
		op.getOrglink().click();
		
		OrganizationsPage orp = new OrganizationsPage(driver);
		orp.getCreateorg().click();
		
		CreatingNewOrganizationPage cnp = new CreatingNewOrganizationPage(driver);
		cnp.createOrgaName(orgname);
		
		OrganizationInfoPage onp = new OrganizationInfoPage(driver);
		String actorgname = onp.getHeadermsg().getText();
		if(actorgname.contains(orgname)) {
			System.out.println(orgname + " name is verified");
		}else {
			System.out.println(orgname + " name is not verified");
		}
		
		op.Logout();
	}
		

//		driver.findElement(By.linkText("Organizations")).click();
//		driver.findElement(By.xpath("//img[@src='themes/softed/images/btnL3Add.gif']")).click();
//		driver.findElement(By.name("accountname")).sendKeys(orgname);
//		
//		driver.findElement(By.name("ship_street")).sendKeys("wer");
//		
//
//		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
//		
//		
//		//verify header msg expected result
//		String headerInfo = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
//		if(headerInfo.contains(orgname)) {
//			System.out.println(orgname  + "header verified");		
//			}else {
//				System.out.println(orgname  + "header not verified");
//				
//			}
//		
//		//verify header orgname info expected result
//		String actOrgname = driver.findElement(By.id("dtlview_Organization Name")).getText();
//		if(actOrgname.contains(orgname)) {
//			System.out.println(orgname  + "information is created");		
//			}else {
//				System.out.println(orgname  + "information is not created");
//				
//			}
		
	}


	


