package io.lvlvforever.model;

import org.apache.ibatis.annotations.Param;

/**
 * ClassName:ConfigMapper <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2016年11月23日 下午10:22:52 <br/>
 * @author   lvlv
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
public interface ConfigMapper {
	public void addKey(@Param("key")String key,@Param("value")String value);
	public Config queryKey(@Param("key")String key);
	public void updateKey(@Param("key")String key,@Param("value")String value);

}

