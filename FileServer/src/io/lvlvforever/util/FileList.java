package io.lvlvforever.util;

import io.lvlvforever.model.FileItem;
import io.lvlvforever.servlet.QueryFileList;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map.Entry;
import java.util.Properties;

import sun.misc.Perf;

/**
 * ClassName:FileList <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2016年10月27日 下午11:40:57 <br/>
 * @author   lvlv
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
public class FileList {
	private volatile static FileList fileList;
	private String path;
	private Properties properties;
	private FileList(String path){
		this.path = path;
		properties = new Properties();
		File file = new File(path);
		if(!file.exists()){
			try {
				file.createNewFile();
			} catch (IOException e) {
				
				// TODO Auto-generated catch block
				e.printStackTrace();
				
			}
		}
		try {
			properties.load(new FileInputStream(new File(path)));
		} catch (FileNotFoundException e) {
			
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		} catch (IOException e) {
			
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
	}
	public static FileList getInstance(String path){
		if(fileList == null){
			synchronized (FileList.class) {
				if(fileList == null){
					fileList = new FileList(path);
				}
			}
		}
		return fileList;
	}
	public String getProperty(String key){
		return properties.getProperty(key);
	}
	public void putProperty(String key,String value){
		properties.put(key, value);
		writeBackToFile();
	}
	public void removeProperty(String key){
		properties.remove(key);
		writeBackToFile();
	}
	
	public List<FileItem> queryFileList(){
		List<FileItem> list = new ArrayList<FileItem>();
	for(Entry entry : properties.entrySet()){
		String key = (String)entry.getKey();
		String[] nameAndSize = ((String)entry.getValue()).split("#");
		String name = nameAndSize[0];
		String size = nameAndSize[1];
		
	}
		
		
		return list;
	}
	
	
	
	@SuppressWarnings("deprecation")
	public void writeBackToFile(){
		try {
			properties.store(new FileOutputStream(new File(path)), Calendar.getInstance().getTime().toLocaleString());
		} catch (FileNotFoundException e) {
			
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		} catch (IOException e) {
			
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
	}
}

