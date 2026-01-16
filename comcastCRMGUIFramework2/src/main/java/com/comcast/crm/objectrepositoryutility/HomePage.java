package com.comcast.crm.objectrepositoryutility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {
	
	  WebDriver driver;
	  public HomePage(WebDriver driver){
		  this.driver=driver;
		  PageFactory.initElements(driver, this);
	  }

	@FindBy(partialLinkText = "Organizations")
	private WebElement orglink;
	
	@FindBy(xpath = "//a[text()='Products']")
	private WebElement productlink;

	@FindBy(partialLinkText = "Contacts")
	private WebElement contactlink;
	
	@FindBy(partialLinkText = "Compaigns")
	private WebElement campaignlink;
	
	@FindBy(partialLinkText = "More")
	private WebElement Morelink;
	
	@FindBy(xpath="//img[@src='themes/softed/images/user.PNG']")
    private WebElement adminImg;
	
	@FindBy(linkText="Sign Out")
	private WebElement signOutLink;
	
	
	public WebElement getProductlink() {
		return productlink;
	}
	
	public WebElement getOrglink() {
		return orglink;
	}

	public WebElement getConlink() {
		return contactlink;
	}
	
	public WebElement getCampaignlink() {
		return campaignlink;
	}

	public WebElement getMorelink() {
		return Morelink;
	}

	public void navigateToCampaign() {
		Actions act = new Actions(driver);
		act.moveToElement(Morelink).perform();
		campaignlink.click();
	}
	
	public void Logout() {
	Actions act = new Actions(driver);
	act.moveToElement(adminImg).perform();
	signOutLink.click();
	}
	
	
	
	
	
}
