package com.comcast.crm.objectrepositoryutility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CreateNewContact {
	
	 WebDriver driver;
	  public CreateNewContact(WebDriver driver){
		  this.driver=driver;
		  PageFactory.initElements(driver, this);
	  }
	  
	  @FindBy(xpath = "//img[@src='themes/softed/images/btnL3Add.gif']")
	  private WebElement createcon;
	  
	  @FindBy(name="lastname")
	  private WebElement lastnameEdt;
	  
	  @FindBy(xpath = "//input[@title='Save [Alt+S]']")
	  private WebElement saveBtn;
	  
	  @FindBy(xpath = "//input[@name='account_id']/following-sibling::img")
	  private WebElement createBtn;
	  
	  @FindBy(id="search_txt")
	  private WebElement searchEdt;
	  
	  @FindBy(name="search_field")
	  private WebElement searchDP;
	  
	  @FindBy(xpath = "//input[@type='button']")
	  private WebElement searchBtn;
	  
	  @FindBy(linkText = "Contacts")
	  private WebElement contactlink;
	  
	  @FindBy(name="support_start_date")
	  private WebElement startdate;
	  
	  @FindBy(name="support_end_date")
	  private WebElement enddate;
	  
	  public WebElement getStartdate() {
		return startdate;
	}

	  public WebElement getEnddate() {
		  return enddate;
	  }

	  public WebElement getContactlink() {
		return contactlink;
	}

	  public WebElement getSearchEdt() {
		return searchEdt;
	}

	  public WebElement getSearchDP() {
		  return searchDP;
	  }

	  public WebElement getCreateBtn() {
		return createBtn;
	}

	  public WebElement getSaveBtn() {
		return saveBtn;
	}

	  public WebElement getCreatecon() {
		  return createcon;
	  }

	  public WebElement getLastnameEdt() {
		  return lastnameEdt;
	  }
	  
	  public void createNewContact(String lname) {
		  createcon.click();
		  lastnameEdt.sendKeys(lname);
		  saveBtn.click();
		  
	  }
	  
	  public void createNewContact1(String lname) {
		  createcon.click();
		  lastnameEdt.sendKeys(lname);
		
		  
	  }

	  public WebElement getSearchBtn() {
		  return searchBtn;
	  }
	  
	  public void startandend(String startdate1 , String enddate1) {
		  startdate.clear();
		 startdate.sendKeys(startdate1);
		  enddate.clear();
		 enddate.sendKeys(enddate1);
	  }
	  
	 
}
