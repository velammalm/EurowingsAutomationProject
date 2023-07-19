package com.eurowings.utils;


import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.log4j.Logger;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.MediaEntityModelProvider;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.eurowings.wrappers.SeleniumBase;

public class Reporter {

	public static ExtentHtmlReporter reporter;
	public static ExtentReports extent;
	public static ExtentTest test;
	public ExtentTest node;
	public String testCaseName, testDescription, nodes, authors, category;
	public String excelFileName;
	public static String timeStamp;
	public static Logger log;

	static {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy-HH-mm-ss");
		System.setProperty("currenttime", dateFormat.format(new Date()));
	}

	public Reporter() {
		log = Logger.getLogger(this.getClass());
	}

	public void startReport() {
		timeStamp = new SimpleDateFormat("dd.MM.yyyy_HH.mm.ss").format(new Date());
		reporter = new ExtentHtmlReporter(
				System.getProperty("user.dir") + "/Reports/TestReport_" + timeStamp + ".html");
		
		reporter.setAppendExisting(true);
		extent = new ExtentReports();
		extent.attachReporter(reporter);
	}

	public void startTest() throws IOException {
		test = extent.createTest(testCaseName, testDescription);
		test.assignAuthor(authors);
		test.assignCategory(category);
	}

	public void reportStep(String dec, String status, boolean bSnap) {
		MediaEntityModelProvider img = null;
		if (bSnap && !status.equalsIgnoreCase("INFO")) {

			long snapNumber = 100000L;
			snapNumber = SeleniumBase.takeSnap();
			try {
				img = MediaEntityBuilder
						.createScreenCaptureFromPath(
								System.getProperty("user.dir") + "/Screenshots/" + "Screenshot_" + snapNumber + ".jpg")
						.build();
			} catch (IOException e) {

			}
		}
		if (status.equalsIgnoreCase("pass")) {
			test.pass(dec, img);
		} else if (status.equalsIgnoreCase("fail")) {
			test.fail(dec, img);
		}
	}

	public MediaEntityModelProvider addscreenshot() {
		MediaEntityModelProvider img = null;
		try {
//			long snapNumber = SeleniumBase.takeSnap();
			String path= SeleniumBase.getScreenshot();

//			img = MediaEntityBuilder
//					.createScreenCaptureFromPath(
//							System.getProperty("user.dir") + "/Screenshots/" + "Screenshot_" + snapNumber + ".jpg")
//					.build();
			
			img = MediaEntityBuilder.createScreenCaptureFromPath(path).build();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return img;
	}
	public void reportStep(String desc, String status) {
		reportStep(desc, status, true);
	}

	public void stopReport() throws IOException {
		extent.flush();
	}
}
