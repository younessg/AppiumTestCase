package com.emirates.project.test;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import com.emirates.project.core.DriversFactory;
import com.emirates.project.core.Server;
import com.emirates.project.utils.Platforms;

import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;

/*
 * Creates the Appium driver needed by AUT. 
 * */

public class TestPrimer {

	private DesiredCapabilities caps;
	private Server server;

	@BeforeSuite
	public void setup() {
		System.out.println("BEGINNING OF TEST-SUITE EXECUTION --------------------------------------");
		caps = new DesiredCapabilities();
		caps.setCapability(MobileCapabilityType.DEVICE_NAME, "emulator-5554");
		caps.setCapability(MobileCapabilityType.NO_RESET, false);
		// Path to AUT
		caps.setCapability(MobileCapabilityType.APP, "C:\\appium_files\\selendroid-test-app-0.17.0.apk");
		// Default as null will use the local Appium default url with IP address and
		// port. You may use a string for concrete full qualified URI
		server = new Server(null);
		// Create the actual driver with the given params, to be consumed by the test
		// cases and the pages under test
		DriversFactory.createDriver(Platforms.ANDROID, server, caps);
	}

	@AfterSuite
	public void tearDown() {
		System.out.println("END OF TEST-SUITE EXECUTION --------------------------------------");
		DriversFactory.getDriver().quit();
		caps = null;
		server = null;
	}

}
