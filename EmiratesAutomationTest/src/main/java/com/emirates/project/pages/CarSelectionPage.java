package com.emirates.project.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import com.emirates.project.core.BasePage;
import com.emirates.project.core.IPage;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

/*
 * This is the page where we provide the user name and select a car in. Then we send the
 * data to an other page i.e. the summary page with some greeting wording in it.  
 * */

public class CarSelectionPage extends BasePage implements IPage {

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Web View Interaction' and @index='0']")
	public WebElement webViewText;

	@AndroidFindBy(id = "name_input")
	public WebElement userNameTextInputView;

	@AndroidFindBy(xpath = "//android.widget.Spinner[@index='2']")
	public WebElement carOptionsList;

	@AndroidFindBy(xpath = "//android.widget.Button[@index='6']")
	public WebElement sendUserNameBtn;

	@AndroidFindBy(xpath = "//android.view.View[@content-desc=\"This is my way of saying hello\"]")
	public WebElement sayingHelloTextView;

	private String userName;

	private String selectedCar;

	/**
	 * Car selection page constructor
	 * 
	 * @param driver (required) An instance of the appium driver.
	 */
	public CarSelectionPage(AppiumDriver<WebElement> driver) {
		super(driver);
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}

	// May be called in some cases from the constructor to do more initialization
	public void init() {
		// Intentionally left empty
	}

	/**
	 * Checks if the very first text view has been loaded. If yes, then it returns
	 * true as an indication that the page has loaded. For a more comprehensive
	 * check, it is better to run a check on all the crucial GUI objects necessary
	 * for the page.
	 * 
	 * @return true only if the GUI object is displayed
	 */
	public boolean isTitleDisplayed() {
		boolean isTitleDisplayed = false;
		if (waitToAppear(webViewText, 1)) {
			isTitleDisplayed = true;
		}
		return isTitleDisplayed;
	}

	/**
	 * Takes in a user name and assign it to the input text field.
	 * 
	 * @param name The user name to be keyed-in in the input field
	 */
	public void enterUserName(String name) {
		userName = name;
		userNameTextInputView.click();
		userNameTextInputView.clear();
		System.out.println("Keying in the name takes time!");
		userNameTextInputView.sendKeys(name);
		driver.hideKeyboard();
	}

	/**
	 * Helps in showing the cars option menu and picking one option.
	 * 
	 * @param car The car to be picked from the options menu
	 * @return The retrieved car name after the selection is complete
	 */
	public String performCarSelection(String car) {
		pixelPrecisionClick(carOptionsList);
		WebElement carOption = fetchUntilFound("//android.widget.CheckedTextView[@text='" + car + "' and @index='2']",
				2);
		if (waitToAppear(carOption, 2)) {
			pixelPrecisionClick(carOption);
		}
		if (waitToAppear(carOptionsList, 2))
			// Update local member value for future use
			selectedCar = carOptionsList.getAttribute("name");
		return selectedCar;
	}

	/**
	 * Helps with submitting the name and the car selection.
	 * 
	 * @return true only if the send button was successfully clicked
	 */
	public boolean clickSendNameButton() {
		return pixelPrecisionClick(sendUserNameBtn);
	}

	/**
	 * Checks the existence of a GUI object in the summary screen, one way to
	 * confirm we submitted user data and we came to the next page page.
	 * 
	 * @return true only if the next screen GUI object is displayed
	 */
	public boolean isSummaryScreenShown() {
		return sayingHelloTextView.isDisplayed();
	}

	/**
	 * Checks if the user name and selected car option from the previous screen are
	 * present in the current screen i.e. "say hello page / summary page".
	 * 
	 * @return true only if the same data provided before match with what is in the
	 *         current page.
	 */
	public boolean isUserDataShown() {
		boolean isDataShown = false;
		// Checking if user name is displayed in "say hello" screen
		String path = "//*/android.webkit.WebView/android.view.View[4]";
		WebElement keyedName = fetchUntilFound(path, 2);
		String adjustedUserName = keyedName.getAttribute("name").replaceAll("\"", "").toLowerCase().trim();
		// Checking if car is displayed in "say hello" screen
		path = "//*/android.webkit.WebView/android.view.View[6]";
		WebElement chosenCar = fetchUntilFound(path, 2);
		String adjustedCarName = chosenCar.getAttribute("name").replaceAll("\"", "").toLowerCase().trim();
		if (adjustedUserName.equals(userName.toLowerCase().trim())
				&& adjustedCarName.equals(selectedCar.toLowerCase().trim()))
			isDataShown = true;
		return isDataShown;
	}

}
