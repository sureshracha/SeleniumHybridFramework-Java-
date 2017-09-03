package com.test.pagefactory;

 
import com.core.framework.webdriver.SeWebDriver;
import com.core.framework.webdriver.SeWebElement;
 

 
 
 
public class gPage{
		
	
	  public SeWebElement TextBox(SeWebDriver dr)
	  {	  
			  return new SeWebElement(dr,"name","q");
	  }
	
	
	
	  
}
