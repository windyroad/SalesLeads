package au.com.windyroad.salesleads.ui;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

public class Driver implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static WebDriver driver = null;
	
	public Driver(String type) {
		driver = new HtmlUnitDriver(true);
	}

	protected String url = "";
	protected Object driverX = null;
	protected String[] firstNames = null;
	protected String[] lastNames = null;
	protected String[] emails = null;
	protected String[] phoneNumbers = null;

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
