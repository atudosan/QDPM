package reusableComponents;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import testBase.ExtentReportSetup;
import testBase.DriverFactory;
import testBase.ExtentFactory;

/* To Implement TestListeners we need to follow next steps:
 * 1. Create Extent Report Object(one time)
 * 2. Start Test Case (@Test)
 * 3. Create ExtentTest Object (per each test)
 * 4. Log messages/errors/screenshoots - log in report
 * 5. Check if passed/failed/skipped -  log in report
 * 6. Close ExtentReport - flush() * 
 * */

//!!! To invoke our listeners we should stipulate them in our testng.xml
public class ListenersImplementation implements ITestListener{
	
	static ExtentReports report;
	ExtentTest test;
	
	@Override
	//3. Create ExtentTest Object (per each test)
	public void onTestStart(ITestResult result) {
		 test = report.createTest(result.getMethod().getMethodName());
		 ExtentFactory.getInstance().setExtent(test);
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		ExtentFactory.getInstance().getExtent().log(Status.PASS, "Test Case "+result.getMethod().getMethodName()+" is Passed");
		ExtentFactory.getInstance().removeExtentObject();
	}

	@Override
	public void onTestFailure(ITestResult result) {
		ExtentFactory.getInstance().getExtent().log(Status.FAIL, "Test Case "+result.getMethod().getMethodName()+" is Failed");
		ExtentFactory.getInstance().getExtent().log(Status.FAIL, result.getThrowable());
		
		//screenshot for fail test
		File src = ((TakesScreenshot)DriverFactory.getInstance().getDriver()).getScreenshotAs(OutputType.FILE);
		SimpleDateFormat format = new SimpleDateFormat("dd-MM-yy HH-mm-ss");
		Date date = new Date();
		String actualDate = format.format(date);
		String screenshotPath = System.getProperty("user.dir")+"/Reports/Screenshots/"
				+actualDate+".jpeg";
		File dest = new File(screenshotPath);
		try {
			FileUtils.copyFile(src, dest);
		} catch (IOException e) {
			e.printStackTrace();
		}
		//attach screenshot to the logs
		ExtentFactory.getInstance().getExtent().addScreenCaptureFromPath(screenshotPath, "Test case failor screenshot");
		//remove extentObject
		ExtentFactory.getInstance().removeExtentObject();
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		ExtentFactory.getInstance().getExtent().log(Status.SKIP, "Test Case "+result.getMethod().getMethodName()+" is Skipped");
		ExtentFactory.getInstance().removeExtentObject();
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		
	}

	@Override
	public void onTestFailedWithTimeout(ITestResult result) {
		
	}

	@Override
	//1. Create Extent Report Object(one time)
	public void onStart(ITestContext context) {
		try {
			report = ExtentReportSetup.extentReportsSetUp();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	@Override
	//close ExtentReport by using flush() method
	public void onFinish(ITestContext context) { 
		report.flush();
	}
		
}
