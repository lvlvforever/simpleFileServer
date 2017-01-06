package io.lvlvforever.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;
import java.util.Calendar;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.tomcat.jni.FileInfo;


/**
 * ClassName:Utils <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2016年10月26日 下午10:55:44 <br/>
 * @author   lvlv
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
public class Utils {
	public static long getTimeInSeconds(){
		return Calendar.getInstance().getTimeInMillis() / 1000;
	}
	
	public static String byteMD5toString(byte[] data){
		if(data == null || data.length < 1) return "null md5";
		char[] hexDigits = {'0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F'};
		char[] resultArray = new char[data.length*2];
		int index = 0;
		for(byte b : data){
			resultArray[index++] = hexDigits[b >>> 4 & 0xf];
			resultArray[index++] = hexDigits[b & 0xf];
		}
		return new String(resultArray);
	}
	public static void main(String[] args){
		
	}
	public static String calculateMD5(File file){
		try {
			InputStream  in = new FileInputStream(file);
			try {
				byte[] data = DigestUtils.md5(in);
				return byteMD5toString(data);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		
	}
	
}

