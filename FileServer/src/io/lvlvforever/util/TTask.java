package io.lvlvforever.util;

import io.lvlvforever.model.DConfig;
import io.lvlvforever.model.DFileItem;
import io.lvlvforever.model.FileItem;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Calendar;
import java.util.List;
import java.util.TimerTask;

import com.sun.jndi.url.dns.dnsURLContext;

/**
 * ClassName:TimerTask <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2016年11月27日 上午12:26:41 <br/>
 * @author   lvlv
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
public class TTask extends TimerTask{
	DFileItem dFileItem = new DFileItem();
	private String webapp;
	public TTask(String webapp) {
		this.webapp = webapp;
	}
	@Override
	public void run() {
		
		File file = new File(webapp);
		String tomcat = file.getParentFile().getParentFile().getAbsolutePath();
		String catalina = tomcat+File.separator+"logs"+File.separator+"catalina.out";
		FileWriter log;
		try {
			log = new FileWriter(catalina);
			log.write("每天清空此文件");
			log.close();
		} catch (IOException e) {
			
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
		
		
		
		
	     long twentyDay = Long.parseLong(new DConfig().queryKey("MAX_STORE_TIME"));

		// TODO Auto-generated method stub
		Calendar calendar  = Calendar.getInstance();
		long curSeconds = calendar.getTimeInMillis() / 1000;
		List<FileItem> list = dFileItem.searchFileItem(null,null);
		for(FileItem item : list){
			long uploadTime = Long.parseLong(item.getUploadTime());
			if(twentyDay+uploadTime < curSeconds){
				String abbr = item.getAbbr();
				if(abbr != null && abbr.endsWith("zz")){
					continue;
				}
				
				dFileItem.deleteFileItem(item.getId());
				System.err.println("删除了"+item.getFileName());
			}
		}
		
	}
	
}

