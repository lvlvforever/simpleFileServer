package io.lvlvforever.datasource;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.jdt.internal.compiler.ast.ThisReference;


/**
 * ClassName:DataSource <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2016年11月1日 下午10:38:28 <br/>
 * @author   lvlv
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
public class DataSource {
	private static  String database;
	public DataSource(String database){
		this.database = database;
		try {
			Class.forName("org.sqlite.JDBC");
			System.err.println("static in datasource ");
			Connection c = getConnection();
			System.err.println("static out getConnection");
			Statement s = c.createStatement();
			
			String sql = "CREATE TABLE IF NOT EXISTS FILE_ITEM "+
					  "(ID INTEGER PRIMARY KEY NOT NULL,"
					  + "UPLOADTIME VARCHAR2(255)  ,"
					  + "FILENAME VARCHAR2(255)  ,"
					  + "FILESIZE VARCHAR2(255)  ,"
					  + "MD5 VARCHAR2(32) ,"
					  + "ABBR VARCHAR(32),"
					  + "IP  VARCHAR2(20) )";
			
			s.executeUpdate(sql);
			sql = "CREATE TABLE IF NOT EXISTS CONFIG "+
					"(ID INTEGER PRIMARY KEY NOT NULL,"+
					"KEY VARCHAR2(32) NOT NULL,"+
					"VALUE VARCHAR2(255) NOT NULL)";
			s.executeUpdate(sql);
					
			s.close();
			c.close();
		} catch (ClassNotFoundException e) {
			
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	
	public static Connection getConnection(){
		Connection c = null;
		try {
			c = DriverManager.getConnection("jdbc:sqlite:"+database);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return c;
	}
	
}

