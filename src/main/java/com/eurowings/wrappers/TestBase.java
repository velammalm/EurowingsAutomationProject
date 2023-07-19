package com.eurowings.wrappers;

import java.io.IOException;
import java.util.Properties;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;

import com.aventstack.extentreports.Status;
import com.eurowings.utils.ExcelReader;



public class TestBase extends SeleniumBase
{
	
	public static Properties property;
	public static ChromeOptions chromeOptions;
	public static EventFiringWebDriver e_driver;
    public String dataSheetName;
    ExcelReader reader;
    
		
   	
	@DataProvider(name = "fetchData")
	public Object[][] fetchData() throws IOException {
		return ExcelReader.readExcelData(dataSheetName);
	}		


	@BeforeMethod
	 public void browser() throws IOException
    {
//    	 report();
    	 browserInitialization();
    }
	
	
	@AfterMethod(alwaysRun=true)
	public void tearDown() throws Exception
	{
//		test.addScreenCaptureFromPath(getScreenshot());
		test.log(Status.INFO,"Test execution ended",addscreenshot());
		quit();
		log.info("Browser Terminated");
		log.info("-----------------------------------------------");

		
	}
	
	@AfterSuite
	public void afterSuite() throws IOException {
		stopReport();
	}
}

