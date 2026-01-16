package com.comcast.crm.orgtest;

import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.comcast.crm.generic.fileutility.ExcelUtility;
import com.comcast.crm.generic.fileutility.FileUtility;
import com.comcast.crm.generic.webdriverutility.JavaUtility;
import com.comcast.crm.generic.webdriverutility.WebDriverUtility;
import com.comcast.crm.objectrepositoryutility.CreatingNewOrganizationPage;
import com.comcast.crm.objectrepositoryutility.HomePage;
import com.comcast.crm.objectrepositoryutility.LoginPage;
import com.comcast.crm.objectrepositoryutility.OrganizationInfoPage;
import com.comcast.crm.objectrepositoryutility.OrganizationsPage;

public class DeleteOrgTest {

	public static void main(String[] args) throws IOException, InterruptedException {
		
		  FileUtility flib = new FileUtility();	
	        ExcelUtility elib = new ExcelUtility();
	       JavaUtility jlib = new JavaUtility();
	       WebDriverUtility wlib = new WebDriverUtility();
	       String browser = flib.getDataFromPrpertiesFile("browser");
			String url = flib.getDataFromPrpertiesFile("url");
			
			String userName = flib.getDataFromPrpertiesFile("username");
			String passWord = flib.getDataFromPrpertiesFile("password");

	
		String orgname =elib.getDataFromExcel("org", 10, 2) + jlib.getRandomNumber();
		

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
		
		//go back to organization page 
		op.getOrglink().click();
		
		//search for organization
		orp.createAndSearch(orgname, "Organization Name");
		
		driver.findElement(By.xpath("//a[text()='"+orgname+"']/../../td[8]/a[text()='del']")).click();
		wlib.switchtoAlertAndAccept(driver);
		
		//in dynamic webtable select and delete org
		
		op.Logout();
	}
}
