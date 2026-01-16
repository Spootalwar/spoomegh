package com.comcast.crm.basetest;

import java.io.IOException;
import java.sql.SQLException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;

import com.comcast.crm.generic.fileutility.DataBaseUtlity;
import com.comcast.crm.generic.fileutility.ExcelUtility;
import com.comcast.crm.generic.fileutility.FileUtility;
import com.comcast.crm.generic.webdriverutility.JavaUtility;
import com.comcast.crm.generic.webdriverutility.WebDriverUtility;
import com.comcast.crm.objectrepositoryutility.HomePage;
import com.comcast.crm.objectrepositoryutility.LoginPage;

public class BaseClass2 {

	public DataBaseUtlity dlib = new DataBaseUtlity();
	public FileUtility flib = new FileUtility();
	public ExcelUtility elib = new ExcelUtility();
	public JavaUtility jlib = new JavaUtility();
	public WebDriverUtility wlib = new WebDriverUtility();
	public WebDriver driver = null;
	
	@BeforeSuite(groups = {"smokeTest" , "regressionTest"})
	public  void configBS() throws SQLException {
		System.out.println("===connect to Db===, Report config");
		dlib.getDbConnection();
	}
	
	//@Parameters("BROWSER")
	@BeforeClass(groups = {"smokeTest" , "regressionTest"})
	//public void configBC(String browser) throws IOException
	public void configBC() throws Throwable
	{
		System.out.println("==launch the browser==");
		String BROWSER = flib.getDataFromPrpertiesFile("browser");
				//browser;
				//flib.getDataFromPrpertiesFile("browser");
		
		if (BROWSER.equals("chrome")) {
			driver = new ChromeDriver();
		} else if (BROWSER.equals("firefox")) {
			driver = new FirefoxDriver();
		} else if (BROWSER.equals("edge")) {
			driver = new EdgeDriver();
		} else {
			driver = new ChromeDriver();
		}
	}
	
	@BeforeMethod(groups = {"smokeTest" , "regressionTest"})
	public void configBM() throws IOException {
		System.out.println("=login=");
		String url = flib.getDataFromPrpertiesFile("url");
		String username = flib.getDataFromPrpertiesFile("username");
		String password = flib.getDataFromPrpertiesFile("password");
		LoginPage lp = new LoginPage(driver);
		lp.loginToapp(url, username, password);
	}
	
	@AfterMethod(groups = {"smokeTest" , "regressionTest"})
	public void configAM() {
		System.out.println("=logout=");
		HomePage hp = new HomePage(driver);
		hp.Logout();
	}
	
	@AfterClass(groups = {"smokeTest" , "regressionTest"})
	public void configAC()  {
		System.out.println("==close the browser==");
		driver.quit();
	}
	
	@AfterSuite(groups = {"smokeTest" , "regressionTest"})
	public  void configAS() throws SQLException {
		dlib.closeDbconnection();
		System.out.println("===close Db=== , Report backUP ");
	}
	
	
}
