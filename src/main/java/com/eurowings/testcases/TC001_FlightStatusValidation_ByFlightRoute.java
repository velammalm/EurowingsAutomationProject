
package com.eurowings.testcases;

import java.io.IOException;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.eurowings.pages.FlightStatusPage;
import com.eurowings.utils.ExcelReader;
import com.eurowings.wrappers.TestBase;

public class TC001_FlightStatusValidation_ByFlightRoute extends TestBase {
	int totalResults;
	
	@BeforeSuite
	public void beforeSuite() throws IOException {
		startReport();
		ExcelReader.deleteOutputSheet("TestData_TC001");
	}


	@BeforeTest
	public void setValues() {
		authors = "Eurowings_VELAMMAL";
		category = "Regression";
		dataSheetName = "TestData_TC001";
	}
	

	@Test(dataProvider = "fetchData", invocationCount = 1)
	public void validateProductSearch(String departureAirport, String destinationAirport, String departureDate)throws InterruptedException, IOException {
		testCaseName = "Flight Status Validation for routes "+departureAirport+"-"+destinationAirport+" and departure date "+departureDate;
		testDescription = "validation of flight status from "+departureAirport+" to "+ destinationAirport+" and departure date "+ departureDate+"'";
		startTest();
		test.log(Status.PASS, "Application is launched");
		
		FlightStatusPage flightStatusPage=new FlightStatusPage(driver);
	
			try {
				flightStatusPage.acceptPrivacySettings();
				flightStatusPage.scrollFlightStatusPage();
				flightStatusPage.enterDestinationAirport(destinationAirport);
				flightStatusPage.enterDepartureAirport(departureAirport);
				flightStatusPage.selectDepartureDate(departureDate);
				flightStatusPage.clickShowFlightStatusButton();
				flightStatusPage.validateFlightStatusResult();		
			} catch (Exception e) {
				test.log(Status.FAIL, "Exception occured in flight search by flight route", addscreenshot());
				Assert.fail("Exception occurred in flight search by flight route: " + e.getMessage());
			}	
		
	}
	
	@AfterMethod
	public void afterMethod(ITestResult result) throws IOException {
		if(result.getStatus() == ITestResult.SUCCESS) {
			ExcelReader.writeExcelData("TestData_TC001", "Execution Status", "PASS");
		}
		else if(result.getStatus() == ITestResult.FAILURE)
	    {
	         
			ExcelReader.writeExcelData("TestData_TC001", "Execution Status" ,"FAIL");
	    }

	}

}
