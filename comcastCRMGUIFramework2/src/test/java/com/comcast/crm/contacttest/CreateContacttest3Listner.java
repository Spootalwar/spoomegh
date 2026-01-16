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
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.comcast.crm.basetest.BaseClass;
import com.comcast.crm.basetest.BaseClass3listner;
import com.comcast.crm.generic.fileutility.ExcelUtility;
import com.comcast.crm.generic.fileutility.FileUtility;
import com.comcast.crm.generic.webdriverutility.JavaUtility;
import com.comcast.crm.generic.webdriverutility.UtilityClassObject;
import com.comcast.crm.objectrepositoryutility.CreateContactInfo;
import com.comcast.crm.objectrepositoryutility.CreateNewContact;
import com.comcast.crm.objectrepositoryutility.CreatingNewOrganizationPage;
import com.comcast.crm.objectrepositoryutility.HomePage;
import com.comcast.crm.objectrepositoryutility.LoginPage;
import com.comcast.crm.objectrepositoryutility.WindowHandles;

@Listeners(com.comcast.crm.contacttest.ListeneImp2.class)
public class CreateContacttest3Listner extends BaseClass3listner{
	
	@Test(groups = "smokeTest")
	public void createContactTest() throws EncryptedDocumentException, IOException {
		
		UtilityClassObject.getTest().log(Status.INFO, "read data from excel");
		String lastname = elib.getDataFromExcel("Sheet3", 1, 2)+jlib.getRandomNumber();
	
		UtilityClassObject.getTest().log(Status.INFO, "home page is displayed");
		HomePage hp = new HomePage(driver);
		hp.getConlink().click();
		
		UtilityClassObject.getTest().log(Status.INFO, "create contact");
		CreateNewContact cnc= new CreateNewContact(driver);
		cnc.createNewContact(lastname);
		
		UtilityClassObject.getTest().log(Status.INFO, "vrify the contact name");
		CreateContactInfo cci = new CreateContactInfo(driver);
		String actlastname = cci.getHeaderlastnamemsg().getText();
		boolean status = actlastname.contains(lastname);
		Assert.assertEquals(status, true);
		

	}
	
	@Test(groups = "regressionTest")
	public void createContactWithSupportDate() throws EncryptedDocumentException, IOException {
		 String lastname = elib.getDataFromExcel("Sheet3", 4, 2)+jlib.getRandomNumber();	
			HomePage hp = new HomePage(driver);
			hp.getConlink().click();
			CreateNewContact cnc = new CreateNewContact(driver);
			cnc.createNewContact1(lastname);
			cnc.startandend(jlib.getSystemDateYYYYDDMM(), jlib.getRequiredDateYYYYDDMM(5));
			
			
	}
	
	
	@Test(groups = "regressionTest")
	public void createContactWithOrgTest() throws InterruptedException, EncryptedDocumentException, IOException {
		String orgname =elib.getDataFromExcel("Sheet3", 7, 2) + jlib.getRandomNumber();
		String contactlastname1 = elib.getDataFromExcel("Sheet3", 7, 3);
		
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
	
	}

	
	
}
