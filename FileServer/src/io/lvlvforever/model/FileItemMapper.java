package io.lvlvforever.model;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface FileItemMapper {
	public void addFileItem(@Param("uploadTime")String uploadTime, @Param("fileName")String fileName, @Param("fileSize")String fileSize,@Param("md5")String md5, @Param("ip")String ip, @Param("abbr")String abbr);
	public List<FileItem> searchFileItem(@Param("abbr")String abbr, @Param("name")String name) ;
	public FileItem queryFileItem(@Param("id")String id);
	public void deleteFileItem(@Param("id")String id);
}
