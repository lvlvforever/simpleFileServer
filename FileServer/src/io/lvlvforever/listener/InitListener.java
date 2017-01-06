package io.lvlvforever.listener;

import java.io.File;
import java.util.Timer;
import java.util.TimerTask;

import io.lvlvforever.datasource.DataSource;
import io.lvlvforever.model.DConfig;
import io.lvlvforever.util.Configuration;
import io.lvlvforever.util.Constants;
import io.lvlvforever.util.TTask;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * Application Lifecycle Listener implementation class InitListener
 *
 */
public class InitListener implements ServletContextListener {

    /**
     * Default constructor. 
     */
    public InitListener() {
        // TODO Auto-generated constructor stub
    }

	/**
     * @see ServletContextListener#contextDestroyed(ServletContextEvent)
     */
    public void contextDestroyed(ServletContextEvent sce)  { 
         // TODO Auto-generated method stub
    }

	/**
     * @see ServletContextListener#contextInitialized(ServletContextEvent)
     */
    public void contextInitialized(ServletContextEvent sce)  { 
         // TODO Auto-generated method stub
    	String webapp = sce.getServletContext().getRealPath("/");
    	String configPath = webapp+"WEB-INF"+File.separator+"conf"+File.separator+"app.properties";
    	Configuration c = Configuration.getInstance(configPath);
    	//fileStoragePath contains the last '/' or '\' ,eg /opt/ or c:\
    	String fileStoragePath = c.getProperty("FILE_STORAGE_PATH");
    	String scanInterval =  c.getProperty("SCAN_INTERVAL");
    	String maxStoreTime = c.getProperty("MAX_STORE_TIME");
    	
    	System.err.println("filestoragePath="+fileStoragePath);
		DConfig dConfig = new DConfig();
		dConfig.addKey("FILE_STORAGE_PATH", fileStoragePath);
		dConfig.addKey("SCAN_INTERVAL", scanInterval);
		dConfig.addKey("MAX_STORE_TIME", maxStoreTime);

		System.err.println(dConfig.queryKey("FILE_STORAGE_PATH"));
    	System.err.println("contentInitialized end");
    	Timer timer = new Timer();
    	timer.schedule(new TTask(webapp), 1000, Long.parseLong(dConfig.queryKey("SCAN_INTERVAL"))*1000);

    }
	
}
