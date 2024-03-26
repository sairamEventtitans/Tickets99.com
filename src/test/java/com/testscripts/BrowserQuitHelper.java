package com.testscripts;

import org.testng.annotations.AfterSuite;

import com.utils.Utils;

public class BrowserQuitHelper {
	
	
	@AfterSuite
	
	public void quitBrowsers() {
		
		Utils.exitBrowsers();
		
	}

}
