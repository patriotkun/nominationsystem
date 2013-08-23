package Nomination.dao;

import javax.sql.DataSource;

/* commons-dbcp-1.4.jar Add */
import org.apache.commons.dbcp.BasicDataSource;
/* OriginalClass */

public class DaoUtil {
	private DataSource source;
	private static DaoUtil inst = new DaoUtil();
	/*
	=com.mysql.jdbc.Driver
	=jdbc:mysql://localhost/struts?useUnicode=true&characterEncoding=utf8
	jdbc.driver.user=root
	jdbc.driver.pass=
	*/
	private static String JDBC_URL = "jdbc.driver.url";
	private static String JDBC_USER = "jdbc.driver.user";
	private static String JDBC_PASS = "jdbc.driver.pass";
	private static String JDBC_DRIVER ="jdbc.driver.classname";
	
	private DaoUtil(){
		source = createDataSource();
	}
	
	public static DataSource getSource(){
		return inst.source;
	}
	
	private DataSource createDataSource(){
		BasicDataSource bds = new BasicDataSource();
		/*
		bds.setDriverClassName(SettingUtil.getProperty(JDBC_DRIVER));
		bds.setUsername(SettingUtil.getProperty(JDBC_USER));
		bds.setPassword(SettingUtil.getProperty(JDBC_PASS));
		bds.setUrl(SettingUtil.getProperty(JDBC_URL));
		*/
		bds.setDriverClassName("com.mysql.jdbc.Driver");
		bds.setUsername("root");
		bds.setPassword("");
		bds.setUrl("jdbc:mysql://localhost/nominationsystem?useUnicode=true&characterEncoding=utf8");
		
		return bds;
	}

}
