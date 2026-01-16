package com.comcast.crm.objectrepositoryutility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OrganizationInfoPage {
	
	  WebDriver driver;
	  public OrganizationInfoPage(WebDriver driver){
		  this.driver=driver;
		  PageFactory.initElements(driver, this);
	  }

	

	@FindBy(className ="dvHeaderText")
	private WebElement headermsg;
	
	@FindBy(id="dtlview_Industry")
	private WebElement industrymsg;
	
	@FindBy(id="dtlview_Type")
	private WebElement typemsg;
	
	@FindBy(id="dtlview_Phone")
	private WebElement phonemsg;
	
	public WebElement getPhonemsg() {
		return phonemsg;
	}

	public WebElement getIndustrymsg() {
		return industrymsg;
	}

	public WebElement getTypemsg() {
		return typemsg;
	}

	public WebElement getHeadermsg() {
		return headermsg;
	}
	
	
}
