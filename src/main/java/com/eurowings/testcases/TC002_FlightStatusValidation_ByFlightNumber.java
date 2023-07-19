
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

public class TC002_FlightStatusValidation_ByFlightNumber extends TestBase {
	String flightStatus;
	
	@BeforeSuite
	public void beforeSuite() throws IOException {
		startReport();
		ExcelReader.deleteOutputSheet("TestData_TC002");
	}


	@BeforeTest
	public void setValues() {
		authors = "Eurowings_VELAMMAL";
		category = "Regression";
		dataSheetName = "TestData_TC002";
	}
	

	@Test(dataProvider = "fetchData", invocationCount = 1)
	public void validateProductSearch(String flightNumber, String departureDate)throws InterruptedException, IOException {
		testCaseName = "Flight Status Validation for flight number- "+flightNumber+" and departure date "+departureDate;
		testDescription = "validation of flight status for flight number "+flightNumber+" and departure date "+ departureDate+"'";
		startTest();
		test.log(Status.PASS, "Application is launched");
		
		FlightStatusPage flightStatusPage=new FlightStatusPage(driver);
	
			try {
				flightStatusPage.acceptPrivacySettings();
				flightStatusPage.scrollFlightStatusPage();
				flightStatusPage.clickFlightNumberRadioButton();
				flightStatusPage.enterFlightNumber(flightNumber);
				flightStatusPage.selectDepartureDate(departureDate);
				flightStatusPage.clickShowFlightStatusButton();
				flightStatus = flightStatusPage.getFlightStatus();
				
			} catch (Exception e) {
				test.log(Status.FAIL, "Exception occured in flight search by flight number", addscreenshot());
				Assert.fail("Exception occurred in flight search by flight number: " + e.getMessage());
			}	
		
	}
	
	@AfterMethod
	public void afterMethod(ITestResult result) throws IOException {
		if(result.getStatus() == ITestResult.SUCCESS) {
			ExcelReader.writeExcelData("TestData_TC002", "Flight Status", flightStatus );
			ExcelReader.writeExcelData("TestData_TC002", "Execution Status", "PASS");
		}
		else if(result.getStatus() == ITestResult.FAILURE)
	    {
	         
			ExcelReader.writeExcelData("TestData_TC002", "Flight Status" , flightStatus );
			ExcelReader.writeExcelData("TestData_TC002", "Execution Status" ,"FAIL");
	    }

	}

}
