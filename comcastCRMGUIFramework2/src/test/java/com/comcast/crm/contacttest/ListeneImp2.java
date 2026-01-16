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

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.comcast.crm.basetest.BaseClass3listner;
import com.comcast.crm.generic.webdriverutility.UtilityClassObject;





public class ListeneImp2 implements ITestListener, ISuiteListener {
	public ExtentReports report;
	public ExtentTest test;
	@Override
	public void onStart(ISuite suite) {
	System.out.println("Report Configuration");
	ExtentSparkReporter spark = new ExtentSparkReporter("./AdvanceReport/report.html");
	spark.config().setDocumentTitle("CRM Test Suite Results");
	spark.config().setReportName("CRM Report");
	spark.config().setTheme(Theme.DARK);
	//add environment information & create test
	report = new ExtentReports();
	report.attachReporter(spark);
	report.setSystemInfo("OS","windows-10");
	report.setSystemInfo("BROWSER","CHROME-100");
	}

	@Override
	public void onFinish(ISuite suite) {
		report.flush();
	}

	@Override
	public void onTestStart(ITestResult result) {
	
		System.out.println(result.getMethod().getMethodName());
	    test = report.createTest(result.getMethod().getMethodName());
	    UtilityClassObject.setTest(test);
	    test.log(Status.INFO, result.getMethod().getMethodName()+"===STARTED===");
	}

	@Override
	public void onTestSuccess(ITestResult result) {
	
		System.out.println(result.getMethod().getMethodName());
		test.log(Status.PASS, result.getMethod().getMethodName()+"===COMPLETED===");
	}

	@Override
	public void onTestFailure(ITestResult result) {
		String testname = result.getMethod().getMethodName();
		TakesScreenshot edriver = (TakesScreenshot)BaseClass3listner.sdriver;
		String filepath = edriver.getScreenshotAs(OutputType.BASE64);  
		String time=new Date().toString().replace(" ", "_").replace(" ", "_");
        test.addScreenCaptureFromBase64String(filepath , testname+"_"+time);
        test.log(Status.FAIL, result.getMethod().getMethodName()+"===FAILED===");
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
		
	}
	

}
