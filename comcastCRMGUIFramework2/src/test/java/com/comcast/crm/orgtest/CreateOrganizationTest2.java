package com.comcast.crm.orgtest;


import java.io.IOException;
import java.time.Duration;

import java.util.Random;

import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import com.comcast.crm.basetest.BaseClass;
import com.comcast.crm.generic.fileutility.ExcelUtility;
import com.comcast.crm.generic.fileutility.FileUtility;
import com.comcast.crm.generic.webdriverutility.JavaUtility;
import com.comcast.crm.objectrepositoryutility.CreatingNewOrganizationPage;
import com.comcast.crm.objectrepositoryutility.HomePage;
import com.comcast.crm.objectrepositoryutility.LoginPage;
import com.comcast.crm.objectrepositoryutility.OrganizationInfoPage;
import com.comcast.crm.objectrepositoryutility.OrganizationsPage;

public class CreateOrganizationTest2 extends BaseClass {

	@Test
	public void createOrg() throws EncryptedDocumentException, IOException {

	
		String orgname =elib.getDataFromExcel("org", 1, 2) + jlib.getRandomNumber();
		
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
	}	
		@Test
		public void createOrgWithIndustries() throws EncryptedDocumentException, IOException, InterruptedException {

			String orgname = elib.getDataFromExcel("org", 4, 2) + jlib.getRandomNumber();
			String industry =elib.getDataFromExcel("org", 4, 3);
			String type = elib.getDataFromExcel("org", 4, 4) ;
			

		
		    
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
		    
		
		}
		
		@Test
		public void createOrgWithPhoneNum() throws EncryptedDocumentException, IOException {
			String orgname =elib.getDataFromExcel("org", 7, 2)+ jlib.getRandomNumber() ;
			String phonenum = elib.getDataFromExcel("org", 7, 3);
			
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


	


