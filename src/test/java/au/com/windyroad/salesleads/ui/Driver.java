package au.com.windyroad.salesleads.ui;

import java.io.Serializable;

import org.openqa.selenium.By;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;

public class Driver implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static WebDriver driver = null;
	
	public Driver(String type) {
		driver = new HtmlUnitDriver(true);
	}

	public void destroy() {
		driver.quit();
	}
	
	public Driver getSelf() {
		return this;
	}

	public WebDriver getDriver() {
		return driver;
	}

}
