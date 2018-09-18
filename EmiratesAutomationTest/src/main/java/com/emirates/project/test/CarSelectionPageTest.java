package com.emirates.project.test;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.emirates.project.core.BaseTest;
import com.emirates.project.core.IBaseTest;
import com.emirates.project.pages.CarSelectionPage;

/*
 * Test case for user name entry, car selection and submitting the keyed-in data. 
 * */

public class CarSelectionPageTest extends BaseTest implements IBaseTest {

	// Reference to the car selection page
	CarSelectionPage page;

	@BeforeClass
	public void setup() {
		page = new CarSelectionPage(driver);
		System.out.println("NAME ENTRY AND CAR SELECTION PAGE TESTS ------------------------------");
	}

	@Test(priority = 3)
	public void isPageLoadedTest() {
		Assert.assertTrue(page.isTitleDisplayed(), "Failed to load car selection page!");
		takeScreenShot("isPageLoadedTest");
	}

	@Test(priority = 4)
	public void enterUserNameTest() {
		String name = "Someone";
		page.enterUserName(name);
		Assert.assertTrue(page.userNameTextInputView.getText().equals(name), "Failed to key-in user name!");
		takeScreenShot("enterUserNameTest");
	}

	@Test(priority = 5)
	public void performCarSelectionTest() {
		String car = "Audi";
		String expected = null;
		expected = page.performCarSelection(car);
		Assert.assertTrue(expected.equals(car), "Failed to select a car!");
		takeScreenShot("performCarSelectionTest");
	}

	@Test(priority = 6)
	public void clickSendNameButtonTest() {
		Assert.assertTrue(page.clickSendNameButton(), "Failed to click send user name button!");
		takeScreenShot("clickSendNameButtonTest");
	}

	@Test(priority = 7)
	public void isUserDataShownTest() {
		Assert.assertTrue(page.isUserDataShown(), "Failed to verify user name and selected car!");
		takeScreenShot("isUserDataShownTest");
	}

	@Test(priority = 8)
	public void isPageUnloadedTest() {
		Assert.assertTrue(page.isSummaryScreenShown(), "Page failed to unload user name and car selection page!");
		takeScreenShot("isPageUnloadedTest");
	}

	@AfterClass
	public void tearDown() {
		page = null;
		System.out.println(" ");
	}

	protected void throwExceptionWithMessage(String message) {
		throw new AssertionError(message);
	}

}
