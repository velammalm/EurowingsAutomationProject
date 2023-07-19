
package com.eurowings.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import com.aventstack.extentreports.Status;
import com.eurowings.objectRepository.FlightStatusObjectRepository;
import com.eurowings.wrappers.TestBase;

public class FlightStatusPage extends TestBase {
	
	FlightStatusObjectRepository flightStatusOR = new FlightStatusObjectRepository(driver);

	public FlightStatusPage(WebDriver driver) {
		this.driver = driver;
	}
	
	public void acceptPrivacySettings() {
		
		try {
			if (verifyDisplayed(flightStatusOR.privacySettingsAcceptButton)) {
				click(flightStatusOR.privacySettingsAcceptButton);
			}
			test.log(Status.PASS, "Accepted privacy settings");
		} catch (Exception e) {
			test.log(Status.FAIL, "Unable to click accept button on Privacy settings popup");
		}
	}
	
	public void scrollFlightStatusPage() {
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);",flightStatusOR.flightNumberRadioButton);
	}
	
	public void clickFlightNumberRadioButton() {
		try {
			click(flightStatusOR.flightNumberRadioButton);
			test.log(Status.PASS, "Clicked the Radio button for search by flight number");
			
		} catch(Exception e) {
			test.log(Status.FAIL, "Unable to click radio button for search by flight number");
		}
	}
	
	public void enterFlightNumber(String flightNumber) {
		try {
			waitForElementToBeVisible(flightStatusOR.flightNumberInputBox);
			flightStatusOR.flightNumberInputBox.sendKeys(flightNumber);
			test.log(Status.PASS, "Entered FlightNumber " + flightNumber, addscreenshot());
		} catch (Exception e) {
			test.log(Status.FAIL, "Unable to enter flight number");
			System.out.println(e);
			Assert.fail("Unable to enter flight number");
		}
	}
	
	public void enterDepartureAirport(String departureAirport) {
		try {
			waitForElementToBeVisible(flightStatusOR.departureAirport);
			click(flightStatusOR.departureAirport);
			flightStatusOR.departureAirportInputBox.sendKeys(departureAirport, Keys.ENTER);
			test.log(Status.PASS, "Entered Departure Airport " + departureAirport, addscreenshot());
		} catch (Exception e) {
			test.log(Status.FAIL, "Unable to enter departure airport");
			Assert.fail("Unable to enter departure airport");
		}
	}

	public void enterDestinationAirport(String destinationAirport) {
		try {
			click(flightStatusOR.destinationAirport);
			flightStatusOR.destinationAirportInputBox.sendKeys(destinationAirport, Keys.ENTER);
			test.log(Status.PASS, "Entered destination Airport " + destinationAirport, addscreenshot());
		} catch (Exception e) {
			test.log(Status.FAIL, "Unable to enter destination airport");
			Assert.fail("Unable to enter destination airport");
		}
	}
	
	public void selectDepartureDate(String departuredate) {
		try {
			click(flightStatusOR.departureDate);
			Thread.sleep(1000);
			driver.findElement(By.xpath("//input[@value='"+departuredate+"']")).click();
			test.log(Status.PASS, "Selected Departure date is " + departuredate, addscreenshot());
		} catch (Exception e) {
			test.log(Status.FAIL, "Unable to select departure date");
			Assert.fail("Unable to select departure date");
		}
	}
	
	public void clickShowFlightStatusButton() {
		try {
			click(flightStatusOR.showFlightStatusButton);
			test.log(Status.PASS, "Clicked the Show Flight Status button");
		} catch(Exception e) {
			test.log(Status.FAIL, "Unable to click Show Flight status button");
			Assert.fail("Unable to click Show Flight status button");
		}
	}
	
	public void validateFlightStatusResult() {
		try {
			if (verifyDisplayed(flightStatusOR.flightStatusResult))
				test.log(Status.PASS, "Flight Status result is displayed as expected ", addscreenshot());
			else
				test.log(Status.FAIL, "Flight Status result is not displayed");

		} catch(Exception e) {
			test.log(Status.FAIL, "Flight Status result is not displayed");
			Assert.fail("Flight Status result is not displayed");
		}
	}
		
	public String getFlightStatus() {
		String flightStatus=null;
		try {
		flightStatus= flightStatusOR.flightStatusInfo.getText();
		test.log(Status.PASS, "The flight status is "+flightStatus);
		}
		catch(Exception e){
			test.log(Status.FAIL, "Unable to fetch flight status");
		}
		return flightStatus;
		
	}
}
	

	
	