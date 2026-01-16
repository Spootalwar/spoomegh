package com.comcast.crm.objectrepositoryutility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class OrganizationsPage {

	  WebDriver driver;
	  public OrganizationsPage(WebDriver driver){
		  this.driver=driver;
		  PageFactory.initElements(driver, this);
	  }
	  
	  @FindBy(xpath = "//img[@alt='Create Organization...']")
	  private WebElement createorg;
	  
	  @FindBy(name="search_text")
	  private WebElement searchEdt;
	  
	  @FindBy(id="bas_searchfield")
	  private WebElement searchDP;
	  
	  @FindBy(name="submit")
	  private WebElement searchbtn;	  

	  public WebElement getSearchEdt() {
		return searchEdt;
	}
	  
	  public WebElement getSearchDP() {
		  return searchDP;
	  }

	  public WebElement getSearchbtn() {
		  return searchbtn;
	  }

	  public WebElement getCreateorg() {
		  return createorg;
	  }
	  
	  public void createAndSearch(String orgname , String OrganizationName) {
		  searchEdt.sendKeys(orgname);
		  Select sel=new Select(searchDP);
		  sel.selectByVisibleText(OrganizationName);
		  searchbtn.click();
	  }
	 
}	  
	  
	  
	  
	  


