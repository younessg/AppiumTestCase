package com.emirates.project.core;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;

import com.emirates.project.utils.Platforms;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;

public class DriversFactory {

	// Keeping one driver copy after each creation
	private static AppiumDriver<WebElement> driver;
	// Reference to the currently created driver type
	private static String currentDriverType;

	/**
	 * Factory method for creating drivers sharing the same parent class type i.e
	 * appium driver
	 * 
	 * @param platform The platform we are interested in testing against, such as
	 *                 Android, IOS, Windows etc.
	 * @param server   This wraps the appium server IP address and port number
	 * @param caps     The desired capabilities
	 * @return An instance of the appium driver based on the choices set in the
	 *         platform type, server and capabilities
	 */
	public static AppiumDriver<WebElement> createDriver(String platform, Server server, DesiredCapabilities caps) {
		if (platform.equals(Platforms.ANDROID)) {
			System.out.println("Creating android driver...");
			try {
				driver = new AndroidDriver<WebElement>(server.getUrl(), caps);
				currentDriverType = Platforms.ANDROID;
			} catch (Exception e) {
				printError("Failed to create android driver", e);
			}
		} else if (platform.equals(Platforms.IOS)) {
			// Handle driver creation for iOS
			// currentDriverType = Platforms.IOS;
		} else if (platform.equals(Platforms.WINDOWS)) {
			// handle driver creation for Windows
			// currentDriverType = Platforms.WINDOWS;
		}
		return driver;
	}

	/**
	 * Returns the current driver instance
	 * 
	 * @return the last driver that has been created by calling createDriver()
	 */
	public static AppiumDriver<WebElement> getDriver() {
		System.out.println("Getting driver...");
		return driver;
	}

	/**
	 * Helper method returns a hint on the current driver instance type.
	 * 
	 * @return The type of driver we have at the moment, type is presented in a simple
	 *         string format e.g. Android, IOS, etc.
	 */
	public static String getCurrentDriverType() {
		return currentDriverType;
	};

	/**
	 * Helper method for displaying any encountered run time errors
	 * 
	 * @param issue A message describing the issue
	 * @param e     The actual exception object from runtime
	 */
	private static void printError(String issue, Exception e) {
		System.out.println(issue + "!, Exception details:" + e.getLocalizedMessage());
	}
}
