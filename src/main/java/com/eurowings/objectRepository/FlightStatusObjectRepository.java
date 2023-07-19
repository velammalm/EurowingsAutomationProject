package com.eurowings.objectRepository;


import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.eurowings.wrappers.TestBase;


public class FlightStatusObjectRepository extends TestBase {
	
	public FlightStatusObjectRepository(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath = "//button[@data-toogle='true']")
	public WebElement privacySettingsAcceptButton;
	
	@FindBy(xpath = "(//button[contains(@class,'compact-search')])[1]")
	public WebElement departureAirport;
	
	@FindBy(xpath = "(//button[contains(@class,'compact-search')])[2]")
	public WebElement destinationAirport;

	@FindBy(xpath = "//input[@aria-label='Departure airport']")
	public WebElement departureAirportInputBox;
	
	@FindBy(xpath = "//input[@aria-label='Destination airport']")
	public WebElement destinationAirportInputBox;
	
	@FindBy(xpath = "//input[contains(@name,'datepicker_input')]")
	public WebElement departureDate;
	
	@FindBy(xpath = "//button[@data-component-name='cta' and @type='submit']")
	public WebElement showFlightStatusButton;
	
	@FindBy(xpath = "(//div[@class='m-form-radiobutton__input'])[2]")
	public WebElement flightNumberRadioButton;
	
	@FindBy (xpath = "//input[@name='flightNumber']")
	public WebElement flightNumberInputBox;
	
	@FindBy (xpath = "//div[@class='o-search-flight-status__card']")
	public WebElement flightStatusResult;
	
	@FindBy (xpath = "//div[contains(@class,'o-search-flight-status__card-flight-status')]")
	public WebElement flightStatusInfo;
	
	
	
}
