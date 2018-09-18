package com.emirates.project.core;

import java.net.MalformedURLException;
import java.net.URL;

/*
 * Wraps the information needed about the appium server, info such as IP address and port number.
 * In case of parallel testing there could be a need for creating different instances for the server.
 * */

public class Server {

	// Default server info if nothing is set via the constructor
	String url = "http://0.0.0.0:4723/wd/hub";

	/**
	 * Constructor for the appium server object.
	 * 
	 * @param serverUrl A string representation of a full qualified URL e.g
	 *                  http://0.0.0.0:4723/wd/hub
	 */
	public Server(String serverUrl) {
		if (serverUrl != null)
			url = serverUrl;
	}

	/**
	 * Creates a URL object based on the url string passed through the constructor.
	 * 
	 * @return An instance of the URL
	 */
	public URL getUrl() {
		URL uri = null;
		try {
			uri = new URL(url);
		} catch (MalformedURLException e) {
			e.printStackTrace();
			System.out.println("Failed to create url");
		}
		return uri;
	}
}
