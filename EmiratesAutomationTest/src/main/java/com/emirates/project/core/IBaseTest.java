package com.emirates.project.core;

/*
 * Interface to be implemented by a test case only.
 * Provides basic helper methods needed by the test
 * class.
 * */

public interface IBaseTest {

	// For setting up test cases prerequisites
	void setup();

	// For checking if the page under test is loaded
	void isPageLoadedTest();

	// For checking if the page under test is unloaded
	void isPageUnloadedTest();

	// For tearing down test cases and doing some in-house cleaning if needed
	void tearDown();

}
