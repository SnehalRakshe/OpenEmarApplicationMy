package com.tieto.utilties;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropUtils {
	
	public static String getValueFromKey(String fileDetail, String key) throws IOException 
	{
		FileInputStream file = new FileInputStream(fileDetail);
		Properties prop = new Properties();
		prop.load(file);

		return prop.getProperty(key);
	}
}
