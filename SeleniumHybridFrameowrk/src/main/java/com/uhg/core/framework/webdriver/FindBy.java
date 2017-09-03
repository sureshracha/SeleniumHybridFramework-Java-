package com.uhg.core.framework.webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ByIdOrName;

/**
 * 
 * @author kvarma1
 * 
 */
public class FindBy {

	/*public static enum ByMechanism {
		CLASS_NAME, CSS, ID_OR_NAME, LINK_TEXT, PARTIAL_LINK_TEXT, TAG_NAME, XPATH;
	}

	public static By seByMechanism(ByMechanism find, String locator) {
		return seByMechanism(find.name().toLowerCase(), locator);

	}*/

	public static By seByMechanism(String locator) {
		return seByMechanism("id_or_name", locator);
	}

	public static By seByMechanism(String findByMechanism, String locator) {
		// initializing to null
		By by = null;

		switch (findByMechanism.toLowerCase()) {

		case "class_name":
			by = By.className(locator);
			break;
		case "css":
			by = By.cssSelector(locator);
			break;
		case "id_or_name":
			by = new ByIdOrName(locator);
			break;
		case "linktext":
			by = By.linkText(locator);
			break;
		case "name":
			by = By.name(locator);
			break;
		case "partial_linktext":
			by = By.partialLinkText(locator);
			break;
		case "tag_name":
			by = By.tagName(locator);
			break;
		case "xpath":
			by = By.xpath(locator);
			break;

		default:
			throw new IllegalArgumentException("seByMechanism: Unable to figure out valid find by mechanism");
		}
		return by;

	}

}
