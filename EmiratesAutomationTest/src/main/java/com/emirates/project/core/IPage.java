package com.emirates.project.core;

/*
 * Interface to be implemented only by pages under test. May host more common functionalities
 * necessary for the page. Right now the only thing I needed was the initialization call from
 * some pages constructors. 
 * */

public interface IPage {

	void init();
}
