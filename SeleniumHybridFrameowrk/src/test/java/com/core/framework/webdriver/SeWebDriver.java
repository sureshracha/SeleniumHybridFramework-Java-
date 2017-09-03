package com.core.framework.webdriver;


import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriver.Timeouts;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * 
 * @author sracha
 * 
 */
public class SeWebDriver {

	protected WebDriver driver;
	protected SeDriverFactory browser;
	protected String webUrl;
	private long defTimeOut = 600;

	protected SeWebDriver(WebDriver driver) {
		this.driver = driver;
		this.webUrl = driver.getCurrentUrl();
	}
	public void setDefaultTime(long dtime)
	{
		defTimeOut = dtime;
	}

	public SeWebDriver() {
		this("firefox");
	}

	public SeWebDriver(String browserType) {
		//System.out.println("Browser :" + browserType);
		browser = new SeDriverFactory();
		driver = browser.getDriver(browserType);
		
	}

	public SeWebDriver(String webUrl, String browserType) {
		
		this(browserType);
		this.launch(webUrl);

	}

	public String getURL() {
		return this.webUrl;
	}

	public WebElement findElement(String findByMechanism, String locator) {
		WebElement retElement =  driver.findElement(FindBy
				.seByMechanism(findByMechanism, locator));
		return retElement;
	}
	

	/**
	 * Added newly for properties on 6th March 2014
	 * @author sracha
	 * it takes only one parameter Properites
	 * @param prp
	 * @return
	 */
	public WebElement findElement(Properties prp) {
		WebElement retElement = null;
		
		try {
			
			/*System.out.println("value1=" + prp.getProperty("findby"));
			System.out.println("value2=" + prp.getProperty("prpValue"));*/
			
			 retElement =  driver.findElement(FindBy.seByMechanism(prp.getProperty("findBy"),prp.getProperty("prpValue")));
			
		}catch(Exception ex) {
			System.out.println(ex.getMessage());
			System.out.println(prp.getProperty("prpValue").toString());
		}
		
		return retElement;
	}

	public List<WebElement> findElements(String findByMechanism,
			String locator) {
		List<WebElement> webElements = null;
		webElements = driver.findElements(FindBy.seByMechanism(findByMechanism,
				locator));

		return  webElements;
	}

	 

	// Wrapping-ups WebDriver methods

	public WebDriver.Options manage() {
		return driver.manage();
	}

	public void launch(String webURL) {
		this.webUrl = webURL;
		driver.get(webURL);
	}

	public WebDriver getWebDriver() {
		return driver;
	}

	public void close() {
		driver.close();
	}

	public void quit() {
		driver.quit();
	}

	public String getTitle() {
		return driver.getTitle();
	}

	// wrapping-up WebDriver.Navigation

	public void back() {
		getWebDriver().navigate().back();

	}

	public void forward() {
		getWebDriver().navigate().forward();
	}

	public void refresh() {
		getWebDriver().navigate().refresh();

	}

	public void to(String url) {
		getWebDriver().navigate().to(url);
	}

	public void to(URL url) {
		getWebDriver().navigate().to(url);
	}

	// wrapping-up WebDriver.Options

	public void addCookie(Cookie cookie) {
		getWebDriver().manage().addCookie(cookie);

	}

	public void deleteAllCookies() {
		getWebDriver().manage().deleteAllCookies();

	}

	public void deleteCookie(Cookie cookie) {
		getWebDriver().manage().deleteCookie(cookie);

	}

	public void deleteCookieNamed(String name) {
		getWebDriver().manage().deleteCookieNamed(name);

	}

	public Cookie getCookieNamed(String name) {
		return getWebDriver().manage().getCookieNamed(name);
	}

	public Set<Cookie> getCookies() {
		return getWebDriver().manage().getCookies();
	}

	public SeWebDriver window(String nameOrHandle) {
		return toSeWebDriver((getWebDriver().switchTo().window(nameOrHandle)));
	}

	// wrapping WebDriver.TargetLocator
	public WebElement activeElement() {
		return  getWebDriver().switchTo().activeElement();
	}

	public Alert alert() {
		return getWebDriver().switchTo().alert();
	}

	public void dismissAlert() {
		alert().dismiss();
	}

	public void acceptAlert() {
		alert().accept();
	}

	public String getTextAlert() {
		return alert().getText();
	}

	public void sendKeysAlert(String keysToSend) {
		alert().sendKeys(keysToSend);
	}

	public SeWebDriver defaultContent() {
		return toSeWebDriver((getWebDriver().switchTo().defaultContent()));
	}

	public SeWebDriver frame(int index) {
		return toSeWebDriver(getWebDriver().switchTo().frame(index));

	}

	public SeWebDriver frame(String nameOrId) {
		return toSeWebDriver(getWebDriver().switchTo().frame(nameOrId));

	}

	public SeWebDriver frame(WebElement frameElement) {
		return toSeWebDriver(getWebDriver().switchTo().frame(
				 frameElement));
	}

	// wrapping WebDriver.Timeouts

