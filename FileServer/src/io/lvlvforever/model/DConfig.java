package io.lvlvforever.model;


import org.apache.ibatis.session.SqlSession;

/**
 * ClassName:DConfig <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2016年11月23日 下午10:22:35 <br/>
 * @author   lvlv
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
public class DConfig extends DBase{
	public void addKey(String key,String value){
		if(queryKey(key) != null){
			updateKey(key, value);
			return;
		}
		
		SqlSession session = openSession();
		
		try {
			ConfigMapper cm = session.getMapper(ConfigMapper.class);
			cm.addKey(key, value);
			session.commit();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			
		}finally{
			session.close();
		}
		
	}
	public String queryKey(String key){
		SqlSession session = openSession();
		try {
			ConfigMapper cm = session.getMapper(ConfigMapper.class);
			Config c  = cm.queryKey(key);
			session.commit();
			if(c == null) return null;
			return c.getValue();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			
		}finally{
			session.close();
		}
		return null;
	}
	public void updateKey(String key,String value){
		SqlSession session = openSession();
		try {
			ConfigMapper cm = session.getMapper(ConfigMapper.class);
			cm.updateKey(key,value);
			session.commit();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			
		}finally{
			session.close();
		}
	
	}
}

