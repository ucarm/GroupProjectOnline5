package com.online5.tests;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.online5.utilities.BrowserUtils;
import com.online5.utilities.ConfigurationReader;
import com.online5.utilities.Driver;

public class TestBase {
	protected WebDriver driver;
	protected Actions actions;

	protected static ExtentReports report;
	protected ExtentHtmlReporter htmlReporter;
	protected ExtentTest extentLogger;

	@BeforeTest
	public void setUpTest() {
		// actual reporter
		report = new ExtentReports();
		// System.getProperty("user.dir") ---> get the path to current project
		// test-output --> folder in the current project, will be created by testng if
		// it does not already exist
		// report.html --> name of the report file
		String filePath = System.getProperty("user.dir") + "/test-output/report.html";
		htmlReporter = new ExtentHtmlReporter(filePath);

		report.attachReporter(htmlReporter);

		report.setSystemInfo("ENV", "staging");
		report.setSystemInfo("browser", ConfigurationReader.getProperty("browser"));
		report.setSystemInfo("OS", System.getProperty("os.name"));

		htmlReporter.config().setReportName("MyFitnessPal.com Automated Test Reports");
	}

	@BeforeMethod(alwaysRun = true)
	public void setUp() {
		driver = Driver.getDriver();
		actions = new Actions(driver);
		driver.manage().timeouts().implicitlyWait(8, TimeUnit.SECONDS);
//		driver.manage().window().fullscreen();

		driver.get(ConfigurationReader.getProperty("url"));

	}

	@AfterMethod(alwaysRun = true)
	public void tearDown(ITestResult result) throws IOException {
		// checking if the test method failed
		if (result.getStatus() == ITestResult.FAILURE) {
			// get screenshot using the utility method and save the location of the screenshot
			String screenshotLocation = BrowserUtils.getScreenshot(result.getName());
			
			// capture the name of test method
			extentLogger.fail(result.getName());
			
			// add the screenshot to the report
			extentLogger.addScreenCaptureFromPath(screenshotLocation);
			
			// capture the exception thrown
			extentLogger.fail(result.getThrowable());

		} else if (result.getStatus() == ITestResult.SKIP) {
			extentLogger.skip("Test Case Skipped is " + result.getName());
		}
		Driver.closeDriver();
	}

	@AfterTest
	public void tearDownTest() {
		report.flush();
	}
}
