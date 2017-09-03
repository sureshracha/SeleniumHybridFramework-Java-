package com.test.testsuite;

import java.util.Properties;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.core.framework.reports.Logger;
import com.core.framework.utils.iniConfigFileLoader;
import com.core.framework.webdriver.SeWebDriver;
import com.test.businesslogic.testBusiness;
import com.test.framework.utils.FilePath;

public class Demo {
	
	public  SeWebDriver driver;	
	public  Properties PrjConfg=null;
	
	
	@BeforeMethod
	public void init()
	{
	     
		 iniConfigFileLoader configfile = new iniConfigFileLoader();
		 PrjConfg = configfile.loadProperties(FilePath.getFilepath("Config.ini")); 
		 String webURL = PrjConfg.getProperty("Url");
		 String brw= PrjConfg.getProperty("Browser");
		 driver = new SeWebDriver(webURL,brw); 
		 try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@AfterMethod
	public void TearDown()
	{
		 driver.close();
		 driver.quit();
		 Logger.info("closed the browser");
	}
	
	 @Test
	public void DemoTesting()
	{
		 Logger.info("This is Demo Tesing method");
		 testBusiness tb = new testBusiness();		 
		 tb.WriteText(driver);
		  
		 
	}
}
