package com.notes.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ReadProperties {
	private static final Logger logger = LogManager.getLogger(ReadProperties.class);
	private static FileInputStream fis;
	private static Properties prop = null;
	
	static String getProperty(String propertyKey) {
		
		try {
			fis  =new FileInputStream(new File("C:\\Users\\mayuri.gawandi\\eclipse-workspace\\NotesAPITesting\\src\\test\\resources\\config.properties"));
			prop = new Properties();
			try {
				prop.load(fis);
			} catch (FileNotFoundException nfef) {
				logger.error("The properties file not found", nfef);
			}
			} catch (IOException ioe) {
			
				logger.error("The IOException is got while read the file", ioe);
		}finally {
			try {
				fis.close();
			}catch(Exception e) {
				logger.error("getting exception while closing the file input stream", e);
			}
			
		}
		
		
		return prop.getProperty(propertyKey).trim();
		
		
	}
	
	

}
