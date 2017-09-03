package com.uhg.core.framework.webdriver;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;

import com.uhg.core.framework.utils.DriversFilePath;

/**
 * 
 * @author kvarma1
 *
 */
public class SeDriverFactory {

	public WebDriver getDriver() {
		return this.getDriver("firefox");
	}

	public WebDriver getNewRemoteDriver(String hubUrl, Capabilities capabilities) {
		RemoteWebDriver driver;
		try {
			driver = new RemoteWebDriver(new URL(hubUrl), capabilities);
		} catch (MalformedURLException ex) {
			throw new RuntimeException("Hub URL does not seem to be working: " + hubUrl, ex);
		}
		return driver;
	}

	//intentionally used if-else-if statements over switch
			public WebDriver getDriver(String browserType) {
				WebDriver driver = null;
				File file = null;
				if (browserType.equalsIgnoreCase("Internet Explorer") || browserType.equalsIgnoreCase("iexplore") ||
						browserType.equalsIgnoreCase("IE") || browserType.equalsIgnoreCase("IExplorer")) {
					
					file = new File(DriversFilePath.getDriverFilepath("IEDriverServer.exe"));
					System.setProperty("webdriver.ie.driver",file.getAbsolutePath());				 

					DesiredCapabilities Capability = DesiredCapabilities.internetExplorer();
					Capability.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS,true);
					
					driver = new InternetExplorerDriver(Capability);
					
				} else if (browserType.equalsIgnoreCase("firefox")) {
					 
					driver = new FirefoxDriver();
				} else if (browserType.equalsIgnoreCase("chrome")) {
					
					 
					file = new File(DriversFilePath.getDriverFilepath("chromedriver.exe"));
					System.setProperty("webdriver.chrome.driver",file.getAbsolutePath());
					DesiredCapabilities Capability = DesiredCapabilities.chrome();
					 
					driver = new ChromeDriver(Capability);
					
				} else if (browserType.equalsIgnoreCase("safari")) {
					driver = new SafariDriver();
				} else if (browserType.equalsIgnoreCase("html")
						|| browserType.equalsIgnoreCase("html unit")) {
					driver = new HtmlUnitDriver();
				}
				return driver;
			}


}
