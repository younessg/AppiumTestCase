package com.emirates.project.pages;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import com.emirates.project.core.BasePage;
import com.emirates.project.core.IPage;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidBy;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

/*
 * The pop up page, supposed to handle the modal dialog with the dismiss button and check-box. 
 * */

public class PopUpPage extends BasePage implements IPage {

	@AndroidFindBy(id = "io.selendroid.testapp:id/buttonStartWebview")
	public WebElement chromeIcon;

	// Place holder as a collection for the pop up window
	List<WebElement> displayPopUpWindowList;

	// The actual button fetched from the collection above
	public WebElement displayPopUpBtn;

	@AndroidBy(xpath = "//android.widget.Button[@content-desc=\"exceptionTestButtonCD\"]")
	public WebElement throwExceptionBtn;

	/**
	 * Pop up page constructor
	 * 
	 * @param driver (required) An instance of the appium driver
	 */
	public PopUpPage(AppiumDriver<WebElement> driver) {
		super(driver);
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}

	// May be called in some cases from the constructor to do more initialization
	public void init() {
		// Intentionally left empty
	}

	/**
	 * Takes the user to the home page.
	 * 
	 * @return true only if one of the home page GUI objects is present
	 */
	public boolean returnToHomePage() {
		boolean isBackToHomePage = false;
		driver.navigate().back();
		if (waitToAppear(chromeIcon, 3)) {
			if (chromeIcon.isDisplayed())
				isBackToHomePage = true;
		}
		return isBackToHomePage;
	}

	/**
	 * Grabs the modal dialog text in the top i.e. "It's a PopupWindow".
	 * Intentionally returns null for now due to lack of element reference in the UI
	 * inspector.
	 * 
	 * @return the grabbed text value "It's a PopupWindow"
	 */
	public String getPopUpWindowText() {
		String popUpText = null;
		String targetText = "It's a PopupWindow";
		// Click on the button to show the modal dialog with the dismiss button
		displayPopUpWindowList = driver.findElementsByAccessibilityId("showPopupWindowButtonCD");
		displayPopUpBtn = displayPopUpWindowList.get(0);
		displayPopUpBtn.click();
		// Fetch the text in the modal dialog
		List<WebElement> elementList = ((AndroidDriver<WebElement>) driver)
				.findElements(MobileBy.AndroidUIAutomator("new UiSelector().description(\"" + targetText + "\")"));
		if (waitToAppear(elementList.get(0), 2)) {
			popUpText = elementList.get(0).getText();
		}
		return popUpText;
	}

	/**
	 * Dismisses the pop up modal dialog.
	 * 
	 * @return true only if the modal dialog is dismissed. Intentionally returns
	 *         false for now due to lack of element reference in the UI inspector.
	 */
	public boolean dismissPopUpWindow() {
		boolean isDismissed = false;
		String path = "//*[@text=\"Dismiss\"]";
		WebElement dismissBtn = fetchUntilFound(path, 3);
		dismissBtn.click();
		if (waitToDisappear(dismissBtn, 2)) {
			if (!dismissBtn.isDisplayed())
				isDismissed = true;
		}
		return isDismissed;
	}

	/**
	 * Clicks on the button causing the run time exception.
	 * 
	 * @return true only if the exception button was clicked
	 */
	public boolean clickThrowExceptionButton() {
		boolean isClicked = false;
		try {
			throwExceptionBtn.click();
			isClicked = true;
		} catch (Exception e) {
			// To be handled by the corresponding unit test
		}
		return isClicked;
	}

}
