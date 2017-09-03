package com.core.framework.webdriver;

import java.util.List;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

/**
 * 
 * @author sracha
 * 
 */
public class SeWebElement {
	protected WebElement element;
	private String findby;
	private String loc;
	protected SeWebDriver dr = null;
	
	public SeWebElement(SeWebDriver Driver,String by, String using)
	{
		this.dr = Driver;
		this.findby = by;
		this.loc = using;
	}

	public SeWebElement(WebElement element) {
		this.element = element;
	}

	public String getLocator()
	{
		return this.loc;
	}
	
	public String getFindBy()
	{
		return this.findby;
	}
	
	public boolean isExist()
	{
		this.dr.waitForElementPresent(this.findby, this.loc);
		this.element =  this.dr.findElement(this.findby,  this.loc);
		return this.element.isDisplayed(); 
	}
	
	public boolean isSelected() {
		this.isExist();
		return this.element.isSelected();
	}

	public boolean isEnabled() {
		this.isExist();
		return this.element.isEnabled();
	}

	public boolean isDisplayed() {
		this.isExist();
		return this.element.isDisplayed();
	}

	public String getText() {
		this.isExist();
		return this.element.getText();
	}

	public String getTagName() {
		this.isExist();
		return this.element.getTagName();
	}

	public Dimension getSize() {
		this.isExist();
		return this.element.getSize();
	}

	public Point getLocation() {
		this.isExist();
		return this.element.getLocation();
	}

	public String getCssValue(String arg0) {
		this.isExist();
		return this.element.getCssValue(arg0);
	}

	public String getAttribute(String attribute) {
		this.isExist();
		return this.element.getAttribute(attribute);
	}

	public void submit() {
		this.isExist();
		this.element.submit();
	}

	public void sendKeys(CharSequence... key) {
		this.isExist();
		this.element.sendKeys(key);
	}

	 
	
	/**
	 * Added on 6th March 2014
	 * @author sracha
	 * @param key
	 */
	public void setValue(CharSequence... key) {
		this.isExist();
		this.element.clear();
		this.element.sendKeys(key);		
	}

	public void click() {
		this.isExist();
		this.element.click();
	}

	public void clear() {
		this.isExist();
		this.element.clear();
	}

	public boolean isAvailableForAction() {
		this.isExist();
		if (isDisplayed() && isEnabled())
			return true;
		else
			return false;
	}

	public boolean verifyAttributeEquals(String attribute, String value) {
		this.isExist();
		if (this.element.getAttribute(attribute).equals(value))
			return true;
		else
			return false;
	}

	@SuppressWarnings("unused")
	private WebElement findElement(String findByType, String locator) {
		WebElement retElement = this.element.findElement(FindBy
				.seByMechanism(findByType, locator));
		return retElement;
	}

	public boolean verifyAttributeContains(String attribute, String value) {
		this.isExist();
		if (this.element.getAttribute(attribute).contains(value))
			return true;
		else
			return false;
	}

	public boolean verifyTextEquals(String value) {
		this.isExist();
		if (this.element.getText().equals(value))
			return true;
		else
			return false;
	}

	public boolean verifyTextContains(String value) {
		this.isExist();
		if (this.element.getText().contains(value))
			return true;
		else
			return false;
	}

	public WebElement getWebElement() {
		this.isExist();
		return this.element;
	}
	
	public int getTableRowsCount()
	{
		this.isExist();
		List<WebElement> retElement =   this.element.findElements(FindBy
				.seByMechanism("TagName", "tr"));
		return retElement.size();		
	}
	 
	public int getTableColumnsCount()
	{
		this.isExist();
		List<WebElement> retElement =   this.element.findElements(FindBy
				.seByMechanism("TagName", "td"));
		return retElement.size();		
	}
	public String getTableCellData(int row,int col)
	{
		this.isExist();
		 WebElement  retElement =   this.element.findElement(FindBy
				.seByMechanism("TagName", "tr[" + row + "]/td[" + col + "]" ));
		return retElement.getText();	
	}
	
	public int listItemsCount()
	{
		this.isExist();
		List<WebElement> retElement =   this.element.findElements(FindBy
				.seByMechanism("TagName", "option"));
		return retElement.size();
		
	}
	public void selectTableRow(int row)
	{
		this.isExist();
		String tr = "/tr[" + row +"]";
		 this.element.findElement(FindBy
				.seByMechanism("Xpath", tr)).click();
		
		 
	}
	
	@SuppressWarnings("null")
	public List<String> getElementsText()
	{
		this.isExist();
		List<String> lst = null;
		 
		List<WebElement> retElement =   this.element.findElements(FindBy
				.seByMechanism("TagName", "td"));
		 
		 for( WebElement e : retElement)
			 lst.add(e.getText());
			 
		 return lst;
		
	}
	
	@SuppressWarnings("null")
	public List<String> getElementsText(int col)
	{
		this.isExist();
		List<String> lst = null;
		 
		List<WebElement> retElement =   this.element.findElements(FindBy
				.seByMechanism("TagName", "td[" + col + "]"));
		 
		 for( WebElement e : retElement)
			 lst.add(e.getText());
			 
		 return lst;
		
	}
	 
	public void selectByIndex(int index)
	{
		this.isExist();
			this.dr.selectByIndex(  this.element, index);
	}
	public void selectByValue(String valueToSelect)
	{
		this.isExist();
			this.dr.selectByValue(this.element, valueToSelect);
	}
	public void selectByVisibleText(String visibleText)
	{
		this.isExist();
			this.dr.selectByVisibleText(this.element, visibleText);
	}
	
	public void deSelectByIndex(int index)
	{
		this.isExist();
			this.dr.deselectByIndex(this.element, index);
	}
	public void deSelectByValue(String valueToSelect)
	{
		this.isExist();
			this.dr.deselectByValue(this.element, valueToSelect);
	}
	public void deselectByVisibleText(String visibleText)
	{
		this.isExist();
			this.dr.deselectByVisibleText(this.element, visibleText);
	}
	
	public void deselectAll()
	{
		this.isExist();
			this.dr.deselectAll( this.element );
	}
	public String getSelectedElement()
	{
		this.isExist();
		WebElement ele =  this.element;
		 List<WebElement> eles = new Select( ele).getAllSelectedOptions();
		 return eles.get(0).getText();
		
	}
	
	@SuppressWarnings("null")
	public List<String> getSelectedElements()
	{
		List<String> lst = null;
		this.isExist();
		WebElement ele =  this.element;
		 List<WebElement> eles = new Select( ele).getAllSelectedOptions();
		 
		 for( WebElement e : eles)
			 lst.add(e.getText());
			 
		 return lst;
		
	}
	
	
	@SuppressWarnings("null")
	public List<String> getAllListElements()
	{
		List<String> lst = null;
		this.isExist();
		WebElement ele =  this.element;
		 List<WebElement> eles = new Select(ele).getOptions();
		 
		 for( WebElement e : eles)
			 lst.add(e.getText());
			 
		 return lst;
		
	}
	public void rightClick()
	{
		this.isExist();
		WebElement ele =  this.element;
			this.dr.contextClick(ele);
	}
	
	 
	public List<String> getColumnNameAsString()
	{
		this.isExist();
		 
		List<String> lst =   this.getElementsText();
		return lst;
	}
	 
	public int getTableRowWithCellText(int Text,int col)
	{
		this.isExist();
		 
		List<String> lst =   this.getElementsText( col);
		return lst.indexOf(Text) ;
	}
	 
}
