package com.comcast.crm.objectrepositoryutility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class CreatingNewOrganizationPage {
	
	  WebDriver driver;
	  public CreatingNewOrganizationPage(WebDriver driver){
		  this.driver=driver;
		  PageFactory.initElements(driver, this);
	  }
	
	@FindBy(name="accountname")
	private WebElement orgnameEdt;
	
	@FindBy(xpath="//input[@title='Save [Alt+S]']")
	private WebElement saveBtn;
	
	@FindBy(name="ship_street")
	private WebElement shipEdt;
	
	@FindBy(name="industry")
	private WebElement industryDP;
	
	 @FindBy(name="accounttype")
	 private WebElement searchDP1;
	 
	 @FindBy(id="phone")
	 private WebElement phoneEdt;
	 
	 @FindBy(xpath = "//img[@src='themes/softed/images/btnL3Add.gif']")
	 private WebElement createorg;
	 
	 

	public WebElement getCreateorg() {
		return createorg;
	}

	public WebElement getShipEdt() {
		return shipEdt;
	}

	public WebElement getIndustryDP() {
		return industryDP;
	}

	public WebElement getOrgnameEdt() {
		return orgnameEdt;
	}
	
	 public WebElement getSearchDP1() {
			return searchDP1;
		}

	public WebElement getSaveBtn() {
		return saveBtn;
	}
	
	public void createOrgaName(String orgname) {
		createorg.click();
		orgnameEdt.sendKeys(orgname);
		shipEdt.sendKeys("q");
		saveBtn.click();
	}
	
	public void createOrgaNamewithphone(String orgname , String phone) {
		orgnameEdt.sendKeys(orgname);
		phoneEdt.sendKeys(phone);
		shipEdt.sendKeys("q");	
		saveBtn.click();
	}
	
		
		public void createOrgaName(String orgname , String industry , String type) {
			orgnameEdt.sendKeys(orgname);
			shipEdt.sendKeys("q");
			Select sel = new Select(industryDP);
		    sel.selectByVisibleText(industry);
		    Select sel1 = new Select(searchDP1);
			  sel1.selectByVisibleText(type);
		    
			saveBtn.click();
	}
}
