package com.emirates.project.test;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.emirates.project.core.BaseTest;
import com.emirates.project.core.IBaseTest;
import com.emirates.project.pages.HomePage;

/*
 * Test case for clicking Chrome icon in home page. 
 * */

public class HomePageTest extends BaseTest implements IBaseTest {

	// Reference to the home page
	HomePage page;

	@BeforeClass
	public void setup() {
		page = new HomePage(driver);
		System.out.println("HOME PAGE TESTS ------------------------------");
	}

	@Test(priority = 0)
	public void isPageLoadedTest() {
		Assert.assertTrue(page.chromeIcon.isDisplayed(), "Failed to load home page!");
		takeScreenShot("isPageLoadedTest");
	}

	@Test(priority = 1)
	public void clickChromeIconTest() {
		Assert.assertTrue(page.pixelPrecisionClick(page.chromeIcon), "Failed to click Chrome icon!");
		takeScreenShot("clickChromeIconTest");
	}

	@Test(priority = 2)
	public void isPageUnloadedTest() {
		Assert.assertTrue(!page.chromeIcon.isDisplayed(), "Failed to unload Home page!");
		takeScreenShot("isPageUnloadedTest");
	}

	@AfterClass
	public void tearDown() {
		page = null;
		System.out.println(" ");
	}

}
