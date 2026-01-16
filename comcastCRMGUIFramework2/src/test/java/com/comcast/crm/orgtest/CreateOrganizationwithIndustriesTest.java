package com.comcast.crm.orgtest;


import java.io.IOException;
import java.time.Duration;
import java.util.Random;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

import com.comcast.crm.generic.fileutility.ExcelUtility;
import com.comcast.crm.generic.fileutility.FileUtility;
import com.comcast.crm.generic.webdriverutility.JavaUtility;
import com.comcast.crm.generic.webdriverutility.WebDriverUtility;
import com.comcast.crm.objectrepositoryutility.CreatingNewOrganizationPage;
import com.comcast.crm.objectrepositoryutility.HomePage;
import com.comcast.crm.objectrepositoryutility.LoginPage;
import com.comcast.crm.objectrepositoryutility.OrganizationInfoPage;
import com.comcast.crm.objectrepositoryutility.OrganizationsPage;

public class CreateOrganizationwithIndustriesTest {

	public static void main(String[] args) throws IOException, InterruptedException {
		
		  FileUtility flib = new FileUtility();	
	        ExcelUtility elib = new ExcelUtility();
	        JavaUtility jlib = new JavaUtility();
	        WebDriverUtility wlib = new WebDriverUtility();
	        
			String url = flib.getDataFromPrpertiesFile("url");
			String browser = flib.getDataFromPrpertiesFile("browser");
			String userName = flib.getDataFromPrpertiesFile("username");
			String passWord = flib.getDataFromPrpertiesFile("password");

		String orgname = elib.getDataFromExcel("org", 4, 2) + jlib.getRandomNumber();
		String industry =elib.getDataFromExcel("org", 4, 3);
		String type = elib.getDataFromExcel("org", 4, 4) ;
		

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
	    hp.getOrglink().click();
	    
	    OrganizationsPage op = new OrganizationsPage(driver);
	    op.getCreateorg().click();
	    
	    Thread.sleep(3000);   
	    
	    CreatingNewOrganizationPage cop = new CreatingNewOrganizationPage(driver);
	    cop.createOrgaName(orgname, industry, type);
	    OrganizationInfoPage onp = new OrganizationInfoPage(driver);
	    String actindustry = onp.getIndustrymsg().getText();
	    String acttype = onp.getTypemsg().getText();
	    System.out.println(industry);
	    System.out.println(actindustry);
	    if(actindustry.contains(industry)) {
	    	System.out.println(industry+ " is verified succefully");
	    }else {
	    	System.out.println(industry +" is not verified");
	    }
	    if(acttype.contains(type)) {
	    	System.out.println(type + " is verified succefully");
	    }else {
	    	System.out.println(type +" is not verified");
	    }
	    
	    
	    
//		driver.findElement(By.linkText("Organizations")).click();
//		driver.findElement(By.xpath("//img[@src='themes/softed/images/btnL3Add.gif']")).click();
//		driver.findElement(By.name("accountname")).sendKeys(orgname);
//		
//		WebElement wbsel = driver.findElement(By.name("industry"));
//		Select sel = new Select(wbsel);
//		sel.selectByVisibleText("Energy");
//		
//		
//		
//		WebElement websel1 = driver.findElement(By.name("accounttype"));
//		Select sel1 = new Select(websel1);
//		sel1.selectByVisibleText("Press");
//		
//		
//		
//		driver.findElement(By.name("ship_street")).sendKeys("wer");
//		
//		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
//		
////		Actions action = new Actions(driver);
////		action.moveToElement(driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"))).perform();
////		driver.findElement(By.linkText("Sign Out")).click();
//		
//		
//		String actindustries = driver.findElement(By.id("dtlview_Industry")).getText();
//		System.out.println(actindustries);
//		if(actindustries.equals(industry)) {
//			System.out.println(industry  + "information verified");		
//			}else {
//				System.out.println(industry  + "information not verified");
//				
//			}
//		
//		
//		String acttype = driver.findElement(By.id("dtlview_Type")).getText();
//		if(acttype.contains(type)) {
//			System.out.println(type  + "information is verified");		
//			}else {
//				System.out.println(type  + "information is not verified");
//				
//			}
//		
//	} 


	}
}


