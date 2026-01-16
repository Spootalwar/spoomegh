package Assignment;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;

import com.comcast.crm.generic.fileutility.ExcelUtility;
import com.comcast.crm.generic.fileutility.FileUtility;

public class MyntraTest {
	@Test
	public void myntrSlider() throws InterruptedException, Throwable
	{
		ChromeOptions opt=new ChromeOptions();
		opt.addArguments("--disable-notifications");
		WebDriver driver=new ChromeDriver(opt);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(6));
		driver.get("https://www.myntra.com/men-tshirts");
		Actions a=new Actions(driver);
		WebElement slider = driver.findElement(By.name("discount-product"));
		Thread.sleep(2000);
		a.scrollToElement(slider).perform();
		WebElement slideright = driver.findElement(By.xpath("//div[@class='slider-thumbDot']/parent::div[@id='rootRailThumbRight']"));
		Thread.sleep(2000);
		a.clickAndHold(slideright).moveByOffset(-100, 0).release(slideright).build().perform();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//span[@data-colorhex='blue']/../div[@class='common-checkboxIndicator']")).click();
	     List<WebElement> prodname = driver.findElements(By.xpath("//li[@class='product-base']/descendant::h3")); 
	    int count=prodname.size();
	    for(int i=0;i<count;i++)
	    {
	     System.out.println(prodname.get(i).getText());
	    }
	   List<WebElement> price = driver.findElements(By.xpath("//span[@class='product-discountedPrice']"));
	   for(int j=0;j<count;j++)
	    {
	     System.out.println(price.get(j).getText());
	    }
		       
		     
	}

}
