package com.operations;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ReadObject {
	
	Properties p = new Properties();
	
	public Properties getObjectRepositoriy() throws IOException{
		
		//read object repository file
		InputStream stream = new FileInputStream(new File(System.getProperty("user.dir")+"\\src\\com\\object\\object.properties"));
		
		//load all objects
		p.load(stream);
		
		return p;
	}
}
