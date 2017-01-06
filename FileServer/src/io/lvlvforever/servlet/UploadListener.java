package io.lvlvforever.servlet;

import io.lvlvforever.util.Utils;

import java.util.Calendar;

import org.apache.commons.fileupload.ProgressListener;

/**
 * ClassName:UploadListener <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2016年10月12日 下午11:13:13 <br/>
 * @author   lvlv
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
public class UploadListener implements ProgressListener{
	private UploadStatus status;
	public  UploadListener(UploadStatus status) {
		
		// TODO Auto-generated constructor stub
		this.status = status;
	}
	@Override
	public void update(long pBytesRead, long pContentLength, int pItems) {
		
		// TODO Auto-generated method stub
		double uploadPercent = (double) pBytesRead / pContentLength;
		long duration = (Utils.getTimeInSeconds() - status.getStartTime()) + 1;
		double cur =  pBytesRead / duration / 1024;
		status.setPercent(uploadPercent);
		status.setVelocity(cur);
		status.setDuration(duration - 1);
	}

}

