package com.test.testsuite;

import java.util.Properties;

import org.testng.annotations.*;

import com.core.framework.reports.Logger;
import com.core.framework.reports.Reports;
import com.core.framework.utils.iniConfigFileLoader;
import com.core.framework.webdriver.SeWebDriver;
import com.test.businesslogic.testBusiness;
import com.test.framework.utils.FilePath;
/*
 * @author sracha
 */
public class testcase1 extends Reports {
	
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
	public void test1()
	{
		 testBusiness tb = new testBusiness();		 
		 tb.WriteText(driver);
		 Logger.FailTheTestWithScreenCapture("Hello this is failed");
		 
	}

}