	public Timeouts implicitlyWait(long time, TimeUnit unit) {
		return getWebDriver().manage().timeouts().implicitlyWait(time, unit);
	}

	public Timeouts implicitlyWait(long time) {
		return getWebDriver().manage().timeouts()
				.implicitlyWait(time, TimeUnit.MILLISECONDS);
	}

	public Timeouts setScriptTimeout(long time, TimeUnit unit) {
		return getWebDriver().manage().timeouts().setScriptTimeout(time, unit);
	}

	public Timeouts setScriptTimeout(long time) {
		return getWebDriver().manage().timeouts()
				.setScriptTimeout(time, TimeUnit.MILLISECONDS);
	}

	// wrapping WebDriver.Window
	public void maximize() {
		getWebDriver().manage().window().maximize();
	}

	public Actions getAction() {
		return new Actions(getWebDriver());
	}

	// Additional methods
	public void dragAndDropByCoordinates(WebElement source, int x, int y) {
		getAction().dragAndDropBy(source, x, y).build()
				.perform();

	}

	public void dragAndDrop(WebElement source, WebElement destination) {
		getAction()
				.dragAndDrop(source, destination)
				.build().perform();

	}

	public void keyUp(Keys key) {
		getAction().keyUp(key).perform();

	}

	public void keyUp(WebElement control, Keys key) {
		getAction().keyUp(control, key).perform();
	}

	public void keyDown(Keys key) {
		getAction().sendKeys(key).perform();
	}

	public void keyDown(WebElement control, Keys key) {
		getAction().sendKeys(control, key).perform();
	}

	public void doubleClick(WebElement control) {
		getAction().doubleClick(control).perform();
	}

	public void mouseDown(WebElement control) {
		getAction().clickAndHold(control).perform();

	}

	public void mouseUp(WebElement control) {
		getAction().release(control).perform();
	}

	public void moveByOffset(int xOffset, int yOffset) {
		getAction().moveByOffset(xOffset, yOffset).perform();
	}

	public void mouseMove(WebElement element) {
		getAction().moveToElement(element).build().perform();
	}

	public void contextClick(WebElement element) {
		getAction().contextClick(element).perform();
	}

	public static boolean isChecked(WebElement element) {
		if (element.isSelected()) {
			return true;
		}
		return false;
	}

	public static void uncheck(WebElement element) {
		if (element.isSelected()) {
			element.click();
		}
	}

	public static void submit(WebElement element) {
		element.submit();
	}

	public static void click(WebElement element) {
		element.click();
	}

	public static boolean isEditable(WebElement control) {
		String tagName = control.getTagName().toLowerCase();
		boolean acceptableTagName = "input".equals(tagName)
				|| "select".equals(tagName);
		String readonly = "";
		if ("input".equals(tagName)) {
			readonly = control.getAttribute("readonly");
			if (readonly == null)
				readonly = "";
		}

		return control.isEnabled() && acceptableTagName && "".equals(readonly);
	}

	// Wait methods

	public void waitForPageToLoad() {
		ExpectedCondition<Boolean> expectation = new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver driver) {
				return ((JavascriptExecutor) driver).executeScript(
						"return document.readyState").equals("complete");
			}
		};
		Wait<WebDriver> wait = new WebDriverWait(driver, defTimeOut);
		try {
			wait.until(expectation);
		} catch (Exception error) {
			new Exception("Failed while loading page", error);
		}
	}

	public void waitForElementPresent(String findBy, String locator) {
		WebDriverWait wait = new WebDriverWait(driver, defTimeOut);
		wait.until(ExpectedConditions.presenceOfElementLocated(FindBy
				.seByMechanism(findBy, locator)));
	}

	public void waitForElementVisible(String findBy, String locator) {
		WebDriverWait wait = new WebDriverWait(driver, defTimeOut);
		wait.until(ExpectedConditions.visibilityOfElementLocated(FindBy
				.seByMechanism(findBy, locator)));
	}

	public boolean verifyElementPresent(String findBy, String locator) {
		try {
			this.findElement(findBy, locator);
			return true;
		} catch (org.openqa.selenium.NoSuchElementException ex) {
			 
			return false;
		}
	}

	public void selectByValue(WebElement element, String valueToSelect) {
		new Select(element).selectByValue(valueToSelect);
	}

	public void selectByIndex(WebElement element, int index) {
		new Select(element).selectByIndex(index);
	}

	public void selectByVisibleText(WebElement element, String visibleText) {
		new Select(element).selectByVisibleText(visibleText);
	}

	public void deselectByValue(WebElement element, String valueToDeselect) {
		new Select(element).deselectByValue(valueToDeselect);
	}

	public void deselectByIndex(WebElement element, int index) {
		new Select(element).deselectByIndex(index);
	}

	public void deselectByVisibleText(WebElement element, String visibleText) {
		new Select(element).deselectByVisibleText(visibleText);
	}

	public void deselectAll(WebElement element) {
		new Select(element).deselectAll();
	}

	 
	public SeWebDriver toSeWebDriver(WebDriver driver) {
		return new SeWebDriver(driver);
	}
	
}
