package io.lvlvforever.model;
/**
 * ClassName:FileItem <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2016年10月28日 上午12:35:57 <br/>
 * @author   lvlv
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
public class FileItem {
	private String id;
	private String uploadTime;
	private String fileSize;
	
	private String fileName;
	private String md5;
	private String abbr;
	private String ip;
	
	 
	public String getMd5() {
		return md5;
	}
	public void setMd5(String md5) {
		this.md5 = md5;
	}
	public FileItem() {
		super();
	}
	public FileItem(String id, String uploadTime, String fileSize,
			String fileName,String md5,String abbr,String ip) {
		super();
		this.id = id;
		this.uploadTime = uploadTime;
		this.fileSize = fileSize;
		this.fileName = fileName;
		this.md5 = md5;
		this.abbr = abbr;
		this.ip = ip;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUploadTime() {
		return uploadTime;
	}
	public void setUploadTime(String uploadTime) {
		this.uploadTime = uploadTime;
	}
	public String getFileSize() {
		return fileSize;
	}
	public void setFileSize(String fileSize) {
		this.fileSize = fileSize;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getAbbr() {
		return abbr;
	}
	public void setAbbr(String abbr) {
		this.abbr = abbr;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	
}

