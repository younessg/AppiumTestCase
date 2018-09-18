package com.emirates.project.test;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.emirates.project.core.BaseTest;
import com.emirates.project.core.IBaseTest;
import com.emirates.project.pages.PopUpPage;

/*
 * Test case for manipulating the pop up window with the dismiss button. 
 * */

public class PopUpPageTest extends BaseTest implements IBaseTest {

	// Reference to the Pop up page, a sub-pager of the home page
	PopUpPage popUpPage;

	@BeforeClass
	public void setup() {
		popUpPage = new PopUpPage(driver);
		System.out.println("POP UP PAGE TESTS --------------------------------------");
	}

	@Test(priority = 9)
	public void returnToHomePageTest() {
		Assert.assertTrue(popUpPage.returnToHomePage(), "Failed to return to home page!");
		takeScreenShot("isPageLoadedTest");
	}

	@Test(priority = 10)
	public void isPageLoadedTest() {
		Assert.assertTrue(popUpPage.chromeIcon.isDisplayed(), "Failed to load home page!");
		takeScreenShot("isPageLoadedTest");
	}

	@Test(priority = 11)
	public void getPopUpWindowTextTest() {
		boolean isTitleFound = popUpPage.getPopUpWindowText().equals("It's a PopupWindow");
		Assert.assertTrue(isTitleFound, "Failed to read pop up window text!");
		takeScreenShot("isPageLoadedTest");
	}

	@Test(priority = 12)
	public void dismissPopUpWindowTest() {
		Assert.assertTrue(popUpPage.dismissPopUpWindow(), "Failed to dismiss pop up window!");
		takeScreenShot("isPageLoadedTest");
	}

	@Test(priority = 13)
	public void clickThrowExceptionButton() {
		try {
			Assert.assertTrue(popUpPage.clickThrowExceptionButton(),
					"Expected to faild. Exception thrown by clicking the exception button!");
		} catch (Exception e) {
			takeScreenShot("isPageLoadedTest");
			System.out.println(
					"Clikcing on 'Throw Exception Button' caused a run time exception, exception message from stack trace:"
							+ e.getMessage());
		}
	}

	@Test(priority = 14)
	public void isPageUnloadedTest() {
		// Intentionally left empty, by now the app is closed
	}

	@AfterClass
	public void tearDown() {
		popUpPage = null;
		System.out.println(" ");
	}

}
