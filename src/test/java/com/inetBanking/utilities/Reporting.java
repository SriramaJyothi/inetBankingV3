package com.inetBanking.utilities;


import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;
import org.testng.annotations.BeforeTest;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;

//listener class used to generate extent report

public class Reporting extends TestListenerAdapter {

	public ExtentHtmlReporter htmlReporter;
	public ExtentReports extent;
	public ExtentTest logger;

	@BeforeTest
	public void onStart(ITestContext testContext) {
		String timeStamp = new SimpleDateFormat("yyyy.mm.dd.HH.mm.ss").format(new Date());//time stamp
		String repName = "Test-Report-" + timeStamp + ".html";//report name

		htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir") + "/test-output/" + repName);//Specify location
		htmlReporter.loadXMLConfig(System.getProperty("user.dir") + "/extent-configure.xml");

		extent = new ExtentReports();

		extent.attachReporter(htmlReporter);

		extent.setSystemInfo("Hostname", "LocalHost");
		extent.setSystemInfo("os", "windows10");
		extent.setSystemInfo("TesterName", "jyothi");
		extent.setSystemInfo("Browser", "chrome");

		htmlReporter.config().setDocumentTitle("InetBanking Test Project");//title of report
		htmlReporter.config().setReportName("functional automation report");//name of the report
		htmlReporter.config().setTestViewChartLocation(ChartLocation.TOP);//location of the chart
		htmlReporter.config().setTheme(Theme.DARK);

	}

	public void onTestSuccess(ITestResult tr) {

		logger = extent.createTest(tr.getName());//create new entry in the report
		logger.log(Status.PASS, MarkupHelper.createLabel(tr.getName(), ExtentColor.GREEN));//send passed inf
	}

	public void onTestFailure(ITestResult tr) {

		logger = extent.createTest(tr.getName());//create nwe entry
		logger.log(Status.FAIL, MarkupHelper.createLabel(tr.getName(), ExtentColor.RED));//send failed inf

		String screenshotPath = System.getProperty("user.dir") + "\\Screenshots\\" + tr.getName() + ".png";
		File f = new File(screenshotPath);

		if (f.exists()) {
			try {
				logger.log(null, "screenshot is beloe:" + logger.addScreenCaptureFromPath(screenshotPath));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public void onTestSkipped(ITestResult tr) {

		logger = extent.createTest(tr.getName());
		logger.log(Status.SKIP, MarkupHelper.createLabel(tr.getName(), ExtentColor.ORANGE));
	}

	public void onFinish(ITestContext testcontext) {

		extent.flush();

	}

}
