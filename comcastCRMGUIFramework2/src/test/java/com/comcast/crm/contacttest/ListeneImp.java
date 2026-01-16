package com.comcast.crm.contacttest;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.comcast.crm.basetest.BaseClass3listner;





public class ListeneImp implements ITestListener, ISuiteListener {

	@Override
	public void onStart(ISuite suite) {
	
	}

	@Override
	public void onFinish(ISuite suite) {
	
	}

	@Override
	public void onTestStart(ITestResult result) {
	
		System.out.println(result.getMethod().getMethodName());
	}

	@Override
	public void onTestSuccess(ITestResult result) {
	
		System.out.println(result.getMethod().getMethodName());
	}

	@Override
	public void onTestFailure(ITestResult result) {
		String testname = result.getMethod().getMethodName();
		TakesScreenshot edriver = (TakesScreenshot)BaseClass3listner.sdriver;
		//EventFiringWebDriver edriver = new EventFiringWebDriver(BaseClass3listner.sdriver);
	    File src = edriver.getScreenshotAs(OutputType.FILE);   
	    String time = new Date().toString().replace("", "_").replace(":", "_");
	    File dst  = new File("./screenshot/"+testname+""+""+time+".png");
	    try {
			FileUtils.copyFile(src, dst);
		} catch (IOException e) {
			
			e.printStackTrace();
		}
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		ITestListener.super.onTestFailedButWithinSuccessPercentage(result);
	}

	@Override
	public void onTestFailedWithTimeout(ITestResult result) {
		// TODO Auto-generated method stub
		ITestListener.super.onTestFailedWithTimeout(result);
	}

	@Override
	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
		ITestListener.super.onStart(context);
	}

	@Override
	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub
		ITestListener.super.onFinish(context);
	}
	

}
