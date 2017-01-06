package io.lvlvforever.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;


/**
 * ClassName:SingletonResource <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2016年10月27日 下午11:06:10 <br/>
 * @author   lvlv
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
public class Configuration {
	private volatile static Configuration configuration;
	private String configPath;
	private Properties properties;
	private Configuration(String configPath){
		this.configPath = configPath;
		properties = new Properties();
		try {
			properties.load(new FileInputStream(new File(configPath)));
		} catch (FileNotFoundException e) {
			
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		} catch (IOException e) {
			
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
	}
	public static Configuration getInstance(String configPath){
		if(configuration == null){
			synchronized (Configuration.class) {
				if(configuration == null){
					configuration = new Configuration(configPath);
				}
			}
		}
		return configuration;
	}
	public String getProperty(String key){
		
		return properties.getProperty(key);
	}
}

