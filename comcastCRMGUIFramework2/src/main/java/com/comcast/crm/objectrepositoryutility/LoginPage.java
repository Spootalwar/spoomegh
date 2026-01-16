package com.comcast.crm.objectrepositoryutility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.comcast.crm.generic.webdriverutility.WebDriverUtility;

public class LoginPage extends WebDriverUtility {   //Rule-1 create a separate java class
	                       // Rule-2 Object Creation
	  WebDriver driver;
	  public LoginPage(WebDriver driver){
		  this.driver=driver;
		  PageFactory.initElements(driver, this);
	  }
	
	  @FindBy(name="user_name")
	  private WebElement usernameEdt;
	  
	  @FindBy(name="user_password")
	  private WebElement passwordEdt;
	  
	  @FindBy(id="submitButton")
	  private WebElement loginbtn;
	                           //Rule-3 Object Initialization
	  //Rule-4 Object Encapsulation

	  public WebElement getUsernameEdt() {
		  return usernameEdt;
	  }

	  public WebElement getPasswordEdt() {
		  return passwordEdt;
	  }

	  public WebElement getLoginbtn() {
		  return loginbtn;
	  }
	  
	 // Rule-5 provide action
	  
	  public void loginToapp(String url ,String username , String password) {
		  waitForPageToLoad(driver);
		  driver.manage().window().maximize();
		  driver.get(url);
		  usernameEdt.sendKeys(username);
		  passwordEdt.sendKeys(password);
		  loginbtn.click();
	  }
	  
	
}
