package com.uhg.core.framework.webdriver;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;

/**
 * 
 * @author kvarma1
 * 
 */
public class SeWebElement {
	protected WebElement element;

	public SeWebElement(WebElement element) {
		this.element = element;
	}

	public boolean isSelected() {
		return element.isSelected();
	}

	public boolean isEnabled() {
		return element.isEnabled();
	}

	public boolean isDisplayed() {
		return element.isDisplayed();
	}

	public String getText() {
		return element.getText();
	}

	public String getTagName() {
		return element.getTagName();
	}

	public Dimension getSize() {
		return element.getSize();
	}

	public Point getLocation() {
		return element.getLocation();
	}

	public String getCssValue(String arg0) {
		return element.getCssValue(arg0);
	}

	public String getAttribute(String attribute) {
		return element.getAttribute(attribute);
	}

	public void submit() {
		element.submit();
	}

	public void sendKeys(CharSequence... key) {
		element.sendKeys(key);
	}

	@Deprecated
	public void type(CharSequence... key) {
		clear();
		sendKeys(key);
	}
	
	/**
	 * Added on 6th March 2014
	 * @author sracha
	 * @param key
	 */
	public void setValue(CharSequence... key) {
		element.clear();
		element.sendKeys(key);		
	}

	public void click() {
		element.click();
	}

	public void clear() {
		element.clear();
	}

	public boolean isAvailableForAction() {
		if (isDisplayed() && isEnabled())
			return true;
		else
			return false;
	}

	public boolean verifyAttributeEquals(String attribute, String value) {
		if (element.getAttribute(attribute).equals(value))
			return true;
		else
			return false;
	}

	public SeWebElement findElement(String findByType, String locator) {
		SeWebElement retElement = new SeWebElement(element.findElement(FindBy
				.seByMechanism(findByType, locator)));
		return retElement;
	}

	public boolean verifyAttributeContains(String attribute, String value) {
		if (element.getAttribute(attribute).contains(value))
			return true;
		else
			return false;
	}

	public boolean verifyTextEquals(String value) {
		if (element.getText().equals(value))
			return true;
		else
			return false;
	}

	public boolean verifyTextContains(String value) {
		if (element.getText().contains(value))
			return true;
		else
			return false;
	}

	public WebElement getWebElement() {
		return element;
	}
}
