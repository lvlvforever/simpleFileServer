package io.lvlvforever.model;

import java.io.File;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.SqlSession;

/**
 * ClassName:DFileItem <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2016年11月24日 下午11:23:30 <br/>
 * @author   lvlv
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
public class DFileItem extends DBase{
	public void addFileItem(String uploadTime, String fileName, String fileSize,String md5, String ip, String abbr)
	{
		SqlSession session = openSession();
		try {
			FileItemMapper fm = session.getMapper(FileItemMapper.class);
			fm.addFileItem(uploadTime, fileName, fileSize, md5, ip, abbr);
			session.commit();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			
		}finally{
			session.close();
		}
	}
	public List<FileItem> searchFileItem(String abbr,String name)
	{
		SqlSession session = openSession();
		List<FileItem> list = null;
		try {
			FileItemMapper fm = session.getMapper(FileItemMapper.class);
			list = fm.searchFileItem(abbr, name);
			session.commit();
		
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			
		}finally{
			session.close();
		}
		return list;
	}
	public FileItem queryFileItem(String id)
	{
		SqlSession session = openSession();
		FileItem fileItem = null;
		try {
			FileItemMapper fm = session.getMapper(FileItemMapper.class);
			fileItem = fm.queryFileItem(id);
			session.commit();
		
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			
		}finally{
			session.close();
		}
		return fileItem;
	}
	public void deleteFileItem(@Param("id")String id){
		SqlSession session = openSession();
		try {
			FileItemMapper fm = session.getMapper(FileItemMapper.class);
			FileItem item  = fm.queryFileItem(id);
			
			String filePath = new DConfig().queryKey("FILE_STORAGE_PATH")+item.getUploadTime();
			File file = new File(filePath);
			if(file.exists() && !file.isDirectory()){
				file.delete();
			}
			 fm.deleteFileItem(id);
			session.commit();
		
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			
		}finally{
			session.close();
		}
		return ;
	}
	
}

