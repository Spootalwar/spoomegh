package com.comcast.crm.objectrepositoryutility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import com.comcast.crm.generic.webdriverutility.WebDriverUtility;

public class WindowHandles {


	WebDriver driver;
	  public WindowHandles(WebDriver driver){
		  this.driver=driver;
		  PageFactory.initElements(driver, this);
	  }
	  
	  @FindBy(xpath = "//input[@name='account_id']/following-sibling::img")
	  private WebElement contactBtn;
	  
	  @FindBy(name="search_text")
	  private WebElement searchEdt;
	  
	  @FindBy(name="search_field")
	  private WebElement searchDP;
	  
	  @FindBy(name="search")
	  private WebElement searchBtn;
	  
	  @FindBy(xpath = "//input[@title='Save [Alt+S]']")
	  private WebElement saveBtn;
	  	  
	  public WebElement getSaveBtn() {
		return saveBtn;
	}

	  public WebElement getContactBtn() {
		return contactBtn;
	}

	  public WebElement getSearchEdt() {
		  return searchEdt;
	  }
	  
	  public WebElement getSearchDP() {
		  return searchDP;
	  }

	  public WebElement getSearchBtn() {
		  return searchBtn;
	  }

	  public void window(String orgname , String OrganizationName ) {
		  contactBtn.click();
		  WebDriverUtility wlib = new WebDriverUtility();
		  wlib.switchToTabOnTitle(driver, "module=Accounts&action");
		  searchEdt.sendKeys(orgname);
		  Select sel = new Select(searchDP);
		  sel.selectByVisibleText(OrganizationName);
		  searchBtn.click();
		  
	  }
	  
	  
}
