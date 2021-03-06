package au.com.windyroad.salesleads.ui;

import java.io.Serializable;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.UnreachableBrowserException;

public class Driver implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private final WebDriver driver;

	public Driver(String driverClassname) throws ClassNotFoundException,
			NoSuchMethodException, SecurityException, InstantiationException,
			IllegalAccessException, IllegalArgumentException,
			InvocationTargetException {
		try {
		Logger.getLogger("bw.logger").info("WebDriver Classname: " + driverClassname);
		if ("org.openqa.selenium.htmlunit.HtmlUnitDriver"
				.equals(driverClassname)) {
			driver = new HtmlUnitDriver(true);
		} else if ("org.openqa.selenium.chrome.ChromeDriver"
				.equals(driverClassname)) {
			ChromeOptions options = new ChromeOptions();
			DesiredCapabilities capabilities = DesiredCapabilities.chrome();
			capabilities.setCapability(ChromeOptions.CAPABILITY, options);
			capabilities.setJavascriptEnabled(true);
			driver = new ChromeDriver(capabilities);
		} else if ("org.openqa.selenium.firefox.FirefoxDriver"
				.equals(driverClassname)) {
			FirefoxProfile fp = new FirefoxProfile();
			DesiredCapabilities capabilities = DesiredCapabilities.firefox();
			capabilities.setJavascriptEnabled(true);
			capabilities.setCapability(FirefoxDriver.PROFILE, fp);
			driver = new FirefoxDriver(capabilities);
			final int width = Integer.parseInt(System.getProperty(
					"browser.window.width", "1024"));
			final int height = Integer.parseInt(System.getProperty(
					"browser.window.height", "768"));
			driver.manage().window().setSize(new Dimension(width, height));
		} else {
			Class<?> driverClass = Class.forName(driverClassname);
			Constructor<?> constructor = driverClass.getConstructor();
			driver = (WebDriver) constructor.newInstance();
		}
		}
		catch (UnreachableBrowserException e) {
			Logger.getLogger("bw.logger").error(e.getLocalizedMessage(), e);
			throw e;
		}
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

	public boolean canPngScreenShot() {
		return driver instanceof TakesScreenshot;
	}

	public byte[] getPngScreenShot() {
		if (canPngScreenShot()) {
			return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
		} else {
			return null;
		}
	}

}
