package com.test.businesslogic;

import com.core.framework.reports.Logger;
import com.core.framework.webdriver.SeWebDriver;
import com.test.pagefactory.gPage;

public class testBusiness {
	
 
	public void WriteText(SeWebDriver dr)
	{
		
		  gPage gp = new gPage();
		  
		  if(gp.TextBox(dr).isExist())
			  gp.TextBox(dr).setValue("Hello");	  
		  		Logger.info("Searching Hello in google");
		  		Logger.infoWithScreenCapture("Just Test purpose");
			  
		  try {
				Thread.sleep(300);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
	}

}
