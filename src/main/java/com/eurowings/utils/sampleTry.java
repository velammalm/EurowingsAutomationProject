package com.eurowings.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class sampleTry {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		
		System.setProperty("webdriver.chrome.driver",System.getProperty("user.dir")+"/drivers/chromedriver.exe");
		ChromeDriver driver= new ChromeDriver();
		driver.manage().window().maximize();
		driver.navigate().to("https://www.eurowings.com/en/information/at-the-airport/flight-status.html");
		driver.findElement(By.xpath("//button[@data-toogle='true']")).click();
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);",
				driver.findElementByXPath("(//button[contains(@class,'compact-search')])[1]"));
		Thread.sleep(500); 
		
//		driver.findElementByXPath("(//button[contains(@class,'compact-search')])[2]").click();
//		driver.findElementByXPath("//input[@aria-label='Destination airport']").sendKeys("BER",Keys.ENTER);
//		driver.findElementByXPath("(//button[contains(@class,'compact-search')])[1]").click();
//		
//		driver.findElementByXPath("//input[@aria-label='Departure airport']").sendKeys("CGN",Keys.ENTER);
//		driver.findElement(By.xpath("//input[contains(@name,'datepicker_input')]")).click();
//		driver.findElement(By.xpath("//input[@value='2023-07-22']")).click();
//		
		driver.findElement(By.xpath("(//div[@class='m-form-radiobutton__input'])[2]")).click();
		//Thread.sleep(1000);
		//driver.findElementByXPath("//div[contains(@class,'select__new-station-list')]").click();
		
		
		
		
//		driver.findElementByXPath("(//button[contains(@class,'compact-search')])[2]").click();
//		driver.findElementByXPath("//input[@aria-label='Destination airport']").sendKeys("BER",Keys.ENTER);
		//driver.findElement(By.xpath("//input[contains(@name,'datepicker_input')]")).sendKeys("21/07/2023");
		Thread.sleep(5000);
		driver.close();

	}

}
