package com.emirates.project.core;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.emirates.project.utils.SwipeDirection;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;

/*
 * Base page to be extended by a page wanting to be part of this test framework. Comes with helper methods
 * and shortcuts for saving time while programming and functionalities that can be handy for the page object,
 * such us swipe option with chosen directions, one simple call for fetching a GUI object within a given
 * time frame, waiting for GUI objects to disappear and appear etc. 
 * */

public class BasePage {

	protected AppiumDriver<WebElement> driver;
	@SuppressWarnings("rawtypes")
	protected TouchAction touchAction;
	protected WebDriverWait wait;
	protected int precisionClickDelay = 200;
	protected Dimension dimension;

	/**
	 * Base page constructor
	 * 
	 * @param driver (required) An instance of the appium driver
	 */
	@SuppressWarnings("rawtypes")
	public BasePage(AppiumDriver<WebElement> driver) {
		this.driver = driver;
		touchAction = new TouchAction(driver);
		dimension = driver.manage().window().getSize();
	}

	/**
	 * Helper method for fetching a GUI object within a given time frame of seconds.
	 * 
	 * @param xpath    The xpath to the target GUI object
	 * @param duration The duration in seconds
	 * 
	 * @return The target GUI object
	 */
	public WebElement fetchUntilFound(String xpath, int duration) {
		long startTime = System.currentTimeMillis();
		WebElement element = null;
		while (listItem(xpath).size() == 0) {
			if ((System.currentTimeMillis() - startTime) > duration * 1000) {
				System.out.println("Item was not found!");
				break;
			}
		}
		if (listItem(xpath).size() > 0) {
			element = listItem(xpath).get(0);
		}
		return element;
	}

	/**
	 * Helper method returning an array list of fetched elements once found
	 * 
	 * @param xpath The xpath to the target GUI object
	 * @return A collection of GUI objects
	 */
	protected List<WebElement> listItem(String xpath) {
		return driver.findElements(By.xpath(xpath));
	}

	/**
	 * Waits for a given GUI element that has been parsed in the view tree but not
	 * rendered yet.
	 * 
	 * @param element  The target element
	 * @param duration The duration in seconds
	 * @return true only if the GUI object is visible
	 */
	public boolean waitToAppear(WebElement element, int duration) {
		wait = new WebDriverWait(driver, duration);
		return wait.until(ExpectedConditions.visibilityOf(element)).isDisplayed();
	}

	/**
	 * Waits for a given GUI element to disappear, as in case of scrolling.
	 * 
	 * @param element  The target element
	 * @param duration The duration in seconds
	 * @return true only if the GUI object is invisible
	 */
	public boolean waitToDisappear(WebElement element, int duration) {
		wait = new WebDriverWait(driver, duration);
		return wait.until(ExpectedConditions.invisibilityOf(element));
	}

	/**
	 * Helper method for performing GUI element pixel perfect click. Performed on
	 * the GUI element x,y position rather than the bounding box. The click will
	 * happen in the center of the GUI object.
	 * 
	 * @param element The target element to be clicked
	 * @return true only if the click happens with no issues e.g if we are trying to clock on a null element
	 */
	public boolean pixelPrecisionClick(WebElement element) {
		boolean isClicked = false;
		Point point = element.getLocation();
		int x = point.getX();
		int y = point.getY();
		int centerX = x + (element.getSize().getWidth() / 2);
		int centerY = y + (element.getSize().getHeight() / 2);
		System.out.println("Clicked element at x:" + x + ", y:" + y);
		try {
			touchAction.press(PointOption.point(centerX, centerY)).release().perform();
			isClicked = true;
		} catch (Exception e) {
			System.out.println("Couldn't click on the element");
		}
		return isClicked;
	}

	/**
	 * Helper method for performing swipes in "NEWS" directions. For example if
	 * wanting to swipe left, then do swipe("left", 0.2, 0.5). This will swipe
	 * horizontally by 30% of the screen width.
	 * 
	 * @param direction  up, down, left, right.
	 * @param fromFactor this is a FROM position percentage as in 0.2, translates to
	 *                   20% of the screen width or height, depending on what
	 *                   direction you are going. If swiping up or down then this
	 *                   will be 20% of the height.
	 * @param toFactor   this is a TO position percentage as in 0.3, translates to
	 *                   30% of the screen width or height, depending on what
	 *                   direction you are going. If swiping left or right then this
	 *                   will be 30% of the width.
	 */
	public void swipe(String direction, Double fromFactor, Double toFactor) {
		Dimension dimension = driver.manage().window().getSize();

		double height = dimension.getHeight() * 0.5;
		double width = dimension.getWidth() * 0.5;

		double startY = 0, endY = 0, startX = 0, endX = 0;

		if (direction == SwipeDirection.DOWN || direction == SwipeDirection.UP) {
			startY = height * fromFactor;
			endY = height * toFactor;
		} else if (direction == SwipeDirection.LEFT || direction == SwipeDirection.RIGHT) {
			startX = width * fromFactor;
			endX = width * toFactor;
		}

		int xFrom = (int) startX;
		int yFrom = (int) startY;

		int xTo = (int) endX;
		int yTo = (int) endY;

		touchAction.press(PointOption.point(xFrom, yFrom)).waitAction(WaitOptions.waitOptions(Duration.ofSeconds(0)))
				.moveTo(PointOption.point(xTo, yTo)).release().perform();
	}

	/**
	 * Helper method serves as a simplified shortcut for finding GUI elements by id
	 * 
	 * @param path The xpath for the target GUI object
	 * @return The target GUI object
	 */
	protected WebElement findElementById(String path) {
		return driver.findElement(By.id(path));
	}

	/**
	 * Helper method serves as a simplified shortcut for finding GUI elements by
	 * xpath.
	 * 
	 * @param path The xapth for the target GUI object
	 * @return The target GUI object
	 */
	protected WebElement findElementByXPath(String path) {
		return driver.findElement(By.xpath(path));
	}

}
