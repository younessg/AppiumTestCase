package com.emirates.project.core;

import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeClass;

import io.appium.java_client.AppiumDriver;

/*
 * To be extended by any test class wanting to test a given page functionalities, properties and behavior etc.
 * */

public class BaseTest {

	// A place holder of the kind of driver created by the TestPrime class. This to
	// be passe to the child test case which needs access to the driver.
	protected AppiumDriver<WebElement> driver;

	@BeforeClass
	public void init() {
		driver = DriversFactory.getDriver();
	}

	/**
	 * Takes a screenshot if needed during the test
	 * 
	 * @param caseName The case name we want to take a screen shot for, to be used
	 *                 as a reference for each test if needed
	 */
	protected void takeScreenShot(String caseName) {
		// TODO: I sidn't have time to implement it. A PNG should be taken with
		// a time stamp and case name for establishing the image file name.
	}

}
