package com.emirates.project.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import com.emirates.project.core.BasePage;
import com.emirates.project.core.IPage;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

/*
 * This is the home page / the landing page once the app has started
 * */

public class HomePage extends BasePage implements IPage {

	@AndroidFindBy(id = "io.selendroid.testapp:id/buttonStartWebview")
	public WebElement chromeIcon;

	@AndroidFindBy(xpath = "//android.view.View[content-desc@='Hello, can you please tell me your name?']")
	public WebElement nextPageGuiObject;

	/**
	 * Home page constructor
	 * 
	 * @param driver (required) An instance of the appium driver
	 */
	public HomePage(AppiumDriver<WebElement> driver) {
		super(driver);
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}

	// May be called in some cases from the constructor to do more initialization
	public void init() {
		// Intentionally left empty for this implementation
	}

	/**
	 * Provides a check if the GUI button is clicked with success.
	 * 
	 * @return true only if a GUI object was successfully clicked, may fail if a
	 *         null element is clicked for example
	 */
	public boolean clickChromeIcon() {
		return pixelPrecisionClick(chromeIcon);
	}

}
