package com.core.framework.utils;

 
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
 
import java.util.Properties;

 

/**
 * This class to read the .ini file in the classpath to Property object
 *  
 * @author sracha 
 * @version 1.0
 */
public  class iniConfigFileLoader
{
    //~ Static variables/initializers ----------------------------------------------------

    
 

    //~ Methods --------------------------------------------------------------------------
 


    /**
     * Read the data from given file and return in the form of the Properties
     * object
     *
     * @param name - file name that should be loaded
     *
     * @return Properties - data from the file return in the form of the Properties
     *         object
     * @throws IOException 
     * @throws FileNotFoundException 
     */
    public  Properties loadProperties(final String filename) 
    {
    	  
    	   
    	  
			if (filename!= null)
			{
		    	FileInputStream in = null;
				try {
					in = new FileInputStream(filename); //Filepath
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		    	Properties result = new Properties();
		        try {
					result.load(in);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		        return result;
			}
			 
			return null;
    }

  
 
}
